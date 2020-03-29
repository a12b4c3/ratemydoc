package delegates;

import ui.WrittenReview;

import java.util.Date;

public interface RMDDelegate {
    public void insertReview (WrittenReview reviewObj);
    public void editReview (WrittenReview reviewObj);
    public void showReview();
    public void updateReview();
    public void deleteReview();
}
