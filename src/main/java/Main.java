
import java.sql.*;


public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/databasetest";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "QwErTy123";

    private static final String PREPARED_STATEMENT_QUERY = "INSERT INTO test_table VALUES(?,?)";

    private static final String select = "SELECT  * FROM test_table";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
          PreparedStatement preparedStatement;
//        Connection connection ;
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME , PASSWORD);
//            if (!connection.isClosed()) {
//                System.out.println("Connection complete");
//            }
//
//            connection.close();
//        } catch ( SQLException e) {
//            System.out.println("Connection not complete ");
//            System.err.println("Error " + e);
//
//        }

            try {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
            } catch (SQLException e) {
                System.err.println("Driver not found" + e);
            }
            /* try with resources beter use for Connection  */
        try ( Connection connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
              Statement statement = connection.createStatement()) {

            /*  executeUpdate used to  INSERT UPDATE DELETE */
              statement.executeUpdate("UPDATE test_table SET name = 'New Name' WHERE id = 1");

            /*  executeQuery used to SELECT  return ResultSet */
              statement.executeQuery("SELECT * FROM test_table");

            /* ResultSet is a our Query model
             *  resultSet.next() iterate to all rows on the table */
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {

                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));

                System.out.println(user);
            }

            /* preparedStatement faster then statement  */
            preparedStatement = connection.prepareStatement(PREPARED_STATEMENT_QUERY);

            preparedStatement.setInt(1,4);
            preparedStatement.setString(2,"Afina");

            preparedStatement.execute();

            } catch (SQLException e) {
            System.err.println(" Execute Error:  " + e.getMessage());
        }






    }
}
