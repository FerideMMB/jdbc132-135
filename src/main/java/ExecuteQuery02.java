import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Cekitocum1");
        Statement statement = connection.createStatement();

        //1.Example: Find the company and number_of_employees whose number_of_employees
        // is the second highest from the companies table
        //1.Way:
        String sql1 = "SELECT company,number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW LIMIT 1";
        ResultSet rs1 = statement.executeQuery(sql1);
        while (rs1.next()) {
            System.out.println(rs1.getString("company") + "--" + rs1.getInt("number_of_employees"));//GOOGLE--18000
            //     System.out.println(rs1.getString(1));//GOOGLE
            //     System.out.println(rs1.getString("company"));//GOOGLE
        }

        //2.Way:
        String sql2 = "  SELECT company, number_of_employees\n" +
                "  FROM companies\n" +
                "  WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                "FROM companies\n" +
                "WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                "FROM companies))";
        ResultSet rs2 = statement.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println(rs2.getString(1) + "--" + rs2.getInt(2));//GOOGLE--18000

        }
        ///2.Example: Find the company names and number of employees whose number of employees is
        // less than the average number of employees

        String sql3 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees<(SELECT AVG(number_of_employees) \n" +
                "  FROM companies)";
        ResultSet rs3=statement.executeQuery(sql3);
        while(rs3.next()){
            System.out.println(rs3.getString(1)+"--"+rs3.getInt(2));//IBM--12000
                                                                                        // MICROSOFT--10000
        }
        connection.close();
        statement.close();
    }

}