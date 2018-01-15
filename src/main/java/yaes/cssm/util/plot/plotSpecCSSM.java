package yaes.cssm.util.plot;

import yaes.cssm.cssm.CSSM;
import yaes.ui.format.Formatter;
/**
 * 
 * Specification class for plotting a specific CSSM. Has pointers to 
 * the CSSM class and an externally specified string which will give the
 * label for this particular CSSM in the plot.
 *
 */
public class plotSpecCSSM {
    private String valueLabel;
    private CSSM cssm;
    /**
     * The line style which will be used for the plot. If null: use the default
     */
    private String lineStyle = null;

    public String getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(String lineStyle) {
		this.lineStyle = lineStyle;
	}

	public plotSpecCSSM(String valueLabel, CSSM cssm) {
		this.valueLabel = valueLabel;
		this.cssm = cssm;
	}

    /**
     * @return the valueLabel
     */
    public String getValueLabel() {
        return valueLabel;
    }

	public CSSM getCssm() {
		return cssm;
	}
	
	@Override
	public String toString(){
		Formatter fmt = new Formatter();
		fmt.is("Axis Label", valueLabel);
		fmt.indent();
		fmt.add(cssm.toString());	
		return fmt.toString();
	}
	
	


}
