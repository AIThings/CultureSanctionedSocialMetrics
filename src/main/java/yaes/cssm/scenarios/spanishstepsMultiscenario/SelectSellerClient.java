package yaes.cssm.scenarios.spanishstepsMultiscenario;



/**
 * 
 * @author Taranjeet
 *	This is called in update function for returning 
 *	action state graph instance
 */


public class SelectSellerClient {
//
//	public static ProgressGraphInstance chooseASGI(SpanishStepsContext context){
//		String role;
//		String sellerRole;
//		String clientRole;
//		
//		List<String> roleItems = new ArrayList<String>();
//
//		sellerRole = context.socialScenario.getCurrentSeller();
//		clientRole = context.socialScenario.getCurrentClient();
//
//		if (!(sellerRole == null)) {
//			if (context.socialScenario.getAgent(sellerRole).isEndedTransaction()) {
//				context.socialScenario.getAgent(sellerRole).setEndedTransaction(false);
//				context.socialScenario.getAgent(sellerRole).getAgentPairAvailable()
//						.remove(clientRole);
//				context.socialScenario.getAgent(sellerRole).getAgentPairfinishedTalk()
//						.add(clientRole);
//
//			}
//			if (context.socialScenario.getAgent(clientRole).isEndedTransaction()) {
//				context.socialScenario.getAgent(clientRole).setEndedTransaction(false);
//				context.socialScenario.getAgent(sellerRole).getAgentPairAvailable()
//						.remove(clientRole);
//				context.socialScenario.getAgent(sellerRole).getAgentPairfinishedTalk()
//						.add(clientRole);
//
//			}
//
//			if (!context.socialScenario.getAgent(sellerRole).isHaveClientOrSeller()) {
//				clientRole = null;
//				context.socialScenario.getAgent(sellerRole).setHaveClientOrSeller(true);
//				
//			}
//
//		}
//
//		String pairname = context
//				.getPairedSellerClient(sellerRole + clientRole);
//		ProgressGraphInstance agsi = context.getAsgiMulti(pairname);
//
//		if (sellerRole == null & clientRole == null) {
//			TextUi.println("Select your role:");
//			int roleId = TextUi.menu(context.getTotalSellers(), 0, "Choice: ");
//			role = context.getTotalSellers().get(roleId);
//			context.setCurrentSeller(role);
//
//			TextUi.println("Current role: " + context.socialScenario.getCurrentSeller());
//			sellerRole = role;
//
//			TextUi.println("Select your client:");
//
//			// roleId = TextUi.menu(context.clientList, 0, "Choice: ");
//			roleItems = context.socialScenario.getAgent(sellerRole).getAgentPairAvailable();
//
//			roleId = TextUi.menu(roleItems, 0, "Choice: ");
//			String role1 = context.socialScenario.getAgent(sellerRole)
//					.getAgentPairAvailable().get(roleId);
//			context.setCurrentClient(role1);
//			TextUi.println("Current client: " + context.socialScenario.getCurrentClient());
//			clientRole = role1;
//			String pairName = context.getPairedSellerClient(sellerRole
//					+ clientRole);
//			agsi = context.getAsgiMulti(pairName);
//			context.setAgsi(agsi);
//			// TextUi.println("Current state:" + agsi.getCurrentState());
//
//			context.socialScenario.getAgent(sellerRole).setHaveClientOrSeller(true);
//			context.socialScenario.getAgent(clientRole).setHaveClientOrSeller(true);
//		} else if (!(sellerRole == null) & clientRole == null) {
//			role = sellerRole;
//
//			List<String> availableClient = context.socialScenario.getAgent(role)
//					.getAgentPairAvailable();
//			if (availableClient.isEmpty()) {
//				return agsi;
//
//			}
//			TextUi.println("Select your client:");
//			int roleId = TextUi.menu(context.socialScenario.getAgent(role)
//					.getAgentPairAvailable(), 0, "Choice: ");
//			String role1 = context.socialScenario.getAgent(role).getAgentPairAvailable()
//					.get(roleId);
//			context.setCurrentClient(role1);
//			TextUi.println("Current client: " + context.socialScenario.getCurrentClient());
//			clientRole = role1;
//			String pairName = context.getPairedSellerClient(role + clientRole);
//			agsi = context.getAsgiMulti(pairName);
//			context.setAgsi(agsi);
//			// TextUi.println("Current state:" + agsi.getCurrentState());
//
//			context.socialScenario.getAgent(sellerRole).setHaveClientOrSeller(true);
//			context.socialScenario.getAgent(clientRole).setHaveClientOrSeller(true);
//
//		} else if (!(sellerRole == null) & !(clientRole == null)) {
//			context.setAgsi(agsi);
//			context.socialScenario.getAgent(sellerRole).setHaveClientOrSeller(true);
//			context.socialScenario.getAgent(clientRole).setHaveClientOrSeller(true);
//
//		}
//
//		return context.socialScenario.getAgsi();
//		
//	}
	
}
