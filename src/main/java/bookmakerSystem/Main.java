package bookmakerSystem;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bookmakerSystem.DAO.MatchDAO;
import bookmakerSystem.DAO.UserDAO;
import bookmakerSystem.DAO.TheWinnerOfAMatchBetDAO;
import bookmakerSystem.model.Coupon;
import bookmakerSystem.model.Match;
import bookmakerSystem.model.TheWinnerOfAMatchBet;
import bookmakerSystem.model.User;
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
		
		get("/", (request, response) ->
		{
			System.out.println("xxxxxxxx");
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", request.session().attribute("user"));

			Timestamp todayDate = new Timestamp(System.currentTimeMillis());
			LocalDateTime date=LocalDateTime.now();
			Timestamp tomorrowDate=Timestamp.valueOf(date.plusDays(1));
			
			ArrayList<Match> todayMatches = new MatchDAO().getMatches(todayDate);
			ArrayList<Match> tomorrowMatches = new MatchDAO().getMatches(tomorrowDate);
			
			if(request.session().attribute("user") != null)	
			{
				Coupon coupon;
			

					coupon = request.session().attribute("coupon");
					if(request.queryParams("bet") != null)
					{
						coupon.addBets(new TheWinnerOfAMatchBetDAO().getWinnerOfTheMatchBet(
								Integer.parseInt(request.queryParams("bet"))));
					}
					request.session().attribute("coupon", coupon);
		
				if(request.queryParams("delete") != null || request.queryParams("acceptCoupon") != null)
					request.session().attribute("coupon", new Coupon());
				
				//if(request.queryParams("acceptCoupon") != null)
					
				
				model.put("coupon", request.session().attribute("coupon"));
			}
			model.put("todayMatches", todayMatches);
			model.put("tomorrowMatches", tomorrowMatches);
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model, layout);

		}, new VelocityTemplateEngine());
		
		
		post("/", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			Map<String, String> errors = new HashMap<String, String>();
			
			model.put("user", request.session().attribute("user"));

			Timestamp todayDate = new Timestamp(System.currentTimeMillis());
			LocalDateTime date=LocalDateTime.now();
			Timestamp tomorrowDate=Timestamp.valueOf(date.plusDays(1));
			
			ArrayList<Match> todayMatches = new MatchDAO().getMatches(todayDate);
			ArrayList<Match> tomorrowMatches = new MatchDAO().getMatches(tomorrowDate);
			
			model.putAll(errors);
			model.put("coupon", request.session().attribute("coupon"));
			model.put("todayMatches", todayMatches);
			model.put("tomorrowMatches", tomorrowMatches);
			
			if(((Coupon) request.session().attribute("coupon")).getBets().isEmpty())
				errors.put("betsError", "Nie dodales zadnego zakladu");
			
			else if(((User) request.session().attribute("user")).getAccountBalance() - 
					Double.parseDouble(request.queryParams("bid")) < 0)
				errors.put("bidError", "Nie masz wystarczajaco duzo pieniedzy");

			if(errors.isEmpty())
			{
				Coupon coupon = request.session().attribute("coupon");
				coupon.setBid(Double.parseDouble(request.queryParams("bid")));
			coupon.setPossibleWin(coupon.getBid());
			for(TheWinnerOfAMatchBet temp: coupon.getBets())
				coupon.setPossibleWin(coupon.getPossibleWin()*temp.getCourse());
			
			coupon.setPossibleWin(Math.round(coupon.getPossibleWin()*100)/100);
			
				model.put("template", "templates/sendedCoupon.vtl");
			}
			else
			{				
				model.put("template", "templates/index.vtl");
			}
			
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		
		get("/register", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("template", "templates/register.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		post("/register", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			Map<String, String> errors = new HashMap<String, String>();
			UserDAO userDAO = new UserDAO();
			
			String login = request.queryParams("login");
			String password = request.queryParams("password");
			String repeatedPassword = request.queryParams("password2");
			String email = request.queryParams("email");
			String name = request.queryParams("name");
			String surname = request.queryParams("surname");
			
			System.out.println(login);
			
			if(login!=null)
			{
				if(new UserDAO().getByLogin(login) != null)
					errors.put("loginError", "Podany login jest zajety");
				else if(login.length()<4)
					errors.put("loginError", "Podany login jest za krotki - musi zawierac przynajmniej 4 znaki");
				else if(!login.matches("[a-zA-Z0-9]{4,}"))
					errors.put("loginError", "Login moze sie skadac z wielkich i malych liter oraz z cyfr od 0-9");
				else if(password.length()<6)
					errors.put("passwordError", "Podane haslo jest za krotkie - musi zawierac przynajmniej 6 znakow");
				else if(!password.matches("[a-zA-Z0-9]{6,}"))
					errors.put("passwordError", "Haslo moze sie skadac z wielkich i malych liter oraz z cyfr od 0-9");
				else if(!password.equals(repeatedPassword))
					errors.put("repeatedPasswordError", "Podane hasla sie nie zgadzaja");
				
				if(new UserDAO().getByLogin(email) != null)
					errors.put("emailError", "Uzytkownik o podanym adresie e-mail juz istnieje");
				
				else if(!email.matches("[a-zA-Z0-9_\\-]+@[a-zA-Z]+\\.[a-zA-Z]+"))
					errors.put("emailError", "Podany adres e-mail jest niepoprawny");
				
				if (errors.isEmpty())
					
				{
					Timestamp todayDate = new Timestamp(System.currentTimeMillis());
					LocalDateTime date=LocalDateTime.now();
					Timestamp tomorrowDate=Timestamp.valueOf(date.plusDays(1));
					
					ArrayList<Match> todayMatches = new MatchDAO().getMatches(todayDate);
					ArrayList<Match> tomorrowMatches = new MatchDAO().getMatches(tomorrowDate);
					
					userDAO.addUser(login, password, email, name, surname);
					User loggedUser = userDAO.getByLogin(login);
					request.session().attribute("user", loggedUser);
					
					model.put("todayMatches", todayMatches);
					model.put("tomorrowMatches", tomorrowMatches);
					model.put("user", request.session().attribute("user"));
					model.put("name", name);
					model.put("template", "templates/index.vtl");
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
			
			
			Timestamp todayDate = new Timestamp(System.currentTimeMillis());
			LocalDateTime date=LocalDateTime.now();
			Timestamp tomorrowDate=Timestamp.valueOf(date.plusDays(1));
			
			ArrayList<Match> todayMatches = new MatchDAO().getMatches(todayDate);
			ArrayList<Match> tomorrowMatches = new MatchDAO().getMatches(tomorrowDate);

			String login = request.queryParams("login");
			String password = request.queryParams("password");
			User loggedUser = new UserDAO().getByLogin(login);
			
			if (loggedUser == null || !loggedUser.getPassword().equals(password))
			{
				model.put("todayMatches", todayMatches);
				model.put("tomorrowMatches", tomorrowMatches);
				model.put("loginError", "Login lub haslo niepoprawne");
				model.put("template", "templates/index.vtl");
				
				
			} 
			else
			{
				Coupon coupon = new Coupon();
				model.put("coupon", request.session().attribute("coupon"));
				model.put("todayMatches", todayMatches);
				model.put("tomorrowMatches", tomorrowMatches);
				//request.session().attribute("user", loggedUser.getLogin());
				request.session().attribute("user", loggedUser);
				coupon.setUserId(((User) request.session().attribute("user")).getId());
				request.session().attribute("coupon", coupon);
				model.put("user", request.session().attribute("user"));
				model.put("template", "templates/index.vtl");
			}

			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		get("/logout", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			request.session().removeAttribute("coupon");
			request.session().removeAttribute("user");
			model.put("user", request.session().attribute("user"));
			
			
			Timestamp todayDate = new Timestamp(System.currentTimeMillis());
			LocalDateTime date=LocalDateTime.now();
			Timestamp tomorrowDate=Timestamp.valueOf(date.plusDays(1));
			
			ArrayList<Match> todayMatches = new MatchDAO().getMatches(todayDate);
			ArrayList<Match> tomorrowMatches = new MatchDAO().getMatches(tomorrowDate);
			
			model.put("todayMatches", todayMatches);
			model.put("tomorrowMatches", tomorrowMatches);
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/moneyManagement", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", request.session().attribute("user"));
			
			Timestamp todayDate = new Timestamp(System.currentTimeMillis());
			LocalDateTime date=LocalDateTime.now();
			Timestamp tomorrowDate=Timestamp.valueOf(date.plusDays(1));
			
			ArrayList<Match> todayMatches = new MatchDAO().getMatches(todayDate);
			ArrayList<Match> tomorrowMatches = new MatchDAO().getMatches(tomorrowDate);
			
			model.put("todayMatches", todayMatches);
			model.put("tomorrowMatches", tomorrowMatches);
			model.put("template", "templates/moneyManagement.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		post("/moneyManagement", (request, response)->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			
			User loggedUser=request.session().attribute("user");
			UserDAO userOperations=new UserDAO();
			
			model.put("user", request.session().attribute("user"));
			
			String contribute=request.queryParams("contribute");
			String getMoney=request.queryParams("getMoney");
			String getAllMoney=request.queryParams("getAllMoney");
			System.out.println(getAllMoney);
			
			if(contribute!=null)
			{
				int amountToAdd=Integer.parseInt(contribute);
				double accountBalance=loggedUser.getAccountBalance()+amountToAdd;
				loggedUser.setAccountBalance(accountBalance);
				userOperations.setAccountBalance(loggedUser.getId(), accountBalance);
			}
			
			if(getMoney!=null)
			{
				int amountToSubstract=Integer.parseInt(getMoney);
				double accountBalance=loggedUser.getAccountBalance();
				if(accountBalance>=amountToSubstract)
					{
						accountBalance-=amountToSubstract;
						accountBalance*=100;
						accountBalance=Math.round(accountBalance);
						accountBalance/=100;
						loggedUser.setAccountBalance(accountBalance);
						userOperations.setAccountBalance(loggedUser.getId(), accountBalance);
					}
				else
				{
					model.put("getMoneyError","Nie masz wystarczajacych srodkow na koncie!");
				}
			}
			
			if(getAllMoney!=null)
			{
				double accountBalance=loggedUser.getAccountBalance();
				if(accountBalance==0)
				{
					model.put("getAllMoneyError", "Nie posiadasz zadnych srodkow na koncie");
				}
				else
				{
					loggedUser.setAccountBalance(0);
					userOperations.setAccountBalance(loggedUser.getId(), 0);
				}
				
			}
			
			Timestamp todayDate = new Timestamp(System.currentTimeMillis());
			LocalDateTime date=LocalDateTime.now();
			Timestamp tomorrowDate=Timestamp.valueOf(date.plusDays(1));
			
			ArrayList<Match> todayMatches = new MatchDAO().getMatches(todayDate);
			ArrayList<Match> tomorrowMatches = new MatchDAO().getMatches(tomorrowDate);
			
			model.put("todayMatches", todayMatches);
			model.put("tomorrowMatches", tomorrowMatches);
			model.put("template", "templates/moneyManagement.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/userDataManagement", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", request.session().attribute("user"));
			
			
			model.put("template", "templates/userDataManagement.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/couponManagement", (request, response) ->
		{
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", request.session().attribute("user"));
			
			
			model.put("template", "templates/couponManagement.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		
		/*get("/sendedCoupon", (request, response) ->
		{
			System.out.println(request.queryParams("bid"));
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", request.session().attribute("user"));
			
			Timestamp todayDate = new Timestamp(System.currentTimeMillis());
			LocalDateTime date=LocalDateTime.now();
			Timestamp tomorrowDate=Timestamp.valueOf(date.plusDays(1));
			
			ArrayList<Match> todayMatches = new MatchDAO().getMatches(todayDate);
			ArrayList<Match> tomorrowMatches = new MatchDAO().getMatches(tomorrowDate);
			
			if(request.queryParams("delete") != null)
				request.session().attribute("coupon", new Coupon());
			
			Coupon coupon = request.session().attribute("coupon");
			coupon.setBid(Double.parseDouble(request.queryParams("bid")));
			coupon.setPossibleWin(coupon.getBid());
			for(TheWinnerOfAMatchBet temp: coupon.getBets())
				coupon.setPossibleWin(coupon.getPossibleWin()*temp.getCourse());
			
			coupon.setPossibleWin(Math.round(coupon.getPossibleWin()*100)/100);
			
			model.put("coupon", request.session().attribute("coupon"));
			model.put("todayMatches", todayMatches);
			model.put("tomorrowMatches", tomorrowMatches);
			model.put("template", "templates/sendedCoupon.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());*/
	}
}