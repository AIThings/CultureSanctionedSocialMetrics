package yaes.cssm.util.plot;

import yaes.cssm.cssm.ConcreteBelief;
import yaes.ui.format.Formatter;

/**
 * Plot specification class for a specific concrete belief (CB)
 * 
 * Has pointers for a specific CB and has labels for the agent which owns it and
 * a specific label which is to be used in the plot
 * 
 * @author Taranjeet
 * 
 */
public class plotSpecCB {
	private String valueLabel;
	private ConcreteBelief cb;
	/**
	 * The lines style in matlab for the line (keep it null to keep the default)
	 */
	private String lineStyle = null;


	public String getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(String lineStyle) {
		this.lineStyle = lineStyle;
	}

	public plotSpecCB(String valueLabel, ConcreteBelief cbName) {
		super();
		this.valueLabel = valueLabel;
		this.cb = cbName;
	}

	public String getValueLabel() {
		return valueLabel;
	}

	public ConcreteBelief getCb() {
		return cb;
	}

	@Override
	public String toString() {
		Formatter fmt = new Formatter();
		fmt.is("Axis Label", valueLabel);
		fmt.indent();
		fmt.add(cb.toString());
		return fmt.toString();

	}

}
