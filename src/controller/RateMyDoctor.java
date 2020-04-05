package controller;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.RMDDelegate;
import model.DoctorQuerier;
import model.ReviewModel;
import ui.LoginWindow;
import ui.QueryReview;
import ui.RateMyDocGUI;
import ui.WrittenReview;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.LinkedList;


public class RateMyDoctor implements LoginWindowDelegate, RMDDelegate {
    private DatabaseConnectionHandler dbHandler = null;
    private LoginWindow loginWindow = null;
    public RateMyDoctor() { dbHandler = new DatabaseConnectionHandler();
    }

    private void start() {
        loginWindow = new LoginWindow();
        loginWindow.showFrame(this);
    }

    @Override
    public void login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start text transaction flow
            loginWindow.dispose();

            Frame frame = new RateMyDocGUI(this);
            frame.setMinimumSize(new Dimension(1000, 600));
            frame.setVisible(true);

        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
    }

    @Override
    public void insertReview(WrittenReview reviewObj) {
        String username = reviewObj.getReviewerUsername();
        String password = reviewObj.getReviewerPassword();
        String aptType = reviewObj.getAppointmentType();
        String aptDate = reviewObj.getAppointmentDate();
        String demail = reviewObj.getDoctorEmailAddress();
        String reviewText = reviewObj.getReviewText();
        int overallRating = reviewObj.getReviewRating();
        // Models need to be initialized after the driver starts, so can't be a private field

        ReviewModel reviewModel = new ReviewModel();

        reviewModel.insertReview(aptDate, username, password, aptType, demail, reviewText, overallRating);
    }

    @Override
    public void editReview(WrittenReview reviewObj) {
        String username = reviewObj.getReviewerUsername();
        String password = reviewObj.getReviewerPassword();
        int rid = Integer.parseInt(reviewObj.getUpdateReviewId());
        String updateText = reviewObj.getReviewText();
        int overallRating = reviewObj.getReviewRating();

        ReviewModel reviewModel = new ReviewModel();

        reviewModel.updateReview(rid, username, password,updateText, overallRating);
    }

    @Override
    public LinkedList<String> showReview(QueryReview reviewObj) {
        String doctorName = reviewObj.getDoctorName();
        String doctorLocation = reviewObj.getDoctorLocation();
        String doctorSpecialization = reviewObj.getDoctorSpecialization();
        String doctorHospital = reviewObj.getDoctorHospital();
        int onlyDoctorsAboveRating = reviewObj.getOnlyDoctorsAboveRating();

        DoctorQuerier querier = new DoctorQuerier();

        return querier.runQuery(doctorName, doctorLocation, doctorSpecialization, doctorHospital, onlyDoctorsAboveRating);
    }

    @Override
    public void updateReview(WrittenReview reviewObj) {

    }

    @Override
    public void deleteReview(QueryReview reviewObj) {

    }

    public static void main(String[] args) {
        RateMyDoctor rmd = new RateMyDoctor();
        rmd.start();
    }
}
