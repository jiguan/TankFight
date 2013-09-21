import java.sql.*;

/**
 * 
 * @author jiguan
 * @function specify request and call SqlHelper to interaction with database
 */


public class UserModel {

	public String getTitle(String ID, String password)
	{
		SqlHelper sqlhelper = null;
		String title = null;
		try {
			//make up sql sentence
			String sql = "select staff.title from staff, user_password where staff.ID = ? and user_password.ID = staff.ID and user_password.password = ?";
			String params[] = {ID, password};
			sqlhelper = new SqlHelper();
			ResultSet result = sqlhelper.query(sql,params);
			if(result.next())
			{
				title = result.getString(1);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally {
			sqlhelper.close();
		}
		
		return title;
	}
	
}
