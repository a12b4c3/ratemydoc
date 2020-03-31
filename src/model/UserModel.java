package model;

import database.DatabaseConnectionHandler;

import java.sql.*;


public class UserModel {
    private Connection databaseCon;

    public UserModel() {
        this.databaseCon = DatabaseConnectionHandler.getCon();
    }

    public boolean verifyUserInformation(String username, String pwd) {
        String pwd_info = "";

        //check whether the doctorExist in the database
        try {
            Statement stmt = this.databaseCon.createStatement();
            String sql = "SELECT pwd from ALL_USER where username =" + username;
            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println(rs);
            //PreparedStatement ps = this.databaseCon.prepareStatement(sql);
            //ResultSet rs = ps.executeQuery();
            //Username and pwd match
            while (rs.next()) {
                pwd_info = rs.getString("pwd");
                System.out.println(pwd_info);
            }

            return pwd_info.equals(pwd);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

