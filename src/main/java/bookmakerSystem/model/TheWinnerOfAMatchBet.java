package bookmakerSystem.model;

import java.time.LocalDate;

public class TheWinnerOfAMatchBet extends Bet
{
	private int hostResult;
	
	public TheWinnerOfAMatchBet(float course, boolean settlement, 
			LocalDate settlementDate, int hostResult)
	{
		super(hostResult, course, settlement, settlementDate);
		this.hostResult = hostResult;
	}
	
	
}
