import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Cekitocum1");

        //1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement
        ///1.Step: Create Prepared Statement Query
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";
        Statement statement = connection.createStatement();

        //2. Step: Create Prepared Statement Object
        PreparedStatement ps1 = connection.prepareStatement(sql1);

        //3. Step: Assign the values by using 'setInt(), setString() ... methods'
        ps1.setInt(1, 9999);
        ps1.setString(2, "IBM");

        //4. Step: Execute the query
        int numOfRecordsUpdated = ps1.executeUpdate();
        System.out.println("numOfRecordsUpdated = " + numOfRecordsUpdated);//1

        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet1 = statement.executeQuery(sql2);
        while (resultSet1.next()) {
            System.out.println(resultSet1.getObject(1) + " " + resultSet1.getObject(2) + " " + resultSet1.getObject(3));
//            101 GOOGLE 18000
//            103 APPLE 21000
//            102 MICROSOFT 16000
//            100 IBM 9999


        }
//2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement

        ps1.setInt(1, 5555);
        ps1.setString(2, "GOOGLE");

        //4. Step: Execute the query
        int numOfRecordsUpdated2 = ps1.executeUpdate();
        System.out.println("numOfRecordsUpdated = " + numOfRecordsUpdated2);//1

        String sql3 = "SELECT * FROM companies";
        ResultSet resultSet2 = statement.executeQuery(sql3);
        while (resultSet2.next()) {
            System.out.println(resultSet2.getObject(1) + " " + resultSet2.getObject(2) + " " + resultSet2.getObject(3));
//            101 GOOGLE 5555
        }
        connection.close();
        statement.close();
        ps1.close();
    }
}
