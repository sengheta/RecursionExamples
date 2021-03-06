import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * main
 * 
 * used for testing
 * 
 */
public class RecursionQuestion {
	public static void main(String[] args) {
		int [] test = {1,2,3,4};
		powerSet(test);
	}


	/*
	 * KillCommas Recursive Method
	 */
	static String killCommas(String s) {
		int commaCount = 0;

		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == ',') {
				commaCount++;
			}
		}

		//base case - no more commas
		if(commaCount ==0) {
			System.out.println(s);
			return s;
		}

		//recursion case
		else {
			int index = s.indexOf(','); //first occurrence of comma


			return killCommas(s.substring(0, index) + s.substring(index + 1)); //concatenate the string up to the comma, and everything after the comma
		}

	}

	/*
	 * SumDigits Recursive Method
	 */
	static int sumDigits(int num) {

		if (num == 0) {
			return 0;
		}
		return (num % 10 + sumDigits(num/10));

	}


	/*
	 * Helper methods to recursive method:
	 */


	/*
	 * getTail Helper Method:
	 * 
	 * create copy of original arrayList without the first element to isolate the tail
	 * 
	 * 
	 * 
	 */


	public static ArrayList<Integer> getTail(ArrayList<Integer> array) {
		ArrayList<Integer> tailList = new ArrayList<Integer>();
		for(int i=1; i<array.size(); i++) {
			tailList.add(array.get(i));
		}

		return tailList;

	}

	/*
	 * copyPs Helper Method:
	 *
	 * create copy of arrayList of remainder (i.e. the powerSet of restOf)
	 * 
	 * 
	 */
	//[[2],[]]
	public static ArrayList<ArrayList<Integer>> copyPs (ArrayList<ArrayList<Integer>> arrayList){

		//create new ArrayList of ArrayLists to house the copy
		ArrayList<ArrayList<Integer>> copyOfList = new ArrayList<ArrayList<Integer>>();

		for(int i=0; i<arrayList.size(); i++) {

			//create temporary subset ArrayList
			ArrayList<Integer> copyOfSubSet = new ArrayList<Integer>();

			//add all the contents of each subset of the original arrayList element into the temporary subset arrayList
			copyOfSubSet.addAll(arrayList.get(i));

			//add that arrayList into the master list
			copyOfList.add(copyOfSubSet);
		}

		return copyOfList;
	}

	/*
	 * addElement Helper Method:
	 * 
	 * takes in copy of the powerSet of the "restOf", and the current element
	 * adds the current element to each element of the powerSet of the "restOf"
	 * 
	 */

	//[[2],[]]
	public static ArrayList<ArrayList<Integer>> addElement (ArrayList<ArrayList<Integer>> listCopy, int element){

		//loop through each arrayList in the copy and add the element to each individual arrayList
		for(int i=0; i<listCopy.size(); i++) {

			listCopy.get(i).add(element);

		}
		return listCopy;
	}


	/*
	 * powerSet([1])
	 * [[1],[]]
	 * 
	 * powerSet([1,2])
	 * [[1],[], [1,2], [2]]
	 * 
	 * powerSet([1,2,3]
	 * 
	 * [[1],[2],[1,2],[], [1,3],[2,3],[1,2,3], [3]]
	 * 
	 */
	public static ArrayList<ArrayList<Integer>> powerSetHelper (ArrayList<Integer> arr){

		ArrayList<ArrayList<Integer>> psOfRest = new ArrayList<ArrayList<Integer>>();

		//base case
		if(arr.size() == 0) {
			ArrayList<Integer> psOfNull = new ArrayList<Integer>();

			psOfRest.add(psOfNull);
			return psOfRest;
		}

		//recursive case
		// [a, ...]
		// [1]

		//[1,2,...]

		int currentElement = arr.get(0);

		//need an array with the rest of the elements
		// a, [...] 
		// 1, []
		// 1, [2]
		ArrayList<Integer> restOf = getTail(arr);



		//call powerSet on remainder
		// a, [[...], ... ] 
		// 1, [[]]
		// 1, [[2],[]]
		psOfRest = powerSetHelper(restOf);


		// make a copy of [[...], ...] (remainder powerset)
		// 1, [[]], [[]]
		// 1, [[2],[]], [[2],[]]
		ArrayList<ArrayList<Integer>> psOfRestCopy = copyPs(psOfRest);


		// add a to each element of that copy (each arraylist)
		// 1, [[]], [[1]]
		// 1, [[2],[]], [[2,1],[1]]
		ArrayList<ArrayList<Integer>> psOfRestWithElement = addElement(psOfRestCopy, currentElement);


		//concat the two arraylists of arraylists
		// [[], [1]]
		psOfRest.addAll(psOfRestWithElement);


		return psOfRest;

	}


	static void powerSet (int[] arr) {
		ArrayList<Integer> originalList = new ArrayList<Integer>();

		ArrayList<ArrayList<Integer>> finalList = new ArrayList<ArrayList<Integer>>();

		for (int i=0; i<arr.length; i++) {
			originalList.add(arr[i]);
		}


		finalList = powerSetHelper(originalList);
		System.out.println(finalList);

	}

}

