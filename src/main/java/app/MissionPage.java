package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class MissionPage implements Handler {
    public static final String URL = "/mission";

    // Static demo data for team members
    private ArrayList<Map<String, String>> getTeamMembers() {
        ArrayList<Map<String, String>> teamMembers = new ArrayList<>();

        Map<String, String> member1 = new HashMap<>();
        member1.put("full_name", "Sindid Ahmed");
        member1.put("student_id", "4113547");
        member1.put("role", "Lead Developer");
        teamMembers.add(member1);

        Map<String, String> member2 = new HashMap<>();
        member2.put("full_name", "BLANK");
        member2.put("student_id", "BLANK");
        member2.put("role", "Database Specialist");
        teamMembers.add(member2);

        Map<String, String> member3 = new HashMap<>();
        member3.put("full_name", "BLANK");
        member3.put("student_id", "BLANK");
        member3.put("role", "UI/UX Designer");
        teamMembers.add(member3);

        return teamMembers;
    }

    // Static demo data for personas
    private ArrayList<Map<String, String>> getPersonas() {
        ArrayList<Map<String, String>> personas = new ArrayList<>();
        
        Map<String, String> persona1 = new HashMap<>();
        persona1.put("name", "Eco-conscious Emma");
        persona1.put("age", "28");
        persona1.put("occupation", "Environmental Engineer");
        persona1.put("description", "A passionate environmentalist looking to make a difference in her community");
        persona1.put("goals", "Track community recycling impact, Organize local cleanup events");
        persona1.put("challenges", "Coordinating with local authorities, Engaging community members");
        personas.add(persona1);

        Map<String, String> persona2 = new HashMap<>();
        persona2.put("name", "Student Sam");
        persona2.put("age", "21");
        persona2.put("occupation", "University Student");
        persona2.put("description", "A student living in shared accommodation wanting to implement sustainable practices");
        persona2.put("goals", "Learn proper recycling methods, Reduce waste in student housing");
        persona2.put("challenges", "Limited space, Coordinating with housemates");
        personas.add(persona2);

        return personas;
    }

    @Override
    public void handle(Context context) throws Exception {
        // Get static demo data
        ArrayList<Map<String, String>> teamMembers = getTeamMembers();
        ArrayList<Map<String, String>> personas = getPersonas();

        String html = """
            <!DOCTYPE html>
            <html lang='en'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Our Mission - EcoTrack</title>
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

                    .mission-content {
                        max-width: 1200px;
                        margin: 0 auto;
                        padding: 2rem;
                    }

                    .mission-section {
                        background: black;
                        padding: 2rem;
                        border-radius: 10px;
                        margin-bottom: 2rem;
                    }

                    .personas-grid {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
                        gap: 2rem;
                        margin-top: 2rem;
                    }

                    .persona-card {
                        background: #25282d;
                        padding: 1.5rem;
                        border-radius: 8px;
                        border-left: 4px solid #2e7d32;
                    }

                    .team-grid {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                        gap: 1.5rem;
                        margin-top: 2rem;
                    }

                    .team-member-card {
                        background: #25282d;
                        padding: 1.5rem;
                        border-radius: 8px;
                        text-align: center;
                    }
                </style>
            </head>
            <body>
                <!-- Reuse header from other pages -->
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

                <main class='mission-content'>
                    <section class='mission-section'>
                        <h2 '>Our Mission</h2>
                        <p>EcoTrack is dedicated to addressing the critical challenge of waste management and environmental sustainability in our communities. We believe that by providing accessible tools and information, we can empower individuals and organizations to make more environmentally conscious decisions.</p>
                        
                        <h3  margin-top: 2rem;'>How to Use EcoTrack</h3>
                        <ul>
                            <li>Use our Recycling Lookup Tool to quickly find proper disposal methods</li>
                            <li>Track your recycling progress and impact</li>
                            <li>Access educational resources and tips</li>
                            <li>Connect with like-minded individuals in your community</li>
                        </ul>
                    </section>

                    <section class='mission-section'>
                        <h2 '>Our Target Users</h2>
                        <div class='personas-grid'>
        """;

        // Add personas dynamically
        for (Map<String, String> persona : personas) {
            html += """
                <div class='persona-card'>
                    <h3>%s</h3>
                    <p><strong>Age:</strong> %s</p>
                    <p><strong>Occupation:</strong> %s</p>
                    <p>%s</p>
                    <p><strong>Goals:</strong> %s</p>
                    <p><strong>Challenges:</strong> %s</p>
                </div>
            """.formatted(
                persona.get("name"),
                persona.get("age"),
                persona.get("occupation"),
                persona.get("description"),
                persona.get("goals"),
                persona.get("challenges")
            );
        }

        html += """
                        </div>
                    </section>

                    <section class='mission-section'>
                        <h2 '>Our Team</h2>
                        <div class='team-grid'>
        """;

        // Add team members dynamically
        for (Map<String, String> member : teamMembers) {
            html += """
                <div class='team-member-card'>
                    <h3>%s</h3>
                    <p>Student ID: %s</p>
                    <p>Role: %s</p>
                </div>
            """.formatted(
                member.get("full_name"),
                member.get("student_id"),
                member.get("role")
            );
        }

        html += """
                        </div>
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
}
