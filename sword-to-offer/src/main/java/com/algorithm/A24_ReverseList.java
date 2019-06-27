package com.algorithm;

import com.algorithm.node.ListNode;

/**
 * Created by seu627 on 2019/6/26
 * 题目：定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的
 * 头结点
 */
public class A24_ReverseList {

    public ListNode reverseList(ListNode head){
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode = head.next;

        while(nextNode != null){
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
            nextNode = nextNode.next;
        }
        curNode.next = preNode;
        return curNode;
    }

    public static void main(String[] args) {

    }
}
