package yaes.cssm.util.plot;

import java.io.File;

import yaes.cssm.actions.Action;
import yaes.cssm.cssm.CSSM;
import yaes.cssm.cssm.ConcreteBelief;
import yaes.cssm.cssm.ConcreteBeliefValue;
import yaes.cssm.cssm.SocialAgent;
import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.ui.text.TextUi;
import yaes.util.SaveLoadUtil;

/**
 * The result set object allows the access to historical values of CSSMs and
 * CBs.
 * 
 * Basically, one should pass it a dirCheckpoints, and then it can take it from
 * there.
 * 
 * @author Lotzi
 * 
 */
public class ResultSet {

	private File dirCheckpoints;

	/**
	 * Creates a result set by showing the directory where the checkpoint files had been saved.
	 * @param dirNameCheckpoints
	 */
	public ResultSet(String dirNameCheckpoints) {
		dirCheckpoints = new File(dirNameCheckpoints);
	}
	
	
	/**
	 * Returns the context for a specific timepoint
	 * 
	 * @param timePoint
	 * @return
	 */
	private SocialCalculusContext getContext(int timePoint) {
		File file = SocialCalculusContext.getCheckpointFile(timePoint, dirCheckpoints);		
		SaveLoadUtil<SocialCalculusContext> slu = new SaveLoadUtil<>();
		SocialCalculusContext scc = slu.load(file);
		return scc;
	}
	
	/**
	 * Returns the maximum time: to be done by the existence of the file
	 * 
	 * @return
	 */
	public int getMaxTime() {
		int retval = 0;
		int i = 0;
		while(true) {
			File file = SocialCalculusContext.getCheckpointFile(i, dirCheckpoints);
			if (file.exists()) {
				retval = i;
			} else {
				return retval;
			}
			i++;
		}
	}

	
	
	/**
	 * Returns the value of a CSSM at a given time
	 * 
	 * @param timePoint
	 * @param cssm
	 * @return
	 */
	public double getCssmValue(int timePoint, CSSM cssm) {
		SocialCalculusContext scc = getContext(timePoint);		
		SocialAgent sa = scc.agents.get(cssm.getEstimatorAgent());
		return sa.getCSSMValue(cssm).getValue();
	}
	
	/**
	 * Returns the value of the CB at a given time
	 * @param timePoint
	 * @param cb
	 * @return
	 */
	public double getCbValue(int timePoint, ConcreteBelief cb) {
		SocialCalculusContext scc = getContext(timePoint);		
		SocialAgent sa = scc.agents.get(cb.getEstimatorAgent());
		ConcreteBeliefValue cbv = sa.getCBValue(cb);
		if (cbv == null) {
			TextUi.errorPrint("getCbValue: Could not find: " + cb.toString());
			// FIXME: not god idea, this should have been initialized!!!
			//return 0;
		}
		return cbv.getValue();
	}


	/**
	 * Returns the action performed at a specific moment in time
	 * @param timePoint
	 * @return
	 */
	public Action getHistoryAction(int timePoint) {
		SocialCalculusContext scc = getContext(timePoint);		
		Action action = scc.currentAction;
		return action;
	}

}
