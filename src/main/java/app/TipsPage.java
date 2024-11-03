package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class TipsPage implements Handler {
    public static final String URL = "/tips";

    @Override
    public void handle(Context context) throws Exception {
        String html = """
            <!DOCTYPE html>
            <html lang='en'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Waste Management Tips - EcoTrack</title>
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
                    
                    .tips-grid {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                        gap: 2rem;
                        padding: 2rem;
                        max-width: 1200px;
                        margin: 0 auto;
                    }
                    
                    .tip-card {
                        background: black;
                        padding: 2rem;
                        border-radius: 8px;
                        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                        transition: transform 0.3s;
                    }
                    
                    .tip-card:hover {
                        transform: translateY(-5px);
                    }
                    
                    .tip-card img {
                        width: 64px;
                        height: 64px;
                        margin-bottom: 1rem;
                    }
                    
                    .tip-card h3 {
                        color: #2e7d32;
                        margin-bottom: 1rem;
                    }
                    
                    .tip-card ul {
                        list-style-type: none;
                        padding: 0;
                    }
                    
                    .tip-card li {
                        margin-bottom: 0.5rem;
                        padding-left: 1.5rem;
                        position: relative;
                    }
                    
                    .tip-card li:before {
                        content: '✓';
                        color: #2e7d32;
                        position: absolute;
                        left: 0;
                    }
                    
                    .daily-tip {
                        background: #2e7d32;
                        color: white;
                        padding: 2rem;
                        margin: 2rem auto;
                        max-width: 800px;
                        border-radius: 8px;
                        text-align: center;
                    }
                </style>
            </head>
        """;

        // Add header (reusing from PageIndex.java)
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
                
                <main>
                    <section class='tips-content'>
                        <h2 style='text-align: center; color: #2e7d32; margin: 2rem 0;'>Waste Management Tips</h2>
                        
                        <div class='tips-grid'>
                            <article class='tip-card'>
                                <img src='reduce-icon.png' alt='Reduce'>
                                <h3>Reduce</h3>
                                <ul>
                                    <li>Buy products with minimal packaging</li>
                                    <li>Use reusable bags and containers</li>
                                    <li>Choose durable products over disposable ones</li>
                                </ul>
                            </article>
                            
                            <article class='tip-card'>
                                <img src='reuse-icon.png' alt='Reuse'>
                                <h3>Reuse</h3>
                                <ul>
                                    <li>Repair items instead of replacing them</li>
                                    <li>Donate usable items</li>
                                    <li>Repurpose containers and packaging</li>
                                </ul>
                            </article>
                            
                            <article class='tip-card'>
                                <img src='recycle-icon.png' alt='Recycle'>
                                <h3>Recycle</h3>
                                <ul>
                                    <li>Sort recyclables properly</li>
                                    <li>Clean containers before recycling</li>
                                    <li>Check local recycling guidelines</li>
                                </ul>
                            </article>
                            
                            <article class='tip-card'>
                                <img src='compost-icon.png' alt='Compost'>
                                <h3>Compost</h3>
                                <ul>
                                    <li>Start a home composting system</li>
                                    <li>Learn what can be composted</li>
                                    <li>Use compost in your garden</li>
                                </ul>
                            </article>
                        </div>
                        
                        <section class='daily-tip'>
                            <h3>Tip of the Day</h3>
                            <p id='dailyTip'>Did you know? Recycling one aluminum can saves enough energy to run a TV for three hours!</p>
                        </section>
                    </section>
                </main>
                
                <footer style="background-color: #18191f; color: white; text-align: center; padding: 20px; margin-top: 40px;">
    <p>&copy; 2024 EcoTrack. All rights reserved.</p>
</footer>
            </body>
            </html>
        """;

        context.html(html);
    }
} 