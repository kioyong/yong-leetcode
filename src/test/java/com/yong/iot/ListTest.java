package com.yong.iot;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@Slf4j
public class ListTest {

    public List getTestData() {
        return Arrays.asList(64, 27, 85, 35, 74, 2, 75, 85, 36, 27, 5, 82);
    }

    /**
     * IntSummaryStatistics 的使用
     * 计算最大值、最小值、平均值、数量、总和
     */
    @Test
    public void test1() {
        List<Integer> list = getTestData();
        IntSummaryStatistics collect = list.stream().collect(Collectors.summarizingInt(x -> x));
        log.debug("sum = {}", collect.getSum());
        log.debug("count = {}", collect.getCount());
        log.debug("min = {}", collect.getMin());
        log.debug("max = {}", collect.getMax());
        log.debug("average = {}", collect.getAverage());
        assertEquals(85, list.stream().collect(Collectors.summarizingInt(a -> a)).getMax());
    }

    /**
     * filter 和 sort 的使用
     **/
    @Test
    public void test2() {
        List<Integer> list = getTestData();
        List<Integer> result = list.stream().filter(x -> x < 50).sorted().collect(Collectors.toList());
        log.debug("result = {}", result);
        assertEquals(list.stream().collect(Collectors.summarizingInt(x -> x)).getMin(), result.get(0).intValue());
        result = list.stream().filter(x -> x < 50).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        log.debug("result = {}", result);
        assertEquals(list.stream().collect(Collectors.summarizingInt(x -> x)).getMin(), result.get(result.size() - 1).intValue());

        result = list.stream().map(x -> x + 1).collect(Collectors.toList());
        log.debug("result = {}", result);
    }

    @Test
    public void test3() {
        List<Integer> list = getTestData();
//        list.sort(Comparator.reverseOrder());
        list.stream().sorted((t1, t2) -> -1).forEach(System.out::println);
    }

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;

    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        int level = 0;
        levelOrder(res, root, level);
        return res;
    }

    @Test
    public void test5() {
        TreeNode r1 = null;
        TreeNode r2 = new TreeNode(3);
        TreeNode r3 = null;
        TreeNode r4 = new TreeNode(3);

        TreeNode m1 = new TreeNode();
//        m1.setLeft(r1);
//        m1.setRight(r2);

        TreeNode m2 = new TreeNode(-3);
//        m2.setLeft(r3);
//        m2.setRight(r4);

        TreeNode t1 = new TreeNode(-2);
        t1.setLeft(m1);
        t1.setRight(m2);
        boolean symmetric = hasPathSum(t1,-5);
        log.debug("result = {}", symmetric);

    }

    public void levelOrder(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) return;
        if (list.size() > level) {
            list.get(level).add(root.val);
        } else {
            List<Integer> sub = new ArrayList<>();
            sub.add(root.val);
            list.add(sub);
        }
        levelOrder(list, root.left, level + 1);
        levelOrder(list, root.right, level + 1);
    }

    @Data
    @NoArgsConstructor
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;


        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        int level = 0;
        answer = 0;
        maxDepth(root, level);
        return answer;
    }

    static int answer = 0;

    public void maxDepth(TreeNode root, int depth) {
        if (root != null) {
            depth++;
            if (root.left == null && root.right == null) {
                answer = Math.max(answer, depth);
            } else {
                maxDepth(root.left, depth);
                maxDepth(root.right, depth);
            }
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (right == null || left == null || (right.val != left.val)) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        int res = 0;
        return hasPathSum(root, res, sum);
    }

    public boolean hasPathSum(TreeNode root, int res, int sum) {

        if (root == null) return false;
        res += root.val;
        if (root.left == null && root.right == null && res == sum) return true;
        return hasPathSum(root.left, res, sum) || hasPathSum(root.right, res, sum);
    }


}
