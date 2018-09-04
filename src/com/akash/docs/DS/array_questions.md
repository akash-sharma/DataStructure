=>Array Rotations :

rotate array by d elements 
	->gcd way
	->left reversal algo
	->right reversal algo
find pivot element in sorted and rotated array
find sum of pair of numbers in sorted and rotated array
	->sorting  --	O(n^2)
	->hashing  --	O(n)
Find a triplet that sum to a given value
Find all triplets with zero sum
	->sorting then pick one and do sorting approach of 2  --	O(n^2)
	->store sum-A[i] in hashset, check if sum of every pair exist in hashset  --	O(n^2)
Find four elements that sum to a given value
	->create an array of sum of each pair,sort it,approach of 2 -- O(n^2.log n)

find pair of number having sum less than K
find triplets having sum less than k
Closest product pair in an array	

majority element	-> hashmap, moore voting algo
Lexicographically minimum string rotation	-> array of size 26,count occurence of character from A-Z
--Rotate Matrix Elements (shift all elements by 1)
--Print a given matrix in spiral form
--Inplace rotate square matrix by 90 degrees -> rotate -> transpose
--TODO--Rotate each ring of matrix anticlockwise by K elements
Check if all rows of a matrix are circular rotations of each other -> append array to array itself
Minimum rotations required to get the same string -> same as above
Check if strings are rotations of each other or not
	->kmp
Count rotations divisible by 4


=>Array rearrangement
Rearrange an array such that arr[i] = i
Move all negative numbers to beginning and positive to end
Rearrange array such that arr[i] >= arr[j] if i is even and arr[i]<=arr[j] if i is odd and j < i
Convert array into Zig-Zag fashion
Rearrange array in alternating positive & negative items with O(1) extra space  -> quick sort logic
Rearrange array such that even positioned are greater than odd
Rearrange an array such that �arr[j]� becomes �i� if �arr[i]� is �j�
Arrange given numbers to form the biggest number
Longest Bitonic Subsequence  -> DP
Longest Bitonic SubArray	-> O(n) time, O(1) space
Maximum Sum Increasing Subsequence (positive numbers)
	->similar to longest increasing subsequence
--Largest Sum Contiguous Subarray	(negative and positive both)

--Minimum swaps required to bring all elements less than or equal to k together
--Find a sorted subsequence of size 3 in linear time
--Largest subarray with equal number of 0s and 1s



=>Optimization Problems :
--Find the smallest positive integer value that cannot be represented as sum of any subset of a given array
Smallest subarray with sum greater than a given value
Find subarray with given sum (Nonnegative Numbers)
--Find subarray with given sum (array having negative numbers)


--Print all subarrays with 0 sum
Largest Rectangular Area in a Histogram 
print all permutations of a given string


----------------------------------------------------------------------

int findPivot(int arr[]) {
	return findPivot(arr, 0, arr.length-1);
}

private findPivot(int arr[], int start, int end) {
	if(start > end) {
		return -1;
	}
	if(arr[start] < arr[end]) {
		return -1;
	}
	if(start == end) {
		return start;
	}
	int mid = (end-start)/2 + start;
	if(arr[mid-1] < arr[mid] && arr[mid] > arr[mid+1]) {
		return mid;
	}
	if(arr[mid-1] > arr[mid] && arr[mid] < arr[mid+1]) {
		return mid-1;
	}
	if(arr[mid-1] < arr[mid] && arr[mid] < arr[mid+1]) {
		return findPivot(arr, mid+1, end);
	}
	return findPivot(arr, start, mid-1);
}



//Rearrange an array such that arr[i] = i
int place(int arr[]) {
	int i=0;
	while(i < arr.length) {
		if(arr[i] == -1) {
			i++;
		} else {
			if(arr[i] != i) {
				int temp = arr[arr[i]];
				arr[arr[i]] = arr[i];
				arr[i] = temp;
			} else {
				i++;
			}
		}
	}
}


//Move all negative numbers to beginning and positive to end
void moveNegativeToStart(int arr[]) {
	int i=-1;
	int j=0;
	while(j < arr.length) {
		if(arr[j] < 0) {
			i++;
			swap(arr, i, j);
		}
		j++;
	}
}


//Rearrange an array such that �arr[j]� becomes �i� if �arr[i]� is �j�
void replace(int arr[]) {
	int i=0;
	while(i < arr.length) {
		if(arr[i] != i) {
			replaceInternal(arr, i);
		} else {
			i++;
		}
	}
}

void replaceInternal(int arr[], int i) {
	if(arr[i] != i) {
		int j = arr[arr[i]];
		arr[i] = i;
		replaceInternal(arr, j);
	}
}



//print longest increasing substring 
int longestIncreasingSubstring(int arr[]) {
	int max = 1;
	int maxEndingHere = 1;
	int maxStart = 0;
	int maxStartTemp = 0;
	int maxEnd = 0;
	for(int i=1; i<arr.length; i++) {
		if(arr[i] > arr[i-1]) {
			maxEndingHere++;
		} else {
			if(maxEndingHere > max) {
				max = maxEndingHere;
				maxEnd = i-1;
				maxStart = maxStartTemp;
			}
			maxEndingHere = 1;
			maxStartTemp = i;
		}
	}
	if(maxEndingHere > max) {
		max = maxEndingHere;
		maxEnd = arr.length-1;
		maxStart = maxStartTemp;
	}
	System.out.println(maxStart);
	System.out.println(maxEnd);
	return max;
}


// longest increasing subsequence

LIS - lis including current index (memoized array)
MAX - max lis found till now (variable)

Arr :	10, 22, 9, 33, 21, 50, 41, 60, 80

LIS :	1	2	1	3	2	4	4	5	6
MAX :	1	2	2	3	3	4	4	5	6


int longestIncreasingSubsequence(int arr[]) {
	int max = 1;
	int LIS[] = new int[arr.length];
	for(int i=0; i<LIS.length; i++) {
		LIS[i] = 1;
	}
	for(int i=1; i<arr.length; i++) {
		for(int j=i-1; j>=0; j--) {
			if(arr[i] > arr[j]) {
				LIS[i] = LIS[j] + 1;
				if(LIS[i] > max) {
					max = LIS[i];
				}
			}
		}
	}
	return max;
}



//Longest Bitonic Subsequence
//this is based on longest increasing subsequence
int longestBitonicSubsequence(int arr[]) {
	int lis[] = new int[arr.length];
	int lds[] = new int[arr.length];
	for(int i=0; i<lis.length; i++) {
		lis[i] = 1;
	}
	for(int i=0; i<lds.length; i++) {
		lds[i] = 1;
	}
	for(int i=1; i<lis.length; i++) {
		for(int j=i-1; j>=0; j--) {
			if(arr[i] > arr[j]) {
				lis[i] = lis[j] + 1;
			}
		}
	}
	for(int i=n-2; i>=0; i--) {
		for(int j=i+1; j<arr.length; j++) {
			if(arr[i] > arr[j]) {
				lds[i] = lds[j] + 1;
			}
		}
	}
	int max = 0;
	for(int i=0; i<arr.length; i++) {
		if(lis[i] + lds[i] - 1 > max) {
			max = lis[i] + lds[i] - 1;
		}
	}
	return max;
}



//Longest Bitonic Subarray
//	time = O(n)
//	memory = O(n)
int longestBitonicSubarray(int arr[]) {
	int lis[] = new int[arr.length];
	int lds[] = new int[arr.length];
	for(int i=0; i<lis.length; i++) {
		lis[i] = 1;
	}
	for(int i=0; i<lds.length; i++) {
		lds[i] = 1;
	}
	for(int i=1; i<lis.length; i++) {
		if(arr[i] > arr[i-1]) {
			lis[i] = lis[i-1] + 1;
		}
	}
	for(int i=n-2; i>=0; i--) {
		if(arr[i] > arr[i+1]) {
			lds[i] = lds[i+1] + 1;
		}
	}
	int max = 0;
	for(int i=0; i<arr.length; i++) {
		if(lis[i] + lds[i] - 1 > max) {
			max = lis[i] + lds[i] - 1;
		}
	}
	return max;
}



//Longest Bitonic Subarray
//	time = O(n)
//	memory = O(1)
int longestBitonicSubarray(int arr[]) {
	int j=0;
	int n = arr.length;
	int start = 0;
	int startNext = 0;
	int max = 0;
	while(j < n) {
		while(j < n-1 && arr[j] <= arr[j+1]) {
			j++;
		}
		while(j < n-1 && arr[j] >= arr[j+1]) {
			if(j < n-1 && arr[j] > arr[j+1]) {
				startNext = j + 1;
			}
			j++;
		}
		if(j - start + 1 > max) {
			max = j - start + 1;
		}
		start = startNext;
	}
	return max;
}


