package yaes.cssm.cssm;

import java.io.Serializable;

import hypothesis.Hypothesis;
import mass.exact.MassFunction;
import yaes.ui.format.Formatter;
import yaes.ui.format.IDetailLevel;

/**
 * A class encapsulating the Dempster-Shafer evidence
 * 
 * It uses standard values (t and f)
 * 
 * 
 * 
 * @author lboloni
 * 
 */
public class CbDempsterShafer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7656633799447529833L;
	private MassFunction<Character> massFunction;
	public static Character DS_TRUE = 't';
	public static Character DS_FALSE = 'f';

	/**
	 * Initialize with true and false value
	 * 
	 * @param valueT
	 * @param valueF
	 */
	public CbDempsterShafer(double valueT, double valueF) {
		massFunction = new MassFunction<>();
		massFunction.add(new Hypothesis<>(DS_TRUE), valueT);
		massFunction.add(new Hypothesis<>(DS_FALSE), valueF);
		massFunction.add(new Hypothesis<>(DS_TRUE, DS_FALSE), 1.0 - valueT
				- valueF);
	}

	/**
	 * Gets the belief value
	 * 
	 * @return
	 */
	public double getBelief() {
		return massFunction.getBelief(new Hypothesis<>(DS_TRUE));
	}

	public double getPlausibility() {
		return massFunction.getPlausibility(new Hypothesis<>(DS_TRUE));
	}

	/**
	 * Applies an evidence and returns the new value
	 * 
	 * @return
	 */
	public CbDempsterShafer applyEvidence(CbDempsterShafer evidence) {
		CbDempsterShafer retval = new CbDempsterShafer(0.0, 0.0);
		retval.massFunction = massFunction
				.combineConjunctive(evidence.massFunction);
		return retval;
	}

	@Override
	public String toString() {
		return toStringDetailed(IDetailLevel.MAX_DETAIL);
	}

	public String toStringDetailed(int detailLevel) {
		switch (detailLevel) {
		//
		// Writing the mass function in full detail
		//
		case IDetailLevel.MAX_DETAIL: {
			Formatter fmt = new Formatter();
			fmt.add("CbDempsterShafer");
			fmt.indent();
			fmt.add("Mass");
			fmt.indent();
			fmt.is("true", massFunction.getMass(new Hypothesis<>(DS_TRUE)));
			fmt.is("false", massFunction.getMass(new Hypothesis<>(DS_FALSE)));
			fmt.is("true + false",
					massFunction.getMass(new Hypothesis<>(DS_TRUE, DS_FALSE)));
			fmt.is("emptyset",
					massFunction.getMass(new Hypothesis<Character>()));
			fmt.deindent();
			fmt.add("Values");
			fmt.is("Belief(true)",
					massFunction.getBelief(new Hypothesis<>(DS_TRUE)));
			fmt.is("Plausibility(true)",
					massFunction.getPlausibility(new Hypothesis<>(DS_TRUE)));
			fmt.is("Belief(false)",
					massFunction.getBelief(new Hypothesis<>(DS_FALSE)));
			fmt.is("Plausibility(false)",
					massFunction.getPlausibility(new Hypothesis<>(DS_FALSE)));
			return fmt.toString();
		}
		//
		// Writing the mass function with minimum detail
		//
		case IDetailLevel.MIN_DETAIL: {
			return "T="
					+ Formatter.fmt(massFunction.getMass(new Hypothesis<>(
							DS_TRUE)))
					+ " "
					+ "F="
					+ Formatter.fmt(massFunction.getMass(new Hypothesis<>(
							DS_TRUE)));
		}
		default:
			throw new Error(
					"CbDempsterShafer.toStringDetailed: unsupported detail-level "
							+ detailLevel);
		}
	}
}
