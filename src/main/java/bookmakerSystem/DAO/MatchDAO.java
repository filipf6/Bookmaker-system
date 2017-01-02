package bookmakerSystem.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static bookmakerSystem.DatabaseConnector.*;
import bookmakerSystem.model.*;

public class MatchDAO
{
	public static ArrayList<Match>getMatches(LocalDate matchDate)
	{
		//System.out.println(matchDate);
		ArrayList<Match>matches=new ArrayList<Match>();
		matches = null;
		try
		{
			ResultSet rs=getResultStatement("select k1.nazwa_klubu, k1.data_zalozenia, k1.stadion, k2.nazwa_klubu, k2.data_zalozenia, k2.stadion, m.godzina_rozpoczecia, m.numer_kolejki, m.kurs_zwyciestwo_gospodarza,m.kurs_remis,m.kurs_zwyciestwo_goscia "
														+"from mecz m join klub k1 on m.id_gospodarza=k1.id_klubu "
														+"join klub k2 on m.id_goscia=k2.id_klubu "
														+"where m.data_meczu='"+matchDate+"'");
			while(rs.next())
			{
				matches.add(new Match(matchDate,rs.getString(7),rs.getInt(8),new Club(rs.getString(1),rs.getDate(2).toLocalDate(),rs.getString(3)),new Club(rs.getString(4),rs.getDate(5).toLocalDate(),rs.getString(6)),rs.getFloat(9),rs.getFloat(10),rs.getFloat(11)));
			}
			return matches;
			
		} catch (SQLException e)
		{
			System.out.println("Error");
			return null;
		}
	}
}
