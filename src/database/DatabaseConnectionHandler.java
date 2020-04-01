package database;

import ui.WrittenReview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//import ca.ubc.cs304.model.BranchModel;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    //Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private static Connection connection = null;


    //call this in main, on opening the application

    public DatabaseConnectionHandler() {
        try {

            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//            if (connection != null) {
//                connection.close();}
//
//            connection = DriverManager.getConnection(ORACLE_URL, username, password);
//            connection.setAutoCommit(false);
//            System.out.println("\nConnected to Oracle!");
        } catch (SQLException e) {
//            System.out.println("??????");
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

        }
    }
    public static Connection getCon(){return connection;}

    //TODO : do we need to close the datanase somewhere or not?
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }


}
