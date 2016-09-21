import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest{
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void board_instantiatesCorrectly_true() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals(true, testBoard instanceof Board);
  }

  @Test
  public void getName_grabsNameFromBoardObject_String() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals("Al Merrick", testBoard.getName());
  }

  @Test
  public void getWidth_grabsWidthFromBoardObject_double() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals(18.25, testBoard.getWidth(), 0);
  }

  @Test
  public void getimgURL_grabsURLFromBoardObject_String() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals("https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", testBoard.getimgURL());
  }

  @Test
  public void getBoardType_grabsBoardTypeFromBoardObject_String() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals("Shortboard", testBoard.getBoardType());
  }

  @Test
  public void getThickness_grabsThicknessFromBoardObject_double() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals(2.25, testBoard.getThickness(), 0);
  }

  @Test
  public void getPrice_grabsPriceFromBoardObject_double() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals(800, testBoard.getPrice(), 0);
  }

  @Test
  public void getLength_grabsLengthFromBoardObject_String() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals("5'6''", testBoard.getLength());
  }

  @Test
  public void getFin_grabsFinFromBoardObject_3() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals(3, testBoard.getFin());
  }

  @Test
  public void getTail_grabsTailFromBoardObject_String() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals("Squash Tail", testBoard.getTail());
  }

  @Test
  public void getCompanyId_grabsCompanyIdFromBoardObject_1() {
    Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
    assertEquals(1, testBoard.getCompanyId());
  }

  // @Test
  // public void getId_grabsCompanyIdFromBoardObject_1(){
  //   Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
  //   testBoard.save();
  //   assertTrue(testBoard.getId() > 0);
  // }

  @Test
  public void all_getsListOfAllBoards_true() {

  }

  @Test
  public void equals_returnsTrueIfSame_true() {

  }

  @Test
  public void getReviews_grabsAllReviewsForBoard_true() {

  }

  @Test
  public void find_findsBoardAssociatedWithId_true() {

  }

  @Test
  public void update_updatesBoardInformation_true() {

  }

  @Test
  public void delete_deletesBoard_true() {
    
  }

  // @Test
  // public void save_saveboard_true(){
  //   Board testBoard = new Board("Al Merrick", "https://www.downthelinesurf.co.uk/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/4/-/4-utility-deck-692px.jpg", "Shortboard", 2.25, 800, 18.25, "5'6''", 3, "Squash Tail", 1);
  //   testBoard.save();
  //   assertTrue(Board.all().get(0).equals(testBoard));
  // }
}
