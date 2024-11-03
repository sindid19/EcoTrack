package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class LookupPage implements Handler {
    public static final String URL = "/lookup";

    @Override
    public void handle(Context context) throws Exception {
        String html = """
            <!DOCTYPE html>
            <html lang='en'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Recycling Lookup - EcoTrack</title>
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
                    
                    .lookup-content {
                        max-width: 800px;
                        margin: 2rem auto;
                        padding: 0 1rem;
                    }
                    
                    .search-container {
                        background: black;
                        padding: 2rem;
                        border-radius: 8px;
                        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                        margin-bottom: 2rem;
                    }
                    
                    .search-form {
                        display: flex;
                        gap: 1rem;
                        margin-bottom: 1rem;
                    }
                    
                    .search-form input {
                        flex: 1;
                        padding: 0.8rem;
                        border: 1px solid #ddd;
                        border-radius: 4px;
                        font-size: 1rem;
                    }
                    
                    .search-form button {
                        background: #2e7d32;
                        color: white;
                        border: none;
                        padding: 0.8rem 1.5rem;
                        border-radius: 4px;
                        cursor: pointer;
                        transition: background-color 0.3s;
                    }
                    
                    .search-form button:hover {
                        background: #1b5e20;
                    }
                    
                    .categories {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
                        gap: 1rem;
                        margin-top: 2rem;
                    }
                    
                    .category-card {
                        background: black;
                        padding: 1rem;
                        border-radius: 8px;
                        text-align: center;
                        cursor: pointer;
                        transition: transform 0.3s;
                    }
                    
                    .category-card:hover {
                        transform: translateY(-5px);
                    }
                    
                    .category-card img {
                        width: 48px;
                        height: 48px;
                        margin-bottom: 0.5rem;
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
                
                <main class='lookup-content'>
                    <h2 style='text-align: center; color: #2e7d32; margin: 2rem 0;'>Recycling Lookup Tool</h2>
                    
                    <div class='search-container'>
                        <form class='search-form'>
                            <input type='text' placeholder='Search for an item...' required>
                            <button type='submit'>Search</button>
                        </form>
                        
                        <div class='categories'>
                            <div class='category-card'>
                                <img src='plastic-icon.png' alt='Plastic'>
                                <h4>Plastic</h4>
                            </div>
                            <div class='category-card'>
                                <img src='paper-icon.png' alt='Paper'>
                                <h4>Paper</h4>
                            </div>
                            <div class='category-card'>
                                <img src='glass-icon.png' alt='Glass'>
                                <h4>Glass</h4>
                            </div>
                            <div class='category-card'>
                                <img src='metal-icon.png' alt='Metal'>
                                <h4>Metal</h4>
                            </div>
                        </div>
                    </div>
                    
                    <div id='searchResults'></div>
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