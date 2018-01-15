package yaes.cssm.util.webgui.httpserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import yaes.cssm.scenarios.SocialCalculusContext;


public class WebGui {

    
    /**
     * Utility function: returns true if the UI is started correctly
     * 
     * @param agent
     * @return
     */
    public static boolean startWebGui(SocialCalculusContext scc) {
        return startWebGui(scc, 1500);
    }

    /**
     * Utility function: returns true if the UI is started correctly
     * 
     * @param agent
     * @param port
     * @return
     */
    @SuppressWarnings("unused")
    public static boolean startWebGui(SocialCalculusContext scc, int port) {
        try {
            new WebGui(scc, port);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Creates a web based GUI for the specified agent at listening the
     * specified port
     * 
     * @param agent
     * @param port
     * @throws FileNotFoundException
     * @throws IOException
     */
    private WebGui(SocialCalculusContext scc, int port) throws FileNotFoundException,
            IOException {
        File dir = new File("output");
        WgPageGenerator gfs = new WgPageGenerator(scc, dir);
        WgServerThread gs = new WgServerThread(port, gfs);
        gs.start();
        Thread t = new Thread(gs);
        t.start();
    }

}
