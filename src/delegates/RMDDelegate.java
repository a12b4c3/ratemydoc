package delegates;

import ui.QueryReview;
import ui.WrittenReview;

import java.util.Date;

public interface RMDDelegate {
    public void insertReview (WrittenReview reviewObj);
    public void editReview (WrittenReview reviewObj);
    public void showReview(QueryReview queryObj);
    public void updateReview(WrittenReview reviewObj);
    public void deleteReview(QueryReview queryObj);
}
