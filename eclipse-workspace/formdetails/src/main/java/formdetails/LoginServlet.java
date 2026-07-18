package formdetails;


import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><link rel='stylesheet' href='login.css'></head><body>");
        out.println("<div class='form-container' style='text-align:center;'>");

        String storedPassword = FormDetails.userStore.get(email);

        if (storedPassword != null && storedPassword.equals(password)) {
            out.println("<h2>Welcome to the page</h2>");
            out.println("<p>Logged in as: " + email + "</p>");
        } else {
            out.println("<h2>Login Failed</h2>");
            out.println("<p>Invalid email or password.</p>");
            out.println("<p><a href='login.html'>Try again</a></p>");
        }

        out.println("</div></body></html>");
    }
}