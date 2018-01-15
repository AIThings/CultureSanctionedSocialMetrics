package yaes.cssm.scenarios.humanBargaining;

import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.cssm.SocialAgent;
import yaes.framework.simulation.AbstractContext;
import yaes.framework.simulation.SimulationInput;
import yaes.framework.simulation.SimulationOutput;
import yaes.world.physical.PhysicalWorld;
import yaes.world.physical.map.SimpleField;

public class HumanBargainingContext extends AbstractContext implements
		Constants {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7029093622399370560L;
	public Scenario scenario;
	private SimpleField field;
	private PhysicalWorld physicalWorld;

	/**
	 * Initializes the environment
	 * 
	 */
	@Override
	public void initialize(SimulationInput sip, SimulationOutput sop) {
		super.initialize(sip, sop);
		field = new SimpleField(200, 200);
		physicalWorld = new PhysicalWorld(field, sop);
		theWorld = physicalWorld;
		scenario = sbHumanBargaining.createScenario("instance1");
		SocialAgent agentBill = new SocialAgent("Bill");
		SocialAgent agentJill = new SocialAgent("Jill");
		scenario.setActorPlayedBy(sbHumanBargaining.ACTOR_SELLER, agentBill);
		scenario.setActorPlayedBy(sbHumanBargaining.ACTOR_BUYER, agentJill);
	}

}
