package yaes.cssm.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yaes.cssm.cssm.Scenario;
import yaes.ui.format.ToStringDetailed;
import yaes.ui.text.TextUiHelper;

/**
 * The specification of a progress graph
 * 
 * NOTE: this is used both by the old style code, and the new scenario-based
 * one. It should be gradually transitioned.
 * 
 * @author Lotzi Boloni
 * 
 */
public class ProgressGraph implements ToStringDetailed, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8505781236273681226L;

	/**
	 * Create a progress graph, based on the scenario. For the time being, we
	 * are using it to initialize the internal list of actors based on the
	 * actors in the scenario
	 * 
	 * @param scenario
	 */
	public ProgressGraph(Scenario scenario) {
		actors.addAll(scenario.getActors());
	}

	// FIXME: this should not be here, it should share the one in Scenario
	private List<String> actors = new ArrayList<>();
	private Map<String, ProgressState> nodes = new HashMap<>();

	public void addActor(String role) {
		actors.add(role);
	}

	public void addProgressState(ProgressState node) {
		nodes.put(node.getName(), node);
	}

	public ProgressState getProgressState(String name) {
		return nodes.get(name);
	}

	/**
	 * For a given current progress state and an action of type actionType
	 * performed by actor, returns the new progress state
	 * 
	 * @param currentState
	 * @param actor
	 * @param actionType
	 * @return
	 */
	public String getNextProgressState(String currentState, String actor,
			String actionType) {
		ProgressState current = getProgressState(currentState);
		return current.getNextProgressState(actor, actionType);
	}

	@Override
	public String toStringDetailed(int detailLevel) {
		StringBuffer buf = new StringBuffer();
		buf.append("Progress graph:\n");
		buf.append("   Roles:\n");
		for (String role : actors) {
			buf.append("       " + role + "\n");
		}
		for (ProgressState asn : nodes.values()) {
			String s = asn.toStringDetailed(detailLevel);
			buf.append(TextUiHelper.indent(5, s));
		}
		return buf.toString();
	}

	/**
	 * Add a transition, creating the from and to states if necessary
	 * 
	 * @param psFromName
	 * @param actionType
	 * @param actor
	 * @param psToName
	 */
	public void add(String psFromName, String actionType, String actor,
			String psToName) {
		// make sure the from state exists
		ProgressState psFrom = nodes.get(psFromName);
		if (psFrom == null) {
			psFrom = new ProgressState(psFromName);
			addProgressState(psFrom);
		}
		// make sure the destination state exists
		ProgressState psTo = nodes.get(psToName);
		if (psTo == null) {
			psTo = new ProgressState(psToName);
			addProgressState(psTo);
		}
		// now, add the transition
		psFrom.addProgress(actionType, actor, psToName);
	}
}
