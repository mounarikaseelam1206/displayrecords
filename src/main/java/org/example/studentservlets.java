package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/display")
public class studentservlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8090/studentdb",
                    "system",
                    "mounuu0012"
            );

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM student"
            );

            out.println("<html>");
            out.println("<body>");

            out.println("<h2>Student Records</h2>");

            out.println("<table border='1'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Marks</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getInt("marks") + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            out.println("<h3>Error occurred:</h3>");
            out.println("<p>" + e.toString() + "</p>");
        }
    }
}