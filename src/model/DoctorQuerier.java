package model;

import database.DatabaseConnectionHandler;
import java.sql.*;
import java.util.LinkedList;

public class DoctorQuerier {
    private Connection databaseCon =DatabaseConnectionHandler.getCon();

    public DoctorQuerier() {}

    public LinkedList<String> runQuery(String name, String spec, String hospital, int rating, int option) {
        /*try {
            LinkedList<String> result = new LinkedList<String>();
            if (!name.equals("")) {
                if (!spec.equals("")) {
                    //PreparedStatement specJoin = this.databaseCon.prepareStatement();
                }
            } else {

            }
            return result;
        } catch (SQLException e) {
            System.out.println("debug exception, insert review");
            e.printStackTrace();
        }
    }*/
        LinkedList<String> result = new LinkedList<String>();
        return result;
    }
}