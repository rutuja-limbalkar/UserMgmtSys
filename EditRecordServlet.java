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

@WebServlet("/edit")
public class EditRecordServlet extends HttpServlet {

    String query = "update user set name=?, email=?, mobile=?, dob=?, city=?, gender=? where id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String dob = req.getParameter("dob");
        String city = req.getParameter("city");
        String gender = req.getParameter("gender");

        pw.println("<!DOCTYPE html>");
        pw.println("<html lang='en'>");
        pw.println("<head>");
        pw.println("<meta charset='UTF-8'>");
        pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        pw.println("<title>Edit Record</title>");
        pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
        pw.println("<style>");
        pw.println("body { background-color: #f8f9fa; font-family: 'Poppins', sans-serif; }");
        pw.println(".card { width: 500px; margin: 100px auto; box-shadow: 0 4px 10px rgba(0,0,0,0.1); border-radius: 15px; }");
        pw.println(".card-header { background-color: #0d6efd; color: white; text-align: center; font-weight: 600; }");
        pw.println(".card-body h4 { margin-top: 10px; }");
        pw.println(".btn a { text-decoration: none; color: inherit; }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");

        pw.println("<div class='card'>");
        pw.println("<div class='card-header'>Edit User</div>");
        pw.println("<div class='card-body text-center'>");

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
            ps.setInt(7, id);

            int count = ps.executeUpdate();

            if (count == 1) {
                pw.println("<h4 class='text-success'>Record Edited Successfully ✅</h4>");
            } else {
                pw.println("<h4 class='text-danger'>Record Not Edited ❌</h4>");
            }

        } catch (ClassNotFoundException | SQLException se) {
            pw.println("<div class='alert alert-danger mt-3'>Error: " + se.getMessage() + "</div>");
            se.printStackTrace();
        } catch (Exception e) {
            pw.println("<div class='alert alert-danger mt-3'>Error: " + e.getMessage() + "</div>");
            e.printStackTrace();
        }

        pw.println("</div>"); 
        pw.println("<div class='card-footer text-center'>");
        pw.println("<a href='Home.html' class='btn btn-success me-2'>Home</a>");
        pw.println("<a href='showdata' class='btn btn-outline-primary'>Show Users</a>");
        pw.println("</div>"); 
        pw.println("</div>"); 
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
