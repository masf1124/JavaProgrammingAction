package com.algorithm;

import com.algorithm.util.BuildListNode;
import com.algorithm.util.ListNode;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-05-20
 */
public class A23_MergeKLists {

    /**
     * 运行时间长，占用内存多 ，有待优化，尝试以二分的方式做
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0){
            return null;
        }

        if(lists.length==1){
            return lists[0];
        }
        if(lists.length==2){
            return mergeTwoLists(lists[0],lists[1]);
        }
        ListNode tempNode = null;
        for(int i=0;i<lists.length;i++){
            tempNode = mergeTwoLists(tempNode,lists[i]);
        }
        return tempNode;
    }

    private static ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }

        ListNode curNode1 = l1;
        ListNode curNode2 = l2;

        ListNode head ;
        if(curNode1.val<curNode2.val){
            head = curNode1;
            curNode1 = curNode1.next;
        }else{
            head = curNode2;
            curNode2 = curNode2.next;
        }
        ListNode curNode = head;
        while(curNode1!=null && curNode2!=null){
            if(curNode1.val<curNode2.val){
                curNode.next = curNode1;
                curNode1 = curNode1.next;
                curNode = curNode.next;
            }else{
                curNode.next = curNode2;
                curNode2 = curNode2.next;
                curNode = curNode.next;
            }
        }

        while (curNode1!=null){
            curNode.next = curNode1;
            curNode1 = curNode1.next;
            curNode = curNode.next;
        }

        while(curNode2!=null){
            curNode.next = curNode2;
            curNode2 = curNode2.next;
            curNode = curNode.next;
        }

        return head;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,4,5};
        ListNode l1 = BuildListNode.buildListNode(nums1);

        int[] nums2 = {1,3,4};
        ListNode l2 = BuildListNode.buildListNode(nums2);

        int[] nums3 = {2,6};
        ListNode l3 = BuildListNode.buildListNode(nums3);

        ListNode[] lists = new ListNode[3];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;
        ListNode l4 = mergeKLists(lists);
        ListNode curNode = l4;
        while (curNode!=null){
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
    }
}
