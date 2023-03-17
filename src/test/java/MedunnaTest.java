import org.junit.Test;

import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MedunnaTest {
    /*
   Given
     User connects to the database
     (Host name: medunna.com, Database name: medunna_db, Usename: medunna_user, Password: medunna_pass_987))
   When
     User sends the query to get the names of created_by column from "room" table
   Then
     Verify that there are some rooms created by "john doe".
   And
     User closes the connection
  */
    @Test
    public void medunnaTest(){
        // User connects to the database
        JdbcUtils.connectToDatabase("medunna.com","medunna_db","medunna_user","medunna_pass_987");
        Statement statement = JdbcUtils.createStatement();

       // User sends the query to get the names of created_by column from "room" table
        List<Object> objectList = JdbcUtils.getColumnList("room","created_by");
        System.out.println("objectList = " + objectList);

       //Verify that there are some rooms created by "john doe".
        String s="john_doe";
        long roomsCreated = objectList.stream().filter(t-> t.equals(s)).count();
        System.out.println("Rooms created by John Doe : " + roomsCreated);


        // User closes the connection
        JdbcUtils.closeConnectionAndStatement();

    }
}
