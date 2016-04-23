#ifndef __SKISLOPE__
#define __SKISLOPE__

#include <vector>
#include <fstream>

class Node
{
public:
	Node();

private:
	int a, b;

friend class Tree;
};

class Tree
{
public:
	Tree();
	void gather(std::ifstream &);
	void build(Node *);

private:
	std::vector<Node *> nodes;
	std::vector<Node *> roots;
	int total;
};

#endif

		
