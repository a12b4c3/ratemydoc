package model;

import database.DatabaseConnectionHandler;

import java.sql.*;
import java.util.LinkedList;

public class MonitorModel {
    private Connection databaseCon = DatabaseConnectionHandler.getCon();;

    public MonitorModel(){
    }

    public void createMonitor(String usrName, String password, Integer rid){
        UserModel um = new UserModel();
        if (um.verifyUserInformation(usrName, password, true)) {
            try{
                int adid = this.getAdminId(usrName);

                // Create new relation between admin and review in Monitors
                PreparedStatement insertStmt = this.databaseCon.prepareStatement("INSERT INTO Monitors VALUES(?, ?)");
                insertStmt.setInt(1, adid);
                insertStmt.setInt(2, rid);
                insertStmt.executeUpdate();
                this.databaseCon.commit();
                insertStmt.close();
            } catch (SQLException e) {
                System.out.println("debug comment, in create monitor relation");
                e.printStackTrace();
            }
        }
    }

    public LinkedList<String> showMonitoredReviews(String usrName, String password) {
        LinkedList<String> result = new LinkedList<>();
        UserModel um = new UserModel();

        if (um.verifyUserInformation(usrName, password, true)) {
            try{
                int adid = this.getAdminId(usrName);
                PreparedStatement monitorQuery = this.databaseCon.prepareStatement("SELECT rid FROM Monitors WHERE adid =?");
                monitorQuery.setInt(1, adid);
                ResultSet monitorResult = monitorQuery.executeQuery();

                int idx = 1;
                while(monitorResult.next()) {
                    String rid = monitorResult.getString(1);
                    String entry = idx + ". rid: " + rid;
                    result.add(entry);
                    idx += 1;
                }
            } catch (SQLException e) {
                System.out.println("debug comment, in create monitor relation");
                e.printStackTrace();
            }
        }

        return result;
    }

    private int getAdminId(String usrName) {
        int adid = 0;

        try{
            // Get adid from Admin
            PreparedStatement adidQuery = this.databaseCon.prepareStatement("SELECT adid FROM Admin WHERE username =?");
            adidQuery.setString(1, usrName);
            ResultSet adidResult = adidQuery.executeQuery();
            adidResult.next();

            adid = adidResult.getInt(1);

            adidResult.close();
            adidQuery.close();
        } catch (SQLException e) {
            System.out.println("debug comment, in create monitor relation");
            e.printStackTrace();
        }

        return adid;
    }

}