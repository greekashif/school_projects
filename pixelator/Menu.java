//author: Kashif Ahmadi
//UGA ID: 810178315

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//creates Menu object
public class Menu implements ActionListener {

	private JMenuBar menuBar;
	private JMenu file, help;
	private JMenuItem menuItem;

	//builds menu
	public JMenuBar createMenuBar() {

		menuBar = new JMenuBar();
   		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
    	menuBar.add(file);

    	help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
    	menuBar.add(help);

    	menuItem = new JMenuItem("Quit", KeyEvent.VK_U);
		menuItem.addActionListener(this);
    	file.add(menuItem);

    	menuItem = new JMenuItem("Get Started", KeyEvent.VK_E);
		menuItem.addActionListener(this);
    	help.add(menuItem);
    	menuItem = new JMenuItem("About", KeyEvent.VK_B);
		menuItem.addActionListener(this);
    	help.add(menuItem);

		return menuBar;
			
	}

	//Action performed when menu items are selected
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem source = (JMenuItem)(e.getSource());
		if(source.getText().equals("Quit"))
			System.exit(0);

		if(source.getText().equals("Get Started"))
			JOptionPane.showMessageDialog(null, "Welcome to the " +
			"Pixelator!\nLoad a JPG image and select between different" +
			" levels of pixelation.\nYou can restore the original image " +
			"by selecting the 'Reset' button.", "Get Started",
			JOptionPane.PLAIN_MESSAGE);

		if(source.getText().equals("About"))
			JOptionPane.showMessageDialog(null, "The Pixelator v1.3\n" +
   			"Java 1.7.0_15\n" + "Author: Kashif Ahmadi", "About The Pixelator",
			JOptionPane.PLAIN_MESSAGE);
	}
}

	

