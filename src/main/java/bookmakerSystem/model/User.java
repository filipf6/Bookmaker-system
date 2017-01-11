package bookmakerSystem.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bookmakerSystem.DatabaseConnector;

public class User
{
	private final int id;
	private final int type;
	private String name;
	private String surname;
	private final String login;
	private String password;
	private String email;
	private double accountBalance;
	
	public User(int id, int type, String name, String surname, String login, String password, String email,
			double accountBalance)
	{
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.accountBalance = accountBalance;
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

	public double getAccountBalance()
	{
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance)
	{
		this.accountBalance = accountBalance;
	}

	public int getId()
	{
		return id;
	}

	public int getType()
	{
		return type;
	}

	public String getLogin()
	{
		return login;
	}
	
	
	
	
	
	
}
