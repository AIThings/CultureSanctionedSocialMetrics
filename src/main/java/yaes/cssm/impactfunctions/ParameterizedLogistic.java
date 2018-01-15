package yaes.cssm.impactfunctions;

import yaes.ui.format.Formatter;
import yaes.ui.format.IDetailLevel;

/**
 * The parameterized logistic function used by the AIF function. It has a
 * internal Richard's function to which it passes certain parameters
 * 
 * @author lboloni
 * 
 */
public class ParameterizedLogistic implements IDetailLevel {

	private String label; // the label of the parameter
	private double K; // scale - the parameter will be multiplied with this
	private double M; // shift
	private double B; // steepness
	private RichardsCurve richardsCurve;

	/**
	 * Constructor. Stores the parameters and creates the inner Richards' curve
	 * 
	 * @param k
	 * @param m
	 * @param b
	 */
	public ParameterizedLogistic(String label, double k, double m, double b) {
		super();
		this.label = label;
		K = k;
		M = m;
		B = b;
		richardsCurve = new RichardsCurve(K, 0.0, 1.0, M, B, 1.0);
	}

	public double getB() {
		return B;
	}

	public double getK() {
		return K;
	}

	public String getLabel() {
		return label;
	}

	public double getM() {
		return M;
	}

	/**
	 * Return the value - fall back to the inner Richard's curve
	 * 
	 * @param t
	 * @return
	 */
	public double getValue(double t) {
		double retval = richardsCurve.getValue(t);
		return retval;
	}

	/**
	 * Prints the parameters and the inner Richard's curve
	 */
	@Override
	public String toString() {
		return toStringDetailed(MAX_DETAIL);
	}

	public String toStringDetailed(int detailLevel) {
		switch (detailLevel) {
		case MAX_DETAIL: {
			Formatter fmt = new Formatter();
			fmt.add("Parameterized Logistics curve:");
			fmt.indent();
			fmt.is("K", K);
			fmt.is("M", M);
			fmt.is("B", B);
			fmt.add("Inner Richard's curve");
			fmt.indent();
			fmt.add(richardsCurve);
			return fmt.toString();
		}
		case DTL_NAME_ONLY: {
			String retval = "L(" + label + "," + Formatter.fmt(K) + "," + Formatter.fmt(M)
					+ "," + Formatter.fmt(B) + ")";
			return retval;
		}
		default: {
			throw new Error(
					"ParameterizedLogistics.toStringDetailed - Unsupported detail level "
							+ detailLevel);
		}
		}
	}
}
