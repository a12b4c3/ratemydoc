package model;
import database.DatabaseConnectionHandler;
import java.sql.*;


public class DoctorModel {
    private Connection databaseCon = DatabaseConnectionHandler.getCon();;

    public DoctorModel(){}

    public void createDoctor(String demail, String name){

        System.out.println("Creating a new doctor here.");

        //check whether the doctorExist in the database
        try{
            PreparedStatement ps = this.databaseCon.prepareStatement ("INSERT INTO Doctor VALUES(?, ?)");
            ps.setString(1, demail);
            if(name.equals("null")){
                ps.setNull(2, Types.VARCHAR);
            }else{
                ps.setString(2, name);
            }

            ps.executeUpdate();
            this.databaseCon.commit();
            ps.close();

            if(isDoctorExist(demail)) {
                System.out.println("New doctor created in system.");
            }


        } catch (SQLException e) {
            System.out.println("creation failed");
            e.printStackTrace();
        }
    }

    public boolean isDoctorExist(String doctorEmail) {
        try {

            PreparedStatement stmt = this.databaseCon.prepareStatement("select *  from doctor where demail =?");
            stmt.setString(1, doctorEmail);
            ResultSet rs = stmt.executeQuery();

            System.out.println("is doctor exist here???? ");
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
