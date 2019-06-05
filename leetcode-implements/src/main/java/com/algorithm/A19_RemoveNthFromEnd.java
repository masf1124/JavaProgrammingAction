package com.algorithm;

import com.algorithm.util.BuildListNode;
import com.algorithm.util.ListNode;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-04-20
 */
public class A19_RemoveNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n){
        int count = 0;
        ListNode node = head;
        while(node!=null){
            node = node.next;
            count++;
        }

        if(n>count || n<1){
            return head;
        }

        node = head;
        int temp = count-n;
        if(temp==0){
            head=head.next;
        }else{
            while((temp-1)!=0){
                node = node.next;
                temp--;
            }
        }
        node.next = node.next.next;

        return head;
    }



    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        ListNode head = BuildListNode.buildListNode(nums);
        System.out.println(removeNthFromEnd(head,2).val);

    }
}
