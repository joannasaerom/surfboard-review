import org.sql2o.*;

public class Review {
  private String comment;
  private int rating;
  private int id;
  private int boardId;



  public Review(String _comment, int _rating, int _boardId) {
    comment = _comment;
    rating = _rating;
    boardId = _boardId;

  }

  public String getComment() {
    return comment;
  }

  public int getRating() {
    return rating;
  }

  public int getId() {
    return id;
  }

  public static Review find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews where id=:id;";
      Review review = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Review.class);
      return review;
    }
  }

  @Override
  public boolean equals(Object otherReview) {
    if (!(otherReview instanceof Review)) {
      return false;
    } else {
      Review newReview = (Review) otherReview;
      return this.getId() == newReview.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews (comment, rating) VALUES (:comment, :rating);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("comment", this.comment)
        .addParameter("rating", this.rating)
        .executeUpdate().getKey();
    }
  }

  public void update(String comment, int rating) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE reviews SET (comment, rating) = (:comment, :rating) " +
                   "WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("comment", comment)
        .addParameter("rating", rating)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM reviews WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }



}
