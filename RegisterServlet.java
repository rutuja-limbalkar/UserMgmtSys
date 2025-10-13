package com.mgmt.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    String query = "insert into user(name,email,mobile,dob,city,gender) values(?,?,?,?,?,?)";

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
        pw.println("<title>Registration Status</title>");
        pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
        pw.println("<style>");
        pw.println("body { background-color: #f8f9fa; font-family: 'Poppins', sans-serif; }");
        pw.println(".card { width: 500px; margin: 100px auto; box-shadow: 0 4px 10px rgba(0,0,0,0.1); border-radius: 15px; }");
        pw.println(".card-header { background-color: #0d6efd; color: white; text-align: center; font-weight: 600; }");
        pw.println(".btn a { text-decoration: none; color: inherit; }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");

        String name = req.getParameter("userName");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String dob = req.getParameter("dob");
        String city = req.getParameter("city");
        String gender = req.getParameter("gender");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/usermgmt", "root", "rutuja");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);
            int count = ps.executeUpdate();

            pw.println("<div class='card'>");
            pw.println("<div class='card-header'>Registration Status</div>");
            pw.println("<div class='card-body text-center'>");

            if (count == 1) {
                pw.println("<h4 class='text-success'>Record Registered Successfully ✅</h4>");
            } else {
                pw.println("<h4 class='text-danger'>Record Not Registered ❌</h4>");
            }

            pw.println("</div>");
            pw.println("<div class='card-footer text-center'>");
            pw.println("<a href='Home.html' class='btn btn-success me-2'>Home</a>");
            pw.println("<a href='register.html' class='btn btn-primary me-2'>Register Again</a>");
            pw.println("<a href='showdata' class='btn btn-outline-dark'>Show Users</a>");
            pw.println("</div>");
            pw.println("</div>");

        } catch (ClassNotFoundException | SQLException se) {
            pw.println("<div class='alert alert-danger text-center mt-5 mx-auto' style='width:500px;'>");
            pw.println("<strong>Error:</strong> " + se.getMessage());
            pw.println("</div>");
            se.printStackTrace();
        } catch (Exception e) {
            pw.println("<div class='alert alert-danger text-center mt-5 mx-auto' style='width:500px;'>");
            pw.println("<strong>Error:</strong> " + e.getMessage());
            pw.println("</div>");
            e.printStackTrace();
        }

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
