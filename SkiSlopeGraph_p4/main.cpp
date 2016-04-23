#include <iostream>
#include <fstream>
#include "SkiSlope.h"

int main()
{
	std::ifstream input;
	char file[80];
	std::cout << "Enter file: ";
	std::cin >> file;
	input.open(file);
	Tree t;
	t.gather(input);

	input.close();

	return 0;
}

