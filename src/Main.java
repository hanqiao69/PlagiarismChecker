import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/* This java program is meant to identity plagiarism by comparing the two input 
 * text files, going over a series of associated synonyms and returning a 
 * percentage value representing the resemblance of the two input files.
 */

public class Main {


	/* The main function uses the helper functions above to process the inputs.
	 */
	/**
	 * @param syns the file name of containing the set of synonyms
	 * @param file1 the first input file
	 * @param file2	the second input file
	 * @param n the size of tuples (optional)
	 * @return print statement representing the percentage of the 
	 * 					resemblance between the two input files
	 * @author qiao
	 */
	public static void main(String[] args) {
		int n = 3;	
		PlagiarismChecker pc;
		if (args.length > 3) {
			n = Integer.parseInt(args[3]);
		}
		try {
			pc = new PlagiarismChecker(args[0], args[1], args[2], n);
		} catch (IOException e) {
			System.out.println("Error: Invalid Input Format, please enter syns.txt file1.txt file2.txt");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: tuple size larger than input size, try a smaller integer");
		}
	}

}
