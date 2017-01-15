package bookmakerSystem.DAO;

import bookmakerSystem.DatabaseConnector;
import bookmakerSystem.model.Coupon;
import bookmakerSystem.model.TheWinnerOfAMatchBet;

public class CouponDAO
{
	void setCoupon(Coupon coupon)
	{
		DatabaseConnector.executeUpdate("INSERT INTO KUPON VALUES (1, " + coupon.getBid() + ", " + coupon.getPossibleWin() + ", " + null + ", '" + coupon.getSettlementDate() + "', " +  coupon.getUserId());
		for(TheWinnerOfAMatchBet bet: coupon.getBets())
		{
			DatabaseConnector.executeUpdate("INSERT INTO  VALUES (1, " + coupon.getBid() + ", " + coupon.getPossibleWin() + ", " + null + ", '" + coupon.getSettlementDate() + "', " +  coupon.getUserId());
		}
	}
}
