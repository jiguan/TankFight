import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;


public class MainWindow extends JFrame implements ActionListener, MouseListener {

	/**
	 * @param args
	 */
	Image title_icon, image2icon, time_background;
	ImageIcon icon1_1, icon1_2, icon1_3, icon1_4, icon2_1, icon2_2;
	JMenuBar jmenubar;
	JMenu menu1, menu2, menu3, menu4, menu5;
	JMenuItem item1,item2,item3,item4;
	JToolBar jtoolbar;
	JButton button1, button2, button3, button4, button5;
	JPanel panel1, panel2, panel3, panel4, panel5;
	
	JLabel time_plate;
	ImagePanel p1_imgPanel;
	JLabel p1_label1, p1_label2, p1_label3, p1_label4, p1_label5, p1_label6; 
	JLabel panel2_1, panel2_2;
	javax.swing.Timer timer;
	JSplitPane jsp1;
	Container container;
	CardLayout cardPanel2, cardPanel3;
	
	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainWindow main_window = new MainWindow();
	}
	
	public void initWindow()
	{
		
		try {
			icon1_1 = new ImageIcon("./lib/user_25.png");
			icon1_2 = new ImageIcon(ImageIO.read(new File("./lib/money_icon_25.png")));
			icon1_3 = new ImageIcon("./lib/setting_25.png");
			icon1_4 = new ImageIcon("./lib/logout.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		menu1 = new JMenu("System setting");
		menu1.setFont(Tools.font_plain);
		item1 = new JMenuItem("Switch user");
		item1.setIcon(icon1_1);
		item2 = new JMenuItem("Collecting");
		item2.setIcon(icon1_2);
		item3 = new JMenuItem("User management");
		item3.setIcon(icon1_3);
		item4 = new JMenuItem("Log out");
		item4.setIcon(icon1_4);
		
		
		//add in
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu1.add(item4);
		
		menu2 = new JMenu("Staff");
		menu2.setFont(Tools.font_plain);
		menu3 = new JMenu("Ordering");
		menu3.setFont(Tools.font_plain);
		menu4 = new JMenu("Information");
		menu4.setFont(Tools.font_plain);
		menu5 = new JMenu("Help");
		menu5.setFont(Tools.font_plain);
		
		//add into jmenubar
		jmenubar = new JMenuBar();
		jmenubar.add(menu1);
		jmenubar.add(menu2);
		jmenubar.add(menu3);
		jmenubar.add(menu4);
		jmenubar.add(menu5);
		
		
	}
	public void initToolbar()
	{
		//button and jtoolbar
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		button4 = new JButton("4");
		button5 = new JButton("5");
		jtoolbar = new JToolBar();
		//whether can be moved
		jtoolbar.setFloatable(false);
		jtoolbar.add(button1);
		jtoolbar.add(button2);
		jtoolbar.add(button3);
		jtoolbar.add(button4);
		jtoolbar.add(button5);
		
		//container
		container.add(jtoolbar,"North");
	}
	
	public void initPanel() {
		//deal with panel1
		panel1 = new JPanel(new BorderLayout());
		Image panel_background = null;
		try {
			panel_background = ImageIO.read(new File("./lib/window_leftside.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.p1_imgPanel = new ImagePanel(panel_background);
		this.p1_imgPanel.setLayout(new GridLayout(8,1));
		//title
		ImageIcon window_icon = new ImageIcon("./lib/cafe.png");
		JLabel system_title = new JLabel();
		system_title.setIcon(window_icon);
		p1_imgPanel.add(system_title);
		//set cursor
		Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);
		//1
		p1_label1 = new JLabel("Employment",JLabel.CENTER);
		p1_label1.setCursor(myCursor);
		p1_label1.setEnabled(false);
		p1_label1.addMouseListener(this);
		p1_imgPanel.add(p1_label1);
		p1_label1.setFont(Tools.font_blod_14);
		p1_label1.setForeground(Color.black);
		
		//2
		p1_label2 = new JLabel("Menu",JLabel.CENTER);
		p1_label2.setCursor(myCursor);
		p1_label2.setEnabled(false);
		p1_label2.addMouseListener(this);
		p1_imgPanel.add(p1_label2);
		p1_label2.setFont(Tools.font_blod_14);
		p1_label2.setForeground(Color.black);
		
		//3
		p1_label3= new JLabel("Report",JLabel.CENTER);
		p1_label3.setCursor(myCursor);
		p1_label3.setEnabled(false);
		p1_label3.addMouseListener(this);
		p1_imgPanel.add(p1_label3);
		p1_label3.setFont(Tools.font_blod_14);
		p1_label3.setForeground(Color.BLACK);
		
		panel1.add(p1_imgPanel);
		//panel2 panel3 panel4
		//after spread panel4, panel2 is on west, panel3 showing database
		panel4 = new JPanel(new BorderLayout());
		this.cardPanel2 = new CardLayout();
		panel2 = new JPanel(this.cardPanel2);
		this.cardPanel3 = new CardLayout();
		panel3 = new JPanel(this.cardPanel3);
		
		//deal with panel2
		icon2_1 = new ImageIcon("./lib/left_arrow.png");
		icon2_2 = new ImageIcon("./lib/right_arrow.png");
		panel2_1 = new JLabel(icon2_1);
		panel2_2 = new JLabel(icon2_2);
		panel2.add(panel2_1,"0");
		panel2.add(panel2_2,"1");
		panel2_1.addMouseListener(this);
		panel2_2.addMouseListener(this);
		//deal with panel3
		//first add main ImagePanel 
		
		//when enter the main window, showing main_image
		//when choose other subtitles, background changes to main_image_subtitle
		Image main_image = null;
		try {
			main_image = ImageIO.read(new File("./lib/window_rightside_orig.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImagePanel imagepanel = new ImagePanel(main_image);
		//subtitle background
		Image subtitle_image = null;
		try {
			subtitle_image = ImageIO.read(new File("./lib/window_rightside.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//set alternative
		panel3.add(imagepanel,"0");
//		panel3.add(imagepanel_subtitle2, "2");
//		panel3.add(imagepanel_subtitle3, "3");
		Employer employer = new Employer();
		panel3.add(employer, "1");
		Menu menu = new Menu();
		panel3.add(menu, "2");
		//show chart
				
		//distinguish window to store panel1 and panel4
		jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,panel1,panel4);
		//determine how large left cover 
		jsp1.setDividerLocation(120);
		jsp1.setDividerSize(1);
		
		panel4.add(panel2, "West");
		panel4.add(panel3, "Center");
	}
	public void showTime()
	{
		//show current time
		panel5 = new JPanel(new BorderLayout());
		timer = new Timer(1000,this);
		timer.start();
		time_plate = new JLabel(java.util.Calendar.getInstance().getTime().toLocaleString());
		time_plate.setFont(Tools.font_plain);
		try {
			time_background = ImageIO.read(new File("./lib/window_bottom.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		ImagePanel imagepanel1 = new ImagePanel(time_background);
		imagepanel1.setLayout(new BorderLayout());
		imagepanel1.add(time_plate,"East");
		panel5.add(imagepanel1);
		
		
		container.add(jsp1,"Center");
		container.add(panel5,"South");
	}
	public MainWindow() {
		container = this.getContentPane();
		this.initWindow();
		
		
		
		this.initToolbar();
		
		
		
		//add JMenuBar into JFrame
		this.setJMenuBar(jmenubar);
		this.initPanel();
		this.showTime();

		Image title_icon = null;
		try {
			title_icon = ImageIO.read(new File("./lib/title_icon_25.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setIconImage(title_icon);
		this.setTitle("Restaurant Management System");
		this.setSize(this.width,this.height-25);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.time_plate.setText(Calendar.getInstance().getTime().toLocaleString());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==p1_label1)
		{
			this.cardPanel3.show(panel3,"1");
		}
		else if(e.getSource()==p1_label2)
		{
			this.cardPanel3.show(panel3,"2");
		}
		else if(e.getSource()==p1_label3)
		{
			this.cardPanel3.show(panel3,"3");
		}
		else if(e.getSource()==this.panel2_1)
		{
			this.jsp1.setDividerLocation(0);
			this.cardPanel2.show(panel2,"1");
		}
		else if(e.getSource()==this.panel2_2)
		{
			this.jsp1.setDividerLocation(120);
			this.cardPanel2.show(panel2, "0");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.p1_label1)
		{
			this.p1_label1.setEnabled(true);
		}
		else if(e.getSource()==this.p1_label2)
		{
			this.p1_label2.setEnabled(true);
		}
		else if(e.getSource()==this.p1_label3)
		{
			this.p1_label3.setEnabled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.p1_label1)
		{
			this.p1_label1.setEnabled(false);
		}
		else if(e.getSource()==this.p1_label2)
		{
			this.p1_label2.setEnabled(false);
		}
		else if(e.getSource()==this.p1_label3)
		{
			this.p1_label3.setEnabled(false);
		}
		
	}

}
//83 56: