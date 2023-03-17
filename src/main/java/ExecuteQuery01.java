import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
       Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Cekitocum1");
        Statement statement=connection.createStatement();

        //1.Example: Select the country names whose region id's are 1
       String sql1=" SELECT country_name FROM countries WHERE region_id = 1";
       boolean r1=statement.execute(sql1);//IF you use execute() method , it will return true or false
        System.out.println("r1 = " + r1); //true

        //To see the records we have another method which is executeQuery() that returns ResultSet
//        "Belgium"
//        "Switzerland"
//        "Germany"
//        "Denmark"
//        "France"
//        "Italy"
//        "Netherlands"
//        "United Kingdom"
        ResultSet resultSet1=statement.executeQuery(sql1);
        resultSet1.next();
        resultSet1.next();
        resultSet1.next();
       String country= resultSet1.getString("country_name");
        System.out.println("country = "+country);//country = Germany


       ResultSet resultSet2= statement.executeQuery(sql1);
       while (resultSet2.next()){
           System.out.println( resultSet2.getString("country_name"));
       }

     //2.Example: Select the country_id and country_name whose region_id's are greater than 2

     String sql3= "SELECT country_id,country_name FROM countries WHERE region_id>2";
       ResultSet resultSet3= statement.executeQuery(sql3);
       while(resultSet3.next()){
           System.out.println(resultSet3.getString("country_id")+ "--> "+resultSet3.getString("country_name"));
       }

        //3.Example: Select all columns whose number_of_employees is the lowest from companies table
        String sql4="SELECT * FROM companies WHERE number_of_employees =(SELECT MIN(number_of_employees)FROM companies)";
       ResultSet resultSet4= statement.executeQuery(sql4);
       while(resultSet4.next()){
           System.out.println(resultSet4.getInt(1)+" "+resultSet4.getString(2)+" "+ resultSet4.getInt(3));//102 MICROSOFT 10000
       }


    }
}
