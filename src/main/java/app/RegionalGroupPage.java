package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionalGroupPage implements Handler {
    public static final String URL = "/regional-group";
private static final String DATABASE = "jdbc:sqlite:database/new_waste_recycling.db";

   
  
    @Override
    public void handle(Context context) throws Exception {
        String html = """
            <html>
            <head>
                <title>Regional Group Waste Analysis</title>
                <style>
                    /* Existing styles */
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 0;
                        background-color: #1a1d23;
                        color: #ffffff;
                        line-height: 1.6;
                    }
                    
                    header {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        background-color: #25282d;
                        padding: 20px;
                        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);
                    }

                    .logo {
                        display: flex;
                        align-items: center;
                    }

                    .logo img {
                        height: 50px;
                        margin-right: 10px;
                    }

                    .logo h1 {
                        color: #00ff99;
                        font-size: 24px;
                    }

                    nav ul {
                        list-style: none;
                        display: flex;
                        gap: 20px;
                        margin: 0;
                        padding: 0;
                    }

                    nav a {
                        text-decoration: none;
                        color: #ffffff;
                        font-weight: bold;
                        transition: color 0.3s;
                    }

                    nav a:hover {
                        color: #00ff99;
                    }

                    /* New styles for the filter option */
                    form {
                        margin: 20px;
                    }

                    label {
                        font-size: 18px;
                        margin-right: 10px;
                    }

                    select {
                        padding: 5px;
                        font-size: 16px;
                        border-radius: 5px;
                        border: 1px solid #00ff99;
                        background-color: #25282d;
                        color: #ffffff;
                    }

                    button {
                        padding: 5px 10px;
                        font-size: 16px;
                        border-radius: 5px;
                        border: none;
                        background-color: #00ff99;
                        color: #1a1d23;
                        cursor: pointer;
                        transition: background-color 0.3s;
                    }

                    button:hover {
                        background-color: #00cc7a;
                    }
                    
                    /* Rest of your existing styles */
                </style>
            </head>
            <body>
                <header>
                    <div class='logo'>
                        <img src='recycle_logo.png' alt='EcoTrack Logo'>
                        <h1>EcoTrack</h1>
                    </div>
                    <nav>
                        <ul>
                            <li><a href='/'>Home</a></li>
                            <li><a href='/mission'>Our Mission</a></li>
                            <li><a href='/about'>About Us</a></li>
                            <li><a href='/lga-focus'>LGA Analysis</a></li>
                            <li><a href='/regional-group'>Regional Analysis</a></li>
                            <li><a href='/tips'>Tips</a></li>
                            <li><a href='/lookup'>Lookup</a></li>
                        </ul>
                    </nav>
                </header>
                
                <h1>Regional Group Waste Analysis</h1>
                <form method='get'>
                    <label for='categorySelect'>Choose a category:</label>
                    <select name='category' id='categorySelect'>
                        <option value='organics'>Organics</option>
                        <option value='recycling'>Recycling</option>
                        <option value='waste'>Waste</option>
                    </select>
                    
                    <label for='yearSelect'>Choose a year:</label>
                    <select name='year' id='yearSelect'>
                        <option value='2019'>2019</option>
                        <option value='2020'>2020</option>
                    </select>
                    
                    <label for='regionSelect'>Select Regional Group:</label>
                    <select name='region' id='regionSelect'>
                        <option value='sma'>Sydney Metropolitan Area (SMA)</option>
                        <option value='era'>Extended Regulated Area (ERA)</option>
                        <option value='rra'>Regional Regulated Area (RRA)</option>
                        <option value='rest'>Rest of NSW</option>
                    </select>
                    
                    <label for='sortSelect'>Sort by:</label>
                    <select name='sort' id='sortSelect'>
                        <option value='asc'>Ascending</option>
                        <option value='desc'>Descending</option>
                    </select>
                    
                    <button type='submit'>Show Table</button>
                </form>
            """;

        // Get the selected category and year from the request
        String category = context.queryParam("category");
        String year = context.queryParam("year");

        // Default to 'organics' and '2019' if not specified
        if (category == null || category.isEmpty()) {
            category = "organics";
        }
        if (year == null || year.isEmpty()) {
            year = "2019";
        }

        // Get the selected sort order from the request
        String sortOrder = context.queryParam("sort");
        // Default to 'asc' if not specified
        if (sortOrder == null || sortOrder.isEmpty()) {
            sortOrder = "asc";
        }

        // Construct the table name
        String selectedTable = "lga_" + category + "_" + year;

        // Modify the query to include sorting
        String query = "SELECT * FROM " + selectedTable + " ORDER BY some_column " + (sortOrder.equals("desc") ? "DESC" : "ASC");

        html += "<h2>" + selectedTable + "</h2>";
        html += getTableData(selectedTable);

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
            // Removed the loop that adds column names
            html.append("</tr>");

            while (rs.next()) {
                html.append("<tr>");
                int columnCount = rs.getMetaData().getColumnCount(); // Moved inside the loop
                for (int i = 1; i <= columnCount; i++) {
                    String cellValue = rs.getString(i);
                    if (cellValue != null) { // Check for null values
                        html.append("<td>").append(cellValue).append("</td>");
                    } else {
                        html.append("<td></td>"); // Optionally, you can append an empty cell for null values
                    }
                }
                html.append("</tr>");
            }
            html.append("</table>");
        }
        return html.toString();
    }
}
