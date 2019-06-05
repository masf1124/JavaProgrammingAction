package com.algorithm.util;

/**
 * @program: com.algorithm.util
 * @author: mashifei
 * @create: 2019-06-04-20
 */
public class BuildListNode {
    public static ListNode buildListNode(int[] nums){
        if(null==nums || nums.length==0){
            return null;
        }
        ListNode head = new ListNode(nums[0]);
        ListNode node = head;
        for(int i=1;i<nums.length;i++){
            node.next = new ListNode(nums[i]);
            node = node.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ListNode head = buildListNode(nums);
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
