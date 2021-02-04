package com.akash.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
// https://leetcode.com/problems/permutations-ii/discuss/18648/Share-my-Java-code-with-detailed-explanantion

// kth permutation
// https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
public class PrintAllPermutations {

	public static void main(String args[]) {
		/*int arr[] = {1,2,3,4};
		List<List<Integer>> result = printAllPermutation(arr);
		for(List<Integer> temp : result) {
			System.out.println(Arrays.asList(temp));
		}

		System.out.println("<==========>");

		int duplicateArr[] = {1,2,1};
		List<List<Integer>> uniqueResult = printAllUniquePermutation(duplicateArr);
		for(List<Integer> temp : uniqueResult) {
			System.out.println(Arrays.asList(temp));
		}*/
		int arr[] = {1,2,3,4};
		List<List<Integer>> result = printAllPermutation(arr);
		for(List<Integer> temp : result) {
			System.out.println(Arrays.asList(temp));
		}
	}

	public static List<List<Integer>> printAllPermutation(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		// Arrays.sort(nums); // not necessary
		allPermutation(list, new ArrayList<>(), nums);
		return list;
	}

	private static void allPermutation(List<List<Integer>> list, List<Integer> tempList, int [] nums){
		if(tempList.size() == nums.length){
			list.add(new ArrayList<>(tempList));
		} else{
			for(int i = 0; i < nums.length; i++){
				if(tempList.contains(nums[i])) continue; // element already exists, skip
				tempList.add(nums[i]);
				allPermutation(list, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	public static void printAllPermutation(String str) {
		permuteAll(str, 0, str.length()-1);
	}

	private static void permuteAll(String str, int l, int r) {
		if (l == r) {
			System.out.println(str);
		}
		else {
			for (int i = l; i <= r; i++) {
				str = swap(str,l,i);
				permuteAll(str, l+1, r);
				str = swap(str,l,i);
			}
		}
	}

	private static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i] ;
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return new String(charArray);
	}

	public static List<List<Integer>> printAllUniquePermutation(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		uniquePermutation(list, new ArrayList<>(), nums, new boolean[nums.length]);
		return list;
	}

	private static void uniquePermutation(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
		if(tempList.size() == nums.length){
			list.add(new ArrayList<>(tempList));
		} else{
			for(int i = 0; i < nums.length; i++){
				if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
				used[i] = true;
				tempList.add(nums[i]);
				uniquePermutation(list, tempList, nums, used);
				used[i] = false;
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	public static List<List<Integer>> printAllSubsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		printAllSubsets(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private static void printAllSubsets(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
		list.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++){
			tempList.add(nums[i]);
			printAllSubsets(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	public static List<List<Integer>> printAllSubsetsWithoutDup(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		printAllSubsetsWithoutDup(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private static void printAllSubsetsWithoutDup(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
		list.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++){
			if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
			tempList.add(nums[i]);
			printAllSubsetsWithoutDup(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	public static String getKthPermutation(int n, int k) {
		int pos = 0;
		List<Integer> numbers = new ArrayList<>();
		int[] factorial = new int[n+1];
		StringBuilder sb = new StringBuilder();

		// create an array of factorial lookup
		int sum = 1;
		factorial[0] = 1;
		for(int i=1; i<=n; i++){
			sum *= i;
			factorial[i] = sum;
		}
		// factorial[] = {1, 1, 2, 6, 24, ... n!}

		// create a list of numbers to get indices
		for(int i=1; i<=n; i++){
			numbers.add(i);
		}
		// numbers = {1, 2, 3, 4}

		k--;

		for(int i = 1; i <= n; i++){
			int index = k/factorial[n-i];
			sb.append(String.valueOf(numbers.get(index)));
			numbers.remove(index);
			k-=index*factorial[n-i];
		}

		return String.valueOf(sb);
	}
}
