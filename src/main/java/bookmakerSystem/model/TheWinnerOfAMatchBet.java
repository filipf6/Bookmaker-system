package bookmakerSystem.model;

import java.sql.Timestamp;

public class TheWinnerOfAMatchBet extends Bet
{
	final Result hostResult;
	public TheWinnerOfAMatchBet(int id, float course, Boolean settlement, Result hostResult)
	{
		super(id, course, settlement);
		this.hostResult = hostResult;
	}
	
	public Result getHostResult()
	{
		return hostResult;
	}

	@Override
	public String toString()
	{
		return "TheWinnerOfAMatchBet [hostResult=" + hostResult + ", match=" + match + ", getHostResult()="
				+ getHostResult() + ", getId()=" + getId() + ", getCourse()=" + getCourse() + ", getSettlement()="
				+ getSettlement() + ", getMatch()=" + getMatch() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}