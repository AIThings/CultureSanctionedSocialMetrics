package yaes.cssm.scenarios.marketplace;


/**
 * Text based actions for the Spanish steps UI
 * 
 * @author Lotzi Boloni
 * 
 */
public class HumanPlayerTextUi {

//	/**
//	 * Allows a human user to choose an action
//	 */
//	public static Action chooseAction(ProgressGraphInstance agsi) {
//		TextUi.println("Current state:" + agsi.getCurrentState());
//		Set<String> roles = agsi.getRolesWhichCanAct();		
//		TextUi.println("Select your role:");
//		List<String> roleItems = new ArrayList<String>();
//		for (String role : roles){
//			roleItems.add(role);
//		}
//		int roleId = TextUi.menu(roleItems, 0, "Choice: ");
//		String role = roleItems.get(roleId); //		String role = roles.iterator().next();
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
//		ActionType sas = agsi.getSasSet()
//				.getAction(action);
//		Action sa = null;
//		List<Double> detailValues = new ArrayList<Double>();
//		for(int i = 0; i!= sas.getParameters().size(); i++) {
//			double value = TextUi.inputDouble(sas.getParameters().get(i),
//					sas.getDefaultValues().get(i));
//			detailValues.add(value);			
//		}		
//		sa = new Action(role, sas, detailValues);
//		return sa;
//	}

	
	
}
