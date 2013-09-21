/*
 * function: make up SQL sentence, interact with SqlHelper
 */

import javax.swing.table.*;
import java.sql.*;
import java.util.*;

public class EmployerModel extends AbstractTableModel {
	
	Vector<String> colunms; 
	Vector<Vector> rows;
	String database = "staff";
	public void query(String sql, String params[])
	{
		boolean flag = false;
		this.rows = new Vector<Vector>();
		this.colunms = new Vector<String>();
		//this.colunms.add("Salary");
		
		//create object for SqlHelper
		SqlHelper sqlhelper = new SqlHelper();
		ResultSet result = sqlhelper.query(sql, params);
		try {
			//get to know how many columns are returned
			ResultSetMetaData meta_data = result.getMetaData();
			for(int i=0;i<meta_data.getColumnCount();i++)
			{
				this.colunms.add(meta_data.getColumnName(i+1));
			}
			//put into rows
			while(result.next())
			{
				Vector<String> temp = new Vector<String>();
				for(int i=0;i<meta_data.getColumnCount();i++)
				{
					temp.add(result.getString(i+1));
				}
				rows.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlhelper.close();
		}
	}
	
	public boolean insert(String[] params)
	{
		boolean flag = false;
		String sql = "insert into staff values (?,?,?,?,?)";
		SqlHelper sqlhelper = new SqlHelper();
		try {
			flag = sqlhelper.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlhelper.close();
		}
		return flag;
	}
	
	
	public boolean delete(String ID)
	{
		boolean flag = false;
		String sql = "delete from staff where ID = ?";
		String []params = {ID};
		//create object for SqlHelper
		SqlHelper sqlhelper = new SqlHelper();
		try {
			flag = sqlhelper.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlhelper.close();
		}
		return flag;
	}
	
	
	
	public void reload(String params[])
	{
		this.rows = new Vector();
		this.colunms = new Vector<String>();
		String sql = "select ";
		for(int i=0;i<params.length;i++)
		{
			sql = sql + params[i]+", ";
		}
		sql = sql.substring(0, sql.length()-2);
		sql = sql + " from "+database;
		//System.out.println(sql);
		
		params = new String[0];
		//create object for SqlHelper
		SqlHelper sqlhelper = new SqlHelper();
		ResultSet result = sqlhelper.query(sql, params);
		
		try {
			//get to know how many columns are returned
			ResultSetMetaData meta_data = result.getMetaData();
			for(int i=0;i<meta_data.getColumnCount();i++)
			{
				this.colunms.add(meta_data.getColumnName(i+1));
			}
			//put into rows
			while(result.next())
			{
				Vector<String> temp = new Vector<String>();
				for(int i=0;i<meta_data.getColumnCount();i++)
				{
					temp.add(result.getString(i+1));
				}
				rows.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlhelper.close();
		}
	} 
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colunms.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector)this.rows.get(rowIndex)).get(columnIndex);
	}
	
	public String getColumnName(int columnIndex)
	{
		return this.colunms.get(columnIndex).toString();
	}

}
