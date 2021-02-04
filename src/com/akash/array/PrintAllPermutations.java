package com.akash.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
public class PrintAllPermutations {

	public static void main(String args[]) {
		/*int arr[] = {1,2,3,4};
		List<List<Integer>> result = permute(arr);
		for(List<Integer> temp : result) {
			System.out.println(Arrays.asList(temp));
		}

		System.out.println("<==========>");

		int duplicateArr[] = {1,2,1};
		List<List<Integer>> uniqueResult = permuteUnique(duplicateArr);
		for(List<Integer> temp : uniqueResult) {
			System.out.println(Arrays.asList(temp));
		}*/
		int arr[] = {1,2,3,4};
		List<List<Integer>> result = subsets(arr);
		for(List<Integer> temp : result) {
			System.out.println(Arrays.asList(temp));
		}
	}

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		// Arrays.sort(nums); // not necessary
		backtrack(list, new ArrayList<>(), nums);
		return list;
	}

	private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
		if(tempList.size() == nums.length){
			list.add(new ArrayList<>(tempList));
		} else{
			for(int i = 0; i < nums.length; i++){
				if(tempList.contains(nums[i])) continue; // element already exists, skip
				tempList.add(nums[i]);
				backtrack(list, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
		return list;
	}

	private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
		if(tempList.size() == nums.length){
			list.add(new ArrayList<>(tempList));
		} else{
			for(int i = 0; i < nums.length; i++){
				if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
				used[i] = true;
				tempList.add(nums[i]);
				backtrack(list, tempList, nums, used);
				used[i] = false;
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrackSubsets(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private static void backtrackSubsets(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
		list.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++){
			tempList.add(nums[i]);
			backtrackSubsets(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrackSubsetsWithDup(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private static void backtrackSubsetsWithDup(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
		list.add(new ArrayList<>(tempList));
		for(int i = start; i < nums.length; i++){
			if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
			tempList.add(nums[i]);
			backtrackSubsetsWithDup(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
}
