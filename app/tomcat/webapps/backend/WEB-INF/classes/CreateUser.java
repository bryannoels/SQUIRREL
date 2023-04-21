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

         String sqlStr2 = "select student_id, student_name, phone_number, student_age, student_password from students where phone_number = " + "'" + request.getParameter("phone_number") + "'"; 
         ResultSet rset2 = stmt.executeQuery(sqlStr2);

         JSONArray jsonArr = new JSONArray();
         while(rset2.next()) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("student_id", rset2.getInt("student_id"));
            jsonObj.put("student_name", rset2.getString("student_name"));
            jsonObj.put("phone_number", rset2.getString("phone_number"));
            jsonObj.put("student_age", rset2.getInt("student_age"));
            jsonObj.put("student_password", rset2.getString("student_password"));
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
   @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
    doGet(request, response);  // Re-direct POST request to doGet()
    }
}


