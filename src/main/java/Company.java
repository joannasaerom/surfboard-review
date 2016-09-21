import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Company {
  private String name;
  private String location;
  private String imgURL;
  private String website;
  private List<Board> boards;
  private String description;
  private int id;

  public Company(String _name,
                 String _location,
                 String _imgURL,
                 String _website,
                 String _description)
  {
    name = _name;
    location = _location;
    imgURL = _imgURL;
    website = _website;
    description = _description;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public String getURL() {
    return imgURL;
  }

  public String getWebsite() {
    return website;
  }

  public String getDescription() {
    return description;
  }

  public int getId() {
    return id;
  }

  public static List<Company> all() {
    String sql = "SELECT * FROM companies;";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Company.class);
    }
  }

  public static Company find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM companies where id=:id;";
      Company company = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Company.class);
      return company;
    }
  }

  public List<Board> getBoards() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM boards where companyId=:id;";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Board.class);
    }
  }

  @Override
  public boolean equals(Object otherCompany) {
    if (!(otherCompany instanceof Company)) {
      return false;
    } else {
      Company newCompany = (Company) otherCompany;
      return this.getName().equals(newCompany.getName()) &&
             this.getId() == newCompany.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO companies (name, location, imgURL, website," +
                   " description) VALUES (:name, :location, :imgURL, :website," +
                   " :description);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("location", this.location)
        .addParameter("imgURL", this.imgURL)
        .addParameter("website", this.website)
        .addParameter("description", this.description)
        .executeUpdate().getKey();
    }
  }

  public void update(String _name,
                     String _location,
                     String _imgURL,
                     String _website,
                     String _description)
    {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE companies SET name = :name, location = :location, imgURL = :imgURL, website = :website, description = :description WHERE id = :id;";

        con.createQuery(sql)
          .addParameter("name", this.name)
          .addParameter("location", this.location)
          .addParameter("imgURL", this.imgURL)
          .addParameter("website", this.website)
          .addParameter("description", this.description)
          .addParameter("id", this.id)
          .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM companies WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
