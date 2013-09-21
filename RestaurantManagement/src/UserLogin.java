import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class UserLogin extends JDialog implements ActionListener {

	JLabel jlabel1, jlabel2, jlabel3;
	JTextField jtf1;
	JPasswordField jtf2;
	JButton jbutton1, jbutton2;
	Font font = new Font("Arial", Font.BOLD, 16);
	
	public static void main(String[] args) 
	{
		UserLogin login = new UserLogin();
	}
	
	public UserLogin() {
		this.setLayout(null);
		Container container = this.getContentPane();
		BackImage background = new BackImage();
		background.setBounds(0, 0, 360, 360);
		
		
		jlabel1 = new JLabel("Username");
		jlabel2 = new JLabel("Password");
//		jlabel1.setForeground(Color.BLUE);
//		jlabel2.setForeground(Color.BLUE);
		jtf1 = new JTextField();
		jtf2 = new JPasswordField();		
		jbutton1 = new JButton("Login");
		jbutton2 = new JButton("Exit");
		
		jlabel1.setBounds(60,250,120,25);
		jlabel2.setBounds(60,280,120,25);
		jlabel1.setFont(font);
		jlabel2.setFont(font);
		jtf1.setBounds(160,250,120,25);
		jtf1.setBorder(BorderFactory.createLoweredBevelBorder());
		jtf2.setBounds(160,280,120,25);
		jtf2.setBorder(BorderFactory.createLoweredBevelBorder());
		jbutton1.setBounds(60,330,100,25);
		jbutton2.setBounds(180,330,100,25);
		
		container.add(jlabel1);
		container.add(jlabel2);
		container.add(jtf1);
		container.add(jtf2);
		container.add(jbutton1);
		container.add(jbutton2);
		
		//register
		jbutton1.addActionListener(this);
		jbutton2.addActionListener(this);
		jbutton1.setActionCommand("Enter");
		jbutton2.setActionCommand("Exit");
		
		this.add(background);
		this.setUndecorated(true);
//		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
//		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		//this.setLocation(width/2-200,height/2-100);
		this.setSize(360,360);
		this.setVisible(true);
	}
	
	class BackImage extends JPanel {
		Image image = null;
		String login_image = "./lib/CafeTable.jpg";
		public BackImage ()
		{
			try {
				image = ImageIO.read(new File(login_image));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(image, 0,0,360,360,this);
		}
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="Enter")
		{	
			String ID = this.jtf1.getText().trim();
			String password = new String(this.jtf2.getPassword());
			UserModel usermodel = new UserModel();
			String title = usermodel.getTitle(ID, password);
			new MainWindow();
			this.dispose();
		}	
		else if(e.getActionCommand()=="Exit")
			System.exit(0);
	}
}
