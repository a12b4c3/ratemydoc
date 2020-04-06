package model;

import database.DatabaseConnectionHandler;

import java.sql.*;


public class UserModel {
    private Connection databaseCon =DatabaseConnectionHandler.getCon();;

    public UserModel() {
    }

    public boolean verifyUserInformation(String username, String pwd) {
        System.out.println("Verifying user information");
        String pwd_info = "";

        //check whether the doctorExist in the database
        try {

            PreparedStatement stmt = this.databaseCon.prepareStatement("SELECT pwd from ALL_USER where username =?");

            System.out.println("Does username and pws match?");
            System.out.println("User name: " + username);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pwd_info = rs.getString("pwd");
                System.out.println(pwd_info);
            }

            return pwd_info.equals(pwd);

        } catch (SQLException e) {
            System.out.println("Debugging message: verify user information");
            e.printStackTrace();
            return false;
        }
    }
}

