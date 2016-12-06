package bookmakerSystem.model;

import java.sql.Date;

public class Match
{
	private Date matchDate;
	private int matchWeek;
	private int hostGoals;
	private int guestGoals;
	private Club host;
	private Club guest;
	
	public Match(Date matchDate, int matchWeek, int hostGoals, int guestGoals, Club host, Club guest)
	{
		super();
		this.matchDate = matchDate;
		this.matchWeek = matchWeek;
		this.hostGoals = hostGoals;
		this.guestGoals = guestGoals;
		this.host = host;
		this.guest = guest;
	}

	public Date getMatchDate()
	{
		return matchDate;
	}

	public void setMatchDate(Date matchDate)
	{
		this.matchDate = matchDate;
	}

	public int getMatchWeek()
	{
		return matchWeek;
	}

	public void setMatchWeek(int matchWeek)
	{
		this.matchWeek = matchWeek;
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
	
	
	
	
	
}
