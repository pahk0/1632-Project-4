import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;

public class Proj4 {
	private long rngSeed = System.nanoTime();
	public int[] laboonify (int[] x){	
		int[] ret = new int[x.length+1];
		int sum = 0;
		
		// square each value in x and put it in ret
		for (int i = 0 ; i < x.length ; i++){
			ret[i] = (int)Math.pow(x[i], 2);
			sum+=ret[i]; // add the sum to the running total
		}
		ret[x.length] = sum; // put the sum into the last slot
		
		return ret;
	} // end laboonify
	
	public int[] randomArray(Random rng){
		// size should range from 1-100 inclusive
		int randSize = rng.nextInt(99)+1;
		int [] ret = new int[randSize];
		for (int i = 0 ; i < randSize ; i++){
			ret[i] = rng.nextInt();
		}
		return ret;
	} // end randomArray
	
	// Property 1: The size of the returned array...
	// ...should be one more than the size of the input array
	@Test
	public void checkSize(){
		// This will make sure that all 100 arrays are the same across tests
		Random rng = new Random(rngSeed);
		for (int count = 0 ; count < 100 ; count++){
			int[] inputArray = randomArray(rng);
			int[] outputArray = laboonify(inputArray);
			assertEquals(inputArray.length+1, outputArray.length);
		}
		
	} // end checkSize
	
	// Property 2: Passing in the same array multiple times...
	// ...should return the same array every time
	@Test
	public void checkRepetition(){
		// This will make sure that all 100 arrays are the same across tests
		Random rng = new Random(rngSeed);
		for (int count = 0 ; count < 100 ; count++){
			int[] inputArray = randomArray(rng);
			int[] outputArray = laboonify(inputArray);
			int[] outputArray2 = laboonify(inputArray);
			
			// First make sure that the output arrays are of the same length
			assertEquals(outputArray.length, outputArray2.length);
			
			// Next make sure each item within the arrays is identical
			for(int i = 0 ; i < outputArray.length ; i++){
				assertEquals(outputArray[i], outputArray2[i]);
			}
		}
		
	} // end checkRepetition
	
	// Property 3: Passing back in the output of an initial array...
	// ...should return an array different than the other 2 arrays
	// In other words, different input arrays should output different results
	@Test
	public void checkFeedbackLoop(){
		// This will make sure that all 100 arrays are the same across tests
		Random rng = new Random(rngSeed);
		// This for-loop will do the test 100 times in a row
		// It will use the same 100 arrays as the other tests.
		for (int count = 0 ; count < 100 ; count++){
			int[] inputArray = randomArray(rng);
			int[] outputArray = laboonify(inputArray);
			int[] outputArray2 = laboonify(outputArray);
			
			// first compare outputArray2 to outputArray
			// Make sure each item within the arrays is not identical
			// (with the exception of 0 or 1 or max int)
			// Keeping in mind that outputArray should be 1 shorter
			assertTrue(outputArray.length == outputArray2.length - 1);
			for(int i = 0 ; i < outputArray.length ; i++){
				if (outputArray[i] != 0 && outputArray[i] != 1 && outputArray[i] != Integer.MAX_VALUE){
					assertTrue(outputArray[i] != outputArray2[i]);				
					
				}
			}
			
			// Next compare outputArray2 to inputArray
			// Keeping in mind that inputArray should be 2 shorter
			assertTrue(inputArray.length == outputArray2.length - 2);
			for(int i = 0 ; i < inputArray.length ; i++){
				if (inputArray[i] != 0 && inputArray[i] != 1 && outputArray[i] != Integer.MAX_VALUE){
					assertTrue(inputArray[i] != outputArray2[i]);
				} // end if
			} // end for
		}
		
		
	} // end checkFeedbackLoop
}
