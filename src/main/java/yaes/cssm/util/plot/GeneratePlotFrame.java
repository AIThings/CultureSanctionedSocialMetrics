package yaes.cssm.util.plot;

import java.util.List;

import org.jfree.ui.RefineryUtilities;

import yaes.cssm.cssm.Scenario;

public class GeneratePlotFrame {


	public static JFreeChartPlot s2v2graph = null;

	public static void generateSocialValuesPlot(String title,
			Scenario context, List<plotSpecCSSM> specs) {
		// FIXME: initialize the resultset
		ResultSet resultSet = null;
		s2v2graph = new JFreeChartPlot(title, resultSet, specs);
		s2v2graph.dispose();
		s2v2graph.pack();
		// RefineryUtilities.centerFrameOnScreen(s2v2graph);
		RefineryUtilities.positionFrameOnScreen(s2v2graph, 1.0, 0.0);
		s2v2graph.setVisible(true);

	}

	public static void closePreviousgraph() {
		if (GeneratePlotFrame.s2v2graph != null)
			GeneratePlotFrame.s2v2graph.dispose();
	}

}
