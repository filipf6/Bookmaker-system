package bookmakerSystem.DAO;

import static bookmakerSystem.DatabaseConnector.*;
import bookmakerSystem.model.Club;
import bookmakerSystem.model.Match;
import bookmakerSystem.model.TheWinnerOfAMatchBet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import bookmakerSystem.model.Result;

public class TheWinnerOfAMatchBetDAO
{
	public static ArrayList<TheWinnerOfAMatchBet> getWinnerOfTheMatchBets(int matchId, Timestamp date)
	{
		ArrayList<TheWinnerOfAMatchBet> winnerOfTheMatchBets = new ArrayList<TheWinnerOfAMatchBet>();
		ResultSet rs = getResultStatement("SELECT * FROM ZWYCIEZCA_MECZU WHERE ID_MECZU = "
				+ "(SELECT ID_MECZU FROM MECZ WHERE ID_MECZU = " + matchId + " AND TRUNC(DATA_ROZPOCZECIA) = "
						+ "'" + date.toLocalDateTime().toLocalDate().format(DateTimeFormatter.BASIC_ISO_DATE) + "')");
		try
		{
			while(rs.next())
			{
				winnerOfTheMatchBets.add(new TheWinnerOfAMatchBet(rs.getInt(1), rs.getFloat(2),
						(rs.getInt(3) == 0 ? false : (rs.getObject(3) == null ? null : true)), 
						Result.valueOf(rs.getString(4))));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		return winnerOfTheMatchBets;
	}
	
}
