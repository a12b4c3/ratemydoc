package model;

import database.DatabaseConnectionHandler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MonitorModel {
    private Connection databaseCon = DatabaseConnectionHandler.getCon();;

    public MonitorModel(){
    }

    public void createAppointment(int aid, Date adate, String appointmentType){
        System.out.println("In AppointmentModel, creating new appointment");

        //check whether the doctorExist in the database
        try{
            System.out.println("appointment");
            System.out.println(this.databaseCon);
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
            System.out.println("debug comment, in AppointmentModel, creatAppointment");
            e.printStackTrace();
        }
        System.out.println("new appointment created");
    }

}