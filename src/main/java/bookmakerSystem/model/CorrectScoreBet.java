package bookmakerSystem.model;

public class CorrectScoreBet extends Bet
{
	private int hostGoals;
	private int guestGoals;
	public CorrectScoreBet(int id, float course, Boolean settlement, int hostGoals, int guestGoals)
	{
		super(id, course, settlement);
		this.hostGoals = hostGoals;
		this.guestGoals = guestGoals;
	}

}
