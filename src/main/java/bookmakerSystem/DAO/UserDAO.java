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
	public User getByLogin(String login)
	{
		ResultSet rs = DatabaseConnector.getResultStatement("SELECT * FROM UZYTKOWNIK WHERE LOGIN = '" + login + "'");
		try
		{
			if(rs.next())
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getString(6));
			else
				return null;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public User getByEmail(String email)
	{
		ResultSet rs = DatabaseConnector.getResultStatement("SELECT * FROM UZYTKOWNIK WHERE E_MAIL = '" + email + "'");
		try
		{
			if(rs.next())
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getString(6));
			else
				return null;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void addUser(String login, String password, String email, String name, String surname)
	{
        DatabaseConnector.executeUpdate("INSERT INTO UZYTKOWNIK VALUES (SEQ_ID.NEXTVAL,'"+name+"', '"+surname+"', '"+login+"', '"+password+"', '"+email+"')");
	}
}
