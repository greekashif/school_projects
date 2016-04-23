import java.util.*;
import java.text.DecimalFormat;

/**

Census class builds an object that describes basic
statistics of cities of crime and poverty in the
United States
*	@author	Kashif Ahmadi
*	@version 1.0
*/
public class Census implements Comparable<Census> {

	private String county, state;
	private int crimeReported;
	private double poverty, crimePercentage;
	private long population;

	/**
	Builds a Census object
	*	@param	_county			the county of a census entry
	*	@param	_state			the state of the county
	*	@param 	_poverty		poverty percentage of the county 
	*	@param	crimes			number of crimes reported in the county
	*	@param	_population		population of county
	*/
	public Census(String _county, String _state, double _poverty,
				  int crimes, long _population)
	{
		county = _county;
		state= _state;
		poverty = _poverty;
		crimeReported = crimes;
		population = _population;

		if(population > 0)
			crimePercentage = (crimeReported / (double)population) * 100;
		else
			crimePercentage = 0.0;
	}
	/**
	Returns the crime percentage of the corresponding census entry
	*	@return	double: the crime percentage of the Census object	
	*	
	*/
	public double CrimePercentage()
	{
		return crimePercentage;
	}
	/**
	Returns the population of the corresponding census entry
	*	@return	long: the population of the Census object	
	*	
	*/

	public long Population()
	{
		return population;
	}
	/**
	Returns the county string of the corresponding census entry
	*	@return	String: the population of the Census object	
	*	
	*/

	public String County()
	{
		return county;
	}
	/**
	Compares two Census objects
	*	@param c	Census object
	*/
	public int compareTo(Census c)
	{
		int pop = (int)((Census)c).Population();
		return (int)(this.population - pop);
	}

	/**
	Comparator method that compares the population of
	two Census objects 
	*/	
	public static Comparator<Census> PopulationComp =
					new Comparator<Census>() {

		/**
		Compares two populations of Census objects
		such that the ordering will be largest to smallest
		*	@param c1	first Census object
		*	@param c2	second Census object
		*	@return int: comparison integer value
		*/
		public int compare(Census c1, Census c2)
		{
			int pop = (int)((Census)c1).Population();
			return (int)(c2.population - pop);
		}
	};

	/**
	Comparator method that compares the crime percentage of
	two Census objects 
	*/	
	public static Comparator<Census> CrimeComp =
					new Comparator<Census>() {

		/**
		Compares two crime percentages of Census objects
		such that the ordering will be largest to smallest
		*	@param c1	first Census object
		*	@param c2	second Census object
		*	@return int: comparison integer value
		*/
		public int compare(Census c1, Census c2)
		{
			int result = 0;
			if(c2.crimePercentage < c1.crimePercentage)
		 		result = -1;
			if(c2.crimePercentage == c1.crimePercentage)
		 		result = 0;
			if(c2.crimePercentage > c1.crimePercentage)
		 		result = 1;
			
			return result;
		}
	};

	/**
	Comparator method that compares the counties of
	two Census objects 
	*/	
	public static Comparator<Census> CountyComp = 
					new Comparator<Census>() {
		
		/**
		Compares two countiesof Census objects
		via lexigraphical ordering 
		*	@param c1	first Census object
		*	@param c2	second Census object
		*	@return int: comparison integer value
		*/
		public int compare(Census c1, Census c2)
		{
			return c1.County().compareTo(c2.County());
		}
	};
	/**
	Returns a string that displays the information of
	a Census object
	*	@return	String:	string representation of a Census object
	*/	
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("0.###");

		return "County: " + county + "\nState:" + state +
		"\nPopulation: " + population + "\nPoverty: " + poverty +
		"\nCrimes reported: " + crimeReported +
		"\nCrime percentage: " + df.format(crimePercentage) + "%\n";
	}
	
}
	
