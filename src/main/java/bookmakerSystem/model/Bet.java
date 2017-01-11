package bookmakerSystem.model;

import java.sql.Timestamp;

public abstract class Bet
{

	private int id;
	private float course;
	private Boolean settlement;
	Match match;

	public Bet(int id, float course, Boolean settlement)
	{
		System.out.println(course);
		this.settlement = settlement;
		this.course=course;
	}


	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public float getCourse()
	{
		return course;
	}

	public void setCourse(float course)
	{
		this.course = course;
	}

	public Boolean getSettlement()
	{
		return settlement;
	}

	public void setSettlement(Boolean settlement)
	{
		this.settlement = settlement;
	}


	public Match getMatch()
	{
		return match;
	}


	public void setMatch(Match match)
	{
		this.match = match;
	}
	
}
