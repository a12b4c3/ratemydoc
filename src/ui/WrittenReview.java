package ui;

public class WrittenReview {
    private String reviewerUsername;
    private String reviewerPassword;
    private String appointmentType;
    private String appointmentDate;
    private String doctorEmailAddress;
    private String reviewText;
    private int updateReviewId;

    private int reviewRating;

    public WrittenReview() {
        // do nothing
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getReviewerUsername() {
        return reviewerUsername;
    }

    public void setReviewerUsername(String reviewerUsername) {
        this.reviewerUsername = reviewerUsername;
    }

    public String getReviewerPassword() {
        return reviewerPassword;
    }

    public void setReviewerPassword(String reviewerPassword) {
        this.reviewerPassword = reviewerPassword;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getDoctorEmailAddress() {
        return doctorEmailAddress;
    }

    public void setDoctorEmailAddress(String doctorEmailAddress) {
        this.doctorEmailAddress = doctorEmailAddress;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getUpdateReviewId() {
        return updateReviewId;
    }

    public void setUpdateReviewId(int updateReviewId) {
        this.updateReviewId = updateReviewId;
    }
}