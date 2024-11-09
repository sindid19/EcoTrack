package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the 
 * Javalin HTTP Server and our web application.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

public class App {

    public static final int         JAVALIN_PORT    = 7001;
    // public static final String      CSS_DIR         = "css/";
    public static final String      IMAGES_DIR      = "images/";

    public static void main(String[] args) {
        // Create our HTTP server and listen in port 7001
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            
            // Uncomment this if you have files in the CSS Directory
            // config.addStaticFiles(CSS_DIR);

            // Uncomment this if you have files in the Images Directory
            config.addStaticFiles(IMAGES_DIR);
        }).start(JAVALIN_PORT);


        // Configure Web Routes
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        app.get(PageIndex.URL, new PageIndex());
        app.get(AboutUsPage.URL, new AboutUsPage());

        // app.get(DashboardPage.URL, new DashboardPage());
        app.get(TipsPage.URL, new TipsPage());
        app.get(LookupPage.URL, new LookupPage());
        app.get(MissionPage.URL, new MissionPage());
        app.get(LGAFocusPage.URL, new LGAFocusPage());
        app.get(RegionalGroupPage.URL, new RegionalGroupPage());
    }

}
