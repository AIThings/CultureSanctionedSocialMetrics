package yaes.cssm.cssm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FeatureList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1122974780980977267L;
	private Map<CSSM, Double> cssmList = new HashMap<>();
	private Map<ConcreteBelief, Double> cbList = new HashMap<>();

	public double getCSSMValue(CSSM cssm) {
		if (this.cssmList.containsKey(cssm))
			return this.cssmList.get(cssm);
		return 0;

	}

	public double getCBValue(ConcreteBelief cb) {
		if (this.cbList.containsKey(cb))
			return this.cbList.get(cb);
		return 0;

	}

	public void setCSSMValue(CSSM cssm, double value) {
		this.cssmList.put(cssm, value);
	}

	public void setCBValue(ConcreteBelief cb, double value) {
		this.cbList.put(cb, value);
	}

	public void setCSSMValue(Scenario scenario, String cultureName,
			String metric, String subjectActor, String perspectiveActor,
			String estimatorActor, double value) {

		CSSM cssm = CSSM.createCSSM(scenario, cultureName, metric,
				subjectActor, perspectiveActor, estimatorActor);

		this.cssmList.put(cssm, value);

	}

	public void setCBValue(Scenario scenario, String scenarioType,
			String beliefName, String perspectiveActor, String estimatorActor,
			double value) {

		ConcreteBelief cb = ConcreteBelief.createCB(scenario, 
				beliefName, perspectiveActor, estimatorActor);
		this.cbList.put(cb, value);

	}

}
