import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WarningDialog extends JDialog implements ActionListener {
	JPanel jpanel1, jpanel2;
	JButton comfirm_bt;
	JLabel warning_label;
	
	public WarningDialog(String warning)
	{
		
		jpanel1 = new JPanel();
		jpanel2 = new JPanel();
		comfirm_bt = new JButton("OK");
		comfirm_bt.addActionListener(this);
		jpanel1.add(warning_label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()  == comfirm_bt)
		{
			this.dispose();
		}
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String msg = "KKKK";
//		WarningDialog warning = new WarningDialog(msg);
//	}
}
