//author: Kashif Ahmadi
//UGA ID: 810178315

import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.lang.Exception;
import javax.swing.filechooser.*;

//constructs the GUI elements that make up the panel
public class GUI extends JPanel {

	private JButton reset, load, quit;
	private JPanel panel;
	private JComboBox pixelate;
	private JMenuBar menuBar;
	private JMenu file, help;
	private JMenuItem menuItem;
	private int factor;
	BufferedImage img, pixelated;

	public GUI()
	{
		load = new JButton("Load Image");
		quit = new JButton("Quit");
		reset = new JButton("Reset Image");
		
		load.setMnemonic(KeyEvent.VK_O);
		quit.setMnemonic(KeyEvent.VK_U);
		reset.setMnemonic(KeyEvent.VK_E);

		String[] selections = {"Select pixelation level...",
			"Extreme","Moderate","Light"};
		pixelate = new JComboBox(selections);
		
		add(load);
		add(reset);
		add(pixelate);
		add(quit);
		
		quit.addActionListener(new ButtonListener());
		reset.addActionListener(new ResetListener());
		load.addActionListener(new LoadListener());
		pixelate.addActionListener(new ComboListener());
		
	}
	/*
	Description:		draws image
	pre-conditions:		img and pixelated is valid
	post-conditions:	image is drawn on panel
	parameters: 		g Graphics object
	return:				void
	*/	
	public void paintComponent(Graphics g)
	{
		if(pixelated != null)
			g.drawImage(pixelated, 0, 29, null);
		else
			g.drawImage(img, 0, 30, null);
	}
	//Quit button listener that ends program execution
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Program terminated");
			System.out.println("I'll be back ...");
			System.exit(0);
		}
	}
	//Reset the image to its original state
	private class ResetListener implements ActionListener {

		public void actionPerformed(ActionEvent e)
		{
			pixelated = null;
			repaint();
		}
	}
	//Loads the JPG file and paints it to screen
	private class LoadListener implements ActionListener {

		public void actionPerformed(ActionEvent event)
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File(
										System.getProperty("user.home")));

			FileNameExtensionFilter filter = new FileNameExtensionFilter(
												"JPEG file", "jpg", "jpeg");
			chooser.setFileFilter(filter);

	        int status = chooser.showOpenDialog(null);

	        if(status != JFileChooser.APPROVE_OPTION)
	            System.out.println("Selection canceled");
	        else
	        {
	        	img = null;
			    File file = chooser.getSelectedFile();

				try
            	{
               	 	img = ImageIO.read(new File(chooser.getName(file)));
	          		System.out.println("Image file loaded");
					repaint();
				}
				catch (IOException e)
				{
					System.out.println("Error: file could not be read");
					System.out.println("Program exiting");
					System.exit(1);
				}
			}
		}
	}
	
	//Pixelate the image based on combo box selection
	private class ComboListener implements ActionListener { 
		
		public void actionPerformed(ActionEvent event)
		{
			switch(pixelate.getSelectedIndex()) {
				
				case 0:
				break;

				case 1:
				factor = 20;
				Pixelate();
				repaint();
				break;

				case 2:
				factor = 50;
				Pixelate();
				repaint();
				break;

				case 3:
				factor = 100;
				Pixelate();
				repaint();
				break;
			}
		}
	}
	/*
	Description:		Pixelates image by averaging the colors of equal
						subimages and setting each subimage to the 
						corresponding average color
	pre-conditions:		img is valid
	post-conditions:	img is copied and that copy is pixelated
	parameters: 		none	
	return:				void
	*/	
	public void Pixelate()
	{
		int r = 0; int g = 0; int b = 0;
		int avgR, avgG, avgB;
		int height = img.getHeight();  
		int width = img.getWidth();   
		int yOffset = height / factor;
		int xOffset = width / factor;
		
		pixelated = new BufferedImage(width, height, 
									  BufferedImage.TYPE_INT_ARGB);
		
		int[] samples = new int[yOffset * xOffset];

		for(int y = 0; y < height - yOffset; y += yOffset) {
			for(int x = 0; x < width - xOffset; x += xOffset) {

				//sampling a subimage and placing in samples[]
				img.getSubimage(x, y, xOffset, yOffset).getRGB(
								0, 0, xOffset, yOffset, samples, 0, xOffset);

				//extract colors from samples[]
				for(int i = 0; i < samples.length - 1; i++) {
					Color c = new Color(samples[i]);
					r += c.getRed();
					g += c.getGreen();
					b += c.getBlue();
				}
				//average colors	
			 	avgR = r / samples.length;
				avgG = g / samples.length;
				avgB = b / samples.length;

				//assign average to samples[]
				for(int i = 0; i < samples.length - 1; i++) {
					Color c = new Color(avgR, avgG, avgB);
					samples[i] = c.getRGB();
				}
					
				//set pixels of the average colors from samples[]
				//to the blank BufferedImage 'pixelated'
				pixelated.getSubimage(x, y, xOffset, yOffset).setRGB(0, 0,
									  xOffset, yOffset, samples, 0, xOffset);
			
				//reset color counters
				r = 0; 
				g = 0; 
				b = 0;
			}
		}
	}
}

