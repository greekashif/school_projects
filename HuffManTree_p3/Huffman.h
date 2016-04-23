//author: Kashif Ahmadi
//UGA ID: 810178315

#ifndef _HUFFMAN_
#define _HUFFMAN_

#include <fstream>
#include <vector>

class Node
{
public:
	Node();
	bool isLeaf() const;

private:
	Node * left;
	Node * right;
	Node * parent;
	std::string ch;
	int fq;
	std::string bit;

friend class Tree;
};

class Tree 
{
public:
	Tree();
	void makeCode(std::fstream &);
	void build_huffman_tree(std::vector<Node *> &);
	void traverse(Node *);
	void encode(std::string &);
	void decode(std::string &);
	void check(Node *, std::string &);
	void dec(Node *, std::string &);
	void print_freq() const;
	void print(Node *) const;

private:
	std::string str_bit;
	std::string text;
	double text_size, encoded, compressed;
};

#endif
