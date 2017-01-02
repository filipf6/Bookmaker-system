package bookmakerSystem.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bookmakerSystem.DatabaseConnector;
import bookmakerSystem.model.User;

public class UserDAO
{
	public static User LogIn(String login, String password) throws SQLException
	{
		ResultSet rs = DatabaseConnector.getResultStatement("select * from UZYTKOWNIK where haslo = '"+password+"' and login = '"+login+"'");
		if(!(rs.next())) 
			return null;
		else
		{	
			String name="", surname="", email="";
		
			rs=DatabaseConnector.getResultStatement("select * from UZYTKOWNIK where haslo = '"+password+"' and login = '"+login+"'");
			
			while(rs.next())
			{
				
				name = rs.getString(2);
				surname = rs.getString(3);
				email = rs.getString(6);
			}
			
			return new User(login, password,email,name,surname);
		}
	}
	
	public static Map<String, String> getRegistrationErrors(String login, String password, String repeatedPassword, String email, String name, String surname) throws SQLException
	{
		Map<String, String> errors = new HashMap<String, String>();
		
		ResultSet rs = DatabaseConnector.getResultStatement("SELECT "
				+ "ID_UZYTKOWNIKA FROM UZYTKOWNIK WHERE LOGIN = '"+login+"'");
		if(rs.next())
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
		
		rs = DatabaseConnector.getResultStatement("SELECT "
				+ "ID_UZYTKOWNIKA FROM UZYTKOWNIK WHERE E_MAIL = '"+email+"'");
		if(rs.next())
			errors.put("emailError", "Uzytkownik o podanym adresie e-mail juz istnieje");
		else if(email==null || "".equals(email))
			errors.put("emailError", "Pole e-mail nie moze byc puste");
		else if(!email.matches("[a-zA-Z0-9_\\-]+@[a-zA-Z]+\\.[a-zA-Z]+"))
			errors.put("emailError", "Podany adres e-mail jest niepoprawny");
		if(name==null || name.equals(""))
			errors.put("nameError", "Pole imie nie moze byc puste");
		if(surname==null || surname.equals(""))
			errors.put("surnameError", "Pole nazwisko nie moze byc puste");
		
		return errors;
	}
	
	public static User register(String login, String password, String email, String name, String surname)
	{
        DatabaseConnector.executeUpdate("INSERT INTO UZYTKOWNIK VALUES (SEQ_ID.NEXTVAL,'"+name+"', '"+surname+"', '"+login+"', '"+password+"', '"+email+"')");
		return new User(login, password, email, name, surname);
	}
	
	
}
