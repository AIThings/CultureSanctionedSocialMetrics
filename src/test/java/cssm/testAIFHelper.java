package cssm;

import org.junit.Test;

import yaes.cssm.impactfunctions.util.AIFHelper;


public class testAIFHelper {

	@Test
	public void testAifSigmoid(){
		double[] x = new double[11];
		double[] retval = new double[11];
		for (int i = 0; i < x.length; i++) {
			x[i] = i*0.1;
		}
		retval = AIFHelper.aifSigmoid(x, 2.15, 0, 1.0);
		//retval = AIFHelper.aifSigmoid(x, 0.2, -100, 1.0);
		for (int i = 0; i < retval.length; i++) {
			System.out.println("x[" + i+"]= " +x[i] + "    y["+i+"]= " + retval[i] );
		}
	}
	
	
}
