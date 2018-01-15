package cssm.plot;

import yaes.cssm.cssm.Constants;

public class testPlot implements Constants {

	/*
	@Test
	public void test() throws InstantiationException, IllegalAccessException,
			IOException {
		File inputDir = new File("scenarios/spanishsteps");
		File graphDir = new File("data/cssm-spanishsteps-graphs");

		final SimulationInput sip = Main.createDefaultSimulationInput();
		DirUtil.guaranteeDirectory(graphDir.toString());
		DirUtil.guaranteeDirectory(inputDir.toString());

		sip.setParameter(ACTION_FILE_READ, new File("scenarios/spanishsteps/"
				+ "AAMAS2012_Scen1" + ".sspc").toString());
		sip.setParameter(ACTION_FILE_WRITE, "");

		SpanishStepsContext context = new SpanishStepsContext();

		Simulation.simulate(sip, SpanishStepsSimulation.class, context);

		String plot = "";
		// plot the ACTOR_CLIENT
		List<SVPlotSpec> toplot = new ArrayList<>();
		// toplot.add(new SVPlotSpec(SpanishStepsContext.ACTOR_CLIENT,
		// TIME, "Time"));
		// toplot.add(new SVPlotSpec(SpanishStepsContext.ACTOR_CLIENT,
		// WORTH, "Worth"));
		
		
		toplot.add(new SVPlotSpec("ACTOR_CLIENT", DIGNITY, new CSSM(SPANISH_STEPS,
				DIGNITY, ACTOR_CLIENT, ACTOR_CLIENT, ACTOR_CLIENT, 0, 100, 50)));
		// toplot.add(new SVPlotSpec(ACTOR_CLIENT,
		// DIGNITY_PEER, "Peer Dignity"));
		toplot.add(new SVPlotSpec("ACTOR_CLIENT", DIGNITY_PUBLIC, new CSSM(
				SPANISH_STEPS, DIGNITY, ACTOR_CLIENT, ACTOR_CROWD, ACTOR_CLIENT, 0, 100, 50)));
		toplot.add(new SVPlotSpec("ACTOR_CLIENT", POLITENESS, new CSSM(SPANISH_STEPS,
				POLITENESS, ACTOR_CLIENT, ACTOR_CLIENT, ACTOR_CLIENT, 0, 100, 50)));
		// toplot.add(new SVPlotSpec(ACTOR_CLIENT,
		// POLITENESS_PEER, "Peer politeness"));
		toplot.add(new SVPlotSpec("ACTOR_CLIENT", POLITENESS_PUBLIC, new CSSM(
				SPANISH_STEPS, POLITENESS, ACTOR_CLIENT, ACTOR_CROWD, ACTOR_CLIENT, 0, 100, 50)));
		plot = GenerateValuePlot.generateSocialValuesPlot(context.socialScenario, toplot);
     	GeneratePlotFrame.generateSocialValuesPlot("CSSM", context.socialScenario, toplot); 		
		FileWritingUtil.writeToTextFile(new File(graphDir, "client_"
				+ "testFile" + ".m"), plot);
		char t = 'y';
		while(t=='y'){
			
			
		}
		
	}
*/
}
