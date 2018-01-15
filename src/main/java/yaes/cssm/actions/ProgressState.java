package yaes.cssm.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import yaes.ui.format.Formatter;
import yaes.ui.format.IDetailLevel;
import yaes.ui.format.ToStringDetailed;
import yaes.ui.text.TextUi;

/**
 * 
 * A node of the action state.
 * 
 * @author Lotzi Boloni
 * 
 */
public class ProgressState implements ToStringDetailed, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8472293813155555644L;
	private Map<String, String> allowedActionForActor = new HashMap<>();
	/**
	 * The allowed actions and the corresponding roles
	 */
	private Map<String, List<String>> allowedActions = new HashMap<>();
	private boolean isTerminal = false;
	private String name;
	private Set<String> actorsWhoCanAct = new HashSet<>();

	/**
	 * transition[actor][actionType] --> nextProgressState
	 */
	private Map<String, Map<String, String>> transition = new HashMap<>();

	public ProgressState(String name) {
		super();
		this.name = name;
	}

	/**
	 * Adds the progress state transition where given actionType and any of the
	 * specified actors the new progress state will be the given
	 * 
	 * @param actionType
	 * @param actors
	 * @param nextProgressState
	 */
	public void addIdenticalProgressForAllActors(String actionType,
			List<String> actors, String nextProgressState) {
		allowedActions.put(actionType, actors);
		for (String actor : actors) {
			actorsWhoCanAct.add(actor);
			Map<String, String> progressForActor = transition.get(actor);
			if (progressForActor == null) {
				progressForActor = new HashMap<>();
				transition.put(actor, progressForActor);
			}
			progressForActor.put(actionType, nextProgressState);
		}
	}

	public void addProgress(String actionType, String actor,
			String nextProgressState) {
		List<String> actionRoles = allowedActions.get(actionType);
		if(actionRoles == null){
			actionRoles = new ArrayList<>();
		}
		actionRoles.add(actor);
		allowedActions.put(actionType, actionRoles);
		allowedActionForActor.put(actionType, actor);
		actorsWhoCanAct.add(actor);
		Map<String, String> progressForActor = transition.get(actor);
		if (progressForActor == null) {
			progressForActor = new HashMap<>();
			transition.put(actor, progressForActor);
		}
		progressForActor.put(actionType, nextProgressState);
	}

	/**
	 * Gets the action type which a particular actor can perform in this
	 * progress state
	 * 
	 * @param actor
	 * @return
	 */
	public Set<String> getActionTypesForActor(String actor) {
		Set<String> retval = new HashSet<>();
		if (!allowedActions.isEmpty()) {
			for (String action : allowedActions.keySet()) {
				if (allowedActions.get(action).contains(actor)) {
					retval.add(action);
				}
			}
		} else {
			for (String action : allowedActionForActor.keySet()) {
				if (actor.equals(allowedActionForActor.get(action))) {
					retval.add(action);
				}
			}
		}
		return retval;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * For being in a given progress state, when an action type is applied by a
	 * given actor, it returns the new progress state
	 * 
	 * @param actor
	 * @param actionType
	 * @return
	 */
	public String getNextProgressState(String actor, String actionType) {
		if (!allowedActions.isEmpty()) {
			if (allowedActions.get(actionType) == null) {
				TextUi.println(toStringDetailed(IDetailLevel.MAX_DETAIL));
				throw new Error("ActionType " + actionType + " is not allowed");
			}
			if (!allowedActions.get(actionType).contains(actor)) {
				TextUi.println(toStringDetailed(IDetailLevel.MAX_DETAIL));
				throw new Error("Action " + actionType
						+ " is not allowed for role " + actor);
			}
		} else {
			if (allowedActionForActor.get(actionType) == null) {
				TextUi.println(toStringDetailed(IDetailLevel.MAX_DETAIL));
				throw new Error("Action " + actionType + " is not allowed");
			}
			if (!allowedActionForActor.get(actionType).equals(actor)) {
				TextUi.println(toStringDetailed(IDetailLevel.MAX_DETAIL));
				throw new Error("Action " + actionType
						+ " is not allowed for role " + actor);
			}
		}
		return transition.get(actor).get(actionType);
	}

	/**
	 * Returns the set of roles which can act in the given state (usually a
	 * single one)
	 * 
	 * @return
	 */
	public Set<String> getActorsWhoCanAct() {
		return actorsWhoCanAct;
	}


	@Override
	public String toStringDetailed(int detailLevel) {
		Formatter fmt = new Formatter();
		if (actorsWhoCanAct != null) {
			for (String role : actorsWhoCanAct) {
				Map<String, String> transition1 = transition.get(role);
				fmt.add(role + ":");
				for (String actionType : transition1.keySet()) {
					fmt.addIndented(name + "\t-->\t" + actionType + "\t-->\t"
							+ transition1.get(actionType));
				}

			}
		}
		return fmt.toString();

	}

	@Override
	public String toString() {
		return toStringDetailed(MAX_DETAIL);
	}

}
