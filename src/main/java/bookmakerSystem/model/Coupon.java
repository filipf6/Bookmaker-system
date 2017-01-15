package bookmakerSystem.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Coupon
{
	private int id;
	private double bid;
	private double possibleWin;
	private Integer settlement;
	private Timestamp settlementDate;
	private int userId;
	ArrayList<TheWinnerOfAMatchBet> bets = new ArrayList<TheWinnerOfAMatchBet>();
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public double getBid()
	{
		return bid;
	}
	public void setBid(double bid)
	{
		this.bid = bid;
	}
	public double getPossibleWin()
	{
		return possibleWin;
	}
	public void setPossibleWin(double possibleWin)
	{
		this.possibleWin = possibleWin;
	}
	public Integer getSettlement()
	{
		return settlement;
	}
	public void setSettlement(Integer settlement)
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
	public ArrayList<TheWinnerOfAMatchBet> getBets()
	{
		return bets;
	}
	public void setBets(ArrayList<TheWinnerOfAMatchBet> bets)
	{
		this.bets = bets;
	}
	public void addBets(TheWinnerOfAMatchBet bet)
	{
		this.bets.add(bet);
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
}
