import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Board{
  private String name;
  private String imgURL;
  private String boardType;
  private double price;
  private String length;
  private double width;
  private double thickness;
  private int fin;
  private String tail;
  private int id;
  private int companyId;
  private List<Review> reviews;


  public Board(String _name,
               String _imgURL,
               String _boardType,
               double _thickness,
               double _price,
               double _width,
               String _length,
               int _fin,
               String _tail,
               int _companyId)
  {
    name = _name;
    imgURL = _imgURL;
    boardType = _boardType;
    thickness = _thickness;
    price = _price;
    width = _width;
    length = _length;
    fin = _fin;
    tail = _tail;
    companyId = _companyId;
  }

  public double getWidth() {
    return width;
  }
  public String getName() {
    return name;
  }

  public String getimgURL() {
    return imgURL;
  }

  public String getBoardType() {
    return boardType;
  }

  public double getThickness() {
    return thickness;
  }

  public double getPrice() {
    return price;
  }

  public String getLength() {
    return length;
  }

  public int getFin() {
    return fin;
  }

  public String getTail() {
    return tail;
  }

  public int getId() {
    return id;
  }

  public int getCompanyId(){
    return companyId;
  }

  public static List<Board> all() {
    String sql = "SELECT * FROM boards;";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Board.class);
    }
  }

  @Override
  public boolean equals(Object otherBoard){
    if(!(otherBoard instanceof Board)){
      return false;
    } else {
      Board newBoard = (Board) otherBoard;
      return this.getName().equals(newBoard.getName()) &&
             this.getId() == newBoard.getId() &&
             this.getCompanyId() == newBoard.getCompanyId();
    }
  }

  public List<Review> getReviews() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews where boardId=:id;";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Review.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO boards (name, imgURL, boardType, thickness, price, length, fin, tail, companyId, width) VALUES (:name, :imgURL, :boardType, :thickness, :price, :length, :fin, :tail, :companyId, :width) WHERE id = :id;";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("imgURL", this.imgURL)
        .addParameter("boardType", this.boardType)
        .addParameter("thickness", this.thickness)
        .addParameter("price", this.price)
        .addParameter("length", this.length)
        .addParameter("fin", this.fin)
        .addParameter("tail", this.tail)
        .addParameter("companyId", this.companyId)
        .addParameter("width", this.width)
        .executeUpdate().getKey();
    }
  }

  public static Board find(int id) {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM boards where id=:id;";
      Board board = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Board.class);
      return board;
    }
  }

  public void update(String _name,
                     String _imgURL,
                     String _boardType,
                     double _thickness,
                     double _price,
                     String _length,
                     int _fin,
                     double _width,
                     String _tail,
                     int _companyId)
    {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE boards SET (name, imgURL, boardType, thickness, price, " +
                     "length, fin, tail, companyId, width) = (:name, :imgURL, :boardType, " +
                     ":thickness, :price, :length, :fin, :tail, :companyId, :width) WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("name", this.name)
          .addParameter("imgURL", this.imgURL)
          .addParameter("boardType", this.boardType)
          .addParameter("thickness", this.thickness)
          .addParameter("price", this.price)
          .addParameter("length", this.length)
          .addParameter("fin", this.fin)
          .addParameter("tail", this.tail)
          .addParameter("companyId", this.companyId)
          .addParameter("id", this.id)
          .addParameter("width", this.width)
          .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM boards WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
