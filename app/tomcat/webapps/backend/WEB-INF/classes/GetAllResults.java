import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.*;

@WebServlet("/getAllResults")
public class GetAllResults extends HttpServlet {

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
        String sqlStr = "select results.result_id, results.student_id, students.student_name, modules.module_name, questions.topic, results.question_id, results.result from results left join questions on results.question_id = questions.question_id left join modules on questions.module_id = modules.module_id left join students on results.student_id = students.student_id;"; 
         ResultSet rset = stmt.executeQuery(sqlStr);

         JSONArray jsonArr = new JSONArray();
         while(rset.next()) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("result_id", rset.getInt("result_id"));
            jsonObj.put("student_id", rset.getInt("student_id"));
            jsonObj.put("student_name", rset.getString("student_name"));
            jsonObj.put("module_name", rset.getString("module_name"));
            jsonObj.put("topic", rset.getString("topic"));
            jsonObj.put("question_id", rset.getInt("question_id"));
            jsonObj.put("result", rset.getBoolean("result"));
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
