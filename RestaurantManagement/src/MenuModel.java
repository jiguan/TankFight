import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class MenuModel extends AbstractTableModel {

	Vector<String> columns;
	Vector<Vector> rows;

	public void query(String sql, String[]params)
	{
		this.rows = new Vector<Vector>();
		this.columns = new Vector<String>();
		SqlHelper sqlhelper = new SqlHelper();
		ResultSet result = sqlhelper.query(sql, params);
		try {
			ResultSetMetaData meta_data = result.getMetaData();
			for(int i=0;i<meta_data.getColumnCount();i++)
			{
				columns.add(meta_data.getColumnName(i+1));
			}
			while(result.next())
			{
				Vector<String> temp = new Vector<String>();
				for(int i=0;i<meta_data.getColumnCount();i++)
				{
					temp.add(result.getString(i+1));
				}
				rows.add(temp);
			}
		} catch (Exception e) {
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
		return this.columns.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector)this.rows.get(rowIndex)).get(columnIndex);
	}
	
	public String getColumnName(int columnIndex) {
		return this.columns.get(columnIndex).toString().toUpperCase();
	}
}
