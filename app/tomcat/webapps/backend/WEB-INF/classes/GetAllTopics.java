import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.*;

@WebServlet("/getAllTopics")
public class GetAllTopics extends HttpServlet {

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
        String sqlStr = "select question_id, module_name, topic  from questions left join modules on questions.module_id = modules.module_id;"; 
         ResultSet rset = stmt.executeQuery(sqlStr);

         JSONArray jsonArr = new JSONArray();
         while(rset.next()) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("question_id", rset.getInt("question_id"));
            jsonObj.put("module_name", rset.getString("module_name"));
            jsonObj.put("topic", rset.getString("topic"));
            jsonArr.put(jsonObj);
         }
         out.print(jsonArr);
      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }
      
      out.close();
   }
}
