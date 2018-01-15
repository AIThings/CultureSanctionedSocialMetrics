package yaes.cssm.cssm;

import java.io.Serializable;

import yaes.ui.format.Formatter;
import yaes.ui.format.ToStringDetailed;

/**
 * Concrete beliefs (CBs) the beliefs maintained by the actors in a scenario
 * with regards to the answers of concrete questions. This is again immutable
 * class and similar to CSSM class.
 * 
 * @author Taranjeet
 * 
 */
public final class ConcreteBelief implements Constants, ToStringDetailed,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4742561868454056514L;
	/**
	 * The generic identifier of the scenario: eg. SpanishSteps, HumanBargaining
	 */
	private String scenarioId;
	private String beliefName;
	private String perspectiveAgent;
	private String estimatorAgent;

	/**
	 * Constructor
	 * 
	 * * Example: CB(scenario, " Legitimate transaction", Crowd, Client).
	 * In the Spanish Steps scenario what client think that concrete belief of
	 * the "statement" == "legitimate transaction" from perspective of crowd.
	 * 
	 * 
	 * @param scenarioId
	 * @param beliefName
	 * @param perspectiveAgent
	 * @param estimatorAgent
	 * 
	 * 
	 */
	private ConcreteBelief(String scenarioId, String beliefName,
			String perspectiveAgent, String estimatorAgent) {
		this.scenarioId = scenarioId;
		this.beliefName = beliefName;
		this.perspectiveAgent = perspectiveAgent;
		this.estimatorAgent = estimatorAgent;
	}

	/**
	 * A factory function which allows the creation of the CB in such a way that
	 * we specify the actors in a scenario, instead directly the agents who act
	 * as the specific actors
	 * 
	 * @param scenario
	 * @param beliefName
	 * @param perspectiveActor
	 * @param estimatorActor
	 * @return
	 */
	public static ConcreteBelief createCB(Scenario scenario,
			String beliefName, String perspectiveActor,
			String estimatorActor) {
		String perspectiveAgent = scenario.getActorPlayedBy(perspectiveActor)
				.getName();
		String estimatorAgent = scenario.getActorPlayedBy(estimatorActor)
				.getName();
		String scenarioId = scenario.getScenarioType() + "_" + scenario.getScenarioInstance();
		return new ConcreteBelief(scenarioId, beliefName, perspectiveAgent,
				estimatorAgent);
	}

	/**
	 * @return scenarioName
	 */
	public String getScenarioId() {
		return scenarioId;
	}

	/**
	 * @return beliefName
	 */
	public String getBeliefName() {
		return beliefName;
	}

	/**
	 * @return perspectiveAgent
	 */
	public String getPerspectiveAgent() {
		return perspectiveAgent;
	}

	/**
	 * @return estimatorAgent
	 */
	public String getEstimatorAgent() {
		return estimatorAgent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beliefName == null) ? 0 : beliefName.hashCode());
		result = prime * result
				+ ((estimatorAgent == null) ? 0 : estimatorAgent.hashCode());
		result = prime
				* result
				+ ((perspectiveAgent == null) ? 0 : perspectiveAgent.hashCode());
		result = prime * result
				+ ((scenarioId == null) ? 0 : scenarioId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConcreteBelief other = (ConcreteBelief) obj;
		if (beliefName == null) {
			if (other.beliefName != null) {
				return false;
			}
		} else if (!beliefName.equals(other.beliefName)) {
			return false;
		}
		if (estimatorAgent == null) {
			if (other.estimatorAgent != null) {
				return false;
			}
		} else if (!estimatorAgent.equals(other.estimatorAgent)) {
			return false;
		}
		if (perspectiveAgent == null) {
			if (other.perspectiveAgent != null) {
				return false;
			}
		} else if (!perspectiveAgent.equals(other.perspectiveAgent)) {
			return false;
		}
		if (scenarioId == null) {
			if (other.scenarioId != null) {
				return false;
			}
		} else if (!scenarioId.equals(other.scenarioId)) {
			return false;
		}
		return true;
	}

	/**
	 * Defaults to a detailed writeup of the CB and its current values
	 */
	@Override
	public String toString() {
		return toStringDetailed(MAX_DETAIL);
	}

	/**
	 * A pseudo name for data logging CB value
	 */

	public String getPseudoName() {
		return (this.scenarioId + "_" + this.beliefName + "_"
				+ this.perspectiveAgent + "_" + this.estimatorAgent);
	}

	/**
	 * A detailed writeup of the current values.
	 */
	@Override
	public String toStringDetailed(int detailLevel) {
		switch (detailLevel) {
		case MAX_DETAIL: {
			Formatter fmt = new Formatter();
			fmt.add("Concrete Belief");
			fmt.indent();
			fmt.is("ScenarioType", scenarioId);
			fmt.is("Belief", beliefName);
			fmt.is("Perspective agent", perspectiveAgent);
			fmt.is("Estimator agent", estimatorAgent);
			return fmt.toString();
		}
		case DTL_NAME_ONLY: {
			String retval = "CB(" + scenarioId + "," + beliefName + ","
					+ perspectiveAgent + "," + estimatorAgent + ")";
			return retval;
		}
		default:
			return "ConcreteBelief.toStringDetailed - unsupported detail level "
					+ detailLevel;
		}
	}
}
