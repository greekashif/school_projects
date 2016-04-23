/*
	Program creates an image from a green screen
	image and a background image, effectively
	transposing the green screen image on top of
	the background image.
	Author: Kashif Ahmadi
	810178315
	Project 1
*/

import java.awt.*;
import java.awt.image.*;
import java.util.Scanner;
import java.io.*;
import javax.imageio.*;

public class GreenScreen {

	private static int w; //width
	private static int h; //height
	private static BufferedImage bgImg;
	private static BufferedImage grnScrn;
	private static BufferedImage gryImg;
	
/*
	Description: 		reads and stores data of background image 
	pre-conditions: 	args[1] of the main method is defined
							and corresponds to the background image
	post-conditions: 	image file loaded onto a buffered image
					 		and the 2D pixel RBG values are assigned
					 		to an array
	parameters:		bgImgFile	background buffered image
	return:			void	
*/
    public static void processBG(String bgImgFile) {
		bgImg = null;
        try  
		{
            bgImg = ImageIO.read(new File(bgImgFile));
            System.out.println(bgImgFile + " file loaded");
        } 
		catch (IOException e) 
		{
            System.out.println("ERROR: file (" + bgImgFile + ") not found");
            System.out.println("Exiting program");
            System.exit(1);
        }

        w = bgImg.getWidth();
        h = bgImg.getHeight();
		int[][] imgArray = new int[w][h];

        for(int i = 0; i < w ; i++)
            for (int  j = 0; j < h; j++) 
                imgArray[i][j] = bgImg.getRGB(i,j);
             
        System.out.println(bgImgFile + " pixel data read"); 

     }
/*
	Description: 		Loads and reads the green screen image
				 			and replaces each green pixel with the 
				 			corresponding pixel of the background image
	Pre-conditions:		String parameters defined correctly. 
							green screen image and background image's
							dimension must be exactly the same.					
	Post-conditions:	Green screen buffer tranposed onto
					 		background buffer and saved.
	Parameter:	grnImg		green screen image buffer
	Parameter:  filter		color filtered from 'green' screen 
	Parameter:  mod			name of modified image file
	Parameter:  ext			image file extension
	Return:		void
*/    
    public static void convertImg(String grnImg, String filter, 
								  String mod, String ext) {
		grnScrn = null;
        try 
        {
            grnScrn = ImageIO.read(new File(grnImg));
            System.out.println(grnImg + " file loaded");
        } 
		catch (IOException e) 
		{
            System.out.println("ERROR: file (" + grnImg + ") not found");
            System.out.println("Exiting program");
            System.exit(1);
        }

        int W = grnScrn.getWidth();
        int H = grnScrn.getHeight();
        try
        {	//check if image dimensions are equal
            if ( w != W || h != H) throw new Exception();
         	int[][] greenArray = new int[w][h];
         	Color test = null;

         //replace each green pixel with the corresponding pixel
		 //		in the background image 
         for ( int i = 0; i < w ; i++)
             for ( int  j = 0; j < h ; j++) {
                 greenArray[i][j] = grnScrn.getRGB(i,j);
                 test = new Color(grnScrn.getRGB(i,j));

                 if ( !filter.equalsIgnoreCase("green") &&
                      !filter.equalsIgnoreCase("white"))
                      throw new IOException();

                 if ( filter.equalsIgnoreCase("green")) {
                 	if ( test.equals(Color.green)) 
                        grnScrn.setRGB(i,j,bgImg.getRGB(i,j));
                 } else if ( filter.equalsIgnoreCase("white")) {
                    if ( test.equals(Color.white)) 
                        grnScrn.setRGB(i,j,bgImg.getRGB(i,j));
                 }  
				 }

         }

         catch(IOException e)
         {
             System.out.println("ERROR: Color of green screen argument");
             System.out.println("Exiting Program");
             System.exit(1);
         }             
         catch(Exception e)
         {
             System.out.println("ERROR: Unequal image dimensions");
             System.out.println("Exiting Program");
             System.exit(1);
		 }
         
     }
/*
	Description: Saves the green screen buffered image to 
				 jpg or png image file with an option to
				 convert image to greyscale.
	Pre-conditions: Valid string parameters 
					Valid green screen buffer 
	Post-conditions: Green screen buffer tranposed onto
					 background buffer and saved.
	Parameter:	grnImg		green screen image buffer
	Parameter:  filter		color filtered from 'green' screen 
	Parameter:  mod			name of modified image file
	Parameter:  ext			image file extension
	Return:		void
*/    
    public static void saveImage(String arg1, String arg2, String[] arg) {
        try 
        {
            if (arg2.equalsIgnoreCase("jpg")) {
				if (arg.length == 6) drawGrayImg(arg1, arg2, arg);

				else {

				//remove transperancy of png by drawing on top of blank rect
                BufferedImage dummy =
                   new BufferedImage(grnScrn.getWidth(),grnScrn.getHeight(),
                                       BufferedImage.TYPE_INT_RGB);
                   
                dummy.getGraphics().fillRect(0,0, dummy.getWidth(),
                                                 dummy.getHeight());
                dummy.getGraphics().drawImage(grnScrn,0,0, null);
                File outputfile = new File(arg1 + "." + arg2);
                ImageIO.write(dummy, arg2, outputfile);
				}

            } else if (arg.length == 6) drawGrayImg(arg1, arg2, arg);
			
	 	    else {	
            File outputfile = new File(arg1 + "." + arg2);
            ImageIO.write(grnScrn, arg2, outputfile);
            }

         if (!arg2.equalsIgnoreCase("png") && (!arg2.equalsIgnoreCase("jpg")))
                 throw new Exception();
         }  
		 catch (IOException e) 
		 {
             System.out.println("ERROR: " + arg[2] + " file not processed");
             System.exit(1);
         }  
		 catch (Exception e) 
		 {
             System.out.println("ERROR: " + arg2 + ": unsupported extension");
             System.out.println("Exiting Program");
             System.exit(1);
         }   
         
         System.out.println(arg[2] + "." + arg2 + " image file created");
           
     }          
/*
	Description: 		Converts the modified buffer image into greyscale form
	Pre-conditions:		Valid string arguments, valid image buffer
	Post-conditions:	Buffered image converted to greyscale and 
						saved to file
	Parameter:	arg1	name of the modified image file
	Parameter:  arg2	image file extension
	Parameter:	arg[]	command line String array
	Return:		void
*/
	 public static void drawGrayImg(String arg1, String arg2, String[] arg) {
		try
		{
			if(!arg[5].equalsIgnoreCase("bw")) throw new IOException();
		 
     		gryImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
			Graphics base = gryImg.getGraphics();
			base.drawImage(grnScrn, 0, 0, null);
        	File outputfile = new File(arg1 + "." + arg2);
        	ImageIO.write(gryImg, arg2, outputfile);
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR: bw argument syntax");
			System.exit(1);
		}
	 }
/*
	Description:	Checks for omitted command line arguments
						during program execution
	Pre-condition:	none
	Post-condition:	none
	Parameter:	arg[]	command line String array
	Return:		void
*/
	 public static void checkCmdLine(String[] arg) {
         if (arg.length < 5) { 
             System.out.println("ERROR: Only " + 
                                   arg.length + " argument(s) entered");
             System.out.println("5 arguments required: [foreground image]" +
                                 " [background image] [output filename] " +
                                  "[image extension] [color filter]");
             System.out.println("Exiting Program");
             System.exit(1);
         }
	 } 
         
     public static void main(String[] args) {

		checkCmdLine(args);

		String foregroundImg = args[0];
		String backgroundImg = args[1];
		String modifiedImg   = args[2];
		String fileExt       = args[3];
		String colorScrn     = args[4];

		processBG(backgroundImg);
		convertImg(foregroundImg, colorScrn, modifiedImg, fileExt);
		saveImage(modifiedImg, fileExt, args);		
		
	 }

}

