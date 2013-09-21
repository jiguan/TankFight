/**
 * 
 * @author jiguan
 * @function Showing the information about employment
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Employer extends JPanel implements ActionListener {
	JPanel panel1, panel2, panel3, panel4, panel5;
	JLabel p1_label1, p3_label1;
	JTextField  p1_jtf1;
	JButton p1_button1, p4_button1, p4_button2, p4_button3, p4_button4;
	JTable jtable;
	JScrollPane jsp;
	EmployerModel employmodel;
	String[] params ={"ID","name","gender","title", "salary"};;
	public Employer()
	{	
		
		//north
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_label1 = new JLabel("Enter name or title: ");
		p1_label1.setFont(Tools.font_plain);
		p1_jtf1 = new JTextField(20);
		p1_button1 = new JButton("Query");
		p1_button1.setFont(Tools.font_plain);
		p1_button1.addActionListener(this);
		panel1.add(p1_label1);
		panel1.add(p1_jtf1);
		panel1.add(p1_button1);
		
		//center: table
		employmodel = new EmployerModel();
		//table shows columns
		String sql = "select ID, name, gender, title, salary from staff";
		String []params = new String[0];
		employmodel.query(sql,params);
		jtable = new JTable(employmodel);
		//after this, the name of column will be shown
		jsp = new JScrollPane(jtable);
		//panel2
		panel2 = new JPanel(new BorderLayout());
		panel2.add(jsp);
		
		//south
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3_label1 = new JLabel("Total item: "+employmodel.getRowCount());
		panel3.add(p3_label1);
		panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_button1 = new JButton("Detail");
		p4_button1.addActionListener(this);
		p4_button1.setActionCommand("Detail");
		p4_button2 = new JButton("Add");
		p4_button2.addActionListener(this);
		p4_button2.setActionCommand("Add");
		p4_button3 = new JButton("Modify");
		p4_button3.addActionListener(this);
		p4_button3.setActionCommand("Modify");
		p4_button4 = new JButton("Delete");
		p4_button4.addActionListener(this);
		p4_button4.setActionCommand("Delete");
		panel4.add(p4_button1);
		panel4.add(p4_button2);
		panel4.add(p4_button3);
		panel4.add(p4_button4);
		panel5 = new JPanel(new BorderLayout());
		panel5.add(panel3,"West");
		panel5.add(panel4,"East");
		
		
		this.setLayout(new BorderLayout());
		this.add(panel1,"North");
		this.add(panel2,"Center");
		this.add(panel5,"South");
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Query"))
		{
			String content = this.p1_jtf1.getText();
			String sql = "select ID, name, gender, title from staff where name = ? or title = ?";
			String[] params = {content, content};
			employmodel = new EmployerModel();
			employmodel.query(sql, params);
			jtable.setModel(employmodel);
		}
		else if(e.getActionCommand().equals("Add"))
		{
			Window parentWindow = SwingUtilities.windowForComponent(this);
			Frame parentFrame = (Frame)parentWindow;
			//String params[] = {"ID","name","gender","title","salary"};
			Add addEmploy = new Add(parentFrame,"Add new employer",true, params, employmodel);
			employmodel= new EmployerModel();
			employmodel.reload(params);
			jtable.setModel(employmodel);
		}
		else if(e.getActionCommand().equals("Modify"))
		{
			
			
		}
		else if(e.getActionCommand().equals("Delete"))
		{
			int selectRow = jtable.getSelectedRow();
			String employer_ID = (String)employmodel.getValueAt(selectRow,0);
			boolean flag = employmodel.delete(employer_ID);
			if(flag)
			{
				JOptionPane.showMessageDialog(null, "Delete successfully");
			}else {
				JOptionPane.showMessageDialog(null, "Error");
			}
			employmodel= new EmployerModel();
			employmodel.reload(params);
			jtable.setModel(employmodel);
		}
	}
}
