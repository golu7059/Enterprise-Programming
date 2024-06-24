import java.sql.*;
// import com.mysql.cj.protocol.Resultset;


public class App {
    // need 3 things
    //url
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    //username
    private static final String userName = "root";
    //password
    private static final String password = "    ";
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // e.printStackTrace();  // this will give tree like structure
            System.out.println(e.getMessage());
        }

        // making connection with DB
        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement();  // statement need to execute sql queries
            String query = "select * from students";
            ResultSet resultSet =  statement.executeQuery(query); // ResultSet to hold table

            // iterate on row
            while(resultSet.next()){    // next row present or not
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double marks = resultSet.getDouble("marks");

                System.out.println("ID : " + id + " NAME : " + name + " AGE : " + age + " MARKS : " + marks);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
