import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Transection {
    private static final String url = "jdbc:mysql://localhost:3306/lenden";
    private static final String userName = "root";
    private static final String password = "Golu@1234";

    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // making connection with DB
        try (Connection connection = DriverManager.getConnection(url, userName, password);
            Scanner sc = new Scanner(System.in)) {
            connection.setAutoCommit(false); // Ensure auto-commit is off for transaction

            // Verify user
            System.out.println("Welcome ! to Virtual Bank ");
            System.out.print("New User : 1");
            System.out.println("        Existing User : 2");
            System.out.print("Enter your choice : ");
            boolean isNewUser = (sc.nextInt() == 1);

            // If new user
            if (!isNewUser) {
                System.out.print("Enter Account No: ");
                int accountNo = sc.nextInt();
                System.out.print("Enter PIN: ");
                int pin = sc.nextInt();

                if (isValidAccountNumber(connection, accountNo)) {
                    if (isVerifiedUser(connection, accountNo, pin)) {
                        // Proceed to menu
                        displayMenu(connection, sc , accountNo);
                    } else {
                        System.out.println("Incorrect PIN! Please try again.");
                    }
                } else {
                    System.out.println("Incorrect Account number! Try again.");
                }
            } else {
                System.out.println("Please Enter following details to Create Account ");
                createAccount(connection, sc);
                // Proceed to menu
                // displayMenu(connection, sc , account);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static boolean isValidAccountNumber(Connection connection, int accountNo) throws SQLException {
        String query = "SELECT COUNT(*) FROM accounts WHERE ac_no = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }catch(SQLException e){
            System.out.println("unable to varify Account number :"  + e.getMessage());
        }
        return false;
    }

    private static boolean isVerifiedUser(Connection connection, int accountNo, int pin) throws SQLException {
        String query = "SELECT COUNT(*) FROM accounts WHERE ac_no = ? AND pin = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNo);
            preparedStatement.setInt(2, pin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }catch(SQLException e){
            System.out.println("unable to varify user :"  + e.getMessage());
        }
        return false;
    }

    private static void displayMenu(Connection connection, Scanner sc , int accountNo) throws SQLException {
        while (true) {
            System.out.print("Credit Money: 1");
            System.out.println("             Withdraw Money: 2");
            System.out.print("Balance: 3");
            System.out.println("                  change PIN : 4");
            System.out.print("Money Transfer: 5");
            System.out.println("           Delete Account: 6");
            System.out.print("Please Enter your choice: ");

            int menuChoice = sc.nextInt();
            switch (menuChoice) {
                case 1 -> creditMoney(connection, sc, accountNo);
                case 2 -> balanceWithdraw(connection, sc,accountNo);
                case 3 -> balanceEnquiry(connection, sc , accountNo);
                case 4 -> changePassword(connection, sc,accountNo);
                case 5 -> moneyTransfer(connection, sc , accountNo);
                case 6 -> deleteAccount(connection, sc, accountNo);
                default -> System.out.println("Invalid choice!");
            }
            System.out.println("Do you want to do another transaction Y / N? : ");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("N")) break;
        }
            System.out.println("Thank you! Have a nice day");
    }

    private static void moneyTransfer(Connection connection, Scanner sc , int senderAccountNo) throws SQLException {
        String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE ac_no = ?";
        String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE ac_no = ?";

        PreparedStatement debitPreparedStatement = connection.prepareStatement(debitQuery);
        PreparedStatement creditPreparedStatement = connection.prepareStatement(creditQuery);

        System.out.print("Enter receiver account number  : ");
        int receiverAccountNo = sc.nextInt();
        System.out.print("Enter amount to Transfer : ");
        double amount = sc.nextDouble();
        System.out.print("Enter your 4 digit PIN : ");
        int enteredPIN = sc.nextInt();

        debitPreparedStatement.setDouble(1, amount);
        debitPreparedStatement.setInt(2, senderAccountNo);

        creditPreparedStatement.setDouble(1, amount);
        creditPreparedStatement.setInt(2, receiverAccountNo);

        if ( isSufficient(connection, senderAccountNo, amount)) {
            if(! isVerifiedUser(connection, senderAccountNo, enteredPIN)){
                System.out.println("Transection Failed ! Incorrect PIN");
                return ;
            }
            if(! isValidAccountNumber(connection, receiverAccountNo)){
                System.out.println("Transection Failed ! Incorrect Receiver account NUmber ");
            }
            
            int affectedRows1 = debitPreparedStatement.executeUpdate();
            int affectedRows2 = creditPreparedStatement.executeUpdate();
            if (affectedRows1 > 0 && affectedRows2 > 0) {
                connection.commit(); // Commit the transaction if both updates succeed
                System.out.println("Transaction successful");
                System.out.println(amount + " is transferred to " + getName(connection, receiverAccountNo));

                // Verify the update by checking the balance
                double newBalance = getBalance(connection, senderAccountNo);
                System.out.println("Current balance for account " + senderAccountNo + " : " + newBalance);
            } else {
                connection.rollback(); // Rollback if any update fails
                System.out.println("Transaction failed ! Please try Again ");
            }
        } else {
            System.out.println("Insufficient balance ! Transaction Failed ");
        }
    }

    private static boolean isSufficient(Connection connection, int ac_no, double amount) {
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

    private static double getBalance(Connection connection, int ac_no) {
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

    private static String getName(Connection connection, int accountNo) {
        String query = "SELECT name FROM accounts WHERE ac_no = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountNo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            System.out.println("Unable to get Name : " + e.getMessage());
        }
        return "unknown User";
    }

    private static void creditMoney(Connection connection, Scanner sc , int accountNo) throws SQLException {
        String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE ac_no = ?";
        try (PreparedStatement creditPreparedStatement = connection.prepareStatement(creditQuery)) {

            System.out.print("Enter amount to credit: ");
            double amount = sc.nextDouble();

            creditPreparedStatement.setDouble(1, amount);
            creditPreparedStatement.setInt(2, accountNo);

            int affectedRows = creditPreparedStatement.executeUpdate();
            if (affectedRows > 0) {
                connection.commit();
                System.out.println("Amount " + amount + "credited successfully.");
                System.out.println("Current balance is : " + getBalance(connection, accountNo));
            } else {
                connection.rollback();
                System.out.println("Credit operation failed.");
            }
        }
    }

    private static void balanceEnquiry(Connection connection, Scanner sc , int accountNo) throws SQLException {
        System.out.print("Enter your 4 digit PIN : ");
        int enteredPIN = sc.nextInt();
        double balance = getBalance(connection, accountNo);
        if(isVerifiedUser(connection, accountNo, enteredPIN)){
            if (balance >= 0) {
                System.out.println("Current balance for account " + accountNo + " is: " + balance);
            } else {
                System.out.println("Balance enquiry failed.");
            }
        } else {
            System.out.println("Incorrected PIN !");
        }
    }

    private static void balanceWithdraw(Connection connection, Scanner sc , int accountNo) throws SQLException {
        System.out.print("Enter your 4 digit PIN : ");
        int enteredPin = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        if (isVerifiedUser(connection, accountNo, enteredPin) && isSufficient(connection, accountNo, amount)) {
            String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE ac_no = ?";
            try (PreparedStatement withdrawPreparedStatement = connection.prepareStatement(withdrawQuery)) {
                withdrawPreparedStatement.setDouble(1, amount);
                withdrawPreparedStatement.setInt(2, accountNo);

                int affectedRows = withdrawPreparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    connection.commit();
                    System.out.println("Amount withdrawn successfully.");
                } else {
                    connection.rollback();
                    System.out.println("Withdraw operation failed.");
                }
            }
        } else {
            System.out.println("Transection Failed ! Insufficient balance or Incorrect password .");
        }
    }

    private static void changePassword(Connection connection, Scanner sc, int accountNo) throws SQLException {
        System.out.print("Enter your current PIN : ");
        int currentPin = sc.nextInt();
    
        if (!isVerifiedUser(connection, accountNo, currentPin)) {
            System.out.println("Incorrect current PIN! Please try again.");
            return;
        }
    
        System.out.print("Enter your new PIN: ");
        int newPin = sc.nextInt();
        System.out.print("Confirm your new PIN: ");
        int confirmNewPin = sc.nextInt();
    
        if (newPin != confirmNewPin) {
            System.out.println("New PINs do not match! Please try again.");
            return;
        }
    
        String updateQuery = "UPDATE accounts SET pin = ? WHERE ac_no = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, newPin);
            preparedStatement.setInt(2, accountNo);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dear " + getName(connection, accountNo)+ " your PIN successfully changed!");
            } else {
                System.out.println("Failed to change PIN. Please try again.");
            }
        }
    }
    
    private static void createAccount(Connection connection, Scanner sc) throws SQLException {
        System.out.print("Enter account holder's name: ");
        String name = sc.next();
        System.out.print("Enter account number to create: ");
        int accountNo = sc.nextInt();
        System.out.print("Enter 4 digit pin : ");
        int pin = sc.nextInt();
        System.out.print("Enter initial deposit amount : ");
        double balance = sc.nextDouble();

        String createQuery = "INSERT INTO accounts (ac_no, name, balance, pin) VALUES (?, ?, ?,?)";
        try (PreparedStatement createPreparedStatement = connection.prepareStatement(createQuery)) {
            createPreparedStatement.setInt(1, accountNo);
            createPreparedStatement.setString(2, name);
            createPreparedStatement.setDouble(3, balance);
            createPreparedStatement.setInt(4,pin);

            int affectedRows = createPreparedStatement.executeUpdate();
            if (affectedRows > 0) {
                connection.commit();
                System.out.println("Account created successfully.");
            } else {
                connection.rollback();
                System.out.println("Account creation failed.");
            }
        }
    }

    private static void deleteAccount(Connection connection, Scanner sc , int accountNo) throws SQLException {

        String deleteQuery = "DELETE FROM accounts WHERE ac_no = ?";
        try (PreparedStatement deletePreparedStatement = connection.prepareStatement(deleteQuery)) {
            System.out.print("Enter your 4 digit PIN : ");
            int enteredPin = sc.nextInt();
            if(isVerifiedUser(connection, accountNo, enteredPin)){
                deletePreparedStatement.setInt(1, accountNo);
                String name = getName(connection, accountNo);
                int affectedRows = deletePreparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    connection.commit();
    
                    System.out.println("Dear " + name + " Account deleted successfully.");
                } else {
                    connection.rollback();
                    System.out.println("Account deletion failed ! please try Again later .");
                }
            } else{
                System.out.println("Incorrect Password ! ");
            }
        }
    }

}
