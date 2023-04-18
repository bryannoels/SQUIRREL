// Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class JdbcSelectTest {   // Save as "JdbcSelectTest.java"
   public static void main(String[] args) {
      try (
         // Step 1: Construct a database 'Connection' object called 'conn'
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SQUIRREL?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", "myuser", "xxxx");
  // For MySQL only
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
 
         // Step 2: Construct a 'Statement' object called 'stmt' inside the Connection created
         Statement stmt = conn.createStatement();
      ) {
         // Step 3: Write a SQL query string. Execute the SQL query via the 'Statement'.
         //  The query result is returned in a 'ResultSet' object called 'rset'.
         String strSelect = "select * from students";
         System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
 
         ResultSet rset = stmt.executeQuery(strSelect);
 
         // Step 4: Process the 'ResultSet' by scrolling the cursor forward via next().
         //  For each row, retrieve the contents of the cells with getXxx(columnName).
         System.out.println("The records selected are:");
         int rowCount = 0;
         // Row-cursor initially positioned before the first row of the 'ResultSet'.
         // rset.next() inside the whole-loop repeatedly moves the cursor to the next row.
         // It returns false if no more rows.
         while(rset.next()) {   // Repeatedly process each row
            int student_id = rset.getInt("student_id");  // retrieve a 'String'-cell in the row
            String student_name = rset.getString("student_name"); 
            String phone_number = rset.getString("phone_number"); 
            // double price = rset.getDouble("price");  // retrieve a 'double'-cell in the row
            // int    qty   = rset.getInt("qty");       // retrieve a 'int'-cell in the row
            System.out.println(student_id+" "+student_name+" "+phone_number);
            ++rowCount;
         }
         System.out.println("Total number of records = " + rowCount);
 
      } catch(SQLException ex) {
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
   }
}