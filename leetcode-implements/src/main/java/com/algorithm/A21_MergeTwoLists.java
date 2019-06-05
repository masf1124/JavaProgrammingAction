package com.algorithm;

import com.algorithm.util.BuildListNode;
import com.algorithm.util.ListNode;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-04-21
 */
public class A21_MergeTwoLists {

    public static ListNode mergeTwoLists(ListNode l1,ListNode l2){
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
        int[] nums1 = {1,2,4};
        ListNode l1 = BuildListNode.buildListNode(nums1);

        int[] nums2 = {1,3,4};
        ListNode l2 = BuildListNode.buildListNode(nums2);

        ListNode l3 = mergeTwoLists(l1,l2);
        ListNode curNode = l3;
        while (curNode!=null){
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
    }
}
