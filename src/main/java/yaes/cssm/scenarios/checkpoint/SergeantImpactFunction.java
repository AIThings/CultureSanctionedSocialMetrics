package yaes.cssm.scenarios.checkpoint;

import java.util.List;

import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.impactfunctions.IImpactFunction;
import yaes.cssm.impactfunctions.IValueSource;



/**
 * Implements the sergeant impact functions
 * @author Saad Khan
 *
 */
public class SergeantImpactFunction implements IImpactFunction, Constants {

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

//	public enum SergeantIFType {SECURITY_A1, SECURITY_A2, DIGNITY_A2, POLITENESS_PUBLIC_A6, S4_A9, S5_A4, POLITENESS_PEER_A6, S6_A1, S6_A5, S8_A5, S8_A9, POLITENESS_PUBLIC_A9, POLITENESS_PEER_A9, DIGNITY_A11, POLITENESS_PEER_A2, POLITENESS_PEER_A11};
//	
//	private SergeantIFType type;
//	
//	public SergeantImpactFunction(SergeantIFType type) {
//		this.type = type;
//	}
//	
//	@Override
//	public double getImpact(List<Double> values, IValueSource valueSource, String agentName) {
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
//		switch(type) {
//		
//		//-N * Lx^2 where N=x is frequency and Lx=x is the location
//		case SECURITY_A1:	return -1 * MitigatedSpeech * Math.pow(loudness, 2);
//
//		//Lx^2
//		case SECURITY_A2:	
//			return - (MitigatedSpeech+loudness+offensiveness)*0.3;
//		
//		//-10 * x * y where x is loudness and y is 
//		case DIGNITY_A2:
//			return -1 * ImpactFunctionHelper.Heaviside(MitigatedSpeech-2) * Math.exp(MitigatedSpeech/3); 
//			
//		// Order to move
//		case POLITENESS_PUBLIC_A6: 
//			return ImpactFunctionHelper.Signum(5-MitigatedSpeech) * (Math.abs(5-MitigatedSpeech) + ImpactFunctionHelper.Signum(5-MitigatedSpeech)/(Math.pow(offensiveness,2)));
//
//		case POLITENESS_PUBLIC_A9: 
//				return ImpactFunctionHelper.Signum(5-MitigatedSpeech) * (Math.abs(5-MitigatedSpeech) + ImpactFunctionHelper.Signum(5-MitigatedSpeech)/(Math.pow(loudness,2)));
//
//		case POLITENESS_PEER_A9:
//			return -1 * ImpactFunctionHelper.Heaviside(MitigatedSpeech-2) * Math.exp((MitigatedSpeech+loudness+offensiveness)); 
//			
//			//The case when vendor declines to move
//		case POLITENESS_PEER_A2:
//			return -1 * ImpactFunctionHelper.Heaviside(MitigatedSpeech-2) * Math.exp((MitigatedSpeech+loudness+offensiveness)*0.3); 
//
//		case S4_A9:	return -10 * MitigatedSpeech;
//		case S5_A4:	return 10 * MitigatedSpeech;		
//		
//		//-5 * x * y
//		case POLITENESS_PEER_A6:	
//			return ImpactFunctionHelper.Signum(5-MitigatedSpeech) * (Math.abs(5-MitigatedSpeech) + ImpactFunctionHelper.Signum(5-MitigatedSpeech)/(loudness+offensiveness));
//
//		
//		//  -5/N
//		case S6_A1: return -5/MitigatedSpeech;
//		
//		//5*t
//		case S6_A5: return 5 * MitigatedSpeech;
//		
//		// 10*t
//		case S8_A5: return 10 * MitigatedSpeech;
//		
//		// 5 * ML
//		case S8_A9: return 5 * MitigatedSpeech;
//		
//		case DIGNITY_A11:
//			double old_Value =  valueSource.getCSSMValue("SergeantBob", new CSSM(CHECK_POINT, DIGNITY, SERGEANT, SERGEANT, SERGEANT, 0,
//					100, 50));
//			//if(values.get(0) > 24 && agentName.equals("VendorHassan"))
//			if(values.get(0) > 24)	
//				return 50 - old_Value;
//			return 0;
//			
//		case POLITENESS_PEER_A11:
//			double oldValue =  valueSource.getCSSMValue("SergeantBob", new CSSM(CHECK_POINT, POLITENESS, SERGEANT, VENDOR, SERGEANT,
//					0, 100, 50));
//		//	if(values.get(0) > 24 && agentName.equals("VendorHassan"))
//			if(values.get(0) > 24)
//				return 50 - oldValue;
//			return 0;
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
