package bookmakerSystem.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public abstract class Bet
{
	private boolean settlement; 
	private Timestamp settlementDate;
	
	public Bet(boolean settlement, Timestamp settlementDate)
	{
		this.settlement = settlement;
		this.settlementDate = settlementDate;
	}
	
	public boolean isSettlement()
	{
		return settlement;
	}
	public void setSettlement(boolean settlement)
	{
		this.settlement = settlement;
	}
	public Timestamp getSettlementDate()
	{
		return settlementDate;
	}
	public void setSettlementDate(Timestamp settlementDate)
	{
		this.settlementDate = settlementDate;
	}
	
	
}
