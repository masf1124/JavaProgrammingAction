package com.algorithm;

import com.algorithm.util.BuildListNode;
import com.algorithm.util.ListNode;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-05-20
 */
public class A24_SwapPairs {

    //使用三个指针定位要交换的部分preNode、curNode、nextNode
    public static ListNode swapPairs(ListNode head) {
        if(null==head){
            return null;
        }

        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = curNode.next;
        while(curNode!=null&&nextNode!=null){
            if(preNode!=null){
                curNode.next = nextNode.next;
                nextNode.next = curNode;
                preNode.next = nextNode;
                preNode = curNode;
                if(curNode.next!=null && curNode.next.next!=null){
                    curNode = curNode.next;
                    nextNode = curNode.next;
                }else{
                    break;
                }
            }else{
                curNode.next = nextNode.next;
                nextNode.next = curNode;
                head = nextNode;
                preNode = curNode;
                if(curNode.next!=null && curNode.next.next!=null){
                    curNode = curNode.next;
                    nextNode = curNode.next;
                }else{
                    break;
                }
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        ListNode l1 = BuildListNode.buildListNode(nums);


        ListNode curNode = swapPairs(l1);
        while (curNode!=null){
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
    }
}
