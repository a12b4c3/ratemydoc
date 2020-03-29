package ui;

public class QueryReview {
    private String doctorName;
    private String doctorLocation;
    private String doctorSpecialization;
    private String doctorHospital;
    private int onlyDoctorsAboveRating;

    public QueryReview() {

    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorLocation() {
        return doctorLocation;
    }

    public void setDoctorLocation(String doctorLocation) {
        this.doctorLocation = doctorLocation;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getDoctorHospital() {
        return doctorHospital;
    }

    public void setDoctorHospital(String doctorHospital) {
        this.doctorHospital = doctorHospital;
    }

    public int getOnlyDoctorsAboveRating() {
        return onlyDoctorsAboveRating;
    }

    public void setOnlyDoctorsAboveRating(int onlyDoctorsAboveRating) {
        this.onlyDoctorsAboveRating = onlyDoctorsAboveRating;
    }
}
