#include <iostream>
#include "SkiSlope.h"

Node::Node() {}

Tree::Tree() : nodes(0), roots(0), total(0) {}

void Tree::gather(std::ifstream &file)
{
	int jun, leg;
	int count = 1;
	file >> jun >> leg;

	while(leg != 0) {

	for(int i = 0; i < leg; i++)
	{
		int a, b;
		file >> a >> b;
		Node * temp = new Node;
		temp->a = a;
		temp->b = b;
		if(a == 1) roots.push_back(temp);
		nodes.push_back(temp);
	}
	for(int i = 0; i < roots.size(); i++)
		build(nodes[i]);
	std::cout << "Slope " << count << " has " << total << " runs.\n"; 
	++count;
	total = 0;
	roots.resize(0);
	nodes.resize(0);
	file >> jun >> leg;
	}
}

void Tree::build(Node * node)
{
	if(node->b == 0) { ++total; return; }
	for(int i = 0; i < nodes.size(); i++)
	{
		if(node->b == nodes[i]->a)
			build(nodes[i]);
	}
}
