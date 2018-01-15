package yaes.cssm.util.webgui.httpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.cssm.util.webgui.httpserver.GsQuery.ResultType;
import yaes.cssm.util.webgui.prettygraphviz.GraphVizHelper;
import yaes.cssm.util.webgui.prettygraphviz.pgvProgressGraph;
import yaes.cssm.util.webgui.prettyhtml.PwActor;
import yaes.cssm.util.webgui.prettyhtml.PwHelper;
import yaes.cssm.util.webgui.prettyhtml.PwScenario;
import yaes.cssm.util.webgui.prettyhtml.PwScenarioSet;
import yaes.cssm.util.webgui.prettyhtml.PwSocialAgent;
import yaes.ui.format.HtmlFormatter;
import yaes.ui.text.TextUi;
import yaes.util.FileWritingUtil;

/**
 * 
 * This class is responsible for generating the page served in the web gui
 * (html, jpg or other)
 * 
 * @author Lotzi Boloni
 * 
 */
public class WgPageGenerator {

    /**
     * Copies the css file from the directories to the destination
     * 
     * @param agent
     * @param dir
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copyCSS(SocialCalculusContext scc, File dir)
            throws FileNotFoundException, IOException {
        URL url = PwHelper.class.getResource("yaes.css");
        BufferedReader in =
                new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer buffer = new StringBuffer();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            buffer.append(inputLine + "\n");
        }
        in.close();
        File targetFile = new File(dir, "yaes.css");
        FileWritingUtil.writeToTextFile(targetFile, buffer.toString());
    }

    private SocialCalculusContext scc;
    private File dir;

    
    public SocialCalculusContext getSocialCalculusContext() {
    	return scc;
    }
    
    /**
     * @param agent
     * @throws IOException
     * @throws FileNotFoundException
     */
    public WgPageGenerator(SocialCalculusContext scc, File dir) throws FileNotFoundException,
            IOException {
        this.scc = scc;
        this.dir = dir;
        WgPageGenerator.copyCSS(scc, dir);
    }

    /**
     * Returns the graphviz image file for a specific query
     * 
     * @param gq
     * @param format
     *            it can be "jpg","pdf","eps","png" etc
     * @return
     * @throws IOException
     */
    public File createGraphVizImage(GsQuery gq, String format)
            throws IOException {
        String fileRoot = "temp" + System.currentTimeMillis();
        String dot = null;
        switch (gq.getQueryType()) {
        case SCENARIO:
            dot = pgvProgressGraph.generate(scc, gq);
            break;
        default: {
            TextUi.errorPrint("Query type " + gq.getQueryType()
                    + " not supported here.");
            throw new Error("Query type " + gq.getQueryType()
                    + " not supported here.");
        }
        }
        File fileDot = new File(dir, fileRoot + ".dot");
        FileWritingUtil.writeToTextFile(fileDot, dot);
        GraphVizHelper.dotToCompile(dir.toString(), fileRoot, format);
        // delete the unnecessary dot file, comment this out for debugging
        // fileDot.delete();
        return new File(dir, fileRoot + "." + format);
    }

    /**
     * @return the dir
     */
    public File getDir() {
        return dir;
    }
    
    
    /**
     * Returns the HTML (for a query) and adds a possible notification string
     * 
     * @param query
     * @return
     */
    public String getHtml(GsQuery query, String notificationString) {
        // now
        if (!query.getResultType().equals(ResultType.HTML)) {
            throw new Error("This function is for HTML");
        }
        HtmlFormatter fmt =
                new HtmlFormatter("YAES: " + query.getQueryType());
        fmt.openHtml();
        fmt.openBody("onload=\"JavaScript:timedRefresh(5000);\"");
        // add the message string
        if (notificationString != null) {
            // fmt.addPre(notificationString, "class=notification");
            PwHelper.addErrorMessage(fmt, notificationString);
        }
        switch (query.getQueryType()) {
        case SCENARIO_SET: {
            PwScenarioSet.generate(fmt, scc, query);
            break;
        }
        case SCENARIO: {
        	PwScenario.generate(fmt, scc, query);
        	break;
        }
        case ACTOR: {
        	PwActor.generate(fmt, scc, query);
        	break;
        }
        case SOCIAL_AGENT: {
        	PwSocialAgent.generate(fmt, scc, query);
        	break;
        }
        default:
            fmt.add("unsupported query type in getHtml:" + query.getQueryType());
        }
        fmt.closeBody();
        fmt.closeHtml();
        return fmt.toString();
    }

}
