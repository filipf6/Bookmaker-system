package bookmakerSystem.DAO;

import static bookmakerSystem.DatabaseConnector.getResultStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import bookmakerSystem.DatabaseConnector;
import bookmakerSystem.model.Coupon;
import bookmakerSystem.model.TheWinnerOfAMatchBet;

public class CouponDAO
{
	private int i;
	
	public void addCoupon(Coupon coupon) throws SQLException
	{
		ResultSet rs = getResultStatement("SELECT max(ID_KUPONU) FROM KUPON");
		if(rs.next())
		{
			i = rs.getInt(1);
		}
		
		DatabaseConnector.executeUpdate("INSERT INTO KUPON VALUES (" + ++i + ", "
				+ coupon.getBid() + ", " + coupon.getPossibleWin() + ", " + null + ", " + 
				coupon.getUserId() + ")");
		
		for(TheWinnerOfAMatchBet bet: coupon.getBets())
		{
			System.out.println(i + "   " +  bet.getId());
			DatabaseConnector.executeUpdate("INSERT INTO KUPON_ZWYCIEZCA_MECZU VALUES (" +  i + ", " + bet.getId() + ")");
		}
	}
}
