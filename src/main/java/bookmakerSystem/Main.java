package bookmakerSystem;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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

public class Main
{

	public static void main(String[] args)
	{
		DatabaseConnector.connectWithBase();

		port(8012);
		staticFileLocation("/public");

		String layout = "templates/layout.vtl";
		//Timestamp timestamp = Timestamp.valueOf(localDateTime);
		//timeStamp.toLocalDateTime().toLocalDate();
		//localDate.getHour
		
		get("/", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			
			String day = request.queryParams("day");
			//System.out.print(day);
			MatchService.matchesToVTL(day, model);

			model.put("day", day);
			model.put("user", request.session().attribute("user"));
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model, layout);

		}, new VelocityTemplateEngine());

		post("/register", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();

			Map<String, String> errors = new HashMap();
			String login = request.queryParams("login");
			String password = request.queryParams("password");
			String repeatedPassword = request.queryParams("password2");
			String email = request.queryParams("email");
			String name = request.queryParams("name");
			String surname = request.queryParams("surname");
			System.out.println(login);
			if(login!=null)
			{
				errors = UserDAO.getRegistrationErrors(login, password, repeatedPassword, email, name, surname);
				if (errors.isEmpty())
				{
					User person = UserDAO.register(login, password, email, name, surname);
					request.session().attribute("user", person);
					model.put("name", name);
					model.put("template", "templates/welcome.vtl");
				} 
				else
				{
					model.putAll(errors);
					model.put("template", "templates/register.vtl");
				}
			}
			else
			{
				model.putAll(errors);
				model.put("template", "templates/register.vtl");
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

			if (person == null)
			{
				model.put("template", "templates/logingError.vtl");
			} else
			{
				request.session().attribute("user", person);
				// model.put("name", request.queryParams("login"));
				model.put("user", request.session().attribute("user"));
				model.put("template", "templates/index.vtl"); // tutej welcome
			}

			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		get("/logout", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			request.session().removeAttribute("user");
			model.put("user", request.session().attribute("user"));
			String day = request.queryParams("day");
			MatchService.matchesToVTL(day, model);
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model, layout);

		}, new VelocityTemplateEngine());
	}

	/*
	 * public static void main(String[] args) { List<Uzytkownik> users =
	 * DB.sql2o.createQuery("select * from uzytkownik").executeAndFetch(
	 * Uzytkownik.class); for (Uzytkownik user : users) {
	 * System.out.println(user.getIMIE()); }
	 * 
	 * System.out.println(users.size()); }
	 */

}
