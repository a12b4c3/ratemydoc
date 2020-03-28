package delegates;

import java.util.Date;

public interface rmdCADelegate {
    public void insertReview(String username, String password, Date date, String aptType, Date aptDate, String docEmail);
    public void showReview();
    public void updateReview();
    public void deleteReview();
}
