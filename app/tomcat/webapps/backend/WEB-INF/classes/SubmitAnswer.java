import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.*;

@WebServlet("/submitAnswer")
public class SubmitAnswer extends HttpServlet {

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
        String sqlStr = "delete from results where student_id = " + request.getParameter("student_id")
        + " and question_id = " + request.getParameter("question_id");
        String sqlStr2 =  "insert into results (student_id, question_id, result) values( "
        +request.getParameter("student_id")+", "
        +request.getParameter("question_id")+", "
        +request.getParameter("result") + ")";
        stmt.executeUpdate(sqlStr);
        stmt.executeUpdate(sqlStr2);

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


