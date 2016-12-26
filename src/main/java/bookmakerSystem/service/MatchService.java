package bookmakerSystem.service;

import java.time.LocalDate;
import java.util.Map;

import bookmakerSystem.DAO.MatchDAO;

public class MatchService
{
	public static void matchesToVTL(String day, Map<String,Object>model)
	{
		LocalDate today=LocalDate.now();
		if(day==null)
		{
			model.put("matches",MatchDAO.getMatches(today));
		}
		else
		{
			switch(day)
			{
			case "today":
				model.put("matches",MatchDAO.getMatches(today));
				break;
			case "tomorrow":
				model.put("matches", MatchDAO.getMatches(today.plusDays(1)));
				break;
			case "overmorrow":
				model.put("matches", MatchDAO.getMatches(today.plusDays(2)));
				break;
			default:
				model.put("matches",MatchDAO.getMatches(today));
				break;	
			}
		}
	}
}
