package controller;

import database.DatabaseConnectionHandler;
import delegates.LoginWindowDelegate;
import delegates.RMDDelegate;
import model.DoctorQuerier;
import model.MonitorModel;
import model.ReviewModel;
import ui.*;

import java.awt.*;
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
    public int insertReview(WrittenReview reviewObj) {
        String username = reviewObj.getReviewerUsername();
        String password = reviewObj.getReviewerPassword();
        String aptType = reviewObj.getAppointmentType();
        String aptDate = reviewObj.getAppointmentDate();
        String demail = reviewObj.getDoctorEmailAddress();
        String reviewText = reviewObj.getReviewText();
        int overallRating = reviewObj.getReviewRating();
        // Models need to be initialized after the driver starts, so can't be a private field

        ReviewModel reviewModel = new ReviewModel();

        return reviewModel.insertReview(aptDate, username, password, aptType, demail, reviewText, overallRating);
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
        String doctorSpecialization = reviewObj.getDoctorSpecialization();
        String doctorHospital = reviewObj.getDoctorHospital();
        int onlyDoctorsAboveRating = reviewObj.getOnlyDoctorsAboveRating();
        int docIdentifier = reviewObj.getDocIdentifier();

        DoctorQuerier querier = new DoctorQuerier();

        return querier.runQuery(doctorName, doctorSpecialization, doctorHospital, onlyDoctorsAboveRating, docIdentifier);
    }

    @Override
    public void deleteReview(WrittenReview queryObj) {
        String adminusername = queryObj.getReviewerUsername();
        String adminpassword = queryObj.getReviewerPassword();
        String idToDelete = queryObj.getUpdateReviewId();
        

        // todo call delete function.
    }

    @Override
    public LinkedList<String> monitorReview(WrittenReview queryObj) {
        String adminusername = queryObj.getReviewerUsername();
        String adminpassword = queryObj.getReviewerPassword();
        String idToProcess = queryObj.getUpdateReviewId();

        MonitorModel monitor = new MonitorModel();

        if (!idToProcess.equals("ReviewID to process")) {
            Integer rid = Integer.parseInt(idToProcess);
            monitor.createMonitor(adminusername, adminpassword, rid);
        }

        return monitor.showMonitoredReviews(adminusername, adminpassword);
    }

    @Override
    public int countReviews() {
        DoctorQuerier querier = new DoctorQuerier();

        return querier.countQuery();
    }

    @Override
    public LinkedList<String> showMostReviewed(QueryReview reviewObj) {
        int docIdentifier = reviewObj.getDocIdentifier();
        DoctorQuerier querier = new DoctorQuerier();

        return querier.divisionQuery(docIdentifier);
    }

    public static void main(String[] args) {
        RateMyDoctor rmd = new RateMyDoctor();
        rmd.start();
    }
}
