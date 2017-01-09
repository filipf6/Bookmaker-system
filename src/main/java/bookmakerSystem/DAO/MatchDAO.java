package bookmakerSystem.DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static bookmakerSystem.DatabaseConnector.*;
import bookmakerSystem.model.*;

public class MatchDAO
{
	public ArrayList<Match> getMatches(Timestamp matchDate)
	{
		ArrayList<Match> matches = new ArrayList<Match>();
		ClubDAO clubDAO = new ClubDAO();
		TheWinnerOfAMatchBetDAO theWinnerOfAMatchBetDAO = new TheWinnerOfAMatchBetDAO();
		try
		{
			ResultSet rs = getResultStatement("SELECT * FROM MECZ WHERE TRUNC(DATA_ROZPOCZECIA) = "
					+ "'" + matchDate.toLocalDateTime().toLocalDate().format(DateTimeFormatter.BASIC_ISO_DATE) + "'");
			while(rs.next())
			{
				//System.out.println(rs.getTimestamp(2));
				matches.add(new Match(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), clubDAO.getClub(rs.getInt(6)),  
						clubDAO.getClub(rs.getInt(7)), (Integer)rs.getObject(4), (Integer)rs.getObject(5),
						TheWinnerOfAMatchBetDAO.getWinnerOfTheMatchBets(rs.getInt(1), rs.getTimestamp(2))));
			}
			return matches;
			
		} catch (SQLException e)
		{
			System.out.println("Error");
			return null;
		}
	}
}
