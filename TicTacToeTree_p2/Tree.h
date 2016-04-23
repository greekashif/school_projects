//author: Kashif Ahmadi 810178315
//CS 2720 Project 2

#ifndef _TREE__
#define _TREE__

#include <vector>

std::string move(std::string);

class Node 
{
	private:
		std::vector<Node *> children;
		Node * parent;
		std::string board;
		std::vector<int> move_index;
		
	public:
		bool checkMove(int);

	friend class Tree;
};

class Tree 
{
	public:
		Tree();
		void generate(Node *, std::string);
		void levelOrder(Node *);
		void winningPath(Node *, std::string);
		void printNodeSum() const;
		void resetNodeSum();
		

	private:
		Node * root;
		int node_sum;
		int index;
};

#endif
