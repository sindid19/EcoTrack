package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionalGroupPage implements Handler {
    public static final String URL = "/regional-group";

    // Static demo data for regional waste statistics
    private List<Map<String, Object>> getRegionalData() {
        List<Map<String, Object>> data = new ArrayList<>();
        
        // Sydney Metropolitan Area (SMA) data
        Map<String, Object> sma2020 = new HashMap<>();
        sma2020.put("region", "SMA");
        sma2020.put("period", "2019-2020");
        sma2020.put("collected", 8500.5);
        sma2020.put("recycled", 6000.3);
        sma2020.put("disposed", 2500.2);
        sma2020.put("stateDisposalPercentage", 45.2);
        data.add(sma2020);

        Map<String, Object> sma2019 = new HashMap<>();
        sma2019.put("region", "SMA");
        sma2019.put("period", "2018-2019");
        sma2019.put("collected", 8200.1);
        sma2019.put("recycled", 5800.4);
        sma2019.put("disposed", 2400.7);
        sma2019.put("stateDisposalPercentage", 44.8);
        data.add(sma2019);

        // Extended Regulated Area (ERA) data
        Map<String, Object> era2020 = new HashMap<>();
        era2020.put("region", "ERA");
        era2020.put("period", "2019-2020");
        era2020.put("collected", 4500.3);
        era2020.put("recycled", 3200.1);
        era2020.put("disposed", 1300.2);
        era2020.put("stateDisposalPercentage", 23.5);
        data.add(era2020);

        // Add more years of data for better comparison
        // SMA data for 2020-2021
        Map<String, Object> sma2021 = new HashMap<>();
        sma2021.put("region", "SMA");
        sma2021.put("period", "2020-2021");
        sma2021.put("collected", 8800.7);
        sma2021.put("recycled", 6200.5);
        sma2021.put("disposed", 2600.2);
        sma2021.put("stateDisposalPercentage", 46.0);
        data.add(sma2021);

        // Add RRA data
        Map<String, Object> rra2021 = new HashMap<>();
        rra2021.put("region", "RRA");
        rra2021.put("period", "2020-2021");
        rra2021.put("collected", 3500.4);
        rra2021.put("recycled", 2400.2);
        rra2021.put("disposed", 1100.2);
        rra2021.put("stateDisposalPercentage", 19.8);
        data.add(rra2021);

        return data;
    }

    // Add new method to calculate period changes
    private Map<String, Object> calculatePeriodChanges(List<Map<String, Object>> data, 
            String startPeriod, String endPeriod, String region) {
        Map<String, Object> startData = null;
        Map<String, Object> endData = null;
        
        for (Map<String, Object> entry : data) {
            if (entry.get("region").equals(region)) {
                if (entry.get("period").equals(startPeriod)) {
                    startData = entry;
                } else if (entry.get("period").equals(endPeriod)) {
                    endData = entry;
                }
            }
        }

        Map<String, Object> changes = new HashMap<>();
        if (startData != null && endData != null) {
            changes.put("startCollected", startData.get("collected"));
            changes.put("endCollected", endData.get("collected"));
            changes.put("startRecycled", startData.get("recycled"));
            changes.put("endRecycled", endData.get("recycled"));
            
            double collectedChange = ((double)endData.get("collected") - (double)startData.get("collected")) / (double)startData.get("collected") * 100;
            double recycledChange = ((double)endData.get("recycled") - (double)startData.get("recycled")) / (double)startData.get("recycled") * 100;
            
            changes.put("collectedChange", collectedChange);
            changes.put("recycledChange", recycledChange);
        }
        return changes;
    }

    @Override
    public void handle(Context context) throws Exception {
        List<Map<String, Object>> regionalData = getRegionalData();

        String html = """
            <!DOCTYPE html>
            <html lang='en'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Regional Group Waste Analysis - EcoTrack</title>
                <style>
                    /* Reuse common styles from PageIndex.java */
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
                        color: #00ff99; /* Neon green */
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
                        color: #00ff99; /* Neon green */
                    }

                    footer {
                        background-color: #18191f;
                        color: white;
                        text-align: center;
                        padding: 20px;
                        margin-top: 40px;
                    }

                    /* Existing styles remain unchanged */
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 0;
                        background-color: #1a1d23;
                        color: #ffffff;
                        line-height: 1.6;
                    }

                    .filters {
                        background: #25282d;
                        padding: 1.5rem;
                        border-radius: 8px;
                        margin-bottom: 2rem;
                    }

                    .filter-group {
                        margin-bottom: 1rem;
                    }

                    .filter-group label {
                        display: block;
                        margin-bottom: 0.5rem;
                        color: #00ff99;
                    }

                    select, input, button {
                        background: #1a1d23;
                        color: white;
                        padding: 0.5rem;
                        border: 1px solid #00ff99;
                        border-radius: 4px;
                        margin-right: 1rem;
                    }

                    .results-table {
                        width: 100%;
                        border-collapse: collapse;
                        margin-top: 2rem;
                    }

                    .results-table th, .results-table td {
                        padding: 1rem;
                        text-align: left;
                        border-bottom: 1px solid #25282d;
                    }

                    .results-table th {
                        background: #2e7d32;
                        cursor: pointer;
                    }

                    .results-table th:hover {
                        background: #1b5e20;
                    }

                    .results-table tr:hover {
                        background: #25282d;
                    }
                </style>
            </head>
            <body>
        """;

        html += """
            <header>
                <div class='logo'>
                    <img src='recycle_logo.png' alt='EcoTrack Logo'>
                    <h1>EcoTrack</h1>
                </div>
                <nav>
                    <ul>
                        <li><a href='/'>Home</a></li>
                        <li><a href='/mission' '>Our Mission</a></li>
                        <li><a href='/about'>About Us</a></li>
                        <li><a href='/lga-focus'>LGA Analysis</a></li>
                        <li><a href='/regional-group'>Regional Analysis</a></li>
                        <li><a href='/tips'>Tips</a></li>
                        <li><a href='/lookup'>Lookup</a></li>
                    </ul>
                </nav>
            </header>
        """;

        // Add header (reusing from PageIndex.java)
        html += """
                <main class='analysis-content'>
                    <h1>Regional Group Waste Analysis</h1>
                    
                    <div class='filters'>
                        <div class='filter-group'>
                            <label>Select Regional Group:</label>
                            <select id='region'>
                                <option value='SMA'>Sydney Metropolitan Area (SMA)</option>
                                <option value='ERA'>Extended Regulated Area (ERA)</option>
                                <option value='RRA'>Regional Regulated Area (RRA)</option>
                                <option value='NSW'>Rest of NSW</option>
                            </select>
                        </div>

                        <div class='filter-group'>
                            <label>Start Period:</label>
                            <select id='startPeriod'>
                                <option value='2018-2019'>2018-2019</option>
                                <option value='2019-2020'>2019-2020</option>
                                <option value='2020-2021'>2020-2021</option>
                            </select>
                        </div>

                        <div class='filter-group'>
                            <label>End Period:</label>
                            <select id='endPeriod'>
                                <option value='2020-2021'>2020-2021</option>
                                <option value='2019-2020'>2019-2020</option>
                                <option value='2018-2019'>2018-2019</option>
                            </select>
                        </div>

                        <div class='filter-group'>
                            <label>Waste Resource Type:</label>
                            <select id='resourceType'>
                                <option value='recyclable'>Recyclable</option>
                                <option value='organics'>Organics</option>
                                <option value='waste'>Waste</option>
                            </select>
                        </div>

                        <div class='filter-group'>
                            <label>Change Display:</label>
                            <select id='changeDisplay'>
                                <option value='percentage'>Percentage Change</option>
                                <option value='absolute'>Absolute Difference</option>
                            </select>
                        </div>

                        <div class='filter-group'>
                            <label>Threshold Statistic:</label>
                            <select id='threshold'>
                                <option value='above'>Above Threshold</option>
                                <option value='below'>Below Threshold</option>
                                <option value='all'>All Results</option>
                            </select>
                            <input type='number' id='thresholdValue' placeholder='Enter threshold value' 
                                   step='0.1' min='0'>
                        </div>

                        <div class='filter-group'>
                            <label>Sort By:</label>
                            <select id='sortBy'>
                                <option value='period'>Period</option>
                                <option value='collected'>Total Collected</option>
                                <option value='recycled'>Total Recycled</option>
                                <option value='disposed'>Total Disposed</option>
                                <option value='percentage'>State Disposal %</option>
                                <option value='change'>Change %</option>
                            </select>
                            <select id='sortOrder'>
                                <option value='asc'>Ascending</option>
                                <option value='desc'>Descending</option>
                            </select>
                        </div>
                    </div>

                    <div class='comparison-results'>
                        <h2>Period Comparison</h2>
                        <table class='results-table'>
                            <thead>
                                <tr>
                                    <th>Metric</th>
                                    <th>Start Period</th>
                                    <th>End Period</th>
                                    <th>Change</th>
                                </tr>
                            </thead>
                            <tbody>
        """;

        // Add demo comparison data
        Map<String, Object> changes = calculatePeriodChanges(getRegionalData(), "2018-2019", "2020-2021", "SMA");
        if (!changes.isEmpty()) {
            html += String.format("""
                                <tr>
                                    <td>Total Collected (t)</td>
                                    <td>%.1f</td>
                                    <td>%.1f</td>
                                    <td>%.1f%%</td>
                                </tr>
                                <tr>
                                    <td>Total Recycled (t)</td>
                                    <td>%.1f</td>
                                    <td>%.1f</td>
                                    <td>%.1f%%</td>
                                </tr>
                            """,
                    (double)changes.get("startCollected"),
                    (double)changes.get("endCollected"),
                    (double)changes.get("collectedChange"),
                    (double)changes.get("startRecycled"),
                    (double)changes.get("endRecycled"),
                    (double)changes.get("recycledChange")
            );
        }

        html += """
                            </tbody>
                        </table>
                    </div>

                    <table class='results-table'>
                        <thead>
                            <tr>
                                <th>Annual Period</th>
                                <th>Total Collected (t)</th>
                                <th>Total Recycled (t)</th>
                                <th>Total Disposed (t)</th>
                                <th>State Disposal %</th>
                            </tr>
                        </thead>
                        <tbody>
        """;

        // Add demo data rows
        for (Map<String, Object> region : regionalData) {
            html += String.format("""
                            <tr>
                                <td>%s</td>
                                <td>%.1f</td>
                                <td>%.1f</td>
                                <td>%.1f</td>
                                <td>%.1f%%</td>
                            </tr>
                        """,
                region.get("period"),
                (double)region.get("collected"),
                (double)region.get("recycled"),
                (double)region.get("disposed"),
                (double)region.get("stateDisposalPercentage")
            );
        }

        html += """
                        </tbody>
                    </table>
                </main>
                <footer>
                    <p>&copy; 2024 EcoTrack. All rights reserved.</p>
                </footer>
            </body>
            </html>
        """;

        context.html(html);
    }
}
