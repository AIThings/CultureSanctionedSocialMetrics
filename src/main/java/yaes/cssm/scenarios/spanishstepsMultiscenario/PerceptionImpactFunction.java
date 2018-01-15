package yaes.cssm.scenarios.spanishstepsMultiscenario;

import java.util.List;
import java.util.Map;

import mass.exact.MassFunction;
import yaes.cssm.cssm.Constants;
import yaes.cssm.cssm.Scenario;
import yaes.cssm.impactfunctions.IImpactFunction;
import yaes.cssm.impactfunctions.IValueSource;

public class PerceptionImpactFunction implements IImpactFunction, Constants {
	public enum PIFType {
		GIFT_5, GIFT_6, GIFT_12, GIFT_14, GIFT_15, GIFT_21, TRANS_3, TRANS_6, TRANS_13, TRANS_14, TRANS_15, TRANS_20, TRANS_22,

		DECEPTIVE_1, DECEPTIVE_2, DECEPTIVE_3, DECEPTIVE_4, DECEPTIVE_5, DECEPTIVE_6, DECEPTIVE_7, DECEPTIVE_8, DECEPTIVE_9, DECEPTIVE_10, DECEPTIVE_11, DECEPTIVE_12, DECEPTIVE_13, DECEPTIVE_14, DECEPTIVE_15, DECEPTIVE_16, DECEPTIVE_17, DECEPTIVE_18, DECEPTIVE_19, DECEPTIVE_20, DECEPTIVE_21, DECEPTIVE_22, DECEPTIVE_23, DECEPTIVE_24,

		WORLD_PERCEPTION_7, WORLD_PERCEPTION_9, WORLD_PERCEPTION_16, COM_WLD_PERCP
	};

	private PIFType type;
	private Map<String, MassFunction<Character>> massFunctions;

	public PerceptionImpactFunction(PIFType type,
			Map<String, MassFunction<Character>> massFunctions) {
		this.type = type;
		this.massFunctions = massFunctions;
	}

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

	/**
	 * Initializes the mass functions for the perception values
	 * 
	 * @return
	 */
//	public static Map<String, MassFunction<Character>> initializeMassFunctions() {
//		Map<String, MassFunction<Character>> massFunctions = new HashMap<String, MassFunction<Character>>();
//		// the gift mass function: g 0.2, s+g 0.6 s 0.2
//		MassFunction<Character> m_gift = new MassFunction<Character>();
//		m_gift.add(new Hypothesis<Character>('g'), 0.5);
//		m_gift.add(new Hypothesis<Character>('s'), 0.5);
//		m_gift.add(new Hypothesis<Character>('g', 's'), 0.0);
//		massFunctions.put(IS_A_GIFT, m_gift);
//		// the transaction mass function
//		MassFunction<Character> m_transaction = new MassFunction<Character>();
//		m_transaction.add(new Hypothesis<Character>('t'), 0.1);
//		m_transaction.add(new Hypothesis<Character>('n'), 0.1);
//		m_transaction.add(new Hypothesis<Character>('t', 'n'), 0.8);
//		massFunctions.put(HAS_BEEN_A_TRANSACTION, m_transaction);
//
//		// the Deception mass function
//		MassFunction<Character> m_deception = new MassFunction<Character>();
//		m_deception.add(new Hypothesis<Character>('d'), 0.1);
//		m_deception.add(new Hypothesis<Character>('h'), 0.1);
//		m_deception.add(new Hypothesis<Character>('d', 'h'), 0.8);
//		massFunctions.put(IS_DECEPTIVE, m_deception);
//
//		// the Deception mass function
//		MassFunction<Character> m_worldPerception = new MassFunction<Character>();
//		m_worldPerception.add(new Hypothesis<Character>('d'), .1);
//		m_worldPerception.add(new Hypothesis<Character>('h'), .1);
//		m_worldPerception.add(new Hypothesis<Character>('d', 'h'), 0.8);
//		massFunctions.put(IS_DECEPTIVE_IN_WORLD, m_worldPerception);
//
//		return massFunctions;
//	}
//
//
//
//
//
//	@Override
//	public double getImpact(List<Double> values, IValueSource valueSource,
//			String agentName) {
//		switch (type) {
//		case GIFT_5:
//		case GIFT_6:
//		case GIFT_14:
//		case GIFT_15:
//			return getGiftImpact(values, valueSource, agentName);
//		case TRANS_3:
//		case TRANS_6:
//		case TRANS_13:
//		case TRANS_14:
//		case TRANS_15:
//		case TRANS_20:
//		case TRANS_22:
//			return getTransactionImpact(values, valueSource, agentName);
//
//		case DECEPTIVE_1:
//		case DECEPTIVE_2:
//		case DECEPTIVE_3:
//		case DECEPTIVE_4:
//		case DECEPTIVE_5:
//		case DECEPTIVE_6:
//		case DECEPTIVE_7:
//		case DECEPTIVE_8:
//		case DECEPTIVE_9:
//		case DECEPTIVE_10:
//		case DECEPTIVE_11:
//		case DECEPTIVE_12:
//		case DECEPTIVE_13:
//		case DECEPTIVE_14:
//		case DECEPTIVE_15:
//		case DECEPTIVE_16:
//		case DECEPTIVE_17:
//		case DECEPTIVE_18:
//		case DECEPTIVE_19:
//		case DECEPTIVE_20:
//		case DECEPTIVE_21:
//		case DECEPTIVE_22:
//		case DECEPTIVE_23:
//		case DECEPTIVE_24:
//			return getDeceptionImpact(values, valueSource, agentName);
//
//		case WORLD_PERCEPTION_7:
//		case WORLD_PERCEPTION_9:
//		case WORLD_PERCEPTION_16:
//			return getDeceptionImpact(values, valueSource, agentName);
//
//		case COM_WLD_PERCP:
//			return getWorldImpact(values, valueSource, agentName);
//		case GIFT_12:
//			break;
//		case GIFT_21:
//			break;
//		default:
//			break;
//		}
//		return 0;
//	}
//
//	/**
//	 * Handles the impact on the gift
//	 * 
//	 * @param values
//	 * @param valueSource
//	 * @return
//	 */
//	public double getTransactionImpact(List<Double> values,
//			IValueSource valueSource, String agentName) {
//		double oldValue = valueSource.getBeliefValue(agentName,
//				new ConcreteBelief(SPANISH_STEPS, HAS_BEEN_A_TRANSACTION, ACTOR_CROWD,
//						ACTOR_CLIENT, 0, 1, 0.1));
//		MassFunction<Character> m_transaction = massFunctions
//				.get(HAS_BEEN_A_TRANSACTION);
//		MassFunction<Character> m_evidence = new MassFunction<Character>();
//		switch (type) {
//		case TRANS_3: {
//			// transaction complete
//			m_evidence.add(new Hypothesis<Character>('t'), 0.7);
//			m_evidence.add(new Hypothesis<Character>('t', 'n'), 0.3);
//			break;
//		}
//		case TRANS_6: {
//			// forces a gift
//			m_evidence.add(new Hypothesis<Character>('t'), 0.5);
//			m_evidence.add(new Hypothesis<Character>('t', 'n'), 0.5);
//			break;
//		}
//		case TRANS_13: {
//			// accepts the gift
//			m_evidence.add(new Hypothesis<Character>('t'), 0.5);
//			m_evidence.add(new Hypothesis<Character>('t', 'n'), 0.5);
//			break;
//		}
//		case TRANS_14: {
//			// waiting without being asked for money
//			double time = values.get(0);
//			m_evidence.add(new Hypothesis<Character>('t'), 0.05 * time);
//			m_evidence
//					.add(new Hypothesis<Character>('t', 'n'), 1 - 0.05 * time);
//			break;
//		}
//
//		case TRANS_15: {
//			m_evidence.add(new Hypothesis<Character>('n'), 0.7);
//			m_evidence.add(new Hypothesis<Character>('t', 'n'), 0.3);
//			break;
//		}
//		case TRANS_20: {
//			m_evidence.add(new Hypothesis<Character>('n'), 0.7);
//			m_evidence.add(new Hypothesis<Character>('t', 'n'), 0.3);
//			break;
//		}
//		case TRANS_22: {
//			m_evidence.add(new Hypothesis<Character>('t'), 0.7);
//			m_evidence.add(new Hypothesis<Character>('t', 'n'), 0.3);
//			break;
//		}
//		case COM_WLD_PERCP:
//			break;
//		case DECEPTIVE_1:
//			break;
//		case DECEPTIVE_10:
//			break;
//		case DECEPTIVE_11:
//			break;
//		case DECEPTIVE_12:
//			break;
//		case DECEPTIVE_13:
//			break;
//		case DECEPTIVE_14:
//			break;
//		case DECEPTIVE_15:
//			break;
//		case DECEPTIVE_16:
//			break;
//		case DECEPTIVE_17:
//			break;
//		case DECEPTIVE_18:
//			break;
//		case DECEPTIVE_19:
//			break;
//		case DECEPTIVE_2:
//			break;
//		case DECEPTIVE_20:
//			break;
//		case DECEPTIVE_21:
//			break;
//		case DECEPTIVE_22:
//			break;
//		case DECEPTIVE_23:
//			break;
//		case DECEPTIVE_24:
//			break;
//		case DECEPTIVE_3:
//			break;
//		case DECEPTIVE_4:
//			break;
//		case DECEPTIVE_5:
//			break;
//		case DECEPTIVE_6:
//			break;
//		case DECEPTIVE_7:
//			break;
//		case DECEPTIVE_8:
//			break;
//		case DECEPTIVE_9:
//			break;
//		case GIFT_12:
//			break;
//		case GIFT_14:
//			break;
//		case GIFT_15:
//			break;
//		case GIFT_21:
//			break;
//		case GIFT_5:
//			break;
//		case GIFT_6:
//			break;
//		case WORLD_PERCEPTION_16:
//			break;
//		case WORLD_PERCEPTION_7:
//			break;
//		case WORLD_PERCEPTION_9:
//			break;
//		default:
//			break;
//		}
//		MassFunction<Character> m_newtransaction = m_transaction
//				.combineConjunctive(m_evidence);
//		massFunctions.put(HAS_BEEN_A_TRANSACTION, m_newtransaction);
//		double newValue = m_newtransaction.getBelief(new Hypothesis<Character>(
//				't'));
//		return newValue - oldValue;
//	}
//
//	/**
//	 * Handles the impact on the gift
//	 * 
//	 * @param values
//	 * @param valueSource
//	 * @return
//	 */
//	public double getGiftImpact(List<Double> values, IValueSource valueSource,
//			String agentName) {
//		double oldValue = valueSource.getBeliefValue(agentName,
//				new ConcreteBelief(SPANISH_STEPS, IS_A_GIFT, ACTOR_CLIENT, ACTOR_CLIENT, 0,
//						1, 0.1));
//		MassFunction<Character> m_gift = massFunctions.get(IS_A_GIFT);
//		MassFunction<Character> m_evidence = new MassFunction<Character>();
//
//		int reduction = 1;
//		int increment = 1;
//		if (!(agentName.equalsIgnoreCase(valueSource.getCurrentClient()))) {
//			increment = 3;
//			reduction = 2;
//		} else {
//			increment = 1;
//			reduction = 1;
//		}
//		switch (type) {
//		case GIFT_5: {
//			// offered as a gift
//
//			m_evidence.add(new Hypothesis<Character>('g'),
//					ImpactFunctionHelper.doubleFloor(0.7, increment));
//			m_evidence.add(new Hypothesis<Character>('g', 's'),
//					1 - ImpactFunctionHelper.doubleFloor(0.7, increment));
//			break;
//		}
//		case GIFT_6: {
//			// pushed as a gift
//			m_evidence.add(new Hypothesis<Character>('s'),
//					ImpactFunctionHelper.doubleFloor(0.3, reduction));
//			m_evidence.add(new Hypothesis<Character>('g', 's'),
//					1 - ImpactFunctionHelper.doubleFloor(0.3, reduction));
//			break;
//		}
//		case GIFT_12: {
//			// being asked for money
//			m_evidence.add(new Hypothesis<Character>('g'),
//					ImpactFunctionHelper.doubleFloor(0.3, increment));
//			m_evidence.add(new Hypothesis<Character>('g', 's'),
//					1 - ImpactFunctionHelper.doubleFloor(0.3, increment));
//			break;
//		}
//		case GIFT_14: {
//			// waiting without being asked for money
//			double time = values.get(0);
//			m_evidence.add(new Hypothesis<Character>('g'),
//					ImpactFunctionHelper.doubleFloor(0.02 * time, increment));
//			m_evidence.add(new Hypothesis<Character>('g', 's'),
//					1 - ImpactFunctionHelper
//							.doubleFloor(0.02 * time, increment));
//			break;
//		}
//		case GIFT_15: {
//			// being asked for money
//			m_evidence.add(new Hypothesis<Character>('s'),
//					ImpactFunctionHelper.doubleFloor(1, reduction));
//			m_evidence.add(new Hypothesis<Character>('g', 's'),
//					1 - ImpactFunctionHelper.doubleFloor(1, reduction));
//			break;
//		}
//		case COM_WLD_PERCP:
//			break;
//		case DECEPTIVE_1:
//			break;
//		case DECEPTIVE_10:
//			break;
//		case DECEPTIVE_11:
//			break;
//		case DECEPTIVE_12:
//			break;
//		case DECEPTIVE_13:
//			break;
//		case DECEPTIVE_14:
//			break;
//		case DECEPTIVE_15:
//			break;
//		case DECEPTIVE_16:
//			break;
//		case DECEPTIVE_17:
//			break;
//		case DECEPTIVE_18:
//			break;
//		case DECEPTIVE_19:
//			break;
//		case DECEPTIVE_2:
//			break;
//		case DECEPTIVE_20:
//			break;
//		case DECEPTIVE_21:
//			break;
//		case DECEPTIVE_22:
//			break;
//		case DECEPTIVE_23:
//			break;
//		case DECEPTIVE_24:
//			break;
//		case DECEPTIVE_3:
//			break;
//		case DECEPTIVE_4:
//			break;
//		case DECEPTIVE_5:
//			break;
//		case DECEPTIVE_6:
//			break;
//		case DECEPTIVE_7:
//			break;
//		case DECEPTIVE_8:
//			break;
//		case DECEPTIVE_9:
//			break;
//		case GIFT_21:
//			break;
//		case TRANS_13:
//			break;
//		case TRANS_14:
//			break;
//		case TRANS_15:
//			break;
//		case TRANS_20:
//			break;
//		case TRANS_22:
//			break;
//		case TRANS_3:
//			break;
//		case TRANS_6:
//			break;
//		case WORLD_PERCEPTION_16:
//			break;
//		case WORLD_PERCEPTION_7:
//			break;
//		case WORLD_PERCEPTION_9:
//			break;
//		default:
//			break;
//
//		}
//		MassFunction<Character> m_newgift = m_gift
//				.combineConjunctive(m_evidence);
//		massFunctions.put(IS_A_GIFT, m_newgift);
//		double newValue = m_newgift.getBelief(new Hypothesis<Character>('g'));
//		return newValue - oldValue;
//	}
//
//	/**
//	 * Handles the impact on the personal perception of ACTOR_CLIENT about seller
//	 * deception
//	 * 
//	 * @param values
//	 * @param valueSource
//	 * @return
//	 */
//	public double getDeceptionImpact(List<Double> values,
//			IValueSource valueSource, String agentName) {
//		double oldValue = valueSource.getBeliefValue(agentName,
//				new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE, ACTOR_CROWD, ACTOR_CLIENT,
//						0, 1, 0.1));
//		MassFunction<Character> m_transaction = massFunctions.get(IS_DECEPTIVE);
//		MassFunction<Character> m_evidence = new MassFunction<Character>();
//		switch (type) {
//		// case DECEPTIVE_1:
//		// case DECEPTIVE_2:
//
//		case DECEPTIVE_3: {
//			m_evidence.add(new Hypothesis<Character>('h'), 0.5);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.5);
//			break;
//		}
//		// case DECEPTIVE_4:
//		case DECEPTIVE_5: {
//			m_evidence.add(new Hypothesis<Character>('d'), 0.2);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.8);
//			break;
//		}
//		case DECEPTIVE_6: {
//			m_evidence.add(new Hypothesis<Character>('d'), 0.3);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.7);
//			break;
//		}
//		// case DECEPTIVE_7:
//		case DECEPTIVE_8: {
//			m_evidence.add(new Hypothesis<Character>('h'), 0.3);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.8);
//			break;
//		}
//		// case DECEPTIVE_9:
//
//		case DECEPTIVE_10: {
//			m_evidence.add(new Hypothesis<Character>('d'), 0.2);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.8);
//			break;
//		}
//		case DECEPTIVE_11: {
//			m_evidence.add(new Hypothesis<Character>('h'), 0.2);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.8);
//			break;
//		}
//		case DECEPTIVE_12: {
//			m_evidence.add(new Hypothesis<Character>('d'), 0.2);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.8);
//			break;
//		}
//
//		// case DECEPTIVE_13:
//		case DECEPTIVE_14: {
//			// waiting without being asked for money
//			double time = values.get(0);
//			m_evidence.add(new Hypothesis<Character>('h'), 0.05 * time);
//			m_evidence
//					.add(new Hypothesis<Character>('d', 'h'), 1 - 0.05 * time);
//			break;
//		}
//		case DECEPTIVE_15: {
//			m_evidence.add(new Hypothesis<Character>('d'), 0.5);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.5);
//			break;
//		}
//		// case DECEPTIVE_16:
//		case DECEPTIVE_17: {
//			m_evidence.add(new Hypothesis<Character>('d'), 0.5);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.5);
//			break;
//		}
//		case DECEPTIVE_18: {
//			m_evidence.add(new Hypothesis<Character>('d'), 0.2);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.8);
//			break;
//		}
//		// case DECEPTIVE_19:
//		case DECEPTIVE_20: {
//			m_evidence.add(new Hypothesis<Character>('d'), 0.8);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.2);
//			break;
//		}
//		case DECEPTIVE_21: {
//			m_evidence.add(new Hypothesis<Character>('d'), 0.2);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.8);
//			break;
//		}
//		case DECEPTIVE_22: {
//			m_evidence.add(new Hypothesis<Character>('h'), 0.5);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.5);
//			break;
//		}
//		case DECEPTIVE_23: {
//			m_evidence.add(new Hypothesis<Character>('h'), 0.6);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'), 0.4);
//			break;
//		}
//		// case DECEPTIVE_24:
//		case WORLD_PERCEPTION_7: {
//
//			double loudness = values.get(0);
//			double effect = valueSource.getEffectOnWorld();
//
//			m_evidence.add(new Hypothesis<Character>('d'), (loudness) * effect);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'),
//					1 - ((loudness) * effect));
//			break;
//		}
//		case WORLD_PERCEPTION_9: {
//			double loudness = values.get(0);
//			double effect = valueSource.getEffectOnWorld();
//			m_evidence.add(new Hypothesis<Character>('d'), (loudness) * effect);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'),
//					1 - ((loudness) * effect));
//			break;
//		}
//		case WORLD_PERCEPTION_16: {
//			double loudness = values.get(0);
//			double effect = valueSource.getEffectOnWorld();
//			m_evidence.add(new Hypothesis<Character>('d'), (loudness) * effect);
//			m_evidence.add(new Hypothesis<Character>('d', 'h'),
//					1 - ((loudness) * effect));
//			break;
//		}
//		case COM_WLD_PERCP:
//			break;
//		case DECEPTIVE_1:
//			break;
//		case DECEPTIVE_13:
//			break;
//		case DECEPTIVE_16:
//			break;
//		case DECEPTIVE_19:
//			break;
//		case DECEPTIVE_2:
//			break;
//		case DECEPTIVE_24:
//			break;
//		case DECEPTIVE_4:
//			break;
//		case DECEPTIVE_7:
//			break;
//		case DECEPTIVE_9:
//			break;
//		case GIFT_12:
//			break;
//		case GIFT_14:
//			break;
//		case GIFT_15:
//			break;
//		case GIFT_21:
//			break;
//		case GIFT_5:
//			break;
//		case GIFT_6:
//			break;
//		case TRANS_13:
//			break;
//		case TRANS_14:
//			break;
//		case TRANS_15:
//			break;
//		case TRANS_20:
//			break;
//		case TRANS_22:
//			break;
//		case TRANS_3:
//			break;
//		case TRANS_6:
//			break;
//		default:
//			break;
//
//		}
//		MassFunction<Character> m_newtransaction = m_transaction
//				.combineConjunctive(m_evidence);
//		massFunctions.put(IS_DECEPTIVE, m_newtransaction);
//		double newValue = m_newtransaction.getBelief(new Hypothesis<Character>(
//				'd'));
//		return newValue - oldValue;
//	}
//
//	public double getWorldImpact(List<Double> values, IValueSource valueSource,
//			String agentName) {
//		ConcreteBelief cb = new ConcreteBelief(SPANISH_STEPS, IS_DECEPTIVE,
//				ACTOR_CROWD, ACTOR_CLIENT, 0, 1, 0.1);
//
//		double worldPerception = valueSource.getWorldPerception(agentName, cb);
//		return worldPerception;
//	}
//
//	@Override
//	public double getImpact(List<Double> values, IValueSource valueSource) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}
