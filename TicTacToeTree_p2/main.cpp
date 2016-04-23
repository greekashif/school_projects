//author: Kashif Ahmadi 810178315
//CS2720 Project 2

#include <iostream>
#include <cstdlib>
#include "Tree.h"

int print_header(Tree &, std::string);
std::string input();
bool isValid(std::string);

int main()
{
	Tree tree;
	Node * n = new Node;
	std::string position = input();
	if(isValid(position))
	{
		int sel = print_header(tree, position);
		while(sel >= 1 || sel <= 3)
		{
			sel = print_header(tree, position);
		}
	}
	else 
		std::cout << "Invalid input, try again\n";

	return 0;
}

int print_header(Tree &tree, std::string s) 
{
	int temp;
	std::cout << "Please choose from the following:\n\n" <<
				 "1: Total Nodes\n2: LevelOrder Traversal\n" <<
				 "3: Path to a terminal board position\n" <<
				 "Any other number: EXIT\n";
	std::cin >> temp;
	
	Node * n = new Node;
	tree.resetNodeSum();

	switch(temp)
	{
		case 1: 
			tree.generate(n, s);
			tree.printNodeSum();
			break;
		case 2:
			tree.generate(n, s);
			tree.levelOrder(n);
			break;
		case 3:
			tree.winningPath(n, s);
			std::cout << std::endl;
			break;
		default:
			std::cout << "Program exiting ...\n";
			exit(0);
			break;
	}
	return temp;
}

std::string input()
{
	std::cout << "Please enter an initial board position:\n" <<
				 "NOTE: use X, O or * not x, o\n";
	std::string temp;
	std::cin >> temp;

	std::cout << "\nBoard serialization is: " << temp << std::endl;
	return temp;
}

bool isValid(std::string s)
{
	return move(s) == "X" || move(s) == "O";
}
	
