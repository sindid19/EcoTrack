package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

public class JDBCConnection {

    // Update the database path
    public static final String DATABASE = "jdbc:sqlite:database/new_waste_recycling.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }


    


    
    public ArrayList<Map<String, String>> getWasteInformation() {
        ArrayList<Map<String, String>> wasteData = new ArrayList<>();
        
        String query = """
            SELECT 
                regional_group,
                total_collected,
                total_recycled,
                total_disposed,
                ROUND((CAST(total_disposed AS FLOAT) / CAST(total_collected AS FLOAT)) * 100, 2) as disposal_percentage
            FROM regional_total
        """;

        try (Connection connection = DriverManager.getConnection(DATABASE);
             Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(query)) {
            
            while (results.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("regional_group", results.getString("regional_group"));
                row.put("total_collected", results.getString("total_collected"));
                row.put("total_recycled", results.getString("total_recycled"));
                row.put("total_disposed", results.getString("total_disposed"));
                row.put("disposal_percentage", results.getString("disposal_percentage"));
                wasteData.add(row);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return wasteData;
    }

}
