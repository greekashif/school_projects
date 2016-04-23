//author: Kashif Ahmadi 810178315
//CS 2720 Project 2

#include <iostream>
#include <queue>
#include "Tree.h"

bool isFull(std::string);
bool isWon(std::string);

Tree::Tree() : root(new Node), node_sum(1) {}

void Tree::generate(Node * N, std::string input)
{
	//count and find empty spots in root node
	std::vector<int> empty;
	for(int i = 0; i < input.size(); i++)
		if(input.substr(i,1) == "*") 
		{
			Node * new_node = new Node;
			new_node->board = input;
			new_node->parent = N;
			N->children.push_back(new_node);
			node_sum++;
			empty.push_back(i);
		}

	//assign a move for each empty spot
	for(int i = 0; i < empty.size(); i++)
		N->children[i]->board.replace(empty[i],1,move(input));

	//repeat procedure for each child
	for(int i = 0; i < N->children.size(); i++) {
		if(!isFull(N->children[i]->board) && !isWon(N->children[i]->board))
			generate(N->children[i], N->children[i]->board);
	}
}

void Tree::levelOrder(Node * N)
{
	std::queue<Node *> q;
	q.push(N);
	Node * n = new Node;

	while(!q.empty())
	{
		n = q.front();
		q.pop();
		std::cout << n->board << std::endl; //visit
		for(int i = 0; i < n->children.size(); i++)
		{
			if(n->children[i] != NULL)
				q.push(n->children[i]);
			++node_sum;
		}
	}
}
	
void Tree::winningPath(Node * n, std::string str)
{
	Node * new_node = new Node;
	std::cout << str << std::endl;
	for(int i = 0; i < str.size() && !isWon(str); i++)
		if(str.substr(i,1) == "*")
		{
			new_node->board = str.replace(i,1,move(str));
			std::cout << new_node->board << std::endl;
		}
}

void Tree::printNodeSum() const
{
	std::cout << "TOTAL NUMBER OF NODES: " << node_sum << std::endl;
	
}

void Tree::resetNodeSum()
{
	node_sum = 1;
}

bool isFull(std::string str)
{
	bool temp = true;
	for(int i = 0; i < str.size(); i++)
		if(str.substr(i,1) == "*")
			temp = false;
	return temp;
}

std::string move(std::string board)
{
	std::string move;
	int x = 0, o = 0, empty = 0;
	for(int i = 0; i < board.size(); i++)
	{
		if(board.substr(i,1) == "X") ++x;
		if(board.substr(i,1) == "O") ++o;
		if(board.substr(i,1) == "*") ++empty;
	}
	if(x == o || empty == board.size()) move = "X";
	else if(x > o && empty != board.size()) move = "O";
	return move;
}

bool Node::checkMove(int i) 
{
	bool temp = true;
	for(int k = 0; i < this->move_index.size(); ++k)
		if(this->move_index[k] == i)
			temp = false;
	
	return temp;
}

bool isWon(std::string board)
{
	bool temp = false;

	if((board.substr(0,1) == "X" && board.substr(1,1) == "X" &&
	   board.substr(2,1) == "X" )||( board.substr(0,1) == "O" &&
	   board.substr(1,1) == "O" && board.substr(2,1) == "O") ||(
	   board.substr(3,1) == "X" && board.substr(4,1) == "X" &&
	   board.substr(5,1) == "X" )||( board.substr(3,1) == "O" &&
	   board.substr(4,1) == "O" && board.substr(5,1) == "O") ||(
	   board.substr(6,1) == "X" && board.substr(7,1) == "X" &&
	   board.substr(8,1) == "X") ||( board.substr(6,1) == "O" &&
	   board.substr(7,1) == "O" && board.substr(8,1) == "O") ||(
	   board.substr(0,1) == "X" && board.substr(3,1) == "X" &&
	   board.substr(6,1) == "X" )||( board.substr(0,1) == "O" &&
	   board.substr(3,1) == "O" && board.substr(6,1) == "O" )||(
	   board.substr(1,1) == "X" && board.substr(4,1) == "X" &&
	   board.substr(7,1) == "X") ||( board.substr(1,1) == "O" &&
	   board.substr(4,1) == "O" && board.substr(7,1) == "O" )||(
	   board.substr(2,1) == "X" && board.substr(5,1) == "X" &&
	   board.substr(8,1) == "X") ||( board.substr(2,1) == "O" &&
	   board.substr(5,1) == "O" && board.substr(8,1) == "O") ||(
	   board.substr(0,1) == "X" && board.substr(4,1) == "X" &&
	   board.substr(8,1) == "X") ||( board.substr(0,1) == "O" &&
	   board.substr(4,1) == "O" && board.substr(8,1) == "O" )||(
	   board.substr(2,1) == "X" && board.substr(4,1) == "X" &&
	   board.substr(6,1) == "X") ||( board.substr(2,1) == "O" &&
	   board.substr(4,1) == "O" && board.substr(6,1) == "O")) 
	temp = true;

	return temp;
}

