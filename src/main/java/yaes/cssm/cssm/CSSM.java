package yaes.cssm.cssm;

import java.io.Serializable;

import yaes.ui.format.Formatter;
import yaes.ui.format.ToStringDetailed;

/**
 * CSSM class, This class is defined as immutable class. It will serve as
 * complete one time initialized object which work as key for Map<> classes
 * Examples:
 * 
 * 1) Self perspective: CSSM(Western, dignity, John, John,John) This CSSM
 * represents John’s estimate of its own dignity,in the Western cultural model.
 * 2) Peer perspective: CSSM(Western, politeness, John, Mary, John)) John’s
 * estimate about how Mary sees his politeness. 3)CSSM(Western, politeness,John,
 * Mary, Mary) Mary’s own opinion about John’s politeness.
 * 
 * @author Taranjeet:
 * 
 */

public final class CSSM implements Constants, ToStringDetailed, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1389146899648000297L;
	private String cultureName;
	private String metric;
	/**
	 * The name of agent who is the subject (the owner) of the CSSM
	 */
	private String subjectAgent;
	/**
	 * The name of the agent from whose perspective the CSSM is evaluated
	 */
	private String perspectiveAgent;
	/**
	 * The name of the agent who is the estimator of the CSSM. Normally, the
	 * CSSM will be stored with this agent
	 */
	private String estimatorAgent;
//	private double rangeLow;
//	private double rangeHigh;
//	private double defaultValue;

	/**
	 * @param culture
	 *            : which defines name of culture.
	 * @param metric
	 *            : name of the metric
	 * @param subjectAgent
	 *            : subject agent, the agent which owns the metric
	 * @param perspectiveAgent
	 *            : perspective agent, from whose perspective the metric is
	 *            evaluated
	 * @param estimatorAgent
	 *            : which performs the estimate and who is the owner of the
	 *            knowledge
	 */
	private CSSM(String cultureName, String metric, String subjectAgent,
			String perspectiveAgent, String estimatorAgent) {
		this.cultureName = cultureName;
		this.metric = metric;
		this.subjectAgent = subjectAgent;
		this.perspectiveAgent = perspectiveAgent;
		this.estimatorAgent = estimatorAgent;
//		this.rangeLow = rangelow;
//		this.rangeHigh = rangehigh;
//		this.defaultValue = defaultValue;
	}

//	/**
//	 * Simplified constructor, with default range of [0,1] and value 0.5
//	 * 
//	 * @param cultureName
//	 * @param metric
//	 * @param subActor
//	 * @param persActor
//	 * @param estActor
//	 */
//	private CSSM(String cultureName, String metric, String subActor,
//			String persActor, String estActor) {
//		this(cultureName, metric, subActor, persActor, estActor, 0, 1, 0.5);
//	}

	/**
	 * Helper function for creating a CSSM based on the scenario. It allows us
	 * that instead of the names of the agents who are acting as a specific
	 * actor to specify the name of the actor in the scenario.
	 * 
	 * I.e. instead of "Jill", "Hasan", we can say "Client", "Seller" and then
	 * map it to the appropriate values
	 * 
	 * @param scenario
	 * @param metric
	 * @param subjectActor
	 * @param perspectiveActor
	 * @param estimatorActor
	 * @param rangelow
	 * @param rangehigh
	 * @param defaultValue
	 */
	public static CSSM createCSSM(Scenario scenario, String cultureName,
			String metric, String subjectActor, String perspectiveActor,
			String estimatorActor) {
		String perspectiveAgent = scenario.getActorPlayedBy(perspectiveActor)
				.getName();
		String subjectAgent = scenario.getActorPlayedBy(subjectActor).getName();
		String estimatorAgent = scenario.getActorPlayedBy(estimatorActor)
				.getName();
		return new CSSM(cultureName, metric, subjectAgent, perspectiveAgent,
				estimatorAgent);
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
				+ ((cultureName == null) ? 0 : cultureName.hashCode());
		result = prime * result
				+ ((estimatorAgent == null) ? 0 : estimatorAgent.hashCode());
		result = prime * result + ((metric == null) ? 0 : metric.hashCode());
		result = prime
				* result
				+ ((perspectiveAgent == null) ? 0 : perspectiveAgent.hashCode());
		result = prime * result
				+ ((subjectAgent == null) ? 0 : subjectAgent.hashCode());
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
		CSSM other = (CSSM) obj;
		if (cultureName == null) {
			if (other.cultureName != null) {
				return false;
			}
		} else if (!cultureName.equals(other.cultureName)) {
			return false;
		}
		if (estimatorAgent == null) {
			if (other.estimatorAgent != null) {
				return false;
			}
		} else if (!estimatorAgent.equals(other.estimatorAgent)) {
			return false;
		}
		if (metric == null) {
			if (other.metric != null) {
				return false;
			}
		} else if (!metric.equals(other.metric)) {
			return false;
		}
		if (perspectiveAgent == null) {
			if (other.perspectiveAgent != null) {
				return false;
			}
		} else if (!perspectiveAgent.equals(other.perspectiveAgent)) {
			return false;
		}
		if (subjectAgent == null) {
			if (other.subjectAgent != null) {
				return false;
			}
		} else if (!subjectAgent.equals(other.subjectAgent)) {
			return false;
		}
		return true;
	}

	public String getCultureName() {
		return cultureName;
	}

	public String getMetric() {
		return metric;
	}

	public String getSubjectAgent() {
		return subjectAgent;
	}

	public String getPerspectiveAgent() {
		return perspectiveAgent;
	}

	public String getEstimatorAgent() {
		return estimatorAgent;
	}

//	public double getRangeLow() {
//		return rangeLow;
//	}
//
//	public double getRangeHigh() {
//		return rangeHigh;
//	}
//
//	public double getDefaultValue() {
//		return defaultValue;
//	}

	@Override
	public String toString() {
		return toStringDetailed(MAX_DETAIL);
	}

	

	@Override
	public String toStringDetailed(int detailLevel) {
		switch (detailLevel) {
		case MAX_DETAIL: {
			Formatter fmt = new Formatter();
			fmt.add("CSSM");
			fmt.indent();
			fmt.is("Culture", cultureName);
			fmt.is("Metric", metric);
			fmt.is("Subject Actor", subjectAgent);
			fmt.is("Perspective Actor", perspectiveAgent);
			fmt.is("Estimator Actor", estimatorAgent);
			fmt.indent();
//			fmt.is("range", "[" + rangeLow + ", " + rangeHigh + "]");
//			fmt.is("Default Value", defaultValue);
			return fmt.toString();
		}
		case DTL_NAME_ONLY: {
			String retval = "CSSM(" + cultureName + "," + metric + ","
					+ subjectAgent + "," + perspectiveAgent + ","
					+ estimatorAgent + ")";
			return retval;
		}
		default:
			return "CSSM.toStringDetailed unsupported detail level "
					+ detailLevel;
		}
	}

	/**
	 * A pseudo name for data logging CSSM value
	 */

	public String getPseudoName() {
		return (this.cultureName + "_" + this.metric + "_" + this.subjectAgent
				+ "_" + this.perspectiveAgent + "_" + this.estimatorAgent);
	}

//	/**
//	 * Creates a default value for initialization of SocialAgents etc.
//	 * 
//	 * @return
//	 */
//	public CSSMValue getCssmValue() {
//		CSSMValue retval = new CSSMValue(this.rangeLow, this.rangeHigh, this.defaultValue);
//		retval.setValue(defaultValue);
//		return retval;
//	}
}
