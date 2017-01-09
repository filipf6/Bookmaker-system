package bookmakerSystem.model;

import java.sql.Timestamp;

public class TheWinnerOfAMatchBet extends Bet
{
	private Match betMatch;
	private float hostWillWinCourse;
	private float drawCourse;
	private float guestWillWinCourse;
	
	public TheWinnerOfAMatchBet(boolean settlement, Timestamp settlementDate)
	{
		super(settlement, settlementDate);
	}
	
	
	
	
		
}
