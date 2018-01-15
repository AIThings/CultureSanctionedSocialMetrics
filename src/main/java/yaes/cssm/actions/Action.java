package yaes.cssm.actions;

import java.io.Serializable;
import java.util.List;

import yaes.ui.format.Formatter;
import yaes.ui.format.ToStringDetailed;

/**
 * Defines single instance of Action, tuple(scenarioInstance, actor, actionType, detailParameters), E.g tuple(scenario1, seller, A1, x ,y)
 * @author Taranjeet
 *
 */
public class Action implements ToStringDetailed, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 720205318056189732L;
	private String scenarioInstance;
	private String actor;
	private ActionType actionType;
	private double[] parameters;

	/**
	 * @param scenarioInstance
	 * @param actor
	 * @param actionType
	 * @param parameters
	 */
	public Action(String scenarioInstance, String actor, ActionType actionType,
			double... parameters) {
		super();
		this.scenarioInstance = scenarioInstance;
		this.actor = actor;
		this.actionType = actionType;
		this.parameters = parameters;
	}
	
	

	public Action(String scenarioInstance, String actor, ActionType actionType,
			List<Double> detailValues) {
		super();
		this.scenarioInstance = scenarioInstance;
		this.actor = actor;
		this.actionType = actionType;
		this.parameters = new double[detailValues.size()];
		for (int i = 0; i < detailValues.size(); i++) {
			parameters[i] = detailValues.get(i);
		}
	}



	public String getScenarioInstance() {
		return scenarioInstance;
	}



	public void setScenarioInstance(String scenarioInstance) {
		this.scenarioInstance = scenarioInstance;
	}



	public String getActor() {
		return actor;
	}



	public void setActor(String actor) {
		this.actor = actor;
	}



	public ActionType getActionType() {
		return actionType;
	}



	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}



	public double[] getParameters() {
		return parameters;
	}



	public void setParameters(double... parameters) {
		this.parameters = parameters;
	}



	@Override
	public String toStringDetailed(int detailLevel) {
		
		Formatter frm = new Formatter();
		frm.add("Action");
		frm.indent();
		frm.is("Scenario Instance: ", scenarioInstance);
		frm.is("Actor: ", actor);
		frm.add(actionType.getActionType() + ": "
				+ actionType.getDescription() + "\n");
		if (detailLevel == MAX_DETAIL) {
			for (int i = 0; i != actionType.getParameters().size(); i++) {
				frm.add("   " + actionType.getParameters().get(i) + " = "+ parameters[i]);
			}
		}
		return frm.toString();
	}
	@Override
	public String toString() {
		return toStringDetailed(MAX_DETAIL);
	}
	
	

}
