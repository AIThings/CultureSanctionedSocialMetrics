/*
   This file is part of the Xapagy project
   Created on: Apr 3, 2012
 
   com.xapagy.ui.prettygraphviz.pgvFocusInstances
 
   Copyright (c) 2008-2013 Ladislau Boloni
 */

package yaes.cssm.util.webgui.prettygraphviz;

import yaes.cssm.actions.ProgressGraph;
import yaes.cssm.scenarios.SocialCalculusContext;
import yaes.cssm.util.webgui.httpserver.GsQuery;

/**
 * @author Ladislau Boloni
 * 
 */
public class pgvProgressGraph {

    /**
     * Generate the focus instances, organized by scenes
     * 
     * @return
     */
    public static String generate(SocialCalculusContext scc, GsQuery query) {
        GvFormatter fmt = new GvFormatter();
        GvParameters param = new GvParameters();
        fmt.openDiGraph();
        fmt.is("rankdir", "LR");
        fmt.is(GvParameters.SPLINES, param.splines);
        fmt.is("compound", true);
        //Focus fc = agent.getFocus();
        //pgvFocusInstances.pgvNode(fc, agent, fmt, param);
        //pgvFocusInstances.pgvLinks(fc, agent, fmt, param);
        //fmt.close(); // the digraph
        return fmt.toString();
    }

    /**
     * Call for all the scenes (which will call them for the instances)
     * 
     * @param fc
     * @param agent
     * @param fmt
     * @param param
     */
    public static void pgvLinks(ProgressGraph pg, GvFormatter fmt,
            GvParameters param) {
        //for (Instance scene : fc.getAllScenes()) {
        //    pgvScene.pgvLinks(scene, agent, fmt, param);
        //}
    }

    /**
     * Adds all the scenes, which then add the instances
     * 
     * @param fc
     * @param agent
     * @param fmt
     * @param param
     */
    public static void pgvNode(ProgressGraph pg, GvFormatter fmt,
            GvParameters param) {
        //for (Instance scene : fc.getAllScenes()) {
        //    pgvScene.pgvNode(scene, agent, fmt, param);
        //}
    }

}
