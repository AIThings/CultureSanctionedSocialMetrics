package yaes.cssm.cssm;

import java.io.Serializable;

import yaes.ui.format.Formatter;


/**
 * This class encapsulates the (changing) value of a CSSM. 
 * For the time being, we go with the assumption that CSSM is a simple
 * double value
 * 
 * @author Lotzi
 *
 */
public class CSSMValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5865917626346773224L;
	private double value;
	private double rangeLow;
	private double rangeHigh;
	private double defaultValue;

	
	public CSSMValue(double rangeLow, double rangeHigh, double defaultValue){
		this.value = defaultValue;
		this.defaultValue = defaultValue;
		this.rangeLow = rangeLow;
		this.rangeHigh = rangeHigh;
	}
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	
	
	public void setValue(double value) {
		this.value = value;
	}
	
	
	
	
	
	/**
	 * Normalize value between rangeLow and rangeHigh.
	 * <p>
	 * (return (value > rangeHigh) ? rangeHigh: value)
	 * <p>
	 * (return (value < rangeLow) ? rangeLow: value)
	 * 
	 * @param value
	 * @return value
	 */
	public double normalizeBounds(double value) {
		if (value < rangeLow) {
			return rangeLow;
		}
		if (value > rangeHigh) {
			return rangeHigh;
		}
		return value;
	}

	/**
	 * Normalize value between 0 and 1
	 * <p>
	 * return (value - rangeLow) / (rangeHigh - rangeLow)
	 * @param value
	 * @return value
	 */
	public double normlize(double value) {
		return ((value - rangeLow) / (rangeHigh - rangeLow));

	}
	
	
	
	public double getRangeLow() {
		return rangeLow;
	}
	public void setRangeLow(double rangeLow) {
		this.rangeLow = rangeLow;
	}
	public double getRangeHigh() {
		return rangeHigh;
	}
	public void setRangeHigh(double rangeHigh) {
		this.rangeHigh = rangeHigh;
	}
	public void setDefaultValue(double defaultValue) {
		this.defaultValue = defaultValue;
	}
	public double getDefaultValue(){
		return this.defaultValue;
	}
	
	/**
	 * Formatting the value of the CSSM
	 */
	@Override
	public String toString() {
		return Formatter.fmt(value);
	}
	
}
