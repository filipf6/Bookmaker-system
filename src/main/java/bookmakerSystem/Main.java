package bookmakerSystem;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;

import bookmakerSystem.DAO.*;
import bookmakerSystem.model.*;


import bookmakerSystem.model.User;
import bookmakerSystem.service.MatchService;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {

	public static void main(String[] args) {
		DatabaseConnector.connectWithBase();
		
		port(8012);
		staticFileLocation("/public");
		
		String layout="templates/layout.vtl";
		
		get("/", (request, response) -> {
			Map<String,Object>model=new HashMap<String, Object>();
						
			String day=request.queryParams("day");
			MatchService.matchesToVTL(day, model);
			
			model.put("day", day); 
			model.put("user", request.session().attribute("user"));
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model,layout);
			
			
		},new VelocityTemplateEngine());
		
		get("/register",(request,response)->{
			Map<String, Object>model=new HashMap<String, Object>();
			model.put("template", "templates/register.vtl");
			return new ModelAndView(model,layout);
		},new VelocityTemplateEngine());
		
		post("/welcome",(request,response)->{
			Map<String, Object>model=new HashMap<String, Object>();
			
			ArrayList<String>errors=new ArrayList<String>();
			
			String login=request.queryParams("login");
			String password=request.queryParams("password");
			String repeatedPassword=request.queryParams("password2");
			String email=request.queryParams("email");
			String name=request.queryParams("name");
			String surname=request.queryParams("surname");
			
			errors=UserDAO.getRegistrationErrors(login, password, repeatedPassword, email, name, surname);
			
			if(errors.isEmpty())
			{
				User person=UserDAO.register(login, password, email, name, surname);
				request.session().attribute("user",person);
				model.put("name", name);
				model.put("template", "templates/welcome.vtl");
			}
			else
			{
				model.put("errors", errors);
				model.put("template", "templates/error.vtl");
			}
			return new ModelAndView(model,layout);
		},new VelocityTemplateEngine());
		
		post("/login",(request,response)->{
			Map<String, Object>model=new HashMap<String, Object>();
			
			String login=request.queryParams("login");
			String password=request.queryParams("password");
			User person= UserDAO.LogIn(login, password);
			
			String day=request.queryParams("day");
			MatchService.matchesToVTL(day, model);
			
			if(person==null)
			{
				model.put("template", "templates/logingError.vtl");
			}
			else
			{
				request.session().attribute("user",person);
				//model.put("name", request.queryParams("login"));
				model.put("user", request.session().attribute("user"));
				model.put("template", "templates/index.vtl"); //tutej welcome
			}

			return new ModelAndView(model,layout);
		},new VelocityTemplateEngine());
		
		
		get("/logout", (request, response) -> {
			Map<String,Object>model=new HashMap<String, Object>();
			request.session().removeAttribute("user");
			model.put("user", request.session().attribute("user"));
			String day=request.queryParams("day");
			MatchService.matchesToVTL(day, model);
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model,layout);
			
			
		},new VelocityTemplateEngine());
	}
	
	

	
	
	
	
	/*public static void main(String[] args) {
		List<Uzytkownik> users = DB.sql2o.createQuery("select * from uzytkownik").executeAndFetch(Uzytkownik.class);
		for (Uzytkownik user : users) {
			System.out.println(user.getIMIE());
		}
	
		System.out.println(users.size());
	}*/

}
