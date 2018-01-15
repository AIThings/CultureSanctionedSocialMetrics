package yaes.cssm.scenarios.spanishstepsMultiscenario;

import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.SocialCalculusContext;

/**
 * 
 * @author Taranjeet
 * 
 */
public class SpanishStepsContext extends SocialCalculusContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7693427317336853171L;

	@Override
	protected void initScenarioSet() {
		// create the agents here, agents can be shared among the scenarios
				SocialAgent agentJohn = new SocialAgent("John");
				agents.put(agentJohn.getName(), agentJohn);

				SocialAgent agentMike = new SocialAgent("Mike");
				agents.put(agentMike.getName(), agentMike);

				SocialAgent agentEric = new SocialAgent("Eric");
				agents.put(agentEric.getName(), agentEric);

				SocialAgent agentBill = new SocialAgent("Bill");
				agents.put(agentBill.getName(), agentBill);

				SocialAgent agentJolly = new SocialAgent("Jolly");
				agents.put(agentJolly.getName(), agentJolly);
				
				
				SocialAgent agentCrowd = new SocialAgent("CrowdOfOnlookers");
				agents.put(agentCrowd.getName(), agentCrowd);
				
				Scenario scenario1 = SpanishStepsHelper.initializeScenario("one", agentBill, agentJohn,agentJolly, agentCrowd);
				scenarioSet.addScenario(scenario1);
				
				Scenario scenario2 = SpanishStepsHelper.initializeScenario("two", agentBill, agentMike,agentJolly, agentCrowd);
				scenarioSet.addScenario(scenario2);
				
				Scenario scenario3 = SpanishStepsHelper.initializeScenario("three", agentBill, agentEric,agentJolly, agentCrowd);
				scenarioSet.addScenario(scenario3);
				
		
	}
	
	
	

//	private static final long serialVersionUID = 2693495311006924830L;
//	private Map<String, ProgressGraphInstance> asgiMulti = new HashMap<String, ProgressGraphInstance>();
//
//	private SimpleField field;
//	private Map<String, SocialAgent> pairedClient = new HashMap<>();
//	private Map<String, SocialAgent> pairedSeller = new HashMap<>();
//	private Map<String, String> pairedSellerClient = new HashMap<>();
//
//	private PhysicalWorld physicalWorld;
//	public OldSocialScenario socialScenario;
//	
//
//	/**
//	 * @return the asgiMulti
//	 */
//	public ProgressGraphInstance getAsgiMulti(String agentpair) {
//		return asgiMulti.get(agentpair);
//	}
//
//	/**
//	 * @return the pairedClient
//	 */
//	public SocialAgent getPairedClient(String pair) {
//		return pairedClient.get(pair);
//	}
//
//	/**
//	 * @return the pairedSeller
//	 */
//	public SocialAgent getPairedSeller(String seller) {
//		return pairedSeller.get(seller);
//	}
//
//	/**
//	 * @param sellerClient
//	 * @return the pairedSellerClient
//	 */
//	public String getPairedSellerClient(String sellerClient) {
//		return pairedSellerClient.get(sellerClient);
//	}
//
//	/**
//	 * @return the totalClients
//	 */
//	public List<String> getTotalClients() {
//		return socialScenario.totalClients;
//	}
//
//	/**
//	 * @return the totalSellers
//	 */
//	public List<String> getTotalSellers() {
//		return socialScenario.totalSellers;
//	}
//
//	/**
//	 * Initializes the environment
//	 * 
//	 * @throws FileNotFoundException
//	 * 
//	 */
//	@Override
//	public void initialize(SimulationInput sip, SimulationOutput sop) {
//		super.initialize(sip, sop);
//		field = new SimpleField(50, 50);
//		physicalWorld = new PhysicalWorld(field, sop);
//		theWorld = physicalWorld;
//		socialScenario = new OldSocialScenario(theWorld);
//		socialScenario.sass = SpanishStepsHelper.getActions();
//
//		// the client
//		ActionValueImpactMatrix ImpactMatrix = SpanishStepsHelper
//				.getClientImpactMatrix(socialScenario.sass,
//						SpanishStepsHelper.getClientCSSMset());
//		ActionBeliefImpactMatrix BeliefMatrxix = SpanishStepsHelper
//				.getClientBeliefMatrix(socialScenario.sass,
//						SpanishStepsHelper.getClientCBSet(),
//						PerceptionImpactFunction.initializeMassFunctions());
//
//		SocialAgent agent = new SocialAgent("john(client)", 
//				SpanishStepsHelper.getClientCSSMset(),
//				SpanishStepsHelper.getClientCBSet(), ImpactMatrix,
//				BeliefMatrxix);
//
//		socialScenario.agents.put(agent.getName(), agent);
//		socialScenario.totalClients.add(agent.getName());
//
//		// the client
//		ImpactMatrix = SpanishStepsHelper.getClientImpactMatrix(socialScenario.sass,
//				SpanishStepsHelper.getClientCSSMset());
//		BeliefMatrxix = SpanishStepsHelper.getClientBeliefMatrix(socialScenario.sass,
//				SpanishStepsHelper.getClientCBSet(),
//				PerceptionImpactFunction.initializeMassFunctions());
//
//		agent = new SocialAgent("mike(client)", 
//				SpanishStepsHelper.getClientCSSMset(),
//				SpanishStepsHelper.getClientCBSet(), ImpactMatrix,
//				BeliefMatrxix);
//
//		socialScenario.agents.put(agent.getName(), agent);
//		socialScenario.totalClients.add(agent.getName());
//
//		// the client
//		ImpactMatrix = SpanishStepsHelper.getClientImpactMatrix(socialScenario.sass,
//				SpanishStepsHelper.getClientCSSMset());
//		BeliefMatrxix = SpanishStepsHelper.getClientBeliefMatrix(socialScenario.sass,
//				SpanishStepsHelper.getClientCBSet(),
//				PerceptionImpactFunction.initializeMassFunctions());
//
//		agent = new SocialAgent("eric(client)", 
//				SpanishStepsHelper.getClientCSSMset(),
//				SpanishStepsHelper.getClientCBSet(), ImpactMatrix,
//				BeliefMatrxix);
//
//		socialScenario.agents.put(agent.getName(), agent);
//		socialScenario.totalClients.add(agent.getName());
//
//		// the sellers
//		ImpactMatrix = SpanishStepsHelper.getSellerImpactMatrix(socialScenario.sass,
//				SpanishStepsHelper.getSellerCSSMset());
//		BeliefMatrxix = SpanishStepsHelper.getSellerBeliefMatrix(socialScenario.sass,
//				SpanishStepsHelper.getSellerCBSet(),
//				PerceptionImpactFunction.initializeMassFunctions());
//
//		agent = new SocialAgent("bill(seller)", 
//				SpanishStepsHelper.getSellerCSSMset(),
//				SpanishStepsHelper.getSellerCBSet(), ImpactMatrix,
//				BeliefMatrxix);
//
//		socialScenario.agents.put(agent.getName(), agent);
//		socialScenario.totalSellers.add(agent.getName());
//
//		// seller
//		ImpactMatrix = SpanishStepsHelper.getSellerImpactMatrix(socialScenario.sass,
//				SpanishStepsHelper.getSellerCSSMset());
//		BeliefMatrxix = SpanishStepsHelper.getSellerBeliefMatrix(socialScenario.sass,
//				SpanishStepsHelper.getSellerCBSet(),
//				PerceptionImpactFunction.initializeMassFunctions());
//
//		agent = new SocialAgent("steve(seller)", 
//				SpanishStepsHelper.getSellerCSSMset(),
//				SpanishStepsHelper.getSellerCBSet(), ImpactMatrix,
//				BeliefMatrxix);
//
//		socialScenario.agents.put(agent.getName(), agent);
//		socialScenario.totalSellers.add(agent.getName());
//
//		int count = 1;
//		for (String seller : socialScenario.totalSellers) {
//
//			for (String client : socialScenario.totalClients) {
//
//				String pair = "pair" + count;
//				socialScenario.asg = SpanishStepsHelper.createActionStateGraph(seller, client);
//				socialScenario.agsi = new ProgressGraphInstance(socialScenario.asg, "S0", socialScenario.sass);
//
//				agent = socialScenario.agents.get(seller);
//				agent.setAgentPairAvailable(client);
//				asgiMulti.put(pair, socialScenario.agsi);
//				pairedSeller.put(pair, socialScenario.agents.get(seller));
//				pairedClient.put(pair, socialScenario.agents.get(client));
//				pairedSellerClient.put(seller + client, pair);
//
//				count++;
//			}
//		}
//
//		// the social perception
//		BeliefMatrxix = SpanishStepsHelper.getPerceptionImpactMatrix(socialScenario.sass,
//				SpanishStepsHelper.getPerceptionValues(),
//				PerceptionImpactFunction.initializeMassFunctions());
//
//		agent = new SocialAgent(ACTOR_CROWD, null,
//				SpanishStepsHelper.getPerceptionValues(), null, BeliefMatrxix);
//		socialScenario.agents.put(agent.getName(), agent);
//
//		socialScenario.initHistory();
//		if (sip.getParameterString(ACTION_FILE_WRITE).isEmpty()) {
//		    socialScenario.preplannedActions = ParseActions.getActionList(
//					sip.getParameterString(ACTION_FILE_READ), socialScenario.agsi);
//		}
//
//	}
//
//	public void setAgsi(ProgressGraphInstance agsi) {
//		this.socialScenario.agsi = agsi;
//	}
//
//	/**
//	 * @param asgiMulti
//	 *            the asgiMulti to set
//	 */
//	public void setAsgiMulti(String agentpair, ProgressGraphInstance asgi) {
//		this.asgiMulti.put(agentpair, asgi);
//	}
//
//	/**
//	 * @param currentClient
//	 *            the currentClient to set
//	 */
//	public void setCurrentClient(String currentClient) {
//		this.socialScenario.currentClient = currentClient;
//	}
//
//	/**
//	 * 
//	 * @param currentSeller
//	 */
//	public void setCurrentSeller(String currentSeller) {
//		this.socialScenario.currentSeller = currentSeller;
//	}
//
//	/**
//	 * @param pair
//	 * @param client
//	 * @param pairedClient
//	 *            the pairedClient to set
//	 */
//	public void setPairedClient(String pair, SocialAgent client) {
//		this.pairedClient.put(pair, client);
//	}
//
//	/**
//	 * @param pair
//	 * @param seller
//	 * @param pairedSeller
//	 *            the pairedSeller to set
//	 */
//	public void setPairedSeller(String pair, SocialAgent seller) {
//		this.pairedSeller.put(pair, seller);
//	}
//
//	/**
//	 * @param sellerClient
//	 * @param pairname
//	 * @param pairedSellerClient
//	 *            the pairedSellerClient to set
//	 */
//	public void setPairedSellerClient(String sellerClient, String pairname) {
//		this.pairedSellerClient.put(sellerClient, pairname);
//	}
//
//	
//
//	

	

	
	

}
