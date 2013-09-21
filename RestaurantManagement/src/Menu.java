import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener {
	JButton button1, button2, button3, button4, button5;
	JPanel panel1, panel2, panel3, panel4, panel5;
	JLabel p1_label1;
	JTextField jtf;
	JTable table;
	MenuModel menumodel;
	JScrollPane jsp;
	public Menu()
	{
		//panel1, north
		p1_label1 = new JLabel("Enter dish's name: ");
		jtf = new JTextField(30);
		button1 = new JButton("Query");
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(p1_label1);
		panel1.add(jtf);
		panel1.add(button1);
		
		//panel2, center
		String sql = "select * from menu order by category";
		String[]params = new String[0];
		menumodel = new MenuModel();
		menumodel.query(sql, params);
		table = new JTable(menumodel);
		jsp = new JScrollPane(table);
		panel2 = new JPanel(new BorderLayout());
		panel2.add(jsp);
		
		//panel3, south
		button2 = new JButton("Detail");
		button3 = new JButton("Add");
		button4 = new JButton("Modify");
		button5 = new JButton("Delete");
		//register
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button1.setActionCommand("Query");
		button2.setActionCommand("Detail");
		button3.setActionCommand("Add");
		button4.setActionCommand("Modify");
		button5.setActionCommand("Delete");
		
		panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel3.add(button2);
		panel3.add(button3);
		panel3.add(button4);
		panel3.add(button5);
		
		this.setLayout(new BorderLayout());
		this.add(panel1,"North");
		this.add(panel2,"Center");
		this.add(panel3,"South");
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
