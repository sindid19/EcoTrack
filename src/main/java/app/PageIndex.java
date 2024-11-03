package app;

import java.util.ArrayList;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageIndex implements Handler {
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        String html = """
            <!DOCTYPE html>
            <html lang='en'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>EcoTrack - Home</title>
                <style>
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
                    
                    .hero {
                        background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('recycling-hero.jpg');
                        background-size: cover;
                        color: white;
                        text-align: center;
                        padding: 4rem 2rem;
                    }
                    
                    .hero h2 {
                        font-size: 2.5rem;
                        margin-bottom: 1rem;
                    }
                    
                    .cta-button {
                        display: inline-block;
                        background: #2e7d32;
                        color: white;
                        padding: 1rem 2rem;
                        border-radius: 5px;
                        text-decoration: none;
                        margin-top: 1rem;
                        transition: background 0.3s;
                    }
                    
                    .cta-button:hover {
                        background: #1b5e20;
                    }
                    
                    .features {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                        gap: 2rem;
                        padding: 4rem 2rem;
                        max-width: 1200px;
                        margin: 0 auto;
                    }
                    
                    .feature-card {
                        background: black;
                        padding: 2rem;
                        border-radius: 10px;
                        text-align: center;
                        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                        transition: transform 0.3s;
                    }
                    
                    .feature-card:hover {
                        transform: translateY(-5px);
                    }
                    
                    .feature-card img {
                        width: 64px;
                        height: 64px;
                        margin-bottom: 1rem;
                    }
                    
                    .stats-section {
                        background: #2e7d32;
                        color: white;
                        padding: 4rem 2rem;
                        text-align: center;
                    }
                    
                    .stats-grid {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                        gap: 2rem;
                        max-width: 1200px;
                        margin: 0 auto;
                    }
                    
                   
                </style>
            </head>
        """;

        // Header (reusing structure from existing code)
        html += """
            <body>
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
                
                <section class='hero'>
                    <h2>Make a Difference Today</h2>
                    <p>Join our community and learn how to make sustainable choices for a better tomorrow</p>
                    <a href='/mission' class='cta-button'>Get Started</a>
                </section>
                

                <section class='features'>
                    <div class='feature-card'>
                        <h3>DATA FROM 2018 TO 2019</h3>
                        <p>Total 128 LGAs in 3,232,559 households were surveyed 2018 to 2019.</p>
                    </div>
                    
                    <div class='feature-card'>
                        <h3>DATA FROM 2019 TO 2020</h3>
                        <p>Total 128 LGAs in 3,342,024 households were surveyed 2019 to 2020.</p>
                    </div>
                    
                </section>
                
                <section class='stats-section'>
                    <h2>Our Impact</h2>
                    <div class='stats-grid'>
                        <div class='stat-item'>
                            <h3>3+</h3>
                            <p>Active Users</p>
                        </div>
                        <div class='stat-item'>
                            <h3>90000kg+</h3>
                            <p>Waste Recycled</p>
                        </div>
                        <div class='stat-item'>
                            <h3>4+</h3>
                            <p>Local Communities</p>
                        </div>
                    </div>
                </section>
                
                <footer>
                    <p>&copy; 2024 EcoTrack. All rights reserved.</p>
                </footer>
            </body>
            </html>
        """;

        context.html(html);
    }

    // Keeping the existing database method
    public ArrayList<String> getAllLGAs() {
        ArrayList<String> lgas = new ArrayList<String>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery("SELECT * FROM lga");

            while (results.next()) {
                lgas.add(results.getString("name"));
            }

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return lgas;
    }
}
