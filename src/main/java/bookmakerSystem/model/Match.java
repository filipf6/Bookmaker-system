package bookmakerSystem.model;

import java.time.LocalDate;

public class Match
{
	private LocalDate matchDate;
	private String matchHour;
	private int matchWeek;
	private Club host;
	private Club guest;
	private float hostWillWinCourse;
	private float drawCourse;
	private float guestWillWinCourse;
	
	private int hostGoals;
	private int guestGoals;
	
	

	public Match(LocalDate matchDate, String matchHour, int matchWeek, Club host, Club guest, float hostWillWinCourse,
			float drawCourse, float guestWillWinCourse, int hostGoals, int guestGoals)
	{
		super();
		this.matchDate = matchDate;
		this.matchHour = matchHour;
		this.matchWeek = matchWeek;
		this.host = host;
		this.guest = guest;
		this.hostWillWinCourse = hostWillWinCourse;
		this.drawCourse = drawCourse;
		this.guestWillWinCourse = guestWillWinCourse;
		this.hostGoals = hostGoals;
		this.guestGoals = guestGoals;
	}
	
	

	public Match(LocalDate matchDate, String matchHour, int matchWeek, Club host, Club guest, float hostWillWinCourse,
			float drawCourse, float guestWillWinCourse)
	{
		super();
		this.matchDate = matchDate;
		this.matchHour = matchHour;
		this.matchWeek = matchWeek;
		this.host = host;
		this.guest = guest;
		this.hostWillWinCourse = hostWillWinCourse;
		this.drawCourse = drawCourse;
		this.guestWillWinCourse = guestWillWinCourse;
	}



	public LocalDate getMatchDate()
	{
		return matchDate;
	}

	public void setMatchDate(LocalDate matchDate)
	{
		this.matchDate = matchDate;
	}

	public String getMatchHour()
	{
		return matchHour;
	}

	public void setMatchHour(String matchHour)
	{
		this.matchHour = matchHour;
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
	
	
	public float getHostWillWinCourse()
	{
		return hostWillWinCourse;
	}

	public void setHostWillWinCourse(float hostWillWinCourse)
	{
		this.hostWillWinCourse = hostWillWinCourse;
	}

	public float getDrawCourse()
	{
		return drawCourse;
	}

	public void setDrawCourse(float drawCourse)
	{
		this.drawCourse = drawCourse;
	}

	public float getGuestWillWinCourse()
	{
		return guestWillWinCourse;
	}

	public void setGuestWillWinCourse(float guestWillWinCourse)
	{
		this.guestWillWinCourse = guestWillWinCourse;
	}

	@Override
	public String toString()
	{
		return "Match [matchDate=" + matchDate + ", matchHour=" + matchHour + ", matchWeek=" + matchWeek + ", host="
				+ host + ", guest=" + guest + ", hostGoals=" + hostGoals + ", guestGoals=" + guestGoals + "]";
	}
	
	
	
	
	
}
