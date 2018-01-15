package yaes.cssm.scenarios.spanishstepsMultiscenario;


/**
 * 
 * @author Taranjeet This class return action state graph instance for active
 *         agents such as it return the ASGInstance of current seller and
 *         current client So that next state can be find out according to
 *         selection of appropriate action
 * 
 */

public class HumanPlayerTextUi {
	
	
//	public static Action chooseAction(SpanishStepsContext context) {
//
//		ProgressGraphInstance agsi = context.socialScenario.getAgsi();
//
//		if (agsi == null) {
//			Action sa = null;
//			return sa;
//		}
//
//		TextUi.println("Current state:" + agsi.getCurrentState());
//		Set<String> roles = agsi.getRolesWhichCanAct();
//		/*
//		 * if (roles.size() != 1) { throw new Error(
//		 * "We assume that only one role can act, here it is not the case" +
//		 * roles); }
//		 */
//
//		// TextUi.println("Select your role:");
//		List<String> roleItems = new ArrayList<String>();
//		for (String role : roles) {
//			roleItems.add(role);
//		}
//		// int roleId = TextUi.menu(roleItems, 0, "Choice: ");
//		String role = roleItems.get(0); // String role =
//										// roles.iterator().next();
//		TextUi.println("Your are " + role + " choose your next action");
//		List<String> choices = new ArrayList<String>();
//		choices.addAll(agsi.getActionsForRole(role));
//		Collections.sort(choices);
//		List<String> menuItems = new ArrayList<String>();
//		for (String action : choices) {
//			menuItems.add(action
//					+ " - "
//					+ agsi.getSasSet().getAction(action)
//							.getDescription());
//		}
//		int actionId = TextUi.menu(menuItems, 0, "Choice: ");
//		String action = choices.get(actionId);
//		ActionType sas = agsi.getSasSet().getAction(action);
//		Action sa = null;
//		List<Double> detailValues = new ArrayList<Double>();
//		for (int i = 0; i != sas.getParameters().size(); i++) {
//			double value = TextUi.inputDouble(sas.getParameters().get(i), sas
//					.getDefaultValues().get(i));
//			detailValues.add(value);
//		}
//		String roleTo = null;
//		if (role.contains("client")) {
//			roleTo = context.socialScenario.getCurrentSeller();
//
//		} else {
//			roleTo = context.socialScenario.getCurrentClient();
//		}
//
//		sa = new Action(role, roleTo, sas, detailValues);
//		return sa;
//	}
}
