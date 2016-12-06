package bookmakerSystem.model;

import java.sql.Date;

public class Club
{
	private String name;
	private Date establishmentDay;
	private String stadiumName;
	
	public Club(String name, Date establishmentDay, String stadiumName)
	{
		super();
		this.name = name;
		this.establishmentDay = establishmentDay;
		this.stadiumName = stadiumName;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Date getEstablishmentDay()
	{
		return establishmentDay;
	}
	public void setEstablishmentDay(Date establishmentDay)
	{
		this.establishmentDay = establishmentDay;
	}
	public String getStadiumName()
	{
		return stadiumName;
	}
	public void setStadiumName(String stadiumName)
	{
		this.stadiumName = stadiumName;
	}
	
	
	
}
