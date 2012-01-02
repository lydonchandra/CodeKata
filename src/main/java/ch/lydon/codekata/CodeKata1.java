package ch.lydon.codekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.caliper.SimpleBenchmark;

/*
 * From Data Structures and Algorithm in Java, 5th Edition
 * C-1.4 Write a short Java program that outputs all possible strings formed by using the characters c,a,r,b,o,n exactly once
 */
public class CodeKata1 {
	
	/**
	 * How to run:
	 * >> com.google.caliper.Runner ch.lydon.codekata.CodeKata1.CodeKata1Benchmark
	 * @author lchandra
	 *
	 */
	public static class CodeKata1Benchmark extends SimpleBenchmark {
		
		CodeKata1 codeKata1 = new CodeKata1();
		
		public void timePerm1Recursion(int reps) {
			for( int i=0; i < reps; i++ ) {
				// permutation of a simple string, results displayed on standard output
				codeKata1.perm1Recursion("dona");
			}
		}
		
		
		public void timePerm1ListRecursion(int reps) {
			for( int i=0; i < reps; i++ ) {
				List<String> stringList = new ArrayList<String>();
				codeKata1.perm1ListRecursion("dona", stringList);
			}
		}
		
		
		public void timePerm2ListRecursion(int reps) {
			for( int i=0; i < reps; i++ ) {
				List<List<Character>> charListList = new ArrayList<List<Character>>();
				codeKata1.perm2ListRecursion(Arrays.asList('d','o', 'n', 'a'), charListList);
			}
		}
		
		
		public void timePerm2NoRecursion(int reps) {
			for( int i=0; i < reps; i++ ) {
				List<List<Character>> charListList = new ArrayList<List<Character>>();
				codeKata1.permNoRecursion(Arrays.asList('d','o', 'n', 'a'), charListList);
			}
		}
	}
	
	public static void main(String [] args) {
		
			
		CodeKata1 codeKata1 = new CodeKata1();
		
		// permutation of a simple string, results displayed on standard output
		codeKata1.perm1Recursion("dona");
		

		// permutation of a simple string, results returned on a List
		List<String> stringList = new ArrayList<String>();
		codeKata1.perm1ListRecursion("dona", stringList);

		
		// permutation of a List of Character using recursion, result returned on a List of Character List
		List<List<Character>> charListList = new ArrayList<List<Character>>();
		codeKata1.perm2ListRecursion(Arrays.asList('d','o', 'n', 'a'), charListList);
		codeKata1.printListListChar(charListList);
		System.out.println("------------------------");
		 
		// permutation of a List of Character not using RECURSION, results returned on a List of Character List
		charListList = new ArrayList<List<Character>>();
		codeKata1.permNoRecursion(Arrays.asList('d','o', 'n', 'a'), charListList);
		System.out.println(charListList.size());
		codeKata1.printListListChar(charListList);
		
	}
	
	
	

	
	
	
	/*
	 * permutation of a List of Character NOT using RECURSION, results returned on a List of Character List
	 * 
	 * The algorithm used for permutations of the string is an adaptation of the "Countdown Quickperm algorithm"
	 * by Mr. Phillip Paul Fuchs. The credit for this algo goes to him. http://www.quickperm.org/01example.php
	 * 
	 * the C version is at http://www.daniweb.com/software-development/c/code/216767
	 */
	public void permNoRecursion(List<Character> inCharList,  List< List<Character>> outListList ) {
		
		int stringLength = inCharList.size();
		
		int p[] = new int[ stringLength+1 ]; // p is used to control the iteration
		
		for( int j = 0; j <= stringLength; j++) {
			
			p[j] = j;	// init p[1] to 1, p[2] to 2 and so on
		}
		
		List<Character> tempInCharList = new ArrayList<Character>(stringLength);
		tempInCharList.addAll(inCharList);
		
		int counter = 0;
		int i = 1, j = 0;
		
		while( i < stringLength ) {
			
			p[i]--;
			j = i % 2 * p[i];  // if i is odd then j = p[i] otherwise j = 0
			
			// swap tempInCharList[i] and tempInCharList[j]
			Character tempChar = tempInCharList.get(i);
			tempInCharList.set(i, tempInCharList.get(j));
			tempInCharList.set(j, tempChar);
			
			counter++;
			
			// create a new temp List so we can copy it to our output List outListList
			List<Character> tempInCharListCopy = new ArrayList<Character>();
			tempInCharListCopy.addAll( tempInCharList );
			outListList.add(tempInCharListCopy);
			
			
			i = 1;
			while( p[i] == 0) {
				
				p[i] = i;	// reset p[i] zero value
				i++;
			}
			
		}
	}
	
	
	
	/**
	 * The algorithm is from 
	 * http://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
	 *
	 */
	public void perm1Recursion(String s) {
		perm1Recursion( "", s);
	}
	public void perm1Recursion(String prefix, String s) {
		int N = s.length();
		if( N == 0) 
			System.out.println(prefix);
		else
		{
			for (int i=0; i<N; i++) {
				perm1Recursion( prefix + s.charAt(i), s.substring(0,i) + s.substring(i+1,N));
			}
		}
	}
	
	
	/**
	 * The algorithm is an adaptation of 
	 * http://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
	 *
	 */
	public void perm1ListRecursion(String s, List<String> outList) {
		perm1ListRecursion( "", s, outList);
	}
	public void perm1ListRecursion(String prefix, String s, List<String> outList) {
		int N = s.length();
		if( N == 0) {
			outList.add(prefix);
			System.out.println(prefix);
		}
		else
		{
			for (int i=0; i<N; i++) {
				perm1ListRecursion( prefix + s.charAt(i), s.substring(0,i) + s.substring(i+1,N), outList);
			}
		}
	}
	
	
	/**
	 * The algorithm is an adaptation of 
	 * http://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
	 *
	 */
	public void perm2ListRecursion( List<Character> s, List< List<Character>> outListList ) {
		List<Character> emptyCharList = Collections.emptyList();
		perm2ListRecursion( emptyCharList, s, outListList);
	}	
	public void perm2ListRecursion( List<Character> prefix, List<Character> s, List< List<Character>> outListList ) {
		int N = s.size();
		if( N == 0 ) {
			outListList.add( prefix );
		}
		else {
			
			for( int i=0; i<N; i++) {
				
				// created both param1List and param2List because we can't modify the list returned by subList()
				List<Character> param1List = new ArrayList<Character>();
				param1List.addAll(prefix);
				param1List.add(s.get(i));
				
				List<Character> param2List = new ArrayList<Character>();
				param2List.addAll(s.subList(0,i));
				param2List.addAll(s.subList(i+1, N));				
				
				perm2ListRecursion( param1List, param2List , outListList );
			}
			
		}
	}
	
	
	
	/*
	 * Print the content of List< List<Character> >
	 */
	public void printListListChar(List <List<Character>> charListList) {
		
		for( List<Character> charList: charListList) {

			// temp string to concat all character in our List<Character>
			String tempString = "";
			for( Character character: charList) {
				tempString += character;
			}
			System.out.println(tempString);
		}
		
	}
	
}
