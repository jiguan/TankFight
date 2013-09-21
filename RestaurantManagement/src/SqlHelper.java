import java.sql.*;


public class SqlHelper {

	PreparedStatement pstatement = null;
	ResultSet result = null;
	Connection connect = null;
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/restaurant_management?"+"user=sqluser&password=sqluser";
	public SqlHelper() {
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(url);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public ResultSet query(String sql, String [] param)
	{
		try {
			pstatement = connect.prepareStatement(sql);
			for (int i=0;i<param.length;i++)
			{
				pstatement.setString(i+1,param[i]);
			}
			result = pstatement.executeQuery();
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean execute(String sql, String params[])
	{
		boolean flag = false;
		try {
			pstatement = connect.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				pstatement.setString(i+1, params[i]);
			}
			if (pstatement.executeUpdate()!=0)
				flag = true;
//			pstatement.executeUpdate();
		} catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public void close() {
		try {
			if(result!=null) result.close();
			if(pstatement!=null) pstatement.close();
			if(connect.isClosed()) connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
