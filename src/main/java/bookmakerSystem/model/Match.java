package bookmakerSystem.model;

import java.sql.Timestamp;
import java.util.ArrayList;

import bookmakerSystem.DAO.TheWinnerOfAMatchBetDAO;

public class Match
{
	private int id;
	private Timestamp matchDate;
	private int round;
	private Club host;
	private Club guest;
	private Integer hostGoals;
	private Integer guestGoals;
	private ArrayList<TheWinnerOfAMatchBet> theWinnerOfAMatchBets;

	public Match(int id, Timestamp matchDate, int round, Club host, Club guest, Integer hostGoals,
			Integer guestGoals, ArrayList<TheWinnerOfAMatchBet> theWinnerOfAMatchBets)
	{
		this.id = id;
		this.matchDate = matchDate;
		this.round = round;
		this.host = host;
		this.guest = guest;
		this.hostGoals = hostGoals;
		this.guestGoals = guestGoals;
		this.theWinnerOfAMatchBets = theWinnerOfAMatchBets;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Timestamp getMatchDate()
	{
		return matchDate;
	}

	public void setMatchDate(Timestamp matchDate)
	{
		this.matchDate = matchDate;
	}

	public int getRound()
	{
		return round;
	}

	public void setRound(int round)
	{
		this.round = round;
	}

	public Club getHost()
	{
		return host;
	}

	public void setHost(Club host)
	{
		this.host = host;
	}

	public Club getGuest()
	{
		return guest;
	}

	public void setGuest(Club guest)
	{
		this.guest = guest;
	}

	public int getHostGoals()
	{
		return hostGoals;
	}

	public void setHostGoals(int hostGoals)
	{
		this.hostGoals = hostGoals;
	}

	public int getGuestGoals()
	{
		return guestGoals;
	}

	public void setGuestGoals(int guestGoals)
	{
		this.guestGoals = guestGoals;
	}

	public ArrayList<TheWinnerOfAMatchBet> getTheWinnerOfAMatchBets()
	{
		return theWinnerOfAMatchBets;
	}

	public void setTheWinnerOfAMatchBets(ArrayList<TheWinnerOfAMatchBet> theWinnerOfAMatchBets)
	{
		this.theWinnerOfAMatchBets = theWinnerOfAMatchBets;
	}

	
}
