package yaes.cssm.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import yaes.ui.format.Formatter;
import yaes.ui.format.ToStringDetailed;

/**
 * 
 * @author Taranjeet:
 * @description "Action class":
 * 
 *              ActionType(A1)
 * 
 *              Description of action("Offers flowers for sell"),
 * 
 *              parameter of actions: x = offensive, y=loudness, t = wait time
 *              in seconds.
 * 
 *              Not all actions have parameters.
 * 
 * 
 */

public class ActionType implements ToStringDetailed, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328462606112423118L;
	private String actionType;
	private String description;
	private List<String> parameters;
	private List<Double> defaultValues;

	/**
	 * @param actionType
	 * @param description
	 * @param parameters
	 */
	public ActionType(String actionType, String description, List<String> parameters) {
		super();
		this.actionType = actionType;
		this.description = description;
		this.parameters = parameters;
	}

	/**
	 * @param actionType
	 * @param description
	 * @param parameters
	 */
	public ActionType(String actionType, String description, String... params) {
		super();
		this.actionType = actionType;
		this.description = description;
		this.parameters = new ArrayList<>();
		defaultValues = new ArrayList<>();
		for (String param : params) {
			parameters.add(param);
			defaultValues.add(0.0);
		}
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public List<Double> getDefaultValues() {
		return defaultValues;
	}

	public void setDefaultValues(List<Double> defaultValues) {
		this.defaultValues = defaultValues;
	}

	@Override
	public String toString() {
		return toStringDetailed(MAX_DETAIL);
	}

	@Override
	public String toStringDetailed(int detailLevel) {
		Formatter fmt = new Formatter();
		fmt.add("Action:");
		fmt.indent();
		fmt.is("ActionType", actionType);
		fmt.is("Description", description);
		fmt.add("Parameters");
		fmt.indent();
		for (String param : parameters) {
				fmt.add(param);
		}
		fmt.deindent();
		fmt.deindent();
		return fmt.toString();
		

	}

}
