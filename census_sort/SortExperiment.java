import java.util.*;
import java.io.*;

/**
This class tests the efficiencies of different
sorting algorithms by calculating the time it takes
to sort data from a .csv file

*	@author	Kashif Ahmadi
*/
public class SortExperiment {

	private final static int SIZE = 3145;
	
	/**
	Verifies that the array of Census objects are sorted correctly
	*	@param data		an array of Census objects
	*	@param	comp	Comparator object
	*/
	public static void verifySort(Census[] data, Comparator comp)
	{
		boolean sorted = true;
		for(int i = 0; i < data.length-1; i++)
			if(comp.compare(data[i], data[i+1]) > 0)
				sorted = false;
		
		if(sorted) System.out.println("Sorted correctly!");
		else System.out.println("Not sorted correctly.");
	}

	/**
	Calculates the time it takes corresponding sorting algorthims
	to sort the census data
	*	@param data		an array of Census objects
	*	@param comp		Comparator method
	*	@param type		String of sorting type
	*/
	public static void runTest(Census[] data, Comparator comp, String type)
	{
		long runtime, startTime = System.currentTimeMillis();
		System.out.println("Running " + type + " insertion sort...");
		Sorting.insertionSort(data, comp);
		runtime = System.currentTimeMillis() - startTime;
		verifySort(data, comp);
		System.out.println("Runtime: " + runtime + " ms\n");
		
		startTime = System.currentTimeMillis();
		System.out.println("Running " + type + " bubble sort...");
		Sorting.bubbleSort(data, comp);
		runtime = System.currentTimeMillis() - startTime;
		verifySort(data, comp);
		System.out.println("Runtime: " + runtime + " ms\n");
		
		startTime = System.currentTimeMillis();
		System.out.println("Running " + type + " happyHour sort...");
		Sorting.happyHourSort(data, comp);
		runtime = System.currentTimeMillis() - startTime;
		verifySort(data, comp);
		System.out.println("Runtime: " + runtime + " ms\n");

		startTime = System.currentTimeMillis();
		System.out.println("Running " + type + " quick sort...");
		Sorting.quickSort(data, 0, data.length-1, comp);
		runtime = System.currentTimeMillis() - startTime;
		verifySort(data, comp);
		System.out.println("Runtime: " + runtime + " ms\n");
	}
	//main method
	public static void main(String[] arg) {

		Census[] census = new Census[SIZE];
		BufferedReader inputStream = null;
		int index = 0; int crimes;
		String state, county;
		double poverty;
		long population;

		if(arg.length != 1)
		{
			System.out.println("ERROR: exactly 1 argument needed");
			System.out.println("e.g.: java SortExperiment [file]");
			System.exit(1);
		}

		String csvFile = arg[0];

		try {
			inputStream = 
				new BufferedReader(new FileReader(csvFile));
			System.out.println(csvFile + " loaded");
		}
		catch(FileNotFoundException e) {
			System.out.println("Error loading file: " + csvFile);
			System.exit(1);
		}
		
		try {
			//ignore headers
			String line = inputStream.readLine();
			line = inputStream.readLine();
			
			while(line != null) 
			{
				String[] data = line.split(",");

				if(data.length == 6) //ignore state entries
				{
					//remove lingering quotation marks
					county = data[0].substring(1,data[0].length());
					state = data[1].substring(0,3);
					population = Long.parseLong(data[3]);
					poverty = Double.parseDouble(data[4]);
					crimes = Integer.parseInt(data[5]);

					census[index] = new Census(county, state, poverty,
											   crimes, population);
					index++;
				}

				line = inputStream.readLine();
			}
		}
		catch(EOFException e) {
			System.out.println("Reading file completed");
		}
		catch(IOException e) {
			System.out.println("Error reading file");
		}

		runTest(census, Census.CountyComp, "county");
		runTest(census, Census.PopulationComp, "population");
		runTest(census, Census.CrimeComp, "crime percentage");
	}

}

