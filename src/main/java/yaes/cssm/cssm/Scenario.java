package yaes.cssm.cssm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import yaes.cssm.actions.Action;
import yaes.cssm.actions.ActionTypes;
import yaes.cssm.actions.ProgressGraph;
import yaes.cssm.actions.ProgressState;
import yaes.ui.format.Formatter;
import yaes.ui.format.ToStringDetailed;
import yaes.ui.text.TextUi;

/**
 * This is the scenario class, implementing the tuple {A, \alpha, \tau, S, F, P}
 * from the SocialValues paper.
 * 
 * @author Taranjeet
 * 
 */
public class Scenario implements ToStringDetailed, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1791767037256820356L;
	/**
	 * The set of actions
	 */
	private ActionTypes actionTypes;
	private Map<String, Actor> actors = new HashMap<>();
	private int frameCount = 0;
	/**
	 * The progress graph specification - this does not have an internal state
	 */
	private ProgressGraph progressGraph;
	/**
	 * The current location in the progress graph
	 */
	private String currentProgressState;
	/**
	 * The name of the instance of the scenario - allows us to identify multiple
	 * instances of the same scenario
	 */
	private String scenarioInstance;
	/**
	 * The name of the type of the scenario: e.g. HumanBargaining, SpanishSteps,
	 * MarketCheckpoint
	 */
	private String scenarioType;

	/**
	 * Maintain the list of past actions in this scenario
	 */
	private List<String> pastActionTypes = new ArrayList<String>();

	/**
	 * Constructor, creates a new scenario, specifying the type of the scenario
	 * and the specific instance
	 * 
	 * @param scenarioType
	 * @param scenarioInstance
	 * @param actionTypes
	 */
	public Scenario(String scenarioType, String scenarioInstance,
			ActionTypes actionTypes) {
		this.scenarioType = scenarioType;
		this.scenarioInstance = scenarioInstance;
		this.actionTypes = actionTypes;
	}

	/**
	 * Adds an actor to the scenario
	 * 
	 * @param actor
	 */
	public void createActor(String name) {
		Actor actor = new Actor(name);
		actors.put(name, actor);
	}

	/**
	 * Creates the progress graph for this scenario - to be called after all the
	 * actors are created
	 */
	public void createProgressGraph() {
		progressGraph = new ProgressGraph(this);
	}

	public ActionTypes getActionTypes() {
		return actionTypes;
	}

	/**
	 * Returns the social agent which plays a particular actor
	 * 
	 * @param actorName
	 * @return
	 */
	public SocialAgent getActorPlayedBy(String actorName) {
		Actor actor = actors.get(actorName);
		if (actor == null) {
			TextUi.errorPrint("Could not find social agent for the actor: "
					+ actorName);
			throw new Error();
		}
		return actor.getPlayedBy();
	}

	/**
	 * Returns a list of the actor names
	 * 
	 * @return
	 */
	public List<String> getActors() {
		List<String> retval = new ArrayList<>();
		retval.addAll(actors.keySet());
		return retval;
	}

	/**
	 * Returns the value of the specified CB
	 * 
	 * @param cultureName
	 * @param beliefName
	 * @param perspectiveActor
	 * @param estimatorActor
	 * @return value
	 */
	public ConcreteBeliefValue getCB(String beliefName,
			String perspectiveActor, String estimatorActor) {
		SocialAgent estimatorAgent = getActorPlayedBy(estimatorActor);
		ConcreteBelief cb = ConcreteBelief.createCB(this, beliefName,
				perspectiveActor, estimatorActor);
		ConcreteBeliefValue cbv = estimatorAgent.getCBValue(cb);
		return cbv;
	}

	/**
	 * Returns the value of the specified CSSM
	 * 
	 * @param cultureName
	 * @param metric
	 * @param subjectActor
	 * @param perspectiveActor
	 * @param estimatorActor
	 * @return value
	 */
	public CSSMValue getCSSMValue(String cultureName, String metric,
			String subjectActor, String perspectiveActor, String estimatorActor) {
		SocialAgent estimatorAgent = getActorPlayedBy(estimatorActor);
		CSSM cssm = CSSM.createCSSM(this, cultureName, metric, subjectActor,
				perspectiveActor, estimatorActor);
		return estimatorAgent.getCSSMValue(cssm);
	}

	public String getCurrentProgressState() {
		return currentProgressState;
	}

	/**
	 * Return total time frame in scenario
	 * 
	 * @return frameCount
	 */
	public int getFrameCount() {
		return this.frameCount;
	}

	public ProgressGraph getProgressGraph() {
		return progressGraph;
	}

	public String getScenarioInstance() {
		return scenarioInstance;
	}

	public String getScenarioType() {
		return scenarioType;
	}

	/**
	 * Increment frame count
	 */
	public void incBy1Framecount() {
		this.frameCount++;
	}

	/**
	 * Performs the progress for a specific action
	 * 
	 * @param actionTypeName
	 *            - type name of the action we are executing
	 * @param actorName
	 *            - the name of the actor
	 * @param parameters
	 * @return true - if the action has been a terminal action, false otherwise
	 */
	public boolean performProgress(String actionTypeName, String actorName,
			double... parameters) {
		// Verifying whether this actionType and actor is legal according to the
		// progress state
		ProgressState state = progressGraph
				.getProgressState(currentProgressState);

		Set<String> legalActions = state.getActionTypesForActor(actorName);
		if (legalActions.isEmpty()) {
			TextUi.errorPrint("Actor " + actorName
					+ " does not have the turn in state "
					+ currentProgressState);
			System.exit(1);
		}
		if (!legalActions.contains(actionTypeName)) {
			TextUi.errorPrint("Actor " + actorName + " can not perform action "
					+ actionTypeName + " in state " + currentProgressState);
			TextUi.errorPrint("The legal actions are: " + legalActions);
			System.exit(1);
		}
		// apply the restricted progress function - Modify the progress state,
		// depends only on action type
		currentProgressState = progressGraph.getNextProgressState(
				currentProgressState, actorName, actionTypeName);
		return actionTypes.isTerminalActionType(actionTypeName);
	}

	/**
	 * Performs the action, including the progress and the impacts
	 * 
	 * @param action
	 *            the action
	 * @param aif
	 *            the action impact function
	 * @return true - if it was a temporal action
	 */
	public boolean performAction(Action action, iAIF aif) {
		boolean finish = performProgress(
				action.getActionType().getActionType(), action.getActor(),
				action.getParameters());

		return finish;
	}

	/**
	 * Return value of CSSMs and CBs, if agent take action 'a'
	 * 
	 * @return
	 */
	public List<ActionImpact> evaluateAction(Action action, iAIF aif) {
		List<ActionImpact> actionImpacts = aif.getImpacts(this, action
				.getActionType().getActionType(), action.getActor(), action
				.getParameters());
		return actionImpacts;
	}

	/**
	 * Sets a certain actor to be played by a specific social agent
	 * 
	 * @param actorName
	 * @param agent
	 */
	public void setActorPlayedBy(String actorName, SocialAgent agent) {
		createActor(actorName);
		Actor actor = actors.get(actorName);
		actor.setPlayedBy(agent);
	}

	/**
	 * Sets the value of the specified CB
	 * 
	 * @param scenarioType
	 * @param beliefName
	 * @param perspectiveActor
	 * @param estimatorActor
	 * @param value
	 */
	public void setCBValue(String scenarioType, String beliefName,
			String perspectiveActor, String estimatorActor,
			ConcreteBeliefValue cbv) {
		SocialAgent estimatorAgent = getActorPlayedBy(estimatorActor);
		ConcreteBelief cb = ConcreteBelief.createCB(this, beliefName,
				perspectiveActor, estimatorActor);
		estimatorAgent.setCBValue(cb, cbv);
	}

	/**
	 * Sets the value of the specified CSSM
	 * 
	 * @param cultureName
	 * @param metric
	 * @param subjectActor
	 * @param perspectiveActor
	 * @param estimatorActor
	 * @param value
	 */
	public void setCSSMValue(String cultureName, String metric,
			String subjectActor, String perspectiveActor,
			String estimatorActor, double value) {
		SocialAgent estimatorAgent = getActorPlayedBy(estimatorActor);
		CSSM cssm = CSSM.createCSSM(this, cultureName, metric, subjectActor,
				perspectiveActor, estimatorActor);
		estimatorAgent.setCSSMValue(cssm, value);
	}

	public void setCurrentProgressState(String currentProgressState) {
		this.currentProgressState = currentProgressState;
	}

	/**
	 * Increment frame count by argument
	 */
	public void setFrameCount(int count) {
		this.frameCount = count;
	}

	/**
	 * Changes the name of the scenario instance. This is sometimes useful to be
	 * done after the creation of the scenario, but not during execution.
	 * 
	 * @param scenarioInstance
	 */
	public void setScenarioInstance(String scenarioInstance) {
		this.scenarioInstance = scenarioInstance;
	}

	/**
	 * Add past actionTypes to pastActionTypes list
	 * 
	 * @param actionType
	 */

	public void addPerformedAction(String actionType) {
		if (!this.pastActionTypes.contains(actionType)) {
			this.pastActionTypes.add(actionType);
		}
	}

	/**
	 * Return True if specific actionType has been performed in the past.
	 * 
	 * @param actionType
	 * @return
	 */
	public boolean isPastAction(String actionType) {
		return this.pastActionTypes.contains(actionType);
	}

	@Override
	public String toStringDetailed(int detailLevel) {
		Formatter fmt = new Formatter();
		fmt.add("Social scenario");
		fmt.indent();
		fmt.add("Type: " + scenarioType);
		fmt.add("Instance: " + scenarioInstance);
		fmt.indent();
		fmt.add("Actors ");
		fmt.indent();
		for (String actorName : actors.keySet()) {
			Actor actor = actors.get(actorName);
			fmt.add(actor.toStringDetailed(detailLevel));
			SocialAgent socialAgent = actor.getPlayedBy();
			if (socialAgent != null) {
				fmt.addIndented(socialAgent.toStringDetailed(MAX_DETAIL));
			}
		}
		fmt.deindent();
		fmt.add("Action types");
		fmt.indent();
		fmt.add(actionTypes.toStringDetailed(MAX_DETAIL));
		fmt.deindent();
		return fmt.toString();
	}
}
