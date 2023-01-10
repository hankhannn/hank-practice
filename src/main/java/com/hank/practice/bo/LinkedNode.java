package com.hank.practice.bo;

/**
 * @author ：hank.han
 * @date ：Created in 2023/1/10 14:34
 * @description：
 * @version: 1.0
 */

public class LinkedNode<T> {
    public T value;
    public LinkedNode<T> next;

    public LinkedNode(T value) {
        this.value = value;
    }
}
