package yaes.cssm.scenarios;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yaes.cssm.actions.Action;
import yaes.cssm.behaviors.IActorSelector;
import yaes.cssm.behaviors.IBehavior;
import yaes.cssm.behaviors.IScenarioSelector;
import yaes.cssm.behaviors.ISocialCalculusContext;
import yaes.cssm.cssm.ActionImpact;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.ScenarioSet;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.cssm.iAIF;
import yaes.framework.simulation.AbstractContext;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.ui.text.TextUi;
import yaes.util.SaveLoadUtil;
import yaes.world.physical.PhysicalWorld;
import yaes.world.physical.map.SimpleField;

/**
 * General purpose context model for social calculus
 * 
 * @author Lotzi
 * 
 */
public abstract class SocialCalculusContext extends AbstractContext implements
		ISocialCalculusContext, Constants {

	private static final String CHECKPOINT_FILE_ROOT = "scc";
	private static final long serialVersionUID = 8108810975209262656L;

	/**
	 * Returns the file corresponding to a specific checkpoint. It is used from
	 * here and from
	 * 
	 * @param timePoint
	 * @param scenario
	 * @param dirCheckpoints
	 * @return
	 */
	public static File getCheckpointFile(int timePoint, File dirCheckpoints) {
		File retval = new File(dirCheckpoints, CHECKPOINT_FILE_ROOT + "_"
				+ timePoint);
		return retval;
	}

	private SimpleField field;
	private PhysicalWorld physicalWorld;
	public ScenarioSet scenarioSet;
	/**
	 * The scenario selector used in the context
	 */
	private IScenarioSelector scenarioSelector = null;
	/**
	 * The actor selectors indexed by scenario instance names
	 */
	private Map<String, IActorSelector> actorSelectors = new HashMap<>();
	/**
	 * The behaviors indexed by scenario and actor
	 */
	private Map<String, IBehavior> behaviors = new HashMap<>();

	/**
	 * The action impact functions, indexed by Scenario type names
	 */
	private Map<String, iAIF> aifs = new HashMap<>();

	/**
	 * The directory to which the checkpoints will be saved
	 */
	public File dirCheckpoints;

	public File dirWeights;

	/**
	 * A pointer to the last performed action
	 */
	public Action currentAction;

	/**
	 * A pointer to the last scenario in which an action had been performed
	 */
	public Scenario currentScenario;

	/**
	 * The list of social agents participating in all the scenarios
	 */
	public Map<String, SocialAgent> agents = new HashMap<>();

	/**
	 * The list of the preplanned actions
	 */
	public List<Action> preplannedActions;

	/**
	 * Applies an action in the specific scenarios in this context
	 * 
	 * @param sa
	 * @return true if a terminal state has been reached
	 * 
	 */
	public boolean applyAction(Action sa) {
		if (sa == null) {
			TextUi.println("SocialCalculus.applyAction called with a null action.");
			TextUi.abort("SocialCalculus.applyAction called with a null action.");
			return true;
		}
		//
		// Find the scenario and the AIF: note that there is one AIF for every
		// type
		// of scenario, not for every instance
		//
		Scenario scenario = scenarioSet.findScenario(sa.getScenarioInstance());
		currentScenario = scenario;
		iAIF aif = aifs.get(scenario.getScenarioType());
		//
		//
		//
		boolean finish = scenario.performAction(sa, aif);

		List<ActionImpact> actionImpacts = aif.getImpacts(scenario, sa
				.getActionType().getActionType(), sa.getActor(), sa
				.getParameters());

		// Print the actionImpact changes
		// for (ActionImpact impact : actionImpacts) {
		// TextUi.println(impact.toStringDetailed(0));
		// }
		// applies the impacts

		applyImpact(scenario, actionImpacts);

		currentAction = sa;

		// Save complete simulation state
		createCheckpoint();

		// Scenario frame count increase
		scenario.incBy1Framecount();

		if (finish) {
			TextUi.println("Terminal state reached, done");
			return true;
		}
		return false;
	}

	/**
	 * Apply changes over the CSSM and CB
	 * 
	 * @param actionImpact
	 * @param context
	 */
	private void applyImpact(Scenario scenario, List<ActionImpact> actionImpacts) {
		for (ActionImpact ai : actionImpacts) {
			if (ai.isCSSM()) {
				scenario.setCSSMValue(ai.getCultureName(), ai.getMetricName(),
						ai.getSubActor(), ai.getPersActor(), ai.getEstActor(),
						ai.getNewValue());

			} else {
				scenario.setCBValue(scenario.getScenarioType(),
						ai.getMetricName(), ai.getPersActor(),
						ai.getEstActor(), ai.getBeliefValue());
			}
		}

	}

	/**
	 * Create a checkpoint
	 */
	public void createCheckpoint() {
		int timePoint = (int) physicalWorld.getTime();
		File checkpointFile = getCheckpointFile(timePoint, dirCheckpoints);
		SaveLoadUtil<SocialCalculusContext> slu = new SaveLoadUtil<>();
		slu.save(this, checkpointFile);
	}

	/**
	 * Returns the actor selector for the specific scenario
	 * 
	 * @param scenario
	 * @return
	 */
	public IActorSelector getActorSelector(Scenario scenario) {
		IActorSelector retval = actorSelectors.get(scenario
				.getScenarioInstance());
		if (retval == null) {
			TextUi.errorPrint("Couldn't find the actor selector for scenario "
					+ scenario.getScenarioInstance());
			System.exit(1);
		}
		return retval;
	}

	/**
	 * Returns the behavior handling a specific actor in a specific scenario
	 * 
	 * @param scenario
	 * @param actor
	 */
	public IBehavior getBehavior(Scenario scenario, String actor) {
		String key = scenario.getScenarioInstance() + "_" + actor;
		IBehavior behavior = behaviors.get(key);
		if (behavior == null) {
			TextUi.errorPrint("Couldn't find the behavior for " + key);
			System.exit(1);
		}
		return behavior;

	}

	/**
	 * Returns the preplanned action for the current time, or null if none
	 */
	@Override
	public Action getNextPrePlannedAction() {
		int time = (int) (theWorld.getTime());
		if (preplannedActions != null && time < preplannedActions.size()) {
			Action retval = preplannedActions.get(time);
			TextUi.println("scripted action:" + retval.toString());
			return retval;
		}
		return null;
	}

	/**
	 * Get the scenario selector to be used in this context
	 * 
	 * @return
	 */
	public IScenarioSelector getScenarioSelector() {
		return scenarioSelector;
	}

	public ScenarioSet getScenarioSet() {
		return scenarioSet;
	}

	/**
	 * Initialize the context (generalizable) - to be pulled up to
	 * SocialCalculusContext
	 * 
	 */
	@Override
	public void initialize(SimulationInput sip, SimulationOutput sop) {
		super.initialize(sip, sop);
		field = new SimpleField(200, 200);
		physicalWorld = new PhysicalWorld(field, sop);
		theWorld = physicalWorld;
	}

	/**
	 * Initialize the scenarios
	 */
	protected abstract void initScenarioSet();

	/**
	 * Registers an actor selector for a specific scenario
	 * 
	 * @param scenario
	 * @param actorSelector
	 */
	public void registerActorSelector(Scenario scenario,
			IActorSelector actorSelector) {
		actorSelectors.put(scenario.getScenarioInstance(), actorSelector);
	}

	/**
	 * Register an action impact
	 * 
	 * @param scenarioType
	 * @param aif
	 */
	public void registerAIF(String scenarioType, iAIF aif) {
		aifs.put(scenarioType, aif);
	}

	/**
	 * Register a specific behavior for a specific scenario / actor combination.
	 * The behavior must have had previously specified the scenario, actor and
	 * social agent.
	 * 
	 * @param scenario
	 * @param actor
	 * @param behavior
	 */
	public void registerBehavior(IBehavior behavior) {
		String key = behavior.getScenario().getScenarioInstance() + "_"
				+ behavior.getActor();
		behaviors.put(key, behavior);
	}

	/**
	 * Register the scenario selector
	 * 
	 * @param scenarioSelector
	 */
	public void registerScenarioSelector(IScenarioSelector scenarioSelector) {
		this.scenarioSelector = scenarioSelector;
	}

}
