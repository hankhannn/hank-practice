package com.hank.practice.algorithm;

import com.hank.practice.bo.LinkedNode;

/**
 * @author ：hank.han
 * @date ：Created in 2023/1/10 14:33
 * @description：
 * @version: 1.0
 */
public class MargeTwoSortedLinkedNode {

    public LinkedNode<Integer> mergeTwoList(LinkedNode<Integer> l1, LinkedNode<Integer> l2) {
        LinkedNode<Integer> head = new LinkedNode<>(null);
        LinkedNode<Integer> next = head;
        while (null != l1 && null != l1.value && null != l2 && null != l2.value) {
            if (l1.value < l2.value) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head.next.next = null;
            head = head.next;
        }
        if (null != l1) {
            head.next = l1;
        } else {
            head.next = l2;
        }
        return next.next;
    }

    public static void main(String[] args) {
        LinkedNode<Integer> l1 = new LinkedNode<>(1);
        LinkedNode<Integer> l2 = new LinkedNode<>(2);
        LinkedNode<Integer> l3 = new LinkedNode<>(4);
        LinkedNode<Integer> l4 = new LinkedNode<>(9);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        LinkedNode<Integer> l5 = new LinkedNode<>(2);
        LinkedNode<Integer> l6 = new LinkedNode<>(5);
        LinkedNode<Integer> l7 = new LinkedNode<>(10);
        l5.next = l6;
        l6.next = l7;
        LinkedNode<Integer> linkedNode = new MargeTwoSortedLinkedNode().mergeTwoList(l1, l5);
        while (linkedNode != null) {
            System.out.print(linkedNode.value + ",");
            linkedNode = linkedNode.next;
        }
    }
}
