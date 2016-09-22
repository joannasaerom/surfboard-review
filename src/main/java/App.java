import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/companies", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("companies", Company.all());
      model.put("template", "templates/companies.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/companies/:companyId", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Company company = Company.find(Integer.parseInt(request.params(":companyId")));
    //   model.put("company", company);
    //   model.put("template", "templates/company.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    get("/companies/:companyId/boards", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Company company = Company.find(Integer.parseInt(request.params(":companyId")));
      model.put("company", company);
      model.put("boards", company.getBoards());
      model.put("template", "templates/boards.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/companies/:companyId/boards/:boardId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Company company = Company.find(Integer.parseInt(request.params(":companyId")));
      Board board = Board.find(Integer.parseInt(request.params(":boardId")));
      model.put("company", company);
      model.put("board", board);
      model.put("template", "templates/board.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/boards", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int companyId = Integer.parseInt(request.queryParams("companyId"));
      Company company = Company.find(companyId);
      String name = request.queryParams("name");
      String imgURL = request.queryParams("imgURL");
      String boardType = request.queryParams("boardType");
      double thickness = Integer.parseInt(request.queryParams("thickness"));
      double price = Integer.parseInt(request.queryParams("price"));
      double width = Integer.parseInt(request.queryParams("width"));
      String length = request.queryParams("length");
      int fin = Integer.parseInt(request.queryParams("fin"));
      String tail = request.queryParams("tail");
      Board board = new Board(name, imgURL, boardType, thickness, price, width, length, fin, tail, companyId);
      board.save();
      response.redirect("/companies/" + companyId);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/companies/:companyId/boards/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Company company = Company.find(Integer.parseInt(request.params(":companyId")));
      model.put("company", company);
      model.put("template", "templates/board-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/companies/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/company-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/companies", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String location = request.queryParams("location");
      String imgURL = request.queryParams("imgURL");
      String website = request.queryParams("website");
      String description = request.queryParams("description");
      Company company = new Company(name, location, imgURL, website, description);
      company.save();
      response.redirect("/companies");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
