package bookmakerSystem.model;

import java.sql.Timestamp;

public class TheWinnerOfAMatchBet extends Bet
{
	private int hostResult;
	
	public TheWinnerOfAMatchBet(int id, float course, boolean settlement, 
			Timestamp settlementDate, int hostresult)
	{
		super(id, course, settlement, settlementDate);
		this.hostResult = hostResult;
	}
		
}
