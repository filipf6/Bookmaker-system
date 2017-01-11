package bookmakerSystem.model;

import java.time.LocalDate;

public class Club
{
	private int id;
	private String name;
	private LocalDate establishmentDate;
	private String stadiumName;
	
	public Club(int id, String name, LocalDate establishmentDate, String stadiumName)
	{
		this.id = id;
		this.name = name;
		this.establishmentDate = establishmentDate;
		this.stadiumName = stadiumName;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public LocalDate getEstablishmentDate()
	{
		return establishmentDate;
	}

	public void setEstablishmentDate(LocalDate establishmentDate)
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
	
	
}
