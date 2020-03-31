package database;

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

    public DatabaseConnectionHandler(String  username, String password) {
        try {

            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            if (connection != null) {
                connection.close();}

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            System.out.println("\nConnected to Oracle!");
        } catch (SQLException e) {
            System.out.println("??????");
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


//
//    private void rollbackConnection() {
//        try  {
//            connection.rollback();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//    }

//    public void databaseSetup() {
//        dropBranchTableIfExists();
//
//        try {
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate("CREATE TABLE branch (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)");
//            stmt.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//
//        BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
//        insertBranch(branch1);
//
//        BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
//        insertBranch(branch2);
//    }


}
