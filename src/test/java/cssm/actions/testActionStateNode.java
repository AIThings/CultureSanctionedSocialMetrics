package cssm.actions;


import java.util.Arrays;

import org.junit.Test;

import yaes.cssm.actions.ProgressState;
import yaes.ui.text.TextUi;
/**
 * ActionStateNode class test
 * @author Taranjeet
 *
 */
public class testActionStateNode {
	

	@Test
	public void testSimple() {
		ProgressState asn = new ProgressState("s1");
		asn.addProgress("a1", "client", "s2");
		asn.addProgress("a2", "client", "s3");
		asn.addIdenticalProgressForAllActors("a3", Arrays.asList("seller", "client"), "s4");
		
		TextUi.println(asn.toString());
	}
	

}
