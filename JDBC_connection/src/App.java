import java.sql.*;
import java.util.Scanner;

// import com.mysql.cj.protocol.Resultset;

public class App {
    // need 3 things
    // url
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    // username
    private static final String userName = "root";
    // password
    private static final String password = "";

    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // e.printStackTrace(); // this will give tree like structure
            System.out.println(e.getMessage());
        }

        // making connection with DB
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement(); // statement need to
            // execute sql queries
            // String query = "select * from students";
            // String query1 = String.format("INSERT INTO students(name , age , marks)
            // VALUES ('%s',%o,%f)","Vishal",21,56.89);
            // String query1 = String.format("INSERT INTO students(name , age , marks)
            // VALUES ('%s',%o,%f)","raushan",20,56.89);
            // String query2 = String.format("UPDATE students SET marks = %f WHERE id =
            // %d",36.5,2);
            // String query3 = String.format("Delete from students where id = %d",4);
            // ResultSet resultSet = statement.executeQuery(query); // ResultSet to hold
            // table
            // int rowsAffected = statement.executeUpdate(query1);
            // int rowsAffected = statement.executeUpdate(query3);

            // now using prepared statement
            // String insertQuery = "INSERT INTO students(name,age,marks) VALUES (?,?,?)";
            String selectQuery = "SELECT marks FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            // preparedStatement.setString(1, "Ankita");
            // preparedStatement.setInt(2, 18);
            // preparedStatement.setDouble(3, 100.2);
            preparedStatement.setInt(1, 3);
            // int rowsAffected = preparedStatement.executeUpdate();

            // iterate on row
            // while (resultSet.next()) { // next row present or not
            // int id = resultSet.getInt("id");
            // String name = resultSet.getString("name");
            // int age = resultSet.getInt("age");
            // double marks = resultSet.getDouble("marks");

            // System.out.println("ID : " + id + " NAME : " + name + " AGE : " + age + "
            // MARKS : " + marks);
            // }

            // in case of adding
            // if(rowsAffected > 0){
            // System.out.println("Data inserted sucessfully");
            // } else {
            // System.out.println("No change in data");
            // }

            // Batch processing
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Enter name : ");
                String name = sc.next();
                System.out.print("Enter age : ");
                int age = sc.nextInt();
                System.out.print("Enter marks : ");
                double marks = sc.nextDouble();

                String query = String.format("INSERT INTO students(name , age , marks) VALUES ('%s',%o,%f)", name, age,
                        marks);
                statement.addBatch(query);

                System.out.print("Do you want to add more data Y/N ? : ");
                String choice = sc.next();
                if (choice.toUpperCase().equals('N')) {
                    break;
                }
            }
            int[] arr = statement.executeBatch();   // return binary array
            if(rowsAffected > 0){
                System.out.println("Data inserted sucessfully");
                } else {
                System.out.println("No change in data");
                }


            // ResultSet resultSet = preparedStatement.executeQuery();
            // if(resultSet.next()){
            // System.out.println("Marks : " + resultSet.getDouble("marks"));
            // }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
