//author: Kashif Ahmadi
//UGA ID: 810178315

#include <iostream>
#include <vector>
#include <utility>
#include <fstream>
#include "Huffman.h"

static std::vector <Node *> node_set;
static std::ofstream output;
static Node * root;

Node::Node() :left(NULL), right(NULL) {}

Tree::Tree() :str_bit(""), text(""), text_size(0.0), encoded(0.0),
				compressed(0.0) {}

void Tree::makeCode(std::fstream &f)
{
	output.open("library.txt");
	int freq[256] = {}; //zero initialize for counts

	while(f.good())
		//the index represents the ASCII int code for each char
		freq[int(f.get())]++; 

	for(int i = 0; i < 256; i++)
		if(freq[i] > 0 && i != 10) //ignore newline
		{
			Node * temp = new Node;
			temp->ch = std::string(1,char(i));
			temp->fq = freq[i];
			temp->parent = NULL;
			temp->left = NULL;
			temp->right = NULL;
			node_set.push_back(temp);
		}
	
	build_huffman_tree(node_set);
}
			
void Tree::build_huffman_tree(std::vector<Node *> &v)
{
	if(v.size() == 1) 
	{
		root = v[0];
		traverse(v[0]);
		output.close();
		return; 
	}

	int min_index1 = 0, min_index2;
	int min1 = v[0]->fq;
	int min2;

	//find the two nodes with the least weight
	for(int i = 0; i < node_set.size(); i++)
		if(min1 >= v[i]->fq && v[i]->parent == NULL) 
		{
			min1 = v[i]->fq;
			min_index1 = i;
		}

	for(int i = 0; i < node_set.size(); i++)
		if(i != min_index1 && v[i]->parent == NULL)
		{
			min2 = v[i]->fq;
			min_index2 = i;
			break;
		}

	for(int i = 0; i < node_set.size(); i++)
	{
		if(min2 >= v[i]->fq  && i != min_index1 && v[i]->parent == NULL) 
		{
			min2 = v[i]->fq;
			min_index2 = i;
		}
	}

	//create parent for the two mininum
	Node * par = new Node;
	par->ch = node_set[min_index1]->ch + node_set[min_index2]->ch;
	par->fq = node_set[min_index1]->fq + node_set[min_index2]->fq;
	par->left = node_set[min_index1];
	par->right = node_set[min_index2];
	par->left->parent = par;
	par->right->parent = par;
	par->parent = NULL;
	node_set.push_back(par);

	node_set.erase(node_set.begin() + min_index1);
	if(min_index1 > min_index2)
		node_set.erase(node_set.begin() + min_index2);
	else
		node_set.erase(node_set.begin() + (min_index2 - 1));

	build_huffman_tree(node_set);
}

void Tree::traverse(Node * node) 
{
	if(node == NULL) return;
	if(node->parent == NULL) node->bit = "-1";
	if(node->left != NULL) node->left->bit = "0";
	if(node->right != NULL) node->right->bit = "1";
	output << node->ch << ": " << node->fq << std::endl;
	traverse(node->left);
	traverse(node->right);
}

void Tree::encode(std::string &str)
{
	text_size = str.size();
	check(root, str);
	compressed = (str_bit.size() / 5) / (text_size*5);
	std::cout << "The encoded text is: " << str_bit << std::endl;
	std::cout << "The compression ratio is: " << compressed << std::endl;
	str_bit = "";
}

void Tree::decode(std::string &str)
{
	dec(root, str);
	std::cout << "The decoded message is: " << text << std::endl;
	text = "";
}

void Tree::dec(Node * node, std::string &str)
{
	if(node->isLeaf())
	{
		text = text + node->ch;
		if(str[0] == '0') dec(root->left, str.erase(0,1));
		else dec(root->right, str.erase(0,1));
	}
	if(str.size() == 0) return;
	if(str[0] == '0') dec(node->left, str.erase(0,1));
	else dec(node->right, str.erase(0,1));
}


void Tree::check(Node * node, std::string &str)
{
	if(str.size() == 0) return;
	if(node->ch.find(str[0]) == std::string::npos) 
		return;
	if(node->bit != "-1")
		str_bit = str_bit + node->bit;
	if(node->isLeaf())
		check(root, str.erase(0,1));
	check(node->left, str);
	check(node->right, str);
}
	
bool Node::isLeaf() const
{
	return this->left == NULL && this->right == NULL;
}

void Tree::print_freq() const 
{
	print(root);
}

void Tree::print(Node * node) const
{
	if(node == NULL) return;
	if(node->isLeaf())
		std::cout << node->ch << ": " << node->fq << std::endl;
	print(node->left);
	print(node->right);
}


	
