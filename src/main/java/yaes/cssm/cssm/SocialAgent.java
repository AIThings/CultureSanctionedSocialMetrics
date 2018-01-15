package yaes.cssm.cssm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yaes.ui.format.Formatter;
import yaes.ui.format.ToStringDetailed;

/**
 * This class represents a social agent acting in a social scenario, and having
 * a set of values and beliefs.
 * 
 * The social agent might participate in multiple scenarios at the same time,
 * possibly acting as different type of actors/roles in each of them.
 * 
 * For instance a vendor dealing with multiple clients will be represented by a
 * single social agent, who is acting in multiple scenarios.
 * 
 * 
 * @author Taranjeet
 * 
 */
public class SocialAgent implements Serializable, ToStringDetailed {

	private static final long serialVersionUID = 3027363463523483289L;
	/**
	 * The name of the social agent
	 */
	private String name;
	/**
	 * The CSSMs owned by the social agent
	 */
	private Map<CSSM, CSSMValue> cssms = new HashMap<>();
	/**
	 * The ConcreteBeliefs own
	 */
	private Map<ConcreteBelief, ConcreteBeliefValue> cbs = new HashMap<>();

	public SocialAgent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * These variables are need for Reinforcement learning
	 * ========================
	 */
	/**
	 * Remembering previous CSSMs and CBs value, also called as Old Features
	 */

	private FeatureList featureOld = new FeatureList();
	/**
	 * weights for each CSSMs and CBs
	 */
	private FeatureList weights = new FeatureList();

	/**
	 * ========================
	 */

	/**
	 * Initializes the CSSM and CB values
	 * 
	 * @param cssms
	 * @param cbs
	 */
	public void initializeValues(Map<CSSM, CSSMValue> cssmsInit,
			Map<ConcreteBelief, ConcreteBeliefValue> cbsInit) {

		if (cssmsInit != null) {
			for (CSSM cssm : cssmsInit.keySet()) {
				cssms.put(cssm, cssmsInit.get(cssm));
			}
		}
		if (cbsInit != null) {
			for (ConcreteBelief cb : cbsInit.keySet()) {
				cbs.put(cb, cbsInit.get(cb));
			}
		}

	}

	/**
	 * Sets a CSSM to a specific value - by changing in the holder
	 * 
	 * @param cssm
	 * @param value
	 */
	public void setCSSMValue(CSSM cssm, double value) {
		CSSMValue cssmValue = cssms.get(cssm);
		cssmValue.setValue(cssmValue.normalizeBounds(value));
	}

	/**
	 * Sets a CB to a specific value
	 * 
	 * @param cssm
	 * @param value
	 */
	public void setCBValue(ConcreteBelief cb, ConcreteBeliefValue cbv) {
		cbs.put(cb, cbv);
	}

	/**
	 * Returns the current value of a certain CSSM
	 * 
	 * @param cssm
	 * @return
	 */
	public CSSMValue getCSSMValue(CSSM cssm) {
		CSSMValue cssmValue = cssms.get(cssm);
		return cssmValue;
	}

	/**
	 * Returns the current value of a certain CB
	 * 
	 * @param cssm
	 * @return
	 */
	public ConcreteBeliefValue getCBValue(ConcreteBelief cb) {
		return cbs.get(cb);
	}

	@Override
	public String toStringDetailed(int detailLevel) {
		Formatter fmt = new Formatter();
		fmt.add("SocialAgent: " + name);
		fmt.indent();
		//
		// list all the CSSMs
		//
		fmt.add("Culture sanctioned social values (CSSMs):");
		fmt.indent();
		for (CSSM cssm : cssms.keySet()) {
			fmt.is(cssm.toString(), cssms.get(cssm));
		}
		fmt.deindent();
		//
		// list all the CBs
		//
		fmt.add("Concrete beliefs (CBs):");
		fmt.indent();
		for (ConcreteBelief cb : cbs.keySet()) {
			fmt.is(cb.toString(), cbs.get(cb));
		}
		fmt.deindent();
		fmt.deindent();
		return fmt.toString();
	}

	/**
	 * Returns a list of CSSMs which are part of the private state of this
	 * social agent
	 * 
	 * @return
	 */
	public List<CSSM> getCSSMs() {
		List<CSSM> retval = new ArrayList<>();
		retval.addAll(cssms.keySet());
		return retval;
	}

	/**
	 * Returns a list of CBs which are part of the private state of this social
	 * agent
	 */
	public List<ConcreteBelief> getCBs() {
		List<ConcreteBelief> retval = new ArrayList<>();
		retval.addAll(cbs.keySet());
		return retval;
	}

	/**
	 * To set new weights of Features
	 * 
	 * @param ActionImpact
	 * @param Double
	 */
	public void setWeights(CSSM cssm, double value) {
		this.weights.setCSSMValue(cssm, value);
	}

	public void setWeights(ConcreteBelief cb, double value) {
		this.weights.setCBValue(cb, value);
	}

	/**
	 * To get weight of Features
	 * 
	 * @param cssm
	 * @return
	 */
	public double getWeight(CSSM cssm) {
		return this.weights.getCSSMValue(cssm);
	}
	public double getWeight(ConcreteBelief cb) {
		return this.weights.getCBValue(cb);
	}
	/**
	 * Save Feature values
	 * 
	 * @param cssm
	 * @param value
	 */
	public void setFeaturesOld(CSSM cssm, double value) {
		this.featureOld.setCSSMValue(cssm, value);
	}
	public void setFeaturesOld(ConcreteBelief cb, double value) {
		this.featureOld.setCBValue(cb, value);
	}

	/**
	 * Return Feature values
	 * 
	 * @param cssm
	 * @return
	 */
	public double getFeatureOld(CSSM cssm) {
			return this.featureOld.getCSSMValue(cssm);
	}
	public double getFeatureOld(ConcreteBelief cb){
		return this.featureOld.getCBValue(cb);
	}

	/**
	 * This will return following.
	 * <p>
	 * delta(T) = T_current - T_start.<br> 
	 * delta(P) = P_current - P_default <br>
	 * and so on. 
	 * 
	 * @return
	 */
	public Map<CSSM, Double> getDeltaFeatures() {
		Map<CSSM, Double> delta = new HashMap<>();
		for (CSSM cssm : this.getCSSMs()) {
			delta.put(cssm, (this.getCSSMValue(cssm).getValue() - this.cssms.get(cssm).getDefaultValue()));
		}
		return delta;
	}
	
	public double getCSSMDefaultValue(CSSM cssm){
		CSSMValue cssmValue = cssms.get(cssm);
		return cssmValue.getDefaultValue();
	}

	@Override
	public String toString() {
		return toStringDetailed(MAX_DETAIL);
	}

}
