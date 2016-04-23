#include <iostream>
#include "Node.h"

int main()
{
	LinkedList L; 
	L.addFlight(24);
	L.addFlight(21);
	L.addFlight(22);
	L.addFlight(34);
	L.addFlight(3);
	L.addFlight(2);
	L.addPassenger(21, "Dylan", "Bob", 21);
	L.addPassenger(21, "Hendrix", "Jimi", 27);
	L.addPassenger(21, "Morrison", "Jim", 90);
	L.addPassenger(21, "Cobain", "Kurt", 89);
	L.deleteFlight(34);
 	L.showNewPassengers(21, 3);
	L.sortFlights();
	L.showAllFlights();
	L.sortPassengers();
	L.showAllPassengers(21);
	L.showFirstPassengers(21,3);
	L.showAllFlightsAndPassengers();
		
	return 0;
}

