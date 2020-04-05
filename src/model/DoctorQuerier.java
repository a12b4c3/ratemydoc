package model;

import database.DatabaseConnectionHandler;
import java.sql.*;
import java.util.LinkedList;

public class DoctorQuerier {
    private Connection databaseCon =DatabaseConnectionHandler.getCon();

    public DoctorQuerier() {}

    public LinkedList<String> runQuery(String name, int identifier, String spec, String hospital, int rating){
        LinkedList<String> result = new LinkedList<String>();
        return result;
    }
}