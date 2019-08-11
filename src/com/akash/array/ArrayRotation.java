package com.akash.array;

import java.util.Arrays;
import java.util.Collections;

public class ArrayRotation {

	public static void main(String args[]) {
		int arr[] = { 1, 2, 3, 4, 5, 6, 7 };
		leftRotateArray(arr, arr.length, 3);
		System.out.println(Arrays.toString(arr));
		Collections.sort(null);
	}

	private static void leftRotateArray(int arr[], int n, int d) {
		int gcd = gcd(n, d);
		for (int i = 0; i < gcd; i++) {
			int j = i;
			int temp = arr[i];
			while (true) {
				int k = j + d;
				if (k >= n) {
					k = k - n;
				}
				if (k == i) {
					break;
				}
				arr[j] = arr[k];
				j = k;
			}
			arr[j] = temp;
		}
	}

	private static int gcd(int a, int b) {
		if (a < b) {
			return gcd(b, a);
		}
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}
