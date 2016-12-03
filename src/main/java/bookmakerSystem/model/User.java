package bookmakerSystem.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bookmakerSystem.DatabaseConnector;

public class User
{
	private final String login;
	private String password;
	private String email;
	private String name;
	private String surname;
	
	public User(String login, String password, String email, String name, String surname)
	{
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
	}

	public User(String login, String password)
	{
		this.login=login;
		this.password=password;
		// TODO Auto-generated constructor stub
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}


	public String getLogin()
	{
		return login;
	}
	
	@Override
	public String toString()
	{
		return "User [login=" + login + ", password=" + password + ", email=" + email + ", name=" + name + ", surname="
				+ surname + "]";
	}

	public ArrayList<String> register() throws SQLException
	{
		ArrayList<String> errors = new ArrayList<String>();
		
		

        ResultSet rs = DatabaseConnector.getResultStatement("select * from UZYTKOWNIK where login = '"+login+"'");

        if(rs.next()) errors.add("usernameError");
        
        rs = DatabaseConnector.getResultStatement("select * from UZYTKOWNIK where e_mail = '"+email+"'");

        if(rs.next()) errors.add("emailError");


        if(errors.isEmpty())
        {	
            DatabaseConnector.executeUpdate("INSERT INTO UZYTKOWNIK VALUES (SEQ_ID.NEXTVAL,'"+name+"', '"+surname+"', '"+login+"', '"+password+"', '"+email+"')");
        }

		
		return errors;
	}

	
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
	
}
