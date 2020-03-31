package model;

import database.DatabaseConnectionHandler;

import java.sql.*;


public class ReviewModel {
    private Connection databaseCon;


    public ReviewModel(){this.databaseCon = DatabaseConnectionHandler.getCon();}

    public void insertReview(String adate, String userName, String user_password,
                             String appointmentType , String doctorEmail,
                             String comment, int rating){
        int num_likes = 0;
        int aid = userName.hashCode() * adate.hashCode();
        int rid = userName.hashCode() + doctorEmail.hashCode();
        int init_visiable = 1;
        Date sql_adate=Date.valueOf(adate);

        //check whether the doctorExist in the database
        DoctorModel dm = new DoctorModel();
        if(!dm.isDoctorExist(doctorEmail)){
            dm.createDoctor(doctorEmail, "null");
        }

        //Store the record of this appointment
        AppointmentModel am = new AppointmentModel();
        am.createAppointment(aid, sql_adate, appointmentType);

        //Store record of this review
        try{

            //Record the reviewContent
            PreparedStatement ps_rContent = this.databaseCon.prepareStatement("INSERT INTO ReviewContent VALUES(?, ?,  ?, ?, ?)");

            ps_rContent.setString(1,userName);
            ps_rContent.setInt(2, aid);
            ps_rContent.setString(3, doctorEmail);
            ps_rContent.setString(4, comment);
            ps_rContent.setInt(5, rating);


            PreparedStatement ps_Detail = databaseCon.prepareStatement("INSERT INTO ReviewContent VALUES(?, ?, ?, ?, ?, ?, ?)");
            // Just in case I am using the auto increment stuff
            // PreparedStatement reviewContent = databaseCon.prepareStatement("INSERT INTO ReviewContent () VALUES(?, ?,  ?, ?, ?)");

            ps_Detail.setInt(1, rid);
            ps_Detail.setString(2, userName);
            ps_Detail.setInt(3, aid);
            ps_Detail.setString(4, doctorEmail);
            ps_Detail.setInt(5, num_likes);
            ps_Detail.setDate(6, sql_adate);
            ps_Detail.setInt(7, init_visiable);

            ps_rContent.executeUpdate();
            this.databaseCon.commit();
            ps_rContent.close();

            ps_Detail.executeUpdate();
            this.databaseCon.commit();
            ps_Detail.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    // In order to make the update action realistic we need to print out rid after user made a comment
    public void updateReview(int rid, String usrName, String password, String newComment){
       UserModel um = new UserModel();
       String demail = "";
       int aid = 0;
       if(um.verifyUserInformation(usrName,password)){
           //TODO: user and password match --> do some update
           try{
               // Get rid related information: demail, aid --> to get Review content and update it
               String getInfo_sql = "SELECT aid, demail from ReviewDetails where rid =" + rid;
               Statement stmt = this.databaseCon.createStatement();
               ResultSet rs = stmt.executeQuery(getInfo_sql);

               while(rs.next()) {
                   aid = rs.getInt(1);
                   demail = rs.getString(2);
                   System.out.println("aid=" + aid + "-" + "demail" + demail);
               }
               rs.close();
               stmt.close();

               if(!demail.equals("") && aid!=0){
                   //update review with the demail, aid, username
                   PreparedStatement ps = this.databaseCon.prepareStatement( "UPDATE ReviewContent rc SET user_comment=? WHERE username=? AND aid=? AND demail=?");
                   ps.setString(1, newComment);
                   ps.setString(2, usrName);
                   ps.setInt(3, aid);
                   ps.setString(4, demail);

                   ps.executeUpdate();
                   this.databaseCon.commit();
                   ps.close();
               }

           }catch (SQLException e) {
               e.printStackTrace();
               System.out.println(e.getMessage());
           }


       }

    }

}
