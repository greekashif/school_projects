main.exe: main.o SkiSlope.o
	g++ -o main.exe main.o SkiSlope.o

main.o: main.cpp SkiSlope.h
	g++ -c main.cpp

SkiSlope.o: SkiSlope.cpp
	g++ -c SkiSlope.cpp

clean:
	rm *.o *.exe

run:
	./main.exe

