#ifndef _NODE__
#define _NODE__

class P_Node
{
	private:
		P_Node * next;
		int flight;
		std::string lastName;
		std::string firstName;
		int seatNo;
	
	friend class LinkedList;
	friend class P_Stack;
};

class Node
{
	private: 
		Node * next;
		std::string name;
		int flightNo;
	
	friend class LinkedList;
};

class P_Stack
{
	public:
		P_Stack(size_t);
		int size() const;
		bool empty() const;
		const P_Node* top();
		void push(P_Node*);
		void pop();
	
	private:
		P_Node** st;
		int capacity;
		int t;
};

class P_Queue
{
	public:
		P_Queue(size_t);
		int size() const;
		bool empty() const;
		const P_Node* front();
		void enqueue(P_Node*); //place at rear
		void dequeue(); 	   //remove at front
	
	private:
		P_Node** que;
		int capacity;
		int f, r, n; //f = front, r = rear, n = no of elements
};

class LinkedList
{
	public:
		LinkedList();
		~LinkedList();
		bool isEmpty() const;
		void addFlight(int);
		void deleteFlight(int);
		void addPassenger(int, std::string, std::string, int);
		void removePassenger(int, std::string, std::string, int);
		void showPassenger(int, std::string, std::string);
		void showAllPassengers(int);
		void removeFront();
		void showAllFlights();
		void showAllFlightsAndPassengers();
		void showNewPassengers(int, int);
		void showFirstPassengers(int, int);
		void sortFlights();
		void sortPassengers();
	
	private:
		Node * head_f; //flights head
		P_Node * head; //passengers head
		P_Stack * st;
		P_Queue * qu;
};

#endif

