import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {
  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/board_review_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteBoardsQuery = "DELETE FROM boards *;";
      String deleteCompaniesQuery = "DELETE FROM companies *;";
      String deleteReviewsQuery = "DELETE FROM reviews *;";
      con.createQuery(deleteBoardsQuery).executeUpdate();
      con.createQuery(deleteCompaniesQuery).executeUpdate();
      con.createQuery(deleteReviewsQuery).executeUpdate();
    }
  }
}
