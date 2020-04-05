package model;

import database.DatabaseConnectionHandler;
import java.sql.*;
import java.util.LinkedList;

public class DoctorQuerier {
    private Connection databaseCon = DatabaseConnectionHandler.getCon();

    public DoctorQuerier() {}

    // For general query using inputs on right-hand side of GUI
    public LinkedList<String> runQuery(String name, String spec, String hospital, int ratingLim, int option) {
        LinkedList<String> result = new LinkedList<>();

        try {
            boolean emptySpec = true;
            boolean emptyHosp = true;
            boolean emptyRating = true;

            // Handle cases where spec, hospital and rating are non-empty
            if (!spec.equals("")) {
                String specJoinQuery = "CREATE OR REPLACE VIEW SpecJoin(demail, name) AS SELECT demail, name FROM Doctor natural join SpecialistDoctor natural join SpecializesIn " +
                        "WHERE UPPER(specname) LIKE UPPER('%" + spec + "%') OR UPPER(subspec) LIKE UPPER('%" + spec + "%')";
                PreparedStatement specJoin = this.databaseCon.prepareStatement(specJoinQuery);
                //specJoin.setString(1, spec);
                //specJoin.setString(2, spec);
                specJoin.executeUpdate();
                this.databaseCon.commit();
                emptySpec = false;
                specJoin.close();
            }
            if (!hospital.equals("")) {
                String hospJoinQuery = "CREATE OR REPLACE VIEW HospJoin(demail, name) AS SELECT demail, name FROM Doctor natural join IsEmployedAt natural join Hospital " +
                        "WHERE UPPER(hname) LIKE UPPER('%" + hospital + "%')";
                PreparedStatement hospJoin = this.databaseCon.prepareStatement(hospJoinQuery);
                //hospJoin.setString(1, hospital);
                hospJoin.executeUpdate();
                this.databaseCon.commit();
                emptyHosp = false;
                hospJoin.close();
            }
            if (ratingLim != 0) {
                String tempViewQuery = "CREATE OR REPLACE VIEW Temp(demail, name, avgrating) AS SELECT demail, name, AVG(rating) AS avgrating FROM Doctor natural join ReviewContent GROUP BY demail, name";
                PreparedStatement tempView = this.databaseCon.prepareStatement(tempViewQuery);
                tempView.executeQuery();
                String ratingAggQuery = "CREATE OR REPLACE VIEW RatingAgg(demail, name) AS SELECT demail, name FROM Temp WHERE avgrating > " + ratingLim;
                PreparedStatement ratingAgg = this.databaseCon.prepareStatement(ratingAggQuery);
                //ratingAgg.setInt(1, ratingLim);
                ratingAgg.executeUpdate();
                this.databaseCon.commit();
                emptyRating = false;
                ratingAgg.close();
            }

            if (emptySpec && emptyHosp && emptyRating) {
                // none
                String baseJoinQuery = "CREATE OR REPLACE VIEW BaseJoin(demail, name) AS SELECT demail, name FROM Doctor";
                PreparedStatement baseJoinView = this.databaseCon.prepareStatement(baseJoinQuery);
                baseJoinView.executeUpdate();
                this.databaseCon.commit();
                baseJoinView.close();
            }
            else if (!emptySpec && !emptyHosp && !emptyRating) {
                // all 3 inputs
                String baseJoinQuery = "CREATE OR REPLACE VIEW BaseJoin(demail, name) AS SELECT demail, name FROM SpecJoin natural join HospJoin natural join RatingAgg";
                PreparedStatement baseJoinView = this.databaseCon.prepareStatement(baseJoinQuery);
                baseJoinView.executeUpdate();
                this.databaseCon.commit();
                baseJoinView.close();
            }
            else if (emptySpec && emptyHosp) {
                // only Rating
                String baseJoinQuery = "CREATE OR REPLACE VIEW BaseJoin(demail, name) AS SELECT demail, name FROM RatingAgg";
                PreparedStatement baseJoinView = this.databaseCon.prepareStatement(baseJoinQuery);
                baseJoinView.executeUpdate();
                this.databaseCon.commit();
                baseJoinView.close();
            }
            else if (emptySpec && emptyRating) {
                // only Hosp
                String baseJoinQuery = "CREATE OR REPLACE VIEW BaseJoin(demail, name) AS SELECT demail, name FROM HospJoin";
                PreparedStatement baseJoinView = this.databaseCon.prepareStatement(baseJoinQuery);
                baseJoinView.executeUpdate();
                this.databaseCon.commit();
                baseJoinView.close();
            }
            else if (emptyHosp && emptyRating) {
                // only Spec
                String baseJoinQuery = "CREATE OR REPLACE VIEW BaseJoin(demail, name) AS SELECT demail, name FROM SpecJoin";
                PreparedStatement baseJoinView = this.databaseCon.prepareStatement(baseJoinQuery);
                baseJoinView.executeUpdate();
                this.databaseCon.commit();
                baseJoinView.close();
            }
            else if (emptySpec) {
                // only Hosp and Rating
                String baseJoinQuery = "CREATE OR REPLACE VIEW BaseJoin(demail, name) AS SELECT demail, name FROM HospJoin natural join RatingAgg";
                PreparedStatement baseJoinView = this.databaseCon.prepareStatement(baseJoinQuery);
                baseJoinView.executeUpdate();
                this.databaseCon.commit();
                baseJoinView.close();
            }
            else if (emptyHosp) {
                // only Spec and Rating
                String baseJoinQuery = "CREATE OR REPLACE VIEW BaseJoin(demail, name) AS SELECT demail, name FROM SpecJoin natural join RatingAgg";
                PreparedStatement baseJoinView = this.databaseCon.prepareStatement(baseJoinQuery);
                baseJoinView.executeUpdate();
                this.databaseCon.commit();
                baseJoinView.close();
            }
            else if (emptyRating) {
                // only Spec and Hosp
                String baseJoinQuery = "CREATE OR REPLACE VIEW BaseJoin(demail, name) AS SELECT demail, name FROM SpecJoin natural join HospJoin";
                PreparedStatement baseJoinView = this.databaseCon.prepareStatement(baseJoinQuery);
                baseJoinView.executeUpdate();
                this.databaseCon.commit();
                baseJoinView.close();
            }

            String finalQueryInput = "SELECT rid, ";
            if (option == 0) {
                finalQueryInput += "name";
            }
            else {
                finalQueryInput += "demail";
            }
            finalQueryInput += ", type, rating, user_comment FROM BaseJoin natural join ReviewContent natural join ReviewDetails natural join Appointment";
            if (!name.equals("")) {
                finalQueryInput += " WHERE UPPER(name) LIKE UPPER(?)";
            }
            if (option == 0) {
                finalQueryInput += " ORDER BY name";
            }
            else {
                finalQueryInput += " ORDER BY demail";
            }
            PreparedStatement finalQuery = this.databaseCon.prepareStatement(finalQueryInput);

            if (!name.equals("")) {
                finalQuery.setString(1, "%" + name + "%");
            }
            ResultSet queryResult = finalQuery.executeQuery();

            result = this.processResultSet(queryResult);

            queryResult.close();
            finalQuery.close();
        } catch (SQLException e) {
            System.out.println("debug exception, run query");
            e.printStackTrace();
        }

        return result;
    }

    // Query the total number of reviews stored in database
    public int countQuery() {
        int count = 0;

        try{
            PreparedStatement countReviewQuery = this.databaseCon.prepareStatement("SELECT COUNT(*) FROM ReviewContent");
            ResultSet queryResult = countReviewQuery.executeQuery();
            queryResult.next();
            count = queryResult.getInt(1);

            queryResult.close();
            countReviewQuery.close();
        } catch (SQLException e) {
            System.out.println("debug exception, count query");
            e.printStackTrace();
        }

        return count;
    }

    // Query the doctors who were reviewed by all users
    public LinkedList<String> divisionQuery(int option) {
        LinkedList<String> result = new LinkedList<>();

        try{
            PreparedStatement countReviewQuery = this.databaseCon.prepareStatement("CREATE OR REPLACE VIEW DivisionView(demail, name) AS " +
                    "SELECT D.demail, D.name FROM Doctor D WHERE NOT EXISTS " +
                    "((SELECT U.username FROM ALL_USER U) MINUS (SELECT R.username FROM ReviewContent R WHERE R.demail = D.demail))");
            countReviewQuery.executeUpdate();
            this.databaseCon.commit();
            countReviewQuery.close();

            String finalQueryInput = "";
            if (option == 0) {
                finalQueryInput = "SELECT rid, name, type, rating, user_comment " +
                        "FROM DivisionView natural join ReviewContent natural join ReviewDetails natural join Appointment ORDER BY name";
            }
            else {
                finalQueryInput = "SELECT rid, demail, type, rating, user_comment " +
                        "FROM DivisionView natural join ReviewContent natural join ReviewDetails natural join Appointment ORDER BY demail";
            }
            PreparedStatement finalQuery = this.databaseCon.prepareStatement(finalQueryInput);
            ResultSet queryResult = finalQuery.executeQuery();

            result = this.processResultSet(queryResult);

            queryResult.close();
            finalQuery.close();
        } catch (SQLException e) {
            System.out.println("debug exception, division query");
            e.printStackTrace();
        }

        return result;
    }

    private LinkedList<String> processResultSet(ResultSet queryResult) {
        LinkedList<String> result = new LinkedList<>();

        try {
            while(queryResult.next()) {
                String rid = queryResult.getString(1);
                String doctor = queryResult.getString(2);
                String aptType = queryResult.getString(3);
                int rating = queryResult.getInt(4);
                String comment = queryResult.getString(5);
                String entry = "rid: " + rid + "\nDoctor: " + doctor + "\nAppointment Type: " + aptType + "\nOverall Rating: " + rating + "\nReview: '" + comment + "'";
                result.add(entry);
            }
        } catch (SQLException e) {
            System.out.println("debug exception, division query");
            e.printStackTrace();
        }

        return result;
    }
}