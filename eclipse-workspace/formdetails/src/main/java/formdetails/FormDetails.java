package formdetails;

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/formdetails")
public class FormDetails extends HttpServlet {

    // Shared in-memory "database" — email -> password
    public static Map<String, String> userStore = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        // Save credentials (overwrites if email already exists)
        userStore.put(email, password);

        // Redirect straight to login page — no data printed
        response.sendRedirect("login.html");
    }
}