package yaes.cssm.cssm;

import java.io.Serializable;

import yaes.ui.format.Formatter;

/**
 * This class encapsulates the concrete belief calculation
 * 
 * It should return a single value, representing the belief, but for different
 * representational methods, it also needs to contain some of the internal
 * representations.
 * 
 * For instance, for Dempster Shafer this would be the mass function
 * 
 * For particle filters, it would be the collection of particles.
 * 
 * @author Taranjeet
 * 
 */
public class ConcreteBeliefValue implements Serializable {

	public enum CbvType {
		STATIC_VALUE, DEMPSTER_SHAFER
	};

	private static final long serialVersionUID = 3278505920196615969L;
	private CbvType cbvType;
	private double value;
	private CbDempsterShafer cbDempsterShafer;

	public CbDempsterShafer getCbDempsterShafer() {
		return cbDempsterShafer;
	}

	/**
	 * Creates a concrete belief value of the static value type
	 * 
	 * @param value
	 * @param massFunctions
	 */
	public ConcreteBeliefValue(double value) {
		this.cbvType = CbvType.STATIC_VALUE;
		this.value = value;
	}

	/**
	 * Creates a concrete belief value of the Dempster-Shafer mass function type
	 * 
	 * @param value
	 * @param massFunctions
	 */
	public ConcreteBeliefValue(CbDempsterShafer cbDempsterShafer) {
		this.cbvType = CbvType.DEMPSTER_SHAFER;
		this.cbDempsterShafer = cbDempsterShafer;
	}

	/**
	 * Get the belief value
	 * 
	 * FIXME: refactor it to belief!!!
	 * 
	 * @return
	 */
	public double getValue() {
		switch (cbvType) {
		case STATIC_VALUE:
			return value;
		case DEMPSTER_SHAFER:
			return cbDempsterShafer.getBelief();
		}
		return -1;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String retval = Formatter.fmt(getValue()) + cbDempsterShafer;
		return retval;
	}

	
	/**
	 * A new evidence is combined with this value. The new ConcreteBeliefValue is returned.
	 * 
	 * @param evidence
	 * @return
	 */
	public ConcreteBeliefValue applyEvidence(CbDempsterShafer evidence) {
		if (cbvType != CbvType.DEMPSTER_SHAFER) {
			throw new Error(
					"Only a Dempster-Shafer type ConcreteBeliefValue can accept this type of evidence");
		}
		CbDempsterShafer cbds = cbDempsterShafer.applyEvidence(evidence);
		return new ConcreteBeliefValue(cbds);
	}

}
