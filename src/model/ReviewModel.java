package model;

import database.DatabaseConnectionHandler;

import java.sql.*;


public class ReviewModel {
    private Connection databaseCon = DatabaseConnectionHandler.getCon();;

    public ReviewModel(){}

    public int insertReview(String adate, String userName, String user_password,
                             String appointmentType , String doctorEmail,
                             String comment, int rating){
        int num_likes = 0;
        int aid = userName.hashCode() * adate.hashCode();
        int rid = userName.hashCode() + doctorEmail.hashCode();
        int init_visiable = 1;
        Date sql_adate = Date.valueOf(adate);

        //Store the record of this appointment
        AppointmentModel am = new AppointmentModel();
        am.createAppointment(aid, sql_adate, appointmentType);

        //Store record of this review
        try{

            //Record the reviewContent
            System.out.println("recording the user comment");
            System.out.println("recording information");
            System.out.println("username" + userName);
            System.out.println("aid" + aid);
            System.out.println("doctor email" + doctorEmail);
            System.out.println("comment" + comment);
            System.out.println("rating" + rating);

            System.out.println("here0" + rating);

            System.out.println(this.databaseCon);

            PreparedStatement ps_rContent = this.databaseCon.prepareStatement("INSERT INTO REVIEWCONTENT VALUES(?, ?, ?, ?, ?)");
            ps_rContent.setString(1, userName);
            ps_rContent.setInt(2, aid);
            ps_rContent.setString(3, doctorEmail);
            ps_rContent.setString(4, comment);
            ps_rContent.setInt(5, rating);

            ps_rContent.executeUpdate();
            this.databaseCon.commit();
            ps_rContent.close();


            PreparedStatement ps_Detail = databaseCon.prepareStatement("INSERT INTO REVIEWDETAILS VALUES(?, ?, ?, ?, ?, ?, ?)");
            // Just in case I am using the auto increment stuff
            // PreparedStatement reviewContent = databaseCon.prepareStatement("INSERT INTO ReviewContent () VALUES(?, ?,  ?, ?, ?)");

            ps_Detail.setInt(1, rid);
            ps_Detail.setString(2, userName);
            ps_Detail.setInt(3, aid);
            ps_Detail.setString(4, doctorEmail);
            ps_Detail.setInt(5, num_likes);
            ps_Detail.setDate(6, sql_adate);
            ps_Detail.setInt(7, init_visiable);


            ps_Detail.executeUpdate();
            this.databaseCon.commit();
            ps_Detail.close();


        } catch (SQLException e) {
            System.out.println("debug exception, insert review");
            e.printStackTrace();
        }

        return rid;
    }

    // In order to make the update action realistic we need to print out rid after user made a comment
    public void updateReview(int rid, String usrName, String password, String newComment, int overallRating){
       UserModel um = new UserModel();
       String demail = "";
       int aid = 0;
       if(um.verifyUserInformation(usrName,password)){

           System.out.println("User and password matched");

           try{
               // Get rid related information: demail, aid --> to get Review content and update it
               PreparedStatement stmt = this.databaseCon.prepareStatement("SELECT aid, demail from ReviewDetails where rid =?");
               stmt.setInt(1, rid);
               ResultSet rs = stmt.executeQuery();

               while(rs.next()) {
                   aid = rs.getInt(1);
                   demail = rs.getString(2);
                   System.out.println("aid=" + aid + "-" + "demail" + demail);
               }
               rs.close();
               stmt.close();

               if(!demail.equals("") && aid!=0){
                   //update review with the demail, aid, username
                   System.out.println(newComment);
                   PreparedStatement ps = this.databaseCon.prepareStatement( "UPDATE ReviewContent rc SET user_comment=?, RATING =? WHERE username=? AND aid=? AND demail=?");
                   ps.setString(1, newComment);
                   ps.setInt(2, overallRating);
                   ps.setString(3, usrName);
                   ps.setInt(4, aid);
                   ps.setString(5, demail);

                   ps.executeUpdate();
                   this.databaseCon.commit();
                   ps.close();
               }

           }catch (SQLException e) {
               System.out.println("Debug update review session");
               e.printStackTrace();
               System.out.println(e.getMessage());
           }


       }
        System.out.println("User and password does not match");

    }

}
