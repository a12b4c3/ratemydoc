package model;

import database.DatabaseConnectionHandler;

import java.sql.*;
import java.sql.Date;
import java.util.GregorianCalendar;

public class AppointmentModel {
    private Connection databaseCon;

    public AppointmentModel(){this.databaseCon = DatabaseConnectionHandler.getCon();}

    public void createAppointment(int aid, Date adate, String appointmentType){

        //check whether the doctorExist in the database
        try{
            PreparedStatement ps_Appointment = this.databaseCon.prepareStatement("INSERT INTO Appointment VALUES(?, ?,  ?)");
            ps_Appointment.setInt(1, aid);
            ps_Appointment.setDate(2, adate);
            ps_Appointment.setString(3, appointmentType);

            ps_Appointment.executeUpdate();
            this.databaseCon.commit();
            ps_Appointment.close();
            // Just in case I am using the auto increment stuff
            // PreparedStatement reviewContent = databaseCon.prepareStatement("INSERT INTO ReviewContent () VALUES(?, ?,  ?, ?, ?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}