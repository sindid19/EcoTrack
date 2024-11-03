package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageTables implements Handler {

    private static final String DATABASE = "jdbc:sqlite:database/new_waste_recycling.db";

    @Override
    public void handle(Context context) throws Exception {
        String html = "<html><head><title>Database Tables</title></head><body>";
        html += "<h1>Database Tables</h1>";

        List<String> tables = Arrays.asList(
            "regional_total", "regional_recycling", "regional_organics", "regional_waste",
            "lga_organics_2019", "lga_organics_2020", "lga_waste_2020"
        );

        for (String table : tables) {
            html += "<h2>" + table + "</h2>";
            html += getTableData(table);
        }

        html += "</body></html>";
        context.html(html);
    }

    private String getTableData(String tableName) throws SQLException {
        StringBuilder html = new StringBuilder();
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(DATABASE);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            html.append("<table border='1'><tr>");
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                html.append("<th>").append(rs.getMetaData().getColumnName(i)).append("</th>");
            }
            html.append("</tr>");

            while (rs.next()) {
                html.append("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    html.append("<td>").append(rs.getString(i)).append("</td>");
                }
                html.append("</tr>");
            }
            html.append("</table>");
        }
        return html.toString();
    }

    private void logAvailableTables() {
        try (Connection conn = DriverManager.getConnection(DATABASE);
             ResultSet rs = conn.getMetaData().getTables(null, null, "%", null)) {
            System.out.println("Available tables:");
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}