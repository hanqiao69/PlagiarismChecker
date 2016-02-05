import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class PlagiarismChecker {

	HashMap<String, Integer> synMap;
	LinkedList<String> file1;
	LinkedList<String> file2;
	String fileName1;
	String fileName2;
	int n;
	public PlagiarismChecker(String syns, String fileName1, String fileName2, int n) throws IOException {
		this.synMap = new HashMap<String, Integer>(); 
		//hashmap for storing the synonym-value pairs
		this.file1 = new LinkedList<String>(); 
		//for storing the strings in file1
		this.file2 = new LinkedList<String>(); 
		//for storing the strings in file2
		this.fileName2 = fileName1;
		this.fileName2 = fileName2;
		this.n = n;
		readSyns(syns);
		readFile(fileName1, 1);
		readFile(fileName2, 2);
		boolean[] bool = compare();
		calculate(bool, n);
	}


	/** 
	 * readSyns reads from synonym file and create a HashMap that maps every 
	 * word to a integer value which is the same as the lineNumber in this case.
	 * Thus, if two strings have the same values, they are synonyms, otherwise, 
	 * they are different.
	 * 
	 * @param input the file name containing a list of synonyms
	 * @throws IOException to be caught in Main
	 */
	private void readSyns(String input) throws IOException {
		Integer lineNumber = 0; //initialize hashmap value to 0
		FileReader fr = new FileReader(input);
		BufferedReader br = new BufferedReader(fr); //read syns.txt
		String line = "";
		while ((line = br.readLine())!= null) {
			String[] syns = line.split(" ");
			for (String s : syns) {
				synMap.put(s, lineNumber); 
				//read every word in a line and put them into the hashmap with value equals to lineNumber
			}
			lineNumber++;
		}
	}

	/**
	 *  readFiles reads the a input file, creates a linkedlist and stores the 
	 * strings in the original order. 
	 * 
	 * @param input the input file name
	 * @param index 1 or 2, depending on whether this is the first input file
	 * 					or the second one.
	 * @throws IOException
	 */
	private void readFile(String input, int index) throws IOException {
		FileReader fr = new FileReader(input);
		BufferedReader br = new BufferedReader(fr); //read file1.txt / file2.txt
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] strings = line.split("\\W+");
			if (index == 1) {
				for (String s: strings) {
					file1.add(s);
				}
			}
			else if (index == 2) {
				for (String s: strings) {
					file2.add(s);
				}
			}
		}
	}

	/**
	 *  This compare function compares the strings in two linkedlists in pairs 
	 * and determines whether each pair is the same or not. The two strings are
	 * considered the same if they are equal or if they belong to the same
	 * synonym group. False if they are different or one of them is null.
	 * 
	 * @return a boolean array with size equal to the number of strings in the
	 * 			larger input file, where every boolean value represents whether
	 * 			the respective pair of strings in the two files are equal.
	 */
	private boolean[] compare() {
		boolean[] bool = new boolean[Math.max(file1.size(), file2.size())];
		int pointer = -1;
		Arrays.fill(bool, Boolean.FALSE);
		while (!file1.isEmpty() && !file2.isEmpty()) {
			pointer++;
			String s1 = file1.poll();
			String s2 = file2.poll();
			Integer v1, v2;
			if (s1 != null && s2 != null && s1.equals(s2)) {
				bool[pointer] = true;
			}
			else if ((v1 = synMap.get(s1)) != null) {
				if ((v2 = synMap.get(s2)) != null) {
					if (v1 == v2) {
						bool[pointer] = true;
					}
				}
			}
		}
		return bool;
	}

	/**
	 * calculates the percentage of resemblance based on the boolean array.
	 * 
	 * @param bool an array where every index represents whether this pair 
	 * 			   of strings are the same in the two different files
	 * @param n	the size of the tuples
	 * @throws IllegalArgument Exception when given n is too large for 
	 * 				the input size
	 * print the percentage
	 */
	public int calculate(boolean[] bool, int n) {
		int l = bool.length;
		if (l < n) {
			throw new IllegalArgumentException();
			//in case the specified n is larger than the size of inputs.
		}
		int same = 0;
		int total = bool.length - n + 1;
		for (int i = 0; i < l - n + 1; i++) {
			boolean ans = true;
			for (int j = 0; j < n; j++) {
				ans = ans && bool[i+j];
			}
			if (ans) {same++;}
		}
		int sol = same*100/total;
		System.out.println(sol + "%"); 
		return sol; 
	}
}
