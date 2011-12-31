package ch.lydon.codekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeKata1 {
	public static void main(String [] args) {
		
		CodeKata1 codeKata1 = new CodeKata1();
		
//		codeKata1.perm1("dona");
		
		List<String> stringList = new ArrayList<String>();
//		codeKata1.perm1List("dona", stringList);
		
		List<List<Character>> charListList = new ArrayList<List<Character>>();
//		codeKata1.perm2(Arrays.asList('d','o', 'n', 'a'), charListList);
		
//		System.out.println(stringList.size());
		//List< List<Character> > permute1 = codeKata1.permuteChar( Arrays.asList( 'c', 'a', 'r' ) );		
		//codeKata1.printListListChar( permute1 );
		
		charListList = new ArrayList<List<Character>>();
		codeKata1.permNoRecursion(Arrays.asList('d','o', 'n', 'a'), charListList);
		System.out.println(charListList.size());
		codeKata1.printListListChar(charListList);
		
	}
	
	public void printListListChar(List <List<Character>> charListList) {
		
		for( List<Character> charList: charListList) {
			String tempString = "";
			for( Character character: charList) {
				tempString += character;
			}
			System.out.println(tempString);
		}
		
	}

	
	
	
	
	public void permNoRecursion(List<Character> inCharList,  List< List<Character>> outListList ) {
		
		int stringLength = inCharList.size();
		
		int p[] = new int[ stringLength+1 ];
		
		for( int j = 0; j <= stringLength; j++) {
			p[j] = j;
		}
		
//		List<List<Character>> outCharList = new ArrayList<List<Character>>();

		List<Character> tempInCharList = new ArrayList<Character>(stringLength);
		tempInCharList.addAll(inCharList);
		
		int counter = 0;
		int i = 1, j = 0;
		while( i < stringLength ) {
			
			p[i]--;
			j = i % 2 * p[i];
			
			Character tempChar = tempInCharList.get(i);
			tempInCharList.set(i, tempInCharList.get(j));
			tempInCharList.set(j, tempChar);
			
			counter++;
			
			List<Character> tempInCharListCopy = new ArrayList<Character>();
			tempInCharListCopy.addAll( tempInCharList );
			outListList.add(tempInCharListCopy);
			
			i = 1;
			while( p[i] == 0) {
				p[i] = i;
				i++;
			}
			
		}
	}
	
	
	
	/*
	 * C-1.4 Write a short Java program that outputs all possible strings formed by using the characters c,a,r,b,o,n exactly once
	 */
	public void perm1(String s) {
		perm1( "", s);
	}
	public void perm1(String prefix, String s) {
		int N = s.length();
		if( N == 0) 
			System.out.println(prefix);
		else
		{
			for (int i=0; i<N; i++) {
				perm1( prefix + s.charAt(i), s.substring(0,i) + s.substring(i+1,N));
			}
		}
	}
	
	
	
	public void perm1List(String s, List<String> outList) {
		perm1List( "", s, outList);
	}
	public void perm1List(String prefix, String s, List<String> outList) {
		int N = s.length();
		if( N == 0) {
			outList.add(prefix);
			System.out.println(prefix);
		}
		else
		{
			for (int i=0; i<N; i++) {
				perm1List( prefix + s.charAt(i), s.substring(0,i) + s.substring(i+1,N), outList);
			}
		}
	}
	
	
	
	public void perm2( List<Character> s, List< List<Character>> outListList ) {
		List<Character> emptyCharList = Collections.emptyList();
		perm2ListList( emptyCharList, s, outListList);
	}	
	public void perm2ListList( List<Character> prefix, List<Character> s, List< List<Character>> outListList ) {
		int N = s.size();
		if( N == 0 ) {
			outListList.add( prefix );
		}
		else {
			
			for( int i=0; i<N; i++) {
				
				// created both param1List and param2List because we can't modify the list returned by subList()
				List<Character> param1List = new ArrayList<Character>();
				param1List.add(s.get(i));
				
				List<Character> param2List = new ArrayList<Character>();
				param2List.addAll(s.subList(0,i));
				param2List.addAll(s.subList(i+1, N));				
				
				perm2ListList( param1List, param2List , outListList );
			}
			
		}
	}
	
	
	
	
}
