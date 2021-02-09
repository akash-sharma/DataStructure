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

	}

	public static List<List<Integer>> getAllPermutation(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		getAllPermutation(list, new ArrayList<>(), nums);
		return list;
	}

	private static void getAllPermutation(List<List<Integer>> list, List<Integer> tempList, int [] nums){
		if(tempList.size() == nums.length){
			list.add(new ArrayList<>(tempList));
		} else{
			for(int i = 0; i < nums.length; i++) {
				if(tempList.contains(nums[i])) {
					continue;	// element already exists, skip
				}
				tempList.add(nums[i]);
				getAllPermutation(list, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	// https://leetcode.com/problems/permutation-sequence/
	public static List<List<Integer>> getAllUniquePermutation(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		getAllUniquePermutation(list, new ArrayList<>(), nums, new boolean[nums.length]);
		return list;
	}

	private static void getAllUniquePermutation(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
		if(tempList.size() == nums.length){
			list.add(new ArrayList<>(tempList));
		} else{
			for(int i = 0; i < nums.length; i++){
				if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) {
					continue;
				}
				used[i] = true;
				tempList.add(nums[i]);
				getAllUniquePermutation(list, tempList, nums, used);
				used[i] = false;
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	public static List<List<Integer>> getAllSubsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		getAllSubsets(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private static void getAllSubsets(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
		list.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++){
			tempList.add(nums[i]);
			getAllSubsets(list, tempList, nums, i + 1);
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

		// create an array of factorial lookup
		int[] factorial = new int[n+1];
		factorial[0] = 1;
		for(int i=1; i<=n; i++){
			factorial[i] = i * factorial[i-1];
		}
		// factorial[] = {1, 1, 2, 6, 24, ... n!}

		// create a list of numbers to get indices
		List<Integer> numbers = new ArrayList<>();
		for(int i=1; i<=n; i++){
			numbers.add(i);
		}
		// numbers = {1, 2, 3, 4}

		k--;

		StringBuilder result = new StringBuilder();
		for(int i = 1; i <= n; i++){
			int index = k / factorial[n-i];
			result.append(numbers.get(index));
			numbers.remove(index);
			k = k - (index * factorial[n-i]);
		}

		return String.valueOf(result);
	}
}
