package yaes.cssm.impactfunctions;

import yaes.ui.format.Formatter;

/**
 * Implements the Richard's curve, keeps the parameters, a particular value is
 * returned by getValue function
 * 
 * @author lboloni
 * 
 */
public class RichardsCurve {

	private double K;
	private double A;
	private double Q;
	private double M;
	private double B;

	public double getB() {
		return B;
	}

	private double v;

	public double getK() {
		return K;
	}

	public double getA() {
		return A;
	}

	public double getQ() {
		return Q;
	}

	public double getM() {
		return M;
	}

	public double getV() {
		return v;
	}

	/**
	 * @param k
	 * @param a
	 * @param q
	 * @param m
	 * @param v
	 */
	public RichardsCurve(double k, double a, double q, double m, double b,
			double v) {
		super();
		K = k;
		A = a;
		Q = q;
		M = m;
		B = b;
		this.v = v;
	}

	/**
	 * Returns the value of the Richard's curve
	 * 
	 * @param t
	 */
	public double getValue(double t) {
		double upper = K - A;
		double lowerinside = 1.0 + Q * Math.exp(-B * (t - M));
		double lower = Math.pow(lowerinside, 1.0 / v);
		double retval = A + upper / lower;
		return retval;
	}

	/**
	 * Prints the parameters of the Richard's function
	 */
	@Override
	public String toString() {
		Formatter fmt = new Formatter();
		fmt.add("Richards function:");
		fmt.indent();
		fmt.is("K", K);
		fmt.is("A", A);
		fmt.is("Q", Q);
		fmt.is("M", M);
		fmt.is("B", B);
		fmt.is("v", v);
		return fmt.toString();
	}

}
