import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Transection {
    public static void main(String[] args) {
        // driver connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lenden", "root", "")) {
            connection.setAutoCommit(false); // Ensure auto-commit is off for transaction

            String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE ac_no = ?";
            String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE ac_no = ?";
            PreparedStatement debitPreparedStatement = connection.prepareStatement(debitQuery);
            PreparedStatement creditPreparedStatement = connection.prepareStatement(creditQuery);

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter sender account number  : ");
            int senderAccountNo = sc.nextInt(); // Use int for account number
            System.out.print("Enter receiver account number  : ");
            int receiverAccountNo = sc.nextInt(); // Use int for account number
            System.out.print("Enter amount to Transfer : ");
            double amount = sc.nextDouble();

            debitPreparedStatement.setDouble(1, amount);
            debitPreparedStatement.setInt(2, senderAccountNo); // sender account number

            creditPreparedStatement.setDouble(1, amount);
            creditPreparedStatement.setInt(2, receiverAccountNo); // receiver account number

            if (isSufficient(connection, senderAccountNo, amount)) {
                int affectedRows1 = debitPreparedStatement.executeUpdate();
                int affectedRows2 = creditPreparedStatement.executeUpdate();
                if (affectedRows1 > 0 && affectedRows2 > 0) {
                    connection.commit(); // Commit the transaction if both updates succeed
                    System.out.println("Transaction successfull");
                    System.out.println(amount + " is Transferred to " + getName(connection, receiverAccountNo));

                    // Verify the update by checking the balance
                    double newBalance = getBalance(connection, senderAccountNo);
                    System.out.println("current balance for account " + senderAccountNo + " : " + newBalance);
                } else {
                    connection.rollback(); // Rollback if any update fails
                    System.out.println("Transaction failed ");
                }
            } else {
                System.out.println("Insufficient balance! Transaction unsuccessful");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static boolean isSufficient(Connection connection, int ac_no, double amount) {
        String query = "SELECT balance FROM accounts WHERE ac_no = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ac_no);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    double currBalance = resultSet.getDouble("balance");
                    return amount <= currBalance;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
        return false;
    }

    static double getBalance(Connection connection, int ac_no) {
        String query = "SELECT balance FROM accounts WHERE ac_no = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ac_no);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
        return -1; // Return -1 if balance retrieval fails
    }

    static String getName(Connection connection , int accountNo){
        String query = "SELECT name FROM accounts WHERE ac_no = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, accountNo);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString("name");
                }
            }
        }catch(SQLException e){
            System.out.println("Unable to get Name : " + e.getMessage());
        }
        return "unknown User";
    }
}
