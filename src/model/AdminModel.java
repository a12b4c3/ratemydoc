package model;

import database.DatabaseConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminModel {
    private Connection databaseCon = DatabaseConnectionHandler.getCon();

    public AdminModel() {
        // do nothing
    }

    public boolean verifyUserIsAdmin(String adminusername, String pwd) {
        System.out.println("Verifying user is admin");
        String pwd_info = "";

        // check whether the login info provided is for an admin user
        try {
            PreparedStatement stmt = this.databaseCon.prepareStatement("Select pwd From ADMIN where username = ?");
            stmt.setString(1, adminusername);
            System.out.println("validating admin username and password");
            System.out.println("checking username: " + adminusername);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pwd_info = rs.getString("pwd");
                System.out.println("actual password:" + pwd_info);
                System.out.println("given password" + pwd);
            }
            this.databaseCon.commit();
            stmt.close();
            return pwd_info.equals(pwd);

        } catch (SQLException e) {
            System.out.println("Debugging message: verify admin information failed");
            e.printStackTrace();
            return false;
        }
    }
}
