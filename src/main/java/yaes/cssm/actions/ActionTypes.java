package yaes.cssm.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yaes.ui.format.Formatter;
import yaes.ui.format.ToStringDetailed;

/**
 * 
 * A collection of action types, as used from the Scenario class. The 
 * action types are in map indexed by their 
 *
 * 
 * @author Taranjeet
 * @description set of Actions.
 * 
 *              Name of the set
 * 
 *              Terminal actionType
 * 
 * 
 */
public class ActionTypes implements ToStringDetailed, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 679286050615649927L;
	private Map<String, ActionType> setOfActions = new HashMap<>();
	private List<String> terminalActionTypes = new ArrayList<>();

	/**
	 * @param setName
	 */
	public ActionTypes() {
		super();
	}

	/**
	 * Check actionType is terminal actionType
	 * 
	 * @param actionName
	 * @return True or False
	 */
	public boolean isTerminalActionType(String actionType) {
		return terminalActionTypes.contains(actionType);
	}

	/**
	 * Adds an ActionType and specify whether it is Terminal action type
	 * 
	 * @param actionType
	 * @param terminal
	 */
	public void addActionType(ActionType actionType, boolean terminal) {
		setOfActions.put(actionType.getActionType(), actionType);
		if (terminal) {
			terminalActionTypes.add(actionType.getActionType());
		}

	}

	/**
	 * Get a list of the action type names
	 * 
	 * @return List<String>
	 */
	public List<String> getActionTypeNames() {
		List<String> actionTypes = new ArrayList<>();
		for (String key : setOfActions.keySet()) {
			actionTypes.add(key);
		}
		return actionTypes;
	}

	/**
	 * Returns the ActionType object when searched by its name
	 * @param actionType
	 * @return
	 */
	public ActionType getActionType(String actionType) {
		return setOfActions.get(actionType);
	}

	@Override
	public String toString() {
		return toStringDetailed(MAX_DETAIL);
	}

	@Override
	public String toStringDetailed(int detailLevel) {
		Formatter fmt = new Formatter();
		fmt.add("ActionTypes");
		fmt.indent();
		// all action types
		fmt.add("All action types:");
		fmt.indent();
		for (String actionType : getActionTypeNames()) {
			fmt.add(getActionType(actionType).toString());
		}
		fmt.deindent();
		// all action types
		fmt.add("Terminal action types:");
		fmt.indent();
		for (String actionType : getActionTypeNames()) {
			if (isTerminalActionType(actionType)) {
				fmt.add(getActionType(actionType).toString());
			}
		}
		fmt.deindent();

		return fmt.toString();

	}

}
