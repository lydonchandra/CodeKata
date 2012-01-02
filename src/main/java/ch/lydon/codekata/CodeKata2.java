package ch.lydon.codekata;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;


/**
 * 	C-1.3 Write a Java method that takes an array containing the set of all integers in the range 1 to 54 and 
 *  shuffles it into random order. Your method should output each possible order with equal opportunity
 *   
 */
public class CodeKata2 {

	
	public static void main(String[] args) {

		CodeKata2 codeKata2 = new CodeKata2();
		
		//Set<Integer> inSet = new HashSet<Integer>();
		
		int max = 54;
		int min = 1;
		int [] inSet = new int[max];
		for( int i=0; i<max; i++) {
			// integers range from 1 to 54, 
			// inSet[0] == i+1 == 0 + 1 = 1;
			// inSet[1] == i+1 == 53 + 1 = 54;
			inSet[i] = i+1;
		}
		codeKata2.printSet( codeKata2.shuffle(inSet) );
	}

	
	public CodeKata2 () {
		final int SEED_SIZE = 2 * 4096;
		
		byte seed [] = new byte[SEED_SIZE];
		
		// using anoter random gen to create seed
		SecureRandom tempRandom = new SecureRandom( );
		
		// bitCount  = 8 * 2 * 4096 == 65537
		BigInteger biginteger = new BigInteger( 8 * SEED_SIZE, tempRandom );
		String bigintstr = biginteger.toString();

		// an example using ByteBuffer to convert long into byte array
		//byte time1byte[] =  ByteBuffer.allocate(SEED_SIZE).putLong(time1).array();
		  
		// seed has 8192 bits
		seed = biginteger.toByteArray();
		
		secureRandom = new SecureRandom (seed);
	}
	 

	Random secureRandom = new SecureRandom( );
	
	
	/**
	 * http://en.wikipedia.org/wiki/Fisher-Yates_shuffle
	 * 
	 * To shuffle an array a of n elements (indices 0..n-1):
	 *		for i from n − 1 downto 1 do
	 *			j ← random integer with 0 ≤ j ≤ i
     *  		exchange a[j] and a[i]
	 */
	public int[] shuffle (int[] inSet ) {
		
		int [] returnSet = Arrays.copyOf(inSet, inSet.length);
		
		for( int i = inSet.length-1; i > 0; i-- ) {
			
			// j ← random integer with 0 ≤ j ≤ i
			int j = secureRandom.nextInt(i+1); 
			
			// swap returnSet[i] and returnSet[j]
			int temp = returnSet[i];
			returnSet[i] = returnSet[j];
			returnSet[j] = temp; 
		}
		return returnSet;
	}

	
	
	public Set<Integer> shuffle (Set<Integer> inSet ) {
		
		return inSet;
	}
	
	
	public void printSet( int[] inSet ) {
		for( int integer: inSet ) {
			System.out.print(integer + " ");
		}
		System.out.println(" ");
	}
	

	
	public void printSet( Set<Integer> inSet ) {
		
		for( Integer integer: inSet ) {
			System.out.print(integer + " ");
		}
		System.out.println(" ");
	}
	
}
