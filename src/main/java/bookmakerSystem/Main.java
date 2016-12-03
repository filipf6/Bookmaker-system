package bookmakerSystem;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import bookmakerSystem.model.User;
//import bookmakerSystem.model.Uzytkownik;
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
			
			User person=new User(request.queryParams("login"),request.queryParams("password"),request.queryParams("email"),request.queryParams("name"), request.queryParams("surname"));
			List<String> errors =person.register();
			if (!errors.isEmpty()) {
				for(String error:errors)
				{
					model.put(error, error);
				}
				model.put("template", "templates/error.vtl");
			}
			else
			{
				request.session().attribute("user",person);
				model.put("name", request.queryParams("name"));
				model.put("template", "templates/welcome.vtl");
			}
			
			
			return new ModelAndView(model,layout);
		},new VelocityTemplateEngine());
		
		post("/login",(request,response)->{
			Map<String, Object>model=new HashMap<String, Object>();
			
			String login=request.queryParams("login");
			String password=request.queryParams("password");
			User person= User.LogIn(login, password);
			if(person==null)
			{
				model.put("template", "templates/loggingError.vtl");
			}
			else
			{
				request.session().attribute("user",person);
				model.put("name", request.queryParams("login"));
				model.put("template", "templates/welcome.vtl");
			}

			return new ModelAndView(model,layout);
		},new VelocityTemplateEngine());
		
		get("/logout", (request, response) -> {
			Map<String,Object>model=new HashMap<String, Object>();
			request.session().removeAttribute("user");
			model.put("user", request.session().attribute("user"));
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
