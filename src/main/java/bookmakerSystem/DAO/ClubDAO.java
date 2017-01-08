package bookmakerSystem.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static bookmakerSystem.DatabaseConnector.*;

import bookmakerSystem.model.Club;

public class ClubDAO
{
	public Club getClub(int clubId)
	{
		Club club;
		ResultSet rs = getResultStatement("SELECT * FROM KLUB WHERE ID_KLUBU = " + clubId);
		try
		{
			if(rs.next())
				club = new Club(rs.getInt(1), rs.getString(2), (LocalDate)rs.getObject(3), rs.getString(4));
			else club = null;
			
			return club;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
