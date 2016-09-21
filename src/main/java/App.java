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

    // get("/", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "templates/index.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    // get("/companies", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("companies", Company.all());
    //   model.put("template", "templates/companies.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    // get("/companies/:id", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Company company = Company.find(Integer.parseInt(request.params(":id")));
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

    // get("/companies/:companyId/boards/:boardId", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Company company = Company.find(Integer.parseInt(request.params(":companyId")));
    //   Board board = Board.find(Integer.parseInt(request.params(":boardId")));
    //   model.put("company", company);
    //   model.put("board", board);
    //   model.put("template", "templates/board.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    post("/boards", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Company company = Company.find(Integer.parseInt(request.queryParams("")))
    })
  }
}
