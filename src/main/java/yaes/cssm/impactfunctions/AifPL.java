package yaes.cssm.impactfunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import yaes.ui.format.Formatter;
import yaes.ui.format.IDetailLevel;

/**
 * An action impact function designed as a combination of ParameterizedLogistic
 * functions.
 * 
 * The value is a set of terms T1 + T2 + ... + Tn.
 * 
 * Each term is a multiplication of factors T1 = F1_1 * F1_2 * ... * F1_m
 * 
 * Each factor is a parameterized logistic function ParameterizedLogistic
 * 
 * @author lboloni
 * 
 */
public class AifPL implements IDetailLevel {

	private List<List<ParameterizedLogistic>> terms = new ArrayList<>();
	private int parameterCount = 0;
	private String name;

	public AifPL(String name) {
		this.name = name;
	}

	public int getParameterCount() {
		return parameterCount;
	}

	public String getName() {
		return name;
	}

	/**
	 * Creates a new AifPL which is obtained by multiplying together the current
	 * one and an other
	 * 
	 * @return
	 */
	public AifPL multiply(AifPL other) {
		AifPL retval = new AifPL(name + " * " + other.name);
		for (List<ParameterizedLogistic> pls : terms) {
			for (List<ParameterizedLogistic> plsOther : other.terms) {
				List<ParameterizedLogistic> plnew = new ArrayList<>();
				plnew.addAll(pls);
				plnew.addAll(plsOther);
				retval.terms.add(plnew);
			}
		}
		return retval;
	}

	/**
	 * Creates a new AifPL which is obtained by adding the current AifPL and
	 * another one
	 * 
	 * @return
	 */
	public AifPL add(AifPL other) {
		AifPL retval = new AifPL("(" + name + " + " + other.name + ")");
		for (List<ParameterizedLogistic> pls : terms) {
			List<ParameterizedLogistic> plnew = new ArrayList<>();
			plnew.addAll(pls);
			retval.terms.add(plnew);
		}
		for (List<ParameterizedLogistic> plsOther : other.terms) {
			List<ParameterizedLogistic> plnew = new ArrayList<>();
			plnew.addAll(plsOther);
			retval.terms.add(plnew);
		}
		return retval;
	}

	/**
	 * Returns the value of the AIF for a specific set of parameters.
	 * 
	 * The parameters are passed in a map which maps the label in the functions
	 * to the value
	 * 
	 * @param values
	 * @return
	 */
	public double getValue(Map<String, Double> values) {
		double retval = 0;
		int count = 0;
		for (List<ParameterizedLogistic> pls : terms) {
			double valueTerm = 1;
			for (ParameterizedLogistic pl : pls) {
				double t = values.get(pl.getLabel());
				valueTerm = valueTerm * pl.getValue(t);
			}
			retval += valueTerm;
		}
		return retval;
	}

	/**
	 * Returns the value of the AIF for a specific set of parameters.
	 * 
	 * The number of the parameters passed must be exactly the number of
	 * parameters in the terms and their factors, passed in the order in which
	 * they had been created (many times this requires one to repeat the values)
	 * 
	 * 
	 * @param values
	 * @return
	 */
	public double getValue(double... values) {
		double retval = 0;
		int count = 0;
		for (List<ParameterizedLogistic> pls : terms) {
			double valueTerm = 1;
			for (ParameterizedLogistic pl : pls) {
				double t = values[count++];
				valueTerm = valueTerm * pl.getValue(t);
			}
			retval += valueTerm;
		}
		return retval;
	}

	/**
	 * Adds a new term to the AIF
	 * 
	 * @param plarray
	 *            - the factors of the term
	 */
	public void addTerm(ParameterizedLogistic... plarray) {
		List<ParameterizedLogistic> pls = new ArrayList<>();
		for (ParameterizedLogistic pl : plarray) {
			pls.add(pl);
		}
		terms.add(pls);
		parameterCount += plarray.length;
	}

	@Override
	public String toString() {
		String collectedInputs = "";
		Formatter fmt = new Formatter();
		fmt.add("AifPL:" + name + " (" + parameterCount + " inputs)");
		fmt.indent();
		fmt.add("Formula:");
		fmt.indent();
		for (List<ParameterizedLogistic> pls : terms) {
			for (int i = 0; i != pls.size(); i++) {
				ParameterizedLogistic pl = pls.get(i);
				String plstring = pl.toStringDetailed(DTL_NAME_ONLY);
				collectedInputs += pl.getLabel();
				// first one, there will be more
				if (i == 0 && pls.size() != 1) {
					fmt.add(plstring + " * ");
					fmt.indent();
					continue;
				}
				// first one wont be more
				if (i == 0 && pls.size() == 1) {
					fmt.add(plstring);
					continue;
				}
				// in between, there will be more
				if (i < pls.size() - 1) {
					fmt.add(plstring + " * ");
					continue;
				}
				// last one of more than one
				if (i == pls.size() - 1) {
					fmt.add(plstring);
					fmt.deindent();
					continue;
				}
			}
		}
		fmt.deindent();
		fmt.add("Inputs:" + collectedInputs);
		return fmt.toString();
	}

}
