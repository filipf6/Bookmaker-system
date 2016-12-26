package bookmakerSystem.model;

import java.time.LocalDate;

public abstract class Bet
{
	private float course;
	private boolean settlement; 
	private LocalDate settlementDate;
	
	public Bet(float course, boolean settlement, LocalDate settlementDate)
	{
		super();
		this.course = course;
		this.settlement = settlement;
		this.settlementDate = settlementDate;
	}
	
	public float getCourse()
	{
		return course;
	}
	public void setCourse(float course)
	{
		this.course = course;
	}
	public boolean isSettlement()
	{
		return settlement;
	}
	public void setSettlement(boolean settlement)
	{
		this.settlement = settlement;
	}
	public LocalDate getSettlementDate()
	{
		return settlementDate;
	}
	public void setSettlementDate(LocalDate settlementDate)
	{
		this.settlementDate = settlementDate;
	}
	
	
}
