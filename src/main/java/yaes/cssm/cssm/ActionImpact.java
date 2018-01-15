package yaes.cssm.cssm;

import java.io.Serializable;
import java.util.List;

import yaes.ui.format.Formatter;
import yaes.ui.format.ToStringDetailed;

/**
 * The action impact defines a certain change on a specific CB or CSSM
 * 
 * @author Taranjeet
 * 
 */
public class ActionImpact implements ToStringDetailed, Serializable {
	private static final long serialVersionUID = 6307008613373797454L;
	private boolean isCSSM; // if not, it is a CB
	private String scenarioType;
	private String cultureName;
	private String metric;
	private String subjectAgent;
	private String perspectiveAgent;
	private String estimatorAgent;
	private double previousValue;
	private double newValue;
	private CbDempsterShafer evidence;
	
	public CbDempsterShafer getEvidence() {
		return evidence;
	}

	/**
	 * The mass function which describes the change in the belief using the
	 * Dempster Shafer model
	 */
	private ConcreteBeliefValue beliefValue;

	public ConcreteBeliefValue getBeliefValue() {
		return beliefValue;
	}

	public double getNewValue() {
		return newValue;
	}

	public void setNewValue(double newValue) {
		this.newValue = newValue;
	}

	public boolean isCSSM() {
		return isCSSM;
	}

	public String getCultureName() {
		return cultureName;
	}

	public String getMetricName() {
		return metric;
	}

	public String getSubActor() {
		return subjectAgent;
	}

	public String getPersActor() {
		return perspectiveAgent;
	}

	public String getEstActor() {
		return estimatorAgent;
	}

	public double getPreviousValue() {
		return previousValue;
	}

	public String getScenarioName() {
		return scenarioType;
	}

	private ActionImpact() {

	}

	/**
	 * Factory function, creates an action impact for a CSSM without specifying
	 * the new value
	 * 
	 * @param scenario
	 * @param cultureName
	 * @param cssmName
	 * @param subActor
	 * @param persActor
	 * @param estActor
	 * @return ActionImpact
	 */
	public static ActionImpact createCSSMImpact(Scenario scenario,
			String cultureName, String cssmName, String subActor,
			String persActor, String estActor) {
		ActionImpact ai = new ActionImpact();
		ai.scenarioType = scenario.getScenarioInstance();
		ai.cultureName = cultureName;
		ai.metric = cssmName;
		ai.subjectAgent = subActor;
		ai.perspectiveAgent = persActor;
		ai.estimatorAgent = estActor;
		ai.previousValue = scenario.getCSSMValue(cultureName, cssmName, subActor,
				persActor, estActor).getValue();
		ai.isCSSM = true;
		return ai;
	}

	/**
	 * Factory function, creates an action impact for a CSSM for the specific
	 * case when the impact is only a fixed difference on the value. Adds the value to 
	 * the specified list (this is the typical usage)
	 * 
	 * @param scenario
	 * @param cultureName
	 * @param cssmName
	 * @param subActor
	 * @param persActor
	 * @param estActor
	 * @param delta
	 * @return ActionImpact
	 */
	public static ActionImpact cssmDelta(List<ActionImpact> list, Scenario scenario,
			String cultureName, String cssmName, String subActor,
			String persActor, String estActor, double delta) {
		ActionImpact ai = new ActionImpact();
		ai.scenarioType = scenario.getScenarioInstance();
		ai.cultureName = cultureName;
		ai.metric = cssmName;
		ai.subjectAgent = subActor;
		ai.perspectiveAgent = persActor;
		ai.estimatorAgent = estActor;
		ai.previousValue = scenario.getCSSMValue(cultureName, cssmName, subActor,
				persActor, estActor).getValue();
		ai.newValue = ai.previousValue + delta;
		ai.isCSSM = true;
		list.add(ai);
		return ai;
	}

	/**
	 * Factory function, creates an action impact for a CB by providing the new belief value
	 * 
	 * @param list - the list to which the impact to be added
	 * @param scenario
	 * @param cultureName
	 * @param cbName
	 * @param persActor
	 * @param estActor
	 * @param cbv
	 * @return ActionImpact
	 */
	@Deprecated
	public static ActionImpact createCBImpact(List<ActionImpact> list, Scenario scenario,
			String cbName, String persActor,
			String estActor, ConcreteBeliefValue cbv) {
		ActionImpact ai = new ActionImpact();
		ai.scenarioType = scenario.getScenarioInstance();
		ai.cultureName = null;
		ai.metric = cbName;
		ai.perspectiveAgent = persActor;
		ai.estimatorAgent = estActor;
		ai.previousValue = scenario.getCB(cbName, persActor,
				estActor).getValue();
		ai.beliefValue = cbv;
		ai.newValue = cbv.getValue();
		ai.isCSSM = false;
		list.add(ai);
		return ai;
	}

	
	/**
	 * Factory function, creates an action impact for a CB providing the new evidence 
	 * 
	 * @param list - the list to which the impact to be added
	 * @param scenario
	 * @param cultureName
	 * @param cbName
	 * @param persActor
	 * @param estActor
	 * @param cbv
	 * @return ActionImpact
	 */
	public static ActionImpact cbEvidence(List<ActionImpact> list, Scenario scenario,
			String cbName, String persActor,
			String estActor, CbDempsterShafer evidence) {
		ActionImpact ai = new ActionImpact();
		ai.scenarioType = scenario.getScenarioInstance();
		ai.cultureName = null;
		ai.metric = cbName;
		ai.perspectiveAgent = persActor;
		ai.estimatorAgent = estActor;
		ConcreteBeliefValue oldValue = scenario.getCB(cbName, persActor,
				estActor); 
		ai.previousValue = oldValue.getValue();
		ai.beliefValue = oldValue.applyEvidence(evidence);
		ai.evidence = evidence;
		ai.newValue = ai.beliefValue.getValue();
		ai.isCSSM = false;
		list.add(ai);
		return ai;
	}

	
	
	/**
	 * A pseudo name for data logging ActionImpact's value.
	 * 
	 * @return pseudoString
	 */
	public String getPseudoString() {
		if (this.isCSSM) {
			return (this.cultureName + "_" + this.metric + "_"
					+ this.subjectAgent + "_" + this.perspectiveAgent + "_" + this.estimatorAgent);
		} else {
			return (this.scenarioType + "_" + this.metric + "_"
					+ this.perspectiveAgent + "_" + this.estimatorAgent);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see yaes.ui.format.ToStringDetailed#toStringDetailed(int)
	 */
	@Override
	public String toStringDetailed(int detailLevel) {
		Formatter fmt = new Formatter();
		if (isCSSM) {
			fmt.add("(" + scenarioType + ", " + cultureName + ", " + metric
					+ ", " + subjectAgent + ", " + perspectiveAgent + ", "
					+ estimatorAgent + ")");
		} else {
			fmt.add("(" + scenarioType + ", " + cultureName + ", " + metric
					+ ", " + perspectiveAgent + ", " + estimatorAgent + ")");
		}
		fmt.indent();
		fmt.is("Previous: ", previousValue);
		fmt.is("New: ", newValue);
		fmt.deindent();

		return fmt.toString();
	}

	@Override
	public String toString() {
		Formatter fmt = new Formatter();
		if (this.isCSSM) {
			fmt.add(this.cultureName + "_" + this.metric + "_"
					+ this.subjectAgent + "_" + this.perspectiveAgent + "_" + this.estimatorAgent);
		} else {
			fmt.add(this.scenarioType + "_" + this.metric + "_"
					+ this.perspectiveAgent + "_" + this.estimatorAgent);
		}
		
		
		fmt.indent();
		fmt.is("Previous: ", previousValue);
		fmt.is("New: ", newValue);
		fmt.deindent();

		return fmt.toString();
	}

}
