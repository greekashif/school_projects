//author: Kashif Ahmadi
//UGA ID: 810178315

#include <iostream>
#include <cstdlib>
#include <fstream>
#include "Huffman.h"

int printMenu(Tree &, std::fstream &);

int main()
{
	Tree t;
	std::fstream lib;
	int selection = printMenu(t, lib);
	while(selection != 5)
		selection = printMenu(t, lib);

	lib.close();
	return 0;
}

int printMenu(Tree &tree, std::fstream &f) 
{
	int temp;
    std::cout << "Please choose from the following:\n" <<
                 "1: Build the library file from sample.txt\n" <<
                 "2: Print the character list and the frequency" <<
                 " of each char in sample.txt\n" <<
                 "3: Encode a message\n" << "4: Decode a message\n" <<
				 "5: Quit\n";
	std::cin >> temp;
	std::cin.get();
	f.open("sample.txt");

	switch(temp)
	{
		case 1:
			tree.makeCode(f);
			std::cout << "Library file built\n";
			break;
		case 2:
			tree.makeCode(f);
			std::cout << "FREQUENCY TABLE\nchar frequency\n";
			tree.print_freq();
			break;
		case 3:
		{
			std::cout << "Please enter the text to be encoded:\n";
			std::string input;
			std::getline(std::cin, input);
			tree.makeCode(f);
			tree.encode(input);
			break;
		}
		case 4:
		{
			std::cout << "Please enter an encoded message:\n";
			std::string inp;
			std::cin >> inp;
			tree.makeCode(f);
			tree.decode(inp);
			break;
		}
		case 5:
			std::cout << "Goodbye!\n";
			exit(0);

		default:
			std::cout << "Unknown input. program exiting\n";
			exit(0);
	}
	return temp;
}
