package bookmakerSystem;

import org.sql2o.Sql2o;

public class DB
{
	
	 public static Sql2o sql2o = new Sql2o("jdbc:oracle:thin:@localhost:1521:Stachudb", "filip","filip");
	
}
