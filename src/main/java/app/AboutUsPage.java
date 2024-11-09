package app;

import io.javalin.http.Handler;
import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AboutUsPage implements Handler {
    public static final String URL = "/about";

    @Override
    public void handle(Context context) throws Exception {
        String html = """
            <!DOCTYPE html>
            <html lang='en'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>About Us - EcoTrack</title>
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
                    
                    .about-content {
                        max-width: 1200px;
                        margin: 40px auto;
                        padding: 0 20px;
                    }
                    
                    .mission {
                        background-color: white;
                        padding: 40px;
                        border-radius: 10px;
                        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                        margin-bottom: 40px;
                    }
                    
                    .team-grid {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                        gap: 30px;
                        margin: 40px 0;
                    }
                    
                    .team-member {
                        background: black;
                        padding: 20px;
                        border-radius: 10px;
                        text-align: center;
                        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                    }
                    
                    .team-member img {
                        width: 150px;
                        height: 150px;
                        border-radius: 50%;
                        margin-bottom: 15px;
                    }
                    
                    .contact-form {
                        background: black;
                        padding: 40px;
                        border-radius: 10px;
                        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                    }
                    
                    .contact-form input,
                    .contact-form textarea {
                        width: 100%;
                        padding: 12px;
                        margin-bottom: 20px;
                        border: 1px solid #ddd;
                        border-radius: 5px;
                        font-size: 16px;
                    }
                    
                    .contact-form textarea {
                        height: 150px;
                        resize: vertical;
                    }
                    
                    .contact-form button {
                        background-color: green;
                        color: white;
                        padding: 12px 30px;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;
                        font-size: 16px;
                        transition: background-color 0.3s;
                    }
                    
                    .contact-form button:hover {
                        background-color: darkgreen;
                    }
                    
                    
                </style>
            </head>
            <body>
        """;

        // Check if the request is a POST for form submission
        if ("POST".equalsIgnoreCase(context.method())) {
            String name = context.formParam("name");
            String email = context.formParam("email");
            String message = context.formParam("message");
            saveContactForm(name, email, message);
            context.html("<h1>Thank you for your message!</h1>");
            return;
        }

        // Add header (reusing structure from PageIndex.java)
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
                
                <main class='about-content'>
                    <section class='mission' style='background-color: black;'>
                        <h2 style='color: green; margin-bottom: 20px;'>Our Mission</h2>
                        <p>At EcoTrack, we are committed to creating a sustainable future through proper waste management and recycling practices. Our mission is to educate, empower, and enable communities to make environmentally conscious decisions in their daily lives.</p>
                    </section>
                    
                    <section class='team'>
                        <h2 style='color: green; text-align: center; margin-bottom: 30px;'>Our Team</h2>
                        <div class='team-grid'>
                            <div class='team-member'>
                                <h3>Sindid Ahmed</h3>
                                <p>Student ID: 4113547</p>
                                <p>Lead Developer</p>
                            </div>
                            <div class='team-member'>
                                <h3>BLANK</h3>
                                <p>BLANKr</p>
                            </div>
                            <div class='team-member'>
                                <h3>BLANK</h3>
                                <p>BLANK</p>
                            </div>
                        </div>
                    </section>
                    
                    <section class='contact'>
                        <h2 style='color: green; text-align: center; margin-bottom: 30px;'>Contact Us</h2>
                        <form class='contact-form'>
                            <input type='text' placeholder='Name' required>
                            <input type='email' placeholder='Email' required>
                            <textarea placeholder='Message' required></textarea>
                            <button type='submit'>Send Message</button>
                        </form>
                    </section>
                </main>
                
                <footer>
                    <p>&copy; 2024 EcoTrack. All rights reserved.</p>
                </footer>
            </body>
            </html>
        """;

        context.html(html);
    }

    // Method to save contact form data to SQLite database
    private void saveContactForm(String name, String email, String message) {
        String url = "jdbc:sqlite:contact.db"; // Update with your database path
        String sql = "INSERT INTO contact_form(name, email, message) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, message);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
} 