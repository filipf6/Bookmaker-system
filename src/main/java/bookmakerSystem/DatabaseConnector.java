package bookmakerSystem;

import java.sql.*;

public class DatabaseConnector
{
	
	private static Connection connection;
	
	static public void connectWithBase()
	{
		
		try
		{
			  Class.forName("oracle.jdbc.driver.OracleDriver");
	
			  System.out.println("Sterowniki za³adowane");
			  
			  connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Stachudb","filip","filip");
			  
			  System.out.println("Po³¹czenie nawi¹zane");
			
			  
		 }
			catch(Exception wyjatek)
			{  
				System.out.println("B³¹d"); 
			}
		
		
		
		 }
	
	public static ResultSet getResultStatement(String query)
    {
        Statement stmt = null;
        try
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void executeUpdate(String query)
    {
        Statement stmt = null;
        try
        {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
}
