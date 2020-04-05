package ui;

public class QueryReview {
    private String doctorName;
    private String doctorSpecialization;
    private String doctorHospital;
    private int onlyDoctorsAboveRating;
    private int docIdentifier;

    public QueryReview() {

    }

    public int getDocIdentifier() {
        return docIdentifier;
    }

    public void setDocIdentifier(int docIdentifier) {
        this.docIdentifier = docIdentifier;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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
