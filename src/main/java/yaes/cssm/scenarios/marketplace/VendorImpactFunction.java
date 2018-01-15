package yaes.cssm.scenarios.marketplace;

import java.util.List;

import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.impactfunctions.IImpactFunction;
import yaes.cssm.impactfunctions.IValueSource;



/**
 * Implements the seller impact functions
 * @author Saad Khan
 *
 */
public class VendorImpactFunction implements IImpactFunction, Constants {

	@Override
	public double getImpact(Scenario scenario, List<Double> values,
			IValueSource valueSource) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getImpact(Scenario scenario, List<Double> values,
			IValueSource valueSource, String agentName) {
		// TODO Auto-generated method stub
		return 0;
	}

//	public enum VIFType {Financial_A1, DIGNITY_A6, DIGNITY_A9, POLITENESS_PUBLIC_A2, S4_A3, S5_A1, S5_A2, S5_A4, POLITENESS_PEER_A6, S6_A5, S8_A5, S8_A6, DIGNITY_A11, POLITENESS_PEER_A9, POLITENESS_PEER_A11};
//	
//	private VIFType type;
//	
//	public VendorImpactFunction(VIFType type) {
//		this.type = type;
//	}
//	
//	@Override
//	
//	public double getImpact(List<Double> values, IValueSource valueSource,
//			String agentName) {
//		double MitigatedSpeech = 0; 
//		double loudness = 0; 
//		double offensiveness = 0; 
//		
//		
//		if(values.size() == 3){
//			MitigatedSpeech = values.get(0);
//			loudness = values.get(1);
//			offensiveness = values.get(2);
//		}
//		
//		if(values.size() == 2){
//			MitigatedSpeech = values.get(0);
//			loudness = values.get(1);
//		}
//		
//		if(values.size() == 1){
//			MitigatedSpeech = values.get(0);
//		}
//		
//		
//		switch(type) {
//		
//		// Location * Economic Activity * Business hours left
//		// S1_A1
//		case Financial_A1: return -MitigatedSpeech * loudness * offensiveness;
//		
//		// Mitigated Speech + Loudness + Offensiveness
//		// S3_A6
//		case DIGNITY_A6: //return   -(MitigatedSpeech + loudness + offensiveness);
//			return -1 * CanonicalImpactFunctions.Heaviside(MitigatedSpeech-4) * Math.exp(MitigatedSpeech/3); 
//			
//			
//		// Mitigated Speech + Loudness + Offensiveness
//		//S3_A9
//		case DIGNITY_A9: 
//			return -1 * CanonicalImpactFunctions.Heaviside(MitigatedSpeech-4) * Math.exp(MitigatedSpeech/3); 
//			
//		case DIGNITY_A11:
//			double oldValue =  valueSource.getCSSMValue(agentName, new CSSM(MARKET_PLACE, DIGNITY, VENDOR, VENDOR, VENDOR, 0, 100,
//					50));
//			if(values.get(0) > 24)
//					return 50 - oldValue;
//			return 0;
//		// Mitigated Speech + Loudness + Offensiveness
//		// - x * y * z
//		case POLITENESS_PUBLIC_A2: 
//			return ImpactFunctionHelper.Signum(3-MitigatedSpeech) * (Math.abs(3-MitigatedSpeech) + ImpactFunctionHelper.Signum(5-MitigatedSpeech)/(loudness));
//		// 5 * t
//		case S4_A3: return 5 * MitigatedSpeech;
//		
//		// 10/T
//		case S5_A1: return 10/MitigatedSpeech;
//		
//		// -5 * y * z
//		case S5_A2: return -5 * MitigatedSpeech * loudness;
//
//		// 5* ML
//		case S5_A4: return 5 * MitigatedSpeech;
//		
//		// Mitigated Speech + Loudness + Offensiveness
//		// S5_A6
//		case POLITENESS_PEER_A6: 
//	//		return ImpactFunctionHelper.Signum(4 - MitigatedSpeech) * (Math.abs(4-MitigatedSpeech) + ImpactFunctionHelper.Signum(4 - MitigatedSpeech)/(loudness+offensiveness));
//			return -1 * CanonicalImpactFunctions.Heaviside(MitigatedSpeech-4) * Math.exp(MitigatedSpeech/3); 
//
//		case POLITENESS_PEER_A9:
////			return ImpactFunctionHelper.Signum(4 - MitigatedSpeech) * (Math.abs(4-MitigatedSpeech) + ImpactFunctionHelper.Signum(4-MitigatedSpeech)/(loudness));
//			return -1 * CanonicalImpactFunctions.Heaviside(MitigatedSpeech-4) * Math.exp(MitigatedSpeech/3); 
//			
//		case POLITENESS_PEER_A11:
//			double old_Value =  valueSource.getCSSMValue(agentName, new CSSM(MARKET_PLACE, POLITENESS, VENDOR, SERGEANT, VENDOR, 0,
//					100, 50));
//			if(values.get(0) > 24)
//				return 50 - old_Value;
//
///*			if(values.get(0) > 24 && old_Value > 50)
//					return 50 - old_Value;
//			if(values.get(0) > 24 && old_Value < 50)
//				return old_Value - 50;
//*/		
//			return 0;
//			
//		case S6_A5: return 0;
//		
//		// 5 * t
//		case S8_A5: return 5 * MitigatedSpeech;
//		case S8_A6: return 0;
//		
//		
//		}
//		return 0;
//	}
//
//	@Override
//	public double getImpact(List<Double> values, IValueSource valueSource) {
//		// TODO Auto-generated method stub
//		return 0;
//	}



	
}
