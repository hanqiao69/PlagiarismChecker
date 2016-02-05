In order to run the program in terminal:
1. go to the correct directory
2. type in $javac solution1.java
3. in the next line, type in $java solution1
4. followed by the three / four inputs: the file name containing all the 
synonyms, the two input files and an optional field indicating the tuple size
5. the program should return a percentage result representing the resemblance 
of the two input files.

Justification of efficiency and run time of the program:
1. Reading the input files should take constant amount of time
2. Putting the synonyms into a HashMap should take linear time
3. Creating a linkedlist from a txt input should take linear time relative to 
	the size of the words in the input file
4. Comparing every pair of strings should take constant time and retrieving the 
	value from the HashMap should take amortized constant time.
5. Creating the boolean array should take linear space and linear time
6. Calculating the percentage result from the boolean array should take linear 
	time and constant amount of space
