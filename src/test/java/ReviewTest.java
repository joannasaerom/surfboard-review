import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ReviewTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void review_instantiatesCorrectly_true() {
    Review testReview = new Review("this is great", 8, 1);
    assertEquals(true, testReview instanceof Review);
  }

  @Test
  public void getComment_getsCommentFromReviewObject_String() {
    Review testReview = new Review("this is great", 8, 1);
    assertEquals("this is great", testReview.getComment());
  }

  @Test
  public void getRating_getsRatingFromReviewObject_8() {
    Review testReview = new Review("this is great", 8, 1);
    assertEquals(8, testReview.getRating());
  }

  @Test
  public void getId_findsId_true() {

  }

  @Test
  public void find_findsReview_true() {

  }

  @Test
  public void equals_returnsTrueIfReviewsAreSame_true() {

  }

  @Test
  public void save_savesReview_true() {

  }

  @Test
  public void update_updatesReview_true() {

  }

  @Test
  public void delete_deletesReview_true() {
    
  }
}
