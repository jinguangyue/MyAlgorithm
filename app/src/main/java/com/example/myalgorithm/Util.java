package com.example.myalgorithm;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Util {

    /**
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     *
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     *
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     *
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
     * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
     *
     * 链接：https://leetcode.cn/problems/merge-sorted-array
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int length = nums1.length;
        for (int i=0; i<n; i++) {
            nums1[length-n+i] = nums2[i];
        }

        Arrays.sort(nums1);

    }

    /**
     * 删除数组重复项
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int a = 1,b=1;
        while (a < nums.length) {
            if (nums[a-1] != nums[a]) {
                nums[b] = nums[a];
                b++;

            }

            a++;
        }

        return b;
    }

    /**
     * 数组重复元素
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int nums[]) {
//        Arrays.sort(nums);
//        for (int i=0; i<nums.length - 1; i++) {
//            if (nums[i] == nums[i+1]) {
//                return true;
//            }
//        }
//
//        return false;

        Set<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取股票最大利润 12345
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;

        for (int i=1; i<prices.length; i++) {
            if (prices[i-1] < prices[i]) {
                result += prices[i] - prices[i-1];
            }
        }
        return result;
    }

    public int singleNumber(int[] nums) {
//        Arrays.sort(nums);
//        int length = nums.length;
//        if (length > 1) {
//            if (nums[0] != nums[1]) {
//                return nums[0];
//            }
//
//            if (nums[length - 2] != nums[length - 1]) {
//                return nums[length - 1];
//            }
//        } else {
//            return nums[0];
//        }
//        for (int i=2; i<nums.length; i++) {
//            if (nums[i-2] != nums[i-1] && nums[i-1] != nums[i]) {
//                return nums[i-1];
//            }
//        }
        int length = nums.length;
        int result = nums[0];
        for (int i=1; i<length; i++) {
            result ^= nums[i];
        }

        return result;
    }

    /**
     * 两个数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            intersect(nums2, nums1);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }

        int[] intersects = new int[nums1.length];
        int index = 0;

        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersects[index] = num;
                index++;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }

        return Arrays.copyOfRange(intersects, 0, index);
    }

    /**
     * 旋转数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        // 1234567 7654321 3  (i + k) % n
//        k %= nums.length;
//        reverse(nums, 0, nums.length - 1);
//        reverse(nums, 0, k - 1);
//        reverse(nums, k, nums.length - 1);


        int[] newArry = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            newArry[(i+k)%nums.length] = nums[i];
        }

        System.arraycopy(newArry, 0, nums, 0, nums.length);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 数组加一算法
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        for (int i=digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 移动0的元素到末尾 [0, 1, 0, 3, 12]
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }

        int i = 0;
        int j = 0;

        while (i < n) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
            i++;
        }
    }


    /**
     * [3, 2, 4] target=6
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(nums[i]) && i != map.get(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }

        return null;
    }


    /**
     * 数独有效性验证
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
//        int[][] rows = new int[9][9];
//        int[][] columes = new int[9][9];
//        int[][][] subboxs = new int[3][3][9];
//        for (int i=0; i<9; i++) {
//            for (int j=0; j<9; j++) {
//                char c = board[i][j];
//                if (c != '.') {
//                    int index = c - '0' - 1; // 表示数组下标
//                    rows[i][index]++;
//                    columes[j][index]++;
//                    subboxs[i/3][j/3][index]++;
//                    if (rows[i][index] > 1 || columes[j][index] > 1 || subboxs[i/3][j/3][index] > 1) {
//                        return false;
//                    }
//                }
//            }
//        }
//
//        return true;

        int[] rows = new int[10];
        int[] columes = new int[10];
        int[] boxs = new int[10];
        for (int i=0; i<9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1; // 表示数组下标
                    // 0 3 6   0 1 2
                    int idx = i/3 * 3 + j/3;
                    if ((rows[i] >> index & 1) == 1 || (columes[j] >> index & 1) == 1 || (boxs[idx] >> index & 1) == 1 ) {
                        return false;
                    }

                    rows[i] |= (1 << index);
                    columes[j] |= (1 << index);
                    boxs[idx] |= (1 << index);

                }
            }
        }

        return true;
    }

    /**
     * 矩阵旋转 90度 关键点在于找到规律
     * @param matrix
     */
    public void rotate(int[][] matrix) {
//        int n = matrix.length;
//        int[][] matrixNew = new int[n][n];
//        for (int i=0; i<n; i++) {
//            for (int j=0; j<n; j++) {
//                matrixNew[j][n - i - 1] = matrix[i][j];
//            }
//        }
//
//        for (int i=0; i<n; i++) {
//            for (int j=0; j<n; j++) {
//                matrix[i][j] = matrixNew[i][j];
//            }
//        }

//        int n = matrix.length;
//        for (int i=0; i<n/2; i++) {
//            for (int j=0; j<(n+1)/2; j++) {
//                int temp = matrix[i][j];
//                matrix[i][j] = matrix[n - j - 1][i];
//                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
//                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
//                matrix[j][n - i - 1] = temp;
//            }
//        }

        /**
         * 先水平翻转一发
         */
        int n = matrix.length;
        for (int i=0; i<n/2; i++) {
            for (int j=0; j<n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 字符串翻转
     * @param s
     */
    private void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 整数翻转
     * @param x
     * @return
     */
    public int reverse(int x) {
//        boolean isFu = false;
//        if (x < 0) {
//            isFu = true;
//            x = Math.abs(x);
//        }
//        String intStr = String.valueOf(x);  // 将 int 转换为字符串
//
//        int[] intArray = new int[intStr.length()];  // 创建数组，大小为字符串的长度
//
//        for (int i = 0; i < intStr.length(); i++) {
//            intArray[i] = Character.getNumericValue(intStr.charAt(i));  // 将字符转换为数字并存储在数组中
//        }
//
//        int start = 0;
//        int end = intArray.length - 1;
//        while (start < end) {
//            int temp = intArray[start];
//            intArray[start] = intArray[end];
//            intArray[end] = temp;
//            start++;
//            end--;
//        }
//
//        String result = "";
//
//        for (int i=0; i<intArray.length; i++) {
//            result += intArray[i];
//        }
//        int resultInt = 0;
//        try {
//            resultInt = Integer.parseInt(result);
//        } catch (Exception e) {
//            return resultInt;
//        }
//
//        if (isFu) {
//            return - resultInt;
//        }
//
//        return resultInt;


        int result = 0;
        while (x != 0) {
            if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
                return 0;
            }

            int temp = x % 10;
            x = x / 10;
            result = result * 10 + temp;
        }

        return result;
    }
}



















