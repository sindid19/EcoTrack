package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class DashboardPage implements Handler {
    public static final String URL = "/dashboard";

    @Override
    public void handle(Context context) throws Exception {
        // Check if user is logged in
        // If not, redirect to login page

        String html = "<!DOCTYPE html><html lang='en'>";
        // Add head section

        html += "<main class='dashboard-content'>" +
                "<div class='dashboard-grid'>" +
                "<div class='dashboard-card'>" +
                "<h3>Recycling Stats</h3>" +
                // Add recycling statistics
                "</div>" +
                
                "<div class='dashboard-card'>" +
                "<h3>Recent Activities</h3>" +
                // Add recent activities
                "</div>" +
                
                "<div class='dashboard-card'>" +
                "<h3>Tips</h3>" +
                // Add personalized tips
                "</div>" +
                "</div>" +
                "</main>";

        // Add footer
        html += "</body></html>";
        context.html(html);
    }
} 