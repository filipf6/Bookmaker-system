package bookmakerSystem.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bookmakerSystem.DatabaseConnector;

public class User
{
	private final int id;
	private final String login;
	private String password;
	private String email;
	private String name;
	private String surname;
	
	public User(int id, String login, String password, String email, String name, String surname)
	{
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
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

	
	
}
