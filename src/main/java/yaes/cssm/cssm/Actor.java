package yaes.cssm.cssm;

import java.io.Serializable;

import yaes.ui.format.ToStringDetailed;

/**
 * The actor represents a certain role in social scenario, which can be played
 * by a particular social agent.
 * 
 * @author Taranjeet
 * 
 */
public class Actor implements ToStringDetailed, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3456032833919504056L;
	private String name;
	private SocialAgent playedBy;

	public Actor(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public SocialAgent getPlayedBy() {
		return playedBy;
	}

	public void setPlayedBy(SocialAgent playedBy) {
		this.playedBy = playedBy;
	}

	@Override
	public String toStringDetailed(int detailLevel) {
		String retval = "Actor: " + name;
		if (playedBy == null) {
			retval += " << not assigned to social agent>>";
		} else {
			retval += " playedBy: " + playedBy.getName();
		}
		return retval;
	}

}
