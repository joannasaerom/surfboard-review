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
  public void setWidth(double _width){
    this.width = _width;
  }

  public void setLength(String _length){
    this.length = _length;
  }

  public void setFin(int _fin){
    this.fin = _fin;
  }

  public void setTail(String _tail){
    this.tail = _tail;
  }

  public void setCompanyId(int _companyId){
    this.companyId = _companyId;
  }

  public void setName(String _name) {
    this.name = _name;
  }

  public void setimgURL(String _url){
    this.imgURL = _url;
  }

  public void setBoardType(String _boardType) {
    this.boardType = _boardType;
  }

  public void setThickness(double _thickness){
    this.thickness = _thickness;
  }

  public void setPrice(double _price){
    this.price = _price;
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
      String sql = "INSERT INTO boards (name, imgURL, boardType, thickness, price, length, fin, tail, companyId, width) VALUES (:name, :imgURL, :boardType, :thickness, :price, :length, :fin, :tail, :companyId, :width)";
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

  public void update()
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
