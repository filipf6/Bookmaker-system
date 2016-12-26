package bookmakerSystem.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bookmakerSystem.DatabaseConnector;
import bookmakerSystem.model.User;

public class UserDAO
{
	public static User LogIn(String login, String password) throws SQLException
	{
		ResultSet rs = DatabaseConnector.getResultStatement("select * from UZYTKOWNIK where haslo = '"+password+"' and login = '"+login+"'");
		if(!(rs.next())) return null;
		else
		{	
			String name="",surname="",email="";
		
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
	
	public static ArrayList<String> getRegistrationErrors(String login, String password, String repeatedPassword, String email, String name, String surname) throws SQLException
	{
		ArrayList<String>errors=new ArrayList<String>();
		if(!password.equals(repeatedPassword))
		{
			errors.add("Podane hasla sie nie zgadzaja");
		}
		else
		{
			if(password.length()<6) errors.add("Haslo za krotkie- powinno zawierac minimum 6 znakow");
		}
		if(login.length()<3) 
			errors.add("Login za krotki-powinien zawierac minimum 3 znaki");
		if(!email.contains("@") || !(email.indexOf("@")==email.lastIndexOf("@")) || !email.contains(".") || !(email.lastIndexOf(".")>email.indexOf("@"))) 
			errors.add("E-mail niepoprawny");
		
		ResultSet rs = DatabaseConnector.getResultStatement("select id_uzytkownika from UZYTKOWNIK where login = '"+login+"'");

        if(rs.next()) errors.add("Podany login jest juz zajety");
        
        rs = DatabaseConnector.getResultStatement("select id_uzytkownika from UZYTKOWNIK where e_mail = '"+email+"'");

        if(rs.next()) errors.add("Uzytkownik o podanym adresie e-mail istnieje juz w bazie");
		
		return errors;
	}
	
	
	public static User register(String login, String password, String email, String name, String surname)
	{
        DatabaseConnector.executeUpdate("INSERT INTO UZYTKOWNIK VALUES (SEQ_ID.NEXTVAL,'"+name+"', '"+surname+"', '"+login+"', '"+password+"', '"+email+"')");
		return new User(login, password, email, name, surname);
	}
	
	
}
