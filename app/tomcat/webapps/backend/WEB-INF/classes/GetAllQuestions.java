import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.*;

@WebServlet("/getAllQuestions")
public class GetAllQuestions extends HttpServlet {

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
        String sqlStr = "select questions.question_id, topic, question_text, choice_text from questions join choices on choices.choice_id = questions.question_id*4+questions.question_answer-4;"; 
         ResultSet rset = stmt.executeQuery(sqlStr);

         JSONArray jsonArr = new JSONArray();
         while(rset.next()) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("question_id", rset.getInt("questions.question_id"));
            jsonObj.put("topic", rset.getString("topic"));
            jsonObj.put("question_text", rset.getString("question_text"));
            jsonObj.put("choice_text", rset.getString("choice_text"));
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
