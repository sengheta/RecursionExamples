import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//
public class RecursionQuestion {
	public static void main(String[] args) {
		int [] test = {1};
		powerSet(test);
	}

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
	 * create copy of array without first element to separate out the tail
	 */
	public static ArrayList<Integer> getTail(ArrayList<Integer> array) {
		ArrayList<Integer> tailList = new ArrayList<Integer>();
		for(int i=1; i<array.size(); i++) {
			tailList.add(array.get(i));
		}

		return tailList;

	}

	/*
	 * create copy of arrayList 
	 */
	public static ArrayList<ArrayList<Integer>> copyPs (ArrayList<ArrayList<Integer>> arrayList){

		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();

		newList.addAll(arrayList);


		return newList;
	}


	//TODO: pass in a copy of the ArrayList of ArrayLists. This copy will get the additional element
	public static ArrayList<ArrayList<Integer>> addElement (ArrayList<ArrayList<Integer>> listCopy, int element){

		//create a new ArrayList to hold the copy + the new element
		ArrayList<ArrayList<Integer>> newCopyWithElement = new ArrayList<ArrayList<Integer>>();

		//loop through each arrayList in the copy
		for(int i=0; i<listCopy.size(); i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();

			//assign each arrayList to a temporary variable	
			temp = listCopy.get(i);

			//add the element to that arrayList	
			temp.add(element);

			//add the arrayList + new element to the final arrayList
			newCopyWithElement.add(temp);
		}
		return newCopyWithElement;
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
		int currentElement = arr.get(0);
		//System.out.println(currentElement);
		//need an array with the rest of the elements
		// a, [...] 
		// 1, []
		ArrayList<Integer> restOf = getTail(arr);
		//System.out.println(restOf);

		//call powerSet on remainder
		// a, [[...], ... ] 
		// 1, [[]]

		psOfRest = powerSetHelper(restOf);
		//System.out.println(psOfRest);

		// make a copy of [[...], ...] (remainder powerset)
		// 1, [[]], [[]]

		ArrayList<ArrayList<Integer>> psOfRestCopy = copyPs(psOfRest);
		//System.out.println(psOfRestCopy);		
		//		System.out.println(psOfRest);		
		// add a to each element of that copy (each arraylist)
		//1, [[]], [[1]]

		ArrayList<ArrayList<Integer>> psOfRestWithElement = addElement(psOfRestCopy, currentElement);
		//System.out.println(psOfRestWithElement);	
		//System.out.println(psOfRest);s
		//concat the two arraylists of arraylists
		// [[], [1]]
		psOfRest.addAll(psOfRestWithElement);
		return null;

	}


	static void powerSet (int[] arr) {
		ArrayList<Integer> originalList = new ArrayList<Integer>();

		ArrayList<ArrayList<Integer>> finalList = new ArrayList<ArrayList<Integer>>();

		for (int i=0; i<arr.length; i++) {
			originalList.add(arr[i]);
		}


		finalList = powerSetHelper(originalList);

		//		System.out.println(finalList);

	}

}
