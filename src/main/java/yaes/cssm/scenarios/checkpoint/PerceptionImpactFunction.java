package yaes.cssm.scenarios.checkpoint;

import java.util.List;

import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.impactfunctions.IImpactFunction;
import yaes.cssm.impactfunctions.IValueSource;

public class PerceptionImpactFunction implements IImpactFunction, Constants {

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
//    public enum PIFType {
//        FRIEND_5, FRIEND_9, FRIEND_8, THREAT_2, FRIEND_11
//    };
//
//    private PIFType type;
//    private Map<String, MassFunction<Character>> massFunctions;
//
//    
//    
//    /**
//     * Initializes the mass functions for the perception values
//     * @return
//     */
//    public static Map<String, MassFunction<Character>>
//            initializeMassFunctions() {
//        Map<String, MassFunction<Character>> massFunctions =   new HashMap<>();
//        // the friendship mass function: g 0.2, s+g 0.6 s 0.2 
//        MassFunction<Character> m_friend = new MassFunction<>();
//        m_friend.add(new Hypothesis<>('f'), 0.3);
//        m_friend.add(new Hypothesis<>('n'), 0.7);
//        m_friend.add(new Hypothesis<>('n', 'f'), 0.4);
//        massFunctions.put(IS_A_FRIEND, m_friend);
//        // the threat mass function
//        MassFunction<Character> m_threat = new MassFunction<>();
//        m_threat.add(new Hypothesis<>('t'), 0.3);
//        m_threat.add(new Hypothesis<>('n'), 0.1);
//        m_threat.add(new Hypothesis<>('n', 't'), 0.7);
//        massFunctions.put(POSES_A_THREAT, m_threat);
//        return massFunctions;
//    }
//
//    public PerceptionImpactFunction(PIFType type,
//            Map<String, MassFunction<Character>> massFunctions) {
//        this.type = type;
//        this.massFunctions = massFunctions;
//    }
//
//    @Override
//    public double getImpact(List<Double> values, IValueSource valueSource) {
//        switch (type) {
//         case FRIEND_5:
//         case FRIEND_9:
//         case FRIEND_8:
//         case FRIEND_11:
//             return getFriendImpact(values, valueSource);
//
//         case THREAT_2:
//            return getThreatImpact(values, valueSource);
//        }
//        return 0;
//    }
//
//
//    
//    /**
//     * Handles the impact on the gift
//     * @param values
//     * @param valueSource
//     * @return
//     */
//    public double getThreatImpact(List<Double> values, IValueSource valueSource) {
//        double oldValue = valueSource.getBeliefValue("SergeantBob", new ConcreteBelief(CHECK_POINT, POSES_A_THREAT,
//				SERGEANT, SERGEANT, 0, 1.0, 0));
//        MassFunction<Character> m_transaction = massFunctions.get(POSES_A_THREAT);
//        MassFunction<Character> m_evidence = new MassFunction<Character>();
//        switch (type) {
//        case THREAT_2: {
//            // declines to move
//        	  	m_evidence.add(new Hypothesis<Character>('t'), 0.5);
//	            m_evidence.add(new Hypothesis<Character>('n'), 0.1);
//	         	m_evidence.add(new Hypothesis<Character>('n', 't'), 0.3);
//        
//	            break;
//        }
//		case FRIEND_11:
//			break;
//		case FRIEND_5:
//			break;
//		case FRIEND_8:
//			break;
//		case FRIEND_9:
//			break;
//        
//        }
//        MassFunction<Character> m_newthreat =
//                m_transaction.combineConjunctive(m_evidence);
//        massFunctions.put(POSES_A_THREAT, m_newthreat);
//        double newValue = m_newthreat.getBelief(new Hypothesis<Character>('t'));
//        return newValue - oldValue;
//    }
//
//    
//    /**
//     * Handles the impact on the friendship
//     * @param values
//     * @param valueSource
//     * @return
//     */
//    public double getFriendImpact(List<Double> values, IValueSource valueSource) {
//        double oldValue =  valueSource.getBeliefValue("VendorHassan", new ConcreteBelief(CHECK_POINT, IS_A_FRIEND,
//				VENDOR, VENDOR, 0, 1.0, 0));
//        MassFunction<Character> m_friend = massFunctions.get(IS_A_FRIEND);
//        MassFunction<Character> m_evidence = new MassFunction<Character>();
//        switch (type) {
//        case FRIEND_11: {
//            // the overnight delay
//    		double oldDignityValue =  valueSource.getCSSMValue("VendorHassan", new CSSM(CHECK_POINT, DIGNITY, VENDOR, VENDOR, VENDOR, 0, 100,
//    				50));
//    		double oldPolitenessValue =  valueSource.getCSSMValue("VendorHassan", new CSSM(CHECK_POINT, POLITENESS, VENDOR, SERGEANT, VENDOR, 0,
//    				100, 50));
//
//    		System.out.println(oldDignityValue+"<--DV-PV-->"+oldPolitenessValue);
//    		if(oldDignityValue<50 && oldPolitenessValue<50){
//    			m_evidence.add(new Hypothesis<Character>('n'), 0.4);
//    			m_evidence.add(new Hypothesis<Character>('f'), 0.1);
//        }	else
//        		if(oldDignityValue<40 && oldPolitenessValue<60){
//        			m_evidence.add(new Hypothesis<Character>('n'), 0.6);
//    				m_evidence.add(new Hypothesis<Character>('f'), 0.1);
//        }else
//           		if(oldDignityValue>=50 && oldPolitenessValue>=50){
//            			m_evidence.add(new Hypothesis<Character>('f'), 0.2);
//            			m_evidence.add(new Hypothesis<Character>('n', 'f'), 0.3); 
//           		}
//           		else
//            		m_evidence.add(new Hypothesis<Character>('n', 'f'), 0.2);            
//        	break;
//        }
//		case FRIEND_5:
//			break;
//		case FRIEND_8:
//			break;
//		case FRIEND_9:
//			break;
//		case THREAT_2:
//			break;
//
//        }
//        MassFunction<Character> m_newfriend =   m_friend.combineConjunctive(m_evidence);
//        massFunctions.put(IS_A_FRIEND, m_newfriend);
//        double newValue = m_newfriend.getBelief(new Hypothesis<Character>('f'));
//    	//System.out.println(newValue +"<--NV--OV-->"+ oldValue);		
//
//        return newValue - oldValue;
//    }
//
//	@Override
//	public double getImpact(List<Double> values, IValueSource valueSource,
//			String agentName) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public double getImpact(Scenario scenario, List<Double> values,
//			IValueSource valueSource) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public double getImpact(Scenario scenario, List<Double> values,
//			IValueSource valueSource, String agentName) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	

}
