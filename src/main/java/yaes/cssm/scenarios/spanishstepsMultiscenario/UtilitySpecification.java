package yaes.cssm.scenarios.spanishstepsMultiscenario;

import yaes.ui.format.ToStringDetailed;

/**
 * 
 * @author Taranjeet Utility functions for action Impact functions.
 */
public class UtilitySpecification implements ToStringDetailed {

//	private CSSMSet svSpec;
//	private ConcreteBeliefSet cbSpec;
//	private Map<CSSM, Double> weights;
//	private Map<CSSM, Double> biases;
//	private Map<CSSM, Double> prescales;
//
//	/**
//	 * Function for calculating an individual logistic sigmoid (prescaled,
//	 * weighted etc)
//	 * 
//	 * @param value
//	 * @param bias
//	 * @param prescale
//	 * @param weight
//	 * @return
//	 */
//	public static double logisticSigmoid(double value, double bias,
//			double prescale, double weight) {
//		double t = (value + bias) * prescale;
//		double retval = weight * (1 / (1 - Math.exp(-t)));
//		return retval;
//	}
//
//	/**
//	 * Calculating the utility. Notice how the utility is in fact very similar
//	 * to a two layer backprop output
//	 * 
//	 * @param sv
//	 * @return
//	 */
//	public double getUtilityCSSM(CSSMandCBValues sv) {
//		double retval = 0.0;
//		svSpec = sv.getCSSMSet();
//		for (CSSM name : svSpec.getcssmNameList()) {
//			double value = sv.getValuesCSSM(name);
//			double weight = weights.get(name);
//			double bias = biases.get(name);
//			double prescale = prescales.get(name);
//			retval += logisticSigmoid(value, bias, prescale, weight);
//		}
//		return retval;
//	}
//
//	public double getUtilityCB(CSSMandCBValues sv) {
//		double retval = 0.0;
//		cbSpec = sv.getConcreteBeliefSet();
//		for (ConcreteBelief name : cbSpec.getCbNameList()) {
//			double value = sv.getValueCB(name);
//			double weight = weights.get(name);
//			double bias = biases.get(name);
//			double prescale = prescales.get(name);
//			retval += logisticSigmoid(value, bias, prescale, weight);
//		}
//		return retval;
//	}

	@Override
	public String toStringDetailed(int detailLevel) {
		return "UtilitySpecification - not implemented yet";
	}

}
