import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


public class Add extends JDialog implements ActionListener {
	
	JPanel panel1, panel2, panel3;
	JLabel label1, label2, label3, label4, label5;
	Vector<JLabel> labels;
	Vector<JTextField> jtfs; 
	JButton button1, button2;
	Vector rows, columns;
	PreparedStatement pstatement = null;
	ResultSet result = null;
	Connection connect = null;
	EmployerModel employmodel;
	int length;
	
	public void setItem(String params[])
	{
		for(int i=0;i<params.length;i++)
			labels.add(new JLabel(params[i]));
	}
	
	public Add(Frame owner, String title, boolean model, String params[],Object objectmodel)
	{
		super(owner, title, model);
		length = params.length;
		labels = new Vector<JLabel>();
		jtfs = new Vector<JTextField>();
		panel1 = new JPanel(new GridLayout(length,1));
		//salary cannot be shown but need to add;
		panel2 = new JPanel(new GridLayout(length,1)); 
		employmodel = new EmployerModel();
		for(int i=0;i<length;i++)
		{
			labels.add(new JLabel(params[i]));
			panel1.add(labels.get(i));
			jtfs.add(new JTextField(10));
			panel2.add(jtfs.get(i));
		}
		//panel3
		panel3 = new JPanel(new FlowLayout());
		button1 = new JButton("Confirm");
		button2 = new JButton("Cancel");
		button1.addActionListener(this);
		button2.addActionListener(this);
		panel3.add(button1);
		panel3.add(button2);
		
		
		this.add(panel1,BorderLayout.WEST);
		this.add(panel2,BorderLayout.CENTER);
		this.add(panel3,BorderLayout.SOUTH);
		this.setSize(300,180);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource() == button1)
		{
			String[] texts = new String[length];
			boolean read_flag = true;
			byte write_indicator = 0;
			for(int i=0;i<length;i++)
			{
				String text = (jtfs.get(i)).getText();
				if(text.equals(""))
				{
					read_flag = false;
					break;
				}
				texts[i] = text;
			}
			if(read_flag)
			{
				AddModel add_model = new AddModel();
				boolean flag = add_model.add(texts);
				if(flag)
					write_indicator = 1;
				else
					write_indicator = 2;
			}
			else{
				JOptionPane.showMessageDialog(null, "Cannot be empty");
			}
			if (write_indicator==1)
			{
				this.dispose();
			}
			else if(write_indicator==2)
				JOptionPane.showMessageDialog(null, "Adding new member fails");	
		}
		
		else if(e.getSource() == button2)
		{
			this.dispose();
		}
	}
}


