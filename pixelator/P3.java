//author: Kashif Ahmadi
//UGA ID: 810178315

import java.util.Scanner;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class P3 {
	
	//constructs the frame and loads GUI panel
	public static void main(String[] args) throws IOException {

		JFrame f = new JFrame("The Pixelator");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.getContentPane().add(new GUI());

		Menu menu = new Menu();
		f.setJMenuBar(menu.createMenuBar());
		
		f.setPreferredSize(new Dimension(740,530));
		f.pack();
		f.setVisible(true);
	}
}
