package controller;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.RMDDelegate;
import model.ReviewModel;
import ui.LoginWindow;
import ui.QueryReview;
import ui.RateMyDocGUI;
import ui.WrittenReview;

import javax.swing.*;
import java.awt.*;


public class RateMyDoctor implements LoginWindowDelegate, RMDDelegate {
    private DatabaseConnectionHandler dbHandler = null;
    private LoginWindow loginWindow = null;
    private ReviewModel reviewModel = new ReviewModel();
    public RateMyDoctor() { dbHandler = new DatabaseConnectionHandler(); }

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

        reviewModel.insertReview(aptDate,username, password, aptType, demail, reviewText, overallRating);
    }

    @Override
    public void editReview(WrittenReview reviewObj) {

    }

    @Override
    public void showReview(QueryReview reviewObj) {

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
