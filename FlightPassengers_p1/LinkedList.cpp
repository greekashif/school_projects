#include <iostream>
#include "Node.h"

LinkedList::LinkedList() : head_f(NULL), head(NULL), 
							st(new P_Stack(1000)), qu(new P_Queue(1000)){}

LinkedList::~LinkedList() {
	while(!isEmpty()) removeFront(); 
}

bool LinkedList::isEmpty() const {
	return head_f == NULL; 
}

void LinkedList::addFlight(int flight) 
{
	Node * f = new Node;
	f->flightNo = flight;
	f->next = head_f;
	head_f = f;
}

void LinkedList::deleteFlight(int flight) 
{
	Node * curr = head_f;
	if(curr->flightNo == flight)
	{
		std::cout << "Flight " << flight << " removed\n";
		head_f = curr->next;
		return;
	}
	
	Node * prev = curr;
	while(curr != NULL) 
	{
		if(curr->flightNo == flight)
		{
			curr = curr->next;
			prev->next = curr;
			return;
		}

		else if(curr->flightNo == flight && curr->next == NULL)
		{
			curr = prev;
			curr->next = NULL;	
			return;
		}
		
		prev = curr;
		curr = curr->next;
	}

}

void LinkedList::addPassenger(int flght, std::string last, std::string first,
								int seat)
{
	P_Node * temp = head;
	Node * fl_head = head_f;
	while(fl_head != NULL)
	{
		if(fl_head->flightNo == flght)
		{	
			P_Node * pass = new P_Node;
			pass->flight = flght;
			pass->lastName = last;
			pass->firstName = first;
			pass->seatNo = seat;
			pass->next = head;
			head = pass;
			st->push(pass);
			qu->enqueue(pass);
			//add to queue here
		}
		fl_head = fl_head->next;
	}
}

void LinkedList::removePassenger(int fl, std::string last, std::string first,
									int seat)
{
	Node * f_head = head_f;
	P_Node * p_head = head;
	bool wasFound = false;

	//find the correct flight
	while(f_head != NULL)
	{
		if(f_head->flightNo == fl)
		{
			//remove if the match is the first entry
			if(p_head->lastName == last && p_head->firstName == first &&
				p_head->seatNo == seat)
			{
				std::cout << "Passenger: " << p_head->firstName << " "
					<< p_head->lastName << " removed\n";
				head = p_head->next;
				wasFound = true;
				return;
			}

			//remove if the match is neither the first or last 
			P_Node * prev = p_head;
			while(p_head != NULL)
			{
				if(p_head->lastName == last && p_head->firstName == first &&
					p_head->seatNo == seat)
				{
					p_head = p_head->next;
					prev->next = p_head;
					wasFound = true;
					return;
				}
				//remove if the match is the last entry
				else if(p_head->lastName == last && p_head->firstName == 
						first && p_head->seatNo == seat && p_head->next == NULL)
				{
					p_head = prev;
					p_head->next = NULL;
					wasFound = true;
					return;
				}
				prev = p_head;
				p_head = p_head->next;
			}
		}
		f_head = f_head->next;
	}
	if(!wasFound) std::cout << "Unable to locate passenger for removal\n";
}
					
void LinkedList::removeFront() 
{
	Node * temp = head_f;
	head_f = temp->next;
	delete temp;
}

void LinkedList::showAllFlights() 
{
	Node * temp = head_f;
	std::cout << "Current Flights: ";
	while(temp != NULL)
	{
		std::cout << temp->flightNo << ", ";
		temp = temp->next;
	}
	std::cout << "END OF LIST\n";
}
	
void LinkedList::showPassenger(int fl, std::string last, std::string first)
{
	Node * temp = head_f;
	P_Node * top = head;
	bool wasFound;
	std::cout << "Searching ... ";

	while(temp != NULL)
	{
		if(temp->flightNo == fl)
		{
			while(top != NULL)
			{
				if(top->lastName == last && top->firstName == first)
				{
					std::cout << "Found!\n";
					std::cout << "Flight No: " << top->flight << std::endl; 
					std::cout << "Name: " << top->lastName << ", " <<
						top->firstName << std::endl; 
					std::cout << "Seat number: " << top->seatNo << std::endl; 
					wasFound = true;
					return;
				}
				top = top->next;
			}
		}
		temp = temp->next;
	}

	if(!wasFound) std::cout << "\nUnable to locate passenger\n";
}

void LinkedList::showAllPassengers(int flight)
{
	Node * temp = head_f;
	P_Node * top = head;
	std::cout << "Current Passengers on Flight " << flight << ":\n";
	while(temp != NULL)
	{
		if(temp->flightNo == flight)
		{
			while(top != NULL)
			{
				if(top->flight == flight)
				{
					std::cout << "Flight No: " << top->flight << std::endl; 
					std::cout << "Name: " << top->lastName << ", " <<
						top->firstName << std::endl; 
					std::cout << "Seat number: " << top->seatNo << std::endl; 
				}
				top = top->next;
			}
		}
		temp = temp->next;
	}
	std::cout << "END OF LIST\n";
}

void LinkedList::showAllFlightsAndPassengers()
{
	Node * flight_hop = head_f;
	P_Node * pass_hop = head;
	while(flight_hop != NULL)
	{
		while(pass_hop != NULL)
		{
			if(flight_hop->flightNo == pass_hop->flight)
			std::cout << "Passengers on Flight " << pass_hop->flight << 
				":\n";
			std::cout << "Name: " << pass_hop->lastName << ", " << 
				pass_hop->firstName << " => Seat: " << pass_hop->seatNo << 
				std::endl;
			pass_hop = pass_hop->next;
		}
		flight_hop = flight_hop->next;
	}
}
// print info about the K>0 recently added passengers to flight
// use stack
void LinkedList::showNewPassengers(int fl, int K)
{
	std::cout << K << " recently added passengers for flight " << fl << "\n";
	for(size_t i = 0; i < K; i++)
	{
		const P_Node * temp = st->top();
		if(st->empty()) std::cout << "no other passengers listed\n";
		else 
			std::cout << "Name: " << temp->lastName << ", " 
				<< temp->firstName << "; Seat: " << temp->seatNo << std::endl;
		st->pop();
	}
}

// print info about the K>0 passengers that first booked flight
// use queue
void LinkedList::showFirstPassengers(int fl, int K)
{
	std::cout << K << " initially added passengers for flight " << fl << "\n";
	for(size_t i = 0; i < K; i++)
	{
		const P_Node * temp = qu->front();
		if(qu->empty()) std::cout << "no other passengers listed\n";
		else
		std::cout << "Name: " << temp->lastName << ", " << temp->firstName
					<< "; Seat: " << temp->seatNo << std::endl;
		qu->dequeue();
	}
}

P_Stack::P_Stack(size_t N) : st(new P_Node*[N]), capacity(N), t(0) {}
int P_Stack::size() const { return capacity; }
bool P_Stack::empty() const { return t == 0; }
const P_Node* P_Stack::top() { return st[t-1]; }
void P_Stack::push(P_Node * p) { st[t++] = p; } 
void P_Stack::pop() { --t; }

P_Queue::P_Queue(size_t N) : que(new P_Node*[N]), capacity(N), f(0) {}
int P_Queue::size() const { return n; }
bool P_Queue::empty() const { return n == 0; }
const P_Node* P_Queue::front() { return que[f]; }
void P_Queue::enqueue(P_Node * p) { 
	que[r] = p;
	r = (r + 1) % capacity;
	++n; } 
void P_Queue::dequeue() { 
	f = (f + 1) % capacity;
	--n; }

void LinkedList::sortFlights()
{
	Node * curr = head_f;
	Node * after = head_f->next;
	bool swap = false;
	while(!swap)
	{
		curr = head_f;
		swap = false;
		while(after != NULL)
		{
			if(curr->flightNo > after->flightNo)
			{	
				int temp = curr->flightNo;
				curr->flightNo = after->flightNo;
				after->flightNo = temp;
				swap = true;
			}
			curr = curr->next;
			after = after->next;
		}
	}
}	

void LinkedList::sortPassengers()
{
	P_Node * curr = head;
	P_Node * after = head->next;
	bool swap = false;
	while(!swap)
	{
		swap = false;
		while(after != NULL)
		{
			if(curr->lastName > after->lastName)
			{	
				std::string tempLast = curr->lastName;
				std::string tempFirst = curr->firstName;
				int tempSeat = curr->seatNo;
				curr->lastName = after->lastName;
				curr->firstName = after->firstName;
				curr->seatNo = after->seatNo;
				after->lastName = tempLast;
				after->firstName = tempFirst;
				after->seatNo = tempSeat;
				swap = true;
			}
			curr = curr->next;
			after = after->next;
		}
	}
}	















	

	

