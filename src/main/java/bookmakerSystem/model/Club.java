package bookmakerSystem.model;

import java.time.LocalDate;

public class Club
{
	private String name;
	private LocalDate establishmentDate;
	private String stadiumName;
	
	public Club(String name, LocalDate establishmentDate, String stadiumName)
	{
		super();
		this.name = name;
		this.establishmentDate = establishmentDate;
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
	public LocalDate getestablishmentDate()
	{
		return establishmentDate;
	}
	public void setestablishmentDate(LocalDate establishmentDate)
	{
		this.establishmentDate = establishmentDate;
	}
	public String getStadiumName()
	{
		return stadiumName;
	}
	public void setStadiumName(String stadiumName)
	{
		this.stadiumName = stadiumName;
	}
	@Override
	public String toString()
	{
		return "Club [name=" + name + ", establishmentDate=" + establishmentDate + ", stadiumName=" + stadiumName + "]";
	}
	
	
	
	
}
