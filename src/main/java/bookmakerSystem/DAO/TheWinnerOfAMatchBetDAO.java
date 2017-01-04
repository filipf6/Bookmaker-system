package bookmakerSystem.DAO;

import static bookmakerSystem.DatabaseConnector.*;
import bookmakerSystem.model.Club;
import bookmakerSystem.model.Match;
import bookmakerSystem.model.TheWinnerOfAMatchBet;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TheWinnerOfAMatchBetDAO
{
	public static ArrayList<TheWinnerOfAMatchBet> getWinnerOfTheMatchBets(LocalDate date)
	{
		ArrayList<TheWinnerOfAMatchBet> winnerOfTheMatchBets = new ArrayList();
		ResultSet rs = getResultStatement("SELECT * FROM ZWYCIEZCA_MECZU WHERE ID_MECZU = (SELECT ID_MECZU FROM MECZ WHERE DATA_MECZU = '" + date + "')");
		winnerOfTheMatchBets = null;
		
		try
		{
			while(rs.next())
			{
				System.out.println(rs.getDate(4));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return winnerOfTheMatchBets;
	}
	
}
