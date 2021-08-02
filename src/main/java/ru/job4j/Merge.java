package ru.job4j;

import java.util.Arrays;

public class Merge {

    public static int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int size = left.length + right.length;
        int minLeft = left[0];
        int minRight = right[0];
        left[0] = 0;
        right[0] = 0;
        for (int i = 0; i < size; i++) {
            if (minLeft <= minRight) {
                rsl[i] = minLeft;
                for (int j = 0; j < left.length; j++) {
                    if (left[j] != 0) {
                        minLeft = left[j];
                        left[j] = 0;
                        break;
                    }
                }
            } else {
                rsl[i] = minRight;
                for (int j = 0; j < right.length; j++) {
                    if (right[j] != 0) {
                        minRight = right[j];
                        right[j] = 0;
                        break;
                    }
                }
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 3, 6};
        int[] arr2 = {2, 4, 8};
        System.out.println(Arrays.toString(Merge.merge(arr1, arr2)));
    }
}


