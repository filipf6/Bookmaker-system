package bookmakerSystem;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bookmakerSystem.DAO.MatchDAO;
import bookmakerSystem.DAO.UserDAO;
import bookmakerSystem.model.Coupon;
import bookmakerSystem.model.Match;
import bookmakerSystem.model.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main
{

	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
		DatabaseConnector.connectWithBase();

		port(8012);
		staticFileLocation("/public");
		
		String layout = "templates/layout.vtl";
		
		get("/", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", request.session().attribute("user"));


			
			Timestamp date = new Timestamp(System.currentTimeMillis());

			Coupon coupon;
			if(request.session().attribute("coupon") == null)
			{
				coupon = new Coupon();
				request.session().attribute("coupon", coupon);
			}
			else
				coupon = request.session().attribute("coupon");

			
			ArrayList<Match> matches = new MatchDAO().getMatches(date);
			//System.out.println(matches.get(0).getTheWinnerOfAMatchBets().get(0));
			model.put("matches", matches);
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model, layout);

		}, new VelocityTemplateEngine());

		post("/register", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			Map<String, String> errors = new HashMap();
			UserDAO userDAO = new UserDAO();
			
			String login = request.queryParams("login");
			String password = request.queryParams("password");
			String repeatedPassword = request.queryParams("password2");
			String email = request.queryParams("email");
			String name = request.queryParams("name");
			String surname = request.queryParams("surname");
			
			if(login!=null)
			{
				if(new UserDAO().getByLogin(login) != null)
					errors.put("loginError", "Podany login jest zajety");
				else if(login==null || login.equals(""))
					errors.put("loginError", "Pole login nie moze byc puste");
				else if(login.length()<4)
					errors.put("loginError", "Podany login jest za krotki - musi zawierac przynajmniej 4 znaki");
				else if(!login.matches("[a-zA-Z0-9]{4,}"))
					errors.put("loginError", "Login moze sie skadac z wielkich i malych liter oraz z cyfr od 0-9");
				
				if(password==null || password.equals(""))
					errors.put("passwordError", "Pole haslo nie moze byc puste");
				else if(password.length()<6)
					errors.put("passwordError", "Podane haslo jest za krotkie - musi zawierac przynajmniej 6 znakow");
				else if(!password.matches("[a-zA-Z0-9]{6,}"))
					errors.put("passwordError", "Haslo moze sie skadac z wielkich i malych liter oraz z cyfr od 0-9");
				else if(!password.equals(repeatedPassword))
					errors.put("repeatedPasswordError", "Podane hasla sie nie zgadzaja");
				
				if(new UserDAO().getByLogin(email) != null)
					errors.put("emailError", "Uzytkownik o podanym adresie e-mail juz istnieje");
				else if(email==null || "".equals(email))
					errors.put("emailError", "Pole e-mail nie moze byc puste");
				else if(!email.matches("[a-zA-Z0-9_\\-]+@[a-zA-Z]+\\.[a-zA-Z]+"))
					errors.put("emailError", "Podany adres e-mail jest niepoprawny");
				if(name==null || name.equals(""))
					errors.put("nameError", "Pole imie nie moze byc puste");
				if(surname==null || surname.equals(""))
					errors.put("surnameError", "Pole nazwisko nie moze byc puste");
				if (errors.isEmpty())
					
				{
					userDAO.addUser(login, password, email, name, surname);
					User loggedUser = userDAO.getByLogin(login);
					request.session().attribute("user", loggedUser.getLogin());
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

			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());


		post("/login", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();

			String login = request.queryParams("login");
			String password = request.queryParams("password");
			User loggedUser = new UserDAO().getByLogin(login);
			
			if (loggedUser == null || !loggedUser.getPassword().equals(password))
			{
				model.put("template", "templates/logingError.vtl");
			} 
			else
			{
				request.session().attribute("user", loggedUser.getLogin());
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
			model.put("template", "templates/index.vtl");
			
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
	}
}
