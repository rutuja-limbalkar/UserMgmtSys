package com.mgmt.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet {

    String query = "select name,email,mobile,dob,city,gender from user where id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        int id = Integer.parseInt(req.getParameter("id"));

        pw.println("<!DOCTYPE html>");
        pw.println("<html lang='en'>");
        pw.println("<head>");
        pw.println("<meta charset='UTF-8'>");
        pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        pw.println("<title>Edit User</title>");
        // Bootstrap CSS
        pw.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        pw.println("<style>");
        pw.println("body { background-color: #f8f9fa; font-family: 'Segoe UI', system-ui, sans-serif; min-height: 100vh; display: flex; align-items: center; padding: 20px 0; }");
        pw.println(".edit-card { max-width: 600px; margin: 0 auto; border: none; border-radius: 12px; box-shadow: 0 5px 15px rgba(0,0,0,0.08); overflow: hidden; }");
        pw.println(".card-header { background: linear-gradient(135deg, #4361ee, #3a0ca3); color: white; text-align: center; padding: 25px; border-bottom: none; }");
        pw.println(".card-body { padding: 30px; }");
        pw.println(".form-control, .form-select { border-radius: 8px; padding: 12px 15px; border: 1px solid #e1e5ee; transition: all 0.3s; }");
        pw.println(".form-control:focus, .form-select:focus { border-color: #4361ee; box-shadow: 0 0 0 0.2rem rgba(67, 97, 238, 0.15); }");
        pw.println(".btn-primary { background: #4361ee; border: none; border-radius: 8px; padding: 12px 30px; font-weight: 600; transition: all 0.3s; }");
        pw.println(".btn-primary:hover { background: #3a0ca3; transform: translateY(-2px); }");
        pw.println(".btn-outline-secondary { border-radius: 8px; padding: 12px 30px; font-weight: 600; }");
        pw.println(".form-label { font-weight: 600; margin-bottom: 8px; color: #495057; }");
        pw.println(".gender-options { display: flex; gap: 20px; }");
        pw.println("@media (max-width: 576px) { .card-body { padding: 20px; } .gender-options { flex-direction: column; gap: 10px; } }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "rutuja");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pw.println("<div class='container'>");
                pw.println("<div class='edit-card card'>");

                pw.println("<div class='card-header'>");
                pw.println("<h3>Edit User Details</h3>");
                pw.println("</div>");

                pw.println("<div class='card-body'>");
                pw.println("<form action='edit?id=" + id + "' method='post'>");

                pw.println("<div class='mb-3'>");
                pw.println("<label class='form-label'>Full Name</label>");
                pw.println("<input type='text' class='form-control' name='name' value='" + rs.getString(1) + "'>");
                pw.println("</div>");

                pw.println("<div class='mb-3'>");
                pw.println("<label class='form-label'>Email Address</label>");
                pw.println("<input type='email' class='form-control' name='email' value='" + rs.getString(2) + "'>");
                pw.println("</div>");

                pw.println("<div class='mb-3'>");
                pw.println("<label class='form-label'>Phone Number</label>");
                pw.println("<input type='tel' class='form-control' name='mobile' value='" + rs.getString(3) + "'>");
                pw.println("</div>");

                pw.println("<div class='mb-3'>");
                pw.println("<label class='form-label'>Date of Birth</label>");
                pw.println("<input type='date' class='form-control' name='dob' value='" + rs.getString(4) + "'>");
                pw.println("</div>");

                pw.println("<div class='mb-3'>");
                pw.println("<label class='form-label'>City</label>");
                pw.println("<input type='text' class='form-control' name='city' value='" + rs.getString(5) + "'>");
                pw.println("</div>");

                pw.println("<div class='mb-4'>");
                pw.println("<label class='form-label'>Gender</label>");
                pw.println("<div class='gender-options'>");
                String gender = rs.getString(6).toLowerCase();
                pw.println("<div class='form-check'><input class='form-check-input' type='radio' name='gender' id='male' value='Male'" + ("male".equals(gender) ? " checked" : "") + "><label class='form-check-label' for='male'>Male</label></div>");
                pw.println("<div class='form-check'><input class='form-check-input' type='radio' name='gender' id='female' value='Female'" + ("female".equals(gender) ? " checked" : "") + "><label class='form-check-label' for='female'>Female</label></div>");
                pw.println("<div class='form-check'><input class='form-check-input' type='radio' name='gender' id='other' value='Other'" + ("other".equals(gender) ? " checked" : "") + "><label class='form-check-label' for='other'>Other</label></div>");
                pw.println("</div>");
                pw.println("</div>");

                pw.println("<div class='d-flex justify-content-between mt-4'>");
                pw.println("<button type='submit' class='btn btn-primary'>Update</button>");
                pw.println("<button type='reset' class='btn btn-outline-secondary'>Reset</button>");
                pw.println("</div>");

                pw.println("</form>");
                pw.println("</div>"); 

                pw.println("<div class='card-footer text-center bg-light mt-3'>");
                pw.println("<a href='Home.html' class='btn btn-outline-primary me-2'>Home</a>");
                pw.println("<a href='showdata' class='btn btn-outline-secondary'>Show Users</a>");
                pw.println("</div>");

                pw.println("</div>"); 
                pw.println("</div>"); 
            }

        } catch (ClassNotFoundException | SQLException se) {
            pw.println("<p style='color:red; text-align:center;'>Error: " + se.getMessage() + "</p>");
            se.printStackTrace();
        } catch (Exception e) {
            pw.println("<p style='color:red; text-align:center;'>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }

        pw.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
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
