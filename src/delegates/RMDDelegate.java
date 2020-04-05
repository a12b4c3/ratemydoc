package delegates;

import ui.QueryReview;
import ui.WrittenReview;

import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;

public interface RMDDelegate {
    public int insertReview (WrittenReview reviewObj);
    public void editReview (WrittenReview reviewObj);
    public LinkedList<String> showReview(QueryReview queryObj);
    public void deleteReview(WrittenReview reviewObj);
    public int countReviews();
    public LinkedList<String> showMostReviewed(QueryReview queryObj);
}
