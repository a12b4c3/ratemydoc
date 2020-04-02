package database;

import model.AppointmentModel;
import model.DoctorModel;
import ui.WrittenReview;

import java.sql.*;


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

    private static Connection connection;


    //call this in main, on opening the application

    public DatabaseConnectionHandler() {
        try {

            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
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
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");

            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            System.exit(0);
            return false;
        }
    }

//    public void insertReview(String adate, String userName, String user_password,
//                             String appointmentType , String doctorEmail,
//                             String comment, int rating){
//        int num_likes = 0;
//        int aid = userName.hashCode() * adate.hashCode();
//        int rid = userName.hashCode() + doctorEmail.hashCode();
//        int init_visiable = 1;
//        Date sql_adate=Date.valueOf(adate);
//
//
//        System.out.println("userName " + userName);
//        System.out.println("password " + user_password);
//        System.out.println("aptType " + appointmentType);
//        System.out.println("aptDate " + adate);
//        System.out.println("demail " + doctorEmail);
//        System.out.println("comment " + comment);
//        System.out.println("rating " + rating);
//
//        System.out.println("rid " + rid);
//        System.out.println("aid " + aid);
////        //check whether the doctorExist in the database
////        DoctorModel dm = new DoctorModel();
////        if(!dm.isDoctorExist(doctorEmail)){
////            dm.createDoctor(doctorEmail, "null");
////        }
//
//        //Store the record of this appointment
////        AppointmentModel am = new AppointmentModel();
////        am.createAppointment(aid, sql_adate, appointmentType);
//
//        //Store record of this review
//        try{
//
//            //Record the reviewContent
//            PreparedStatement ps_rContent = connection.prepareStatement("INSERT INTO ReviewContent VALUES(?, ?,  ?, ?, ?)");
//
//            ps_rContent.setString(1,userName);
//            ps_rContent.setInt(2, aid);
//            ps_rContent.setString(3, doctorEmail);
//            ps_rContent.setString(4, comment);
//            ps_rContent.setInt(5, rating);
//
//
//            PreparedStatement ps_Detail = connection.prepareStatement("INSERT INTO REVIEWDETAILS VALUES(?, ?, ?, ?, ?, ?, ?)");
//            // Just in case I am using the auto increment stuff
//            // PreparedStatement reviewContent = databaseCon.prepareStatement("INSERT INTO ReviewContent () VALUES(?, ?,  ?, ?, ?)");
//
//            ps_Detail.setInt(1, rid);
//            ps_Detail.setString(2, userName);
//            ps_Detail.setInt(3, aid);
//            ps_Detail.setString(4, doctorEmail);
//            ps_Detail.setInt(5, num_likes);
//            ps_Detail.setDate(6, sql_adate);
//            ps_Detail.setInt(7, init_visiable);
//
////            ps_rContent.executeUpdate();
////            connection.commit();
////            ps_rContent.close();
//
//            ps_Detail.executeUpdate();
//            connection.commit();
//            ps_Detail.close();
//
//            System.out.println("insert success");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }


}
