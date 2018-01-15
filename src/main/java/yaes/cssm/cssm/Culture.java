/**
 * 
 */
package yaes.cssm.cssm;

import java.util.HashMap;

import yaes.ui.format.Formatter;
import yaes.ui.format.ToStringDetailed;

/**
 * 
 * This class defines the name of culture, its language and specifies its rules
 * 
 * @author Taranjeet
 * 
 */
@Deprecated
public class Culture implements ToStringDetailed {

	private String name;
	private String language = "English";

	// Don't know about the rules yet
	private HashMap<String, String> rules;

	/**
	 * @param name
	 *            of culture
	 */
	public Culture(String name, String language) {
		super();
		this.name = name;
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return toStringDetailed(MAX_DETAIL);
	}

	@Override
	public String toStringDetailed(int detailLevel) {
		Formatter fmt = new Formatter();
		fmt.add("Culture: " + name);
		if (detailLevel == DTL_NAME_ONLY) {
			return fmt.toString();
		}
		fmt.indent();
		fmt.is("Language", language);
		return fmt.toString();
	}

	public String getRules(String name) {
		return rules.get(name);
	}

	public void setRules(HashMap<String, String> rules) {
		this.rules = rules;
	}

}
