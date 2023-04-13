import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.*;

@WebServlet("/createUser")
public class CreateUser extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("application/json");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      response.setHeader("Access-Control-Allow-Origin", "*");


      try (
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/SQUIRREL?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");
         Statement stmt = conn.createStatement();
      ) {
        String sqlStr = "insert into students (student_name, phone_number, student_age, student_password) values( '"
        +request.getParameter("student_name")+"', '"
        +request.getParameter("phone_number")+"', "
        +request.getParameter("student_age")+", '"
        +request.getParameter("student_password")+"')";
         stmt.executeUpdate(sqlStr);

      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }
      
      out.close();
   }
   @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
    doGet(request, response);  // Re-direct POST request to doGet()
    }
}


