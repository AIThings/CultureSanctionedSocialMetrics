/**
 * 
 */
package yaes.cssm.cssm;

/**
 * @author Taranjeet
 * 
 */
public interface Constants {
	
	static final String DIR_HISTORY = "DirHistory";
	static final String DIR_WEIGHTS = "DirWeights";
	
	static final String WEIGHT_FILE = "weightsLabel";

	// the file containing the precanned actions
	static final String ACTION_FILE_READ = "ActionFileRead";
	static final String ACTION_FILE_WRITE = "ActionFileWrite";
	
	
	public static final String PERCEPTION = "perception";
	// if set to != 0, perform the visualization in OpenWonderLand
	public static final String OWL_SIMULATION = "OwlSimulation";
	// if set to != 0, visualize the graph
	public static final String JFREECHART_VISUALIZATION = "JFreeChartVisualization";

	public static final String POLITENESS_PEER = "Politeness peer";
	public static final String POLITENESS_PUBLIC = "Politeness public";
	public static final String POLITENESS = "Politeness";
	public static final String DIGNITY_PEER = "Dignity peer";
	public static final String DIGNITY_PUBLIC = "Dignity public";
	public static final String DIGNITY = "Dignity";
	public static final String WORTH = "Worth";
	public static final String TIME = "Time";
	public static final String Q_GIFT = "is_a_gift";
	public static final String Q_AGREED = "has_been_a_transaction";

	public static final String SELLER_PEER = "sellerPeer";
	public static final String CLIENT_PEER = "slientPeer";

	public static final String SPANISH_STEPS = "SpanishStep";

	public static final String SELLER_CSSM = "sellerCSSM";
	public static final String SELLER_BELIEF = "sellerBelief";

	public static final String CLIENT_CSSM = "clientCSSM";
	public static final String CLIENT_BELIEF = "clientBelief";

	public static final String CROWD_BELIEF = "crowd_belief";

	public static final String SPANISH_STEPS_ACTIONS = "SpanishStepsActions";

	public static final String CULTURE_WESTERN = "Western";
	public static final String LANGUAGE_ENGLISH = "English";
	public static final String LANGUAGE_SPANISH = "Spanish";

	public static final String CULTURE_EASTERN = "GeneralEastern";
	
	public static final String R2D2 = "R2D2";
	/*
	 * personal perception of deception
	 */
	public static final String IS_DECEPTIVE = "is_a_deceptive";
	/*
	 * perception of deceptiveness by other clients in the world
	 */
	public static final String IS_DECEPTIVE_IN_WORLD = "is_deceptive_in_world";
	
	
	
	/*
	 * Market place constants
	 */
	public static final String MARKET_PLACE = "marketPlace";
	public static final String FINANCIAL = "financial";
	public static final String SECURITY = "security";
	public static final String COOPERATION = "cooperation";
	public static final String TRUST = "trust";
	public static final String FRIENDSHIP = "friendship";
	public static final String IS_A_FRIEND = "The soldier is a friend of the vendor";
	public static final String POSES_A_THREAT = "The vendor is a threat";
	
	public static final String MARKET_PLACE_ACTIONS = "MarketPlaceActions";
	/*
	 * Check point constants 
	 */
	public static final String CHECK_POINT = "CheckPoint";
	
	public static final String ACTOR_ROBOT = "Robot";
	public static final String ACTOR_PRIVATE = "Private";
	public static final String ACTOR_SERGEANT = "Sergeant";
	public static final String ACTOR_VENDOR = "Vendor";
	
	/*
	 * Give way constants
	 */
	
	public static final String ACTOR_AMERICAN = "American";
	public static final String ACTOR_INDIAN = "Indian";
	public static final String WIMPINESS = "Wimpiness";
	public static final String CULTURE_AMERICAN = "General American";
	public static final String CULTURE_INDIAN = "General Indian";
	public static final String CULTURE_MIXED = "Mixed Culture";
	
	public enum ClientType {
		EASY, ARROGANT, BUSY, SMART, WEALTHY, ;

	};
	
	
	
}
