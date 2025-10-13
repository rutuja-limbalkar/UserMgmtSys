package com.mgmt.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {
    String query = "SELECT id, name, email, mobile, dob, city, gender FROM user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        pw.println("<!DOCTYPE html>");
        pw.println("<html lang='en'>");
        pw.println("<head>");
        pw.println("<meta charset='UTF-8'>");
        pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        pw.println("<title>User List</title>");
        pw.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        pw.println("<style>");
        pw.println("body { background: linear-gradient(120deg, #f8f9fa, #e3f2fd); font-family: 'Segoe UI', system-ui, sans-serif; min-height: 100vh; padding: 20px 0; }");
        pw.println(".container { max-width: 1200px; margin: 0 auto; }");
        pw.println(".card { border: none; border-radius: 12px; box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08); overflow: hidden; }");
        pw.println(".card-header { background: linear-gradient(135deg, #4361ee, #3a0ca3); color: white; text-align: center; padding: 25px; border-bottom: none; }");
        pw.println(".table th { background: linear-gradient( #4361ee); color: white; border: none; padding: 15px; text-align: center; }");
        pw.println(".table td { padding: 12px; vertical-align: middle; text-align: center; border-color: #e9ecef; }");
        pw.println(".btn-warning { background: linear-gradient(135deg, #ffc107, #e0a800); border: none; border-radius: 6px; padding: 8px 16px; font-weight: 600; color: #212529; }");
        pw.println(".btn-danger { background: linear-gradient(135deg, #dc3545, #c82333); border: none; border-radius: 6px; padding: 8px 16px; font-weight: 600; }");
        pw.println(".btn-success { background: linear-gradient(135deg, #198754, #146c43); border: none; border-radius: 8px; padding: 10px 25px; font-weight: 600; transition: all 0.3s; }");
        pw.println(".btn-success:hover { transform: translateY(-2px); box-shadow: 0 5px 15px rgba(25, 135, 84, 0.3); }");
        pw.println(".table-hover tbody tr:hover { background-color: rgba(67, 97, 238, 0.05); transition: all 0.3s; }");
        pw.println(".table-striped tbody tr:nth-of-type(odd) { background-color: rgba(0, 0, 0, 0.02); }");
        pw.println(".no-users { padding: 40px; text-align: center; color: #6c757d; }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");

        pw.println("<div class='container'>");
        pw.println("<div class='card'>");
        pw.println("<div class='card-header'>");
        pw.println("<h3 class='mb-0'>Registered Users</h3>");
        pw.println("</div>");
        pw.println("<div class='card-body p-0'>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/usermgmt", "root", "rutuja");
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            pw.println("<div class='table-responsive'>");
            pw.println("<table class='table table-bordered table-striped table-hover align-middle mb-0'>");
            pw.println("<thead>");
            pw.println("<tr>");
            pw.println("<th>ID</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>Email</th>");
            pw.println("<th>Mobile</th>");
            pw.println("<th>DOB</th>");
            pw.println("<th>City</th>");
            pw.println("<th>Gender</th>");
            pw.println("<th>Actions</th>");
            pw.println("</tr>");
            pw.println("</thead>");
            pw.println("<tbody>");

            boolean found = false;
            while (rs.next()) {
                found = true;
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt("id") + "</td>");
                pw.println("<td>" + rs.getString("name") + "</td>");
                pw.println("<td>" + rs.getString("email") + "</td>");
                pw.println("<td>" + rs.getString("mobile") + "</td>");
                pw.println("<td>" + rs.getDate("dob") + "</td>");
                pw.println("<td>" + rs.getString("city") + "</td>");
                pw.println("<td>" + rs.getString("gender") + "</td>");
                pw.println("<td>");
                pw.println("<a href='editurl?id=" + rs.getInt("id")
                        + "' class='btn btn-warning btn-sm me-2'>Edit</a>");
                pw.println("<a href='deleteurl?id=" + rs.getInt("id")
                        + "' class='btn btn-danger btn-sm'>Delete</a>");
                pw.println("</td>");
                pw.println("</tr>");
            }

            if (!found) {
                pw.println("<tr><td colspan='8' class='no-users'>");
                pw.println("<h5 class='text-muted'>No Users Found</h5>");
                pw.println("<p class='mb-0'>No users have been registered yet.</p>");
                pw.println("</td></tr>");
            }

            pw.println("</tbody>");
            pw.println("</table>");
            pw.println("</div>");

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            pw.println("<div class='alert alert-danger text-center m-4'>Error: " + e.getMessage() + "</div>");
            e.printStackTrace();
        }

        pw.println("</div>"); 
        pw.println("<div class='card-footer text-center'>");
        pw.println("<a href='Home.html' class='btn btn-success me-2'>Home</a>");
        pw.println("</div>");
        pw.println("</div>");
        pw.println("</div>"); 

        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}