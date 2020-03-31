package model;
import database.DatabaseConnectionHandler;
import java.sql.*;


public class DoctorModel {
    private Connection databaseCon;

    public DoctorModel(){this.databaseCon = DatabaseConnectionHandler.getCon();}

    public void createDoctor(String demail, String name){

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isDoctorExist(String doctorEmail) {
        try {
            String check_email = " SELECT * FROM doctor WHERE demail = " + doctorEmail;
            PreparedStatement ps = this.databaseCon.prepareStatement(check_email);
            ResultSet rs = ps.executeQuery();
            //No such doctor exist
            // Insert one doctor
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
