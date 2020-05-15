package com.coding.leetcode.list;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Author shu wj
 * @Date 2020/4/5 00:35
 * @Description
 */
public class Leetcode146 {

  public static void main(String[] args) {
    LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // 返回  1
    cache.put(3, 3);    // 该操作会使得密钥 2 作废
    cache.get(2);       // 返回 -1 (未找到)
    cache.put(4, 4);    // 该操作会使得密钥 1 作废
    cache.get(1);       // 返回 -1 (未找到)
    cache.get(3);       // 返回  3
    cache.get(4);       // 返回  4

  }
}

class LRUCache {

  private Map<Integer, DLinkedNode> cache = new HashMap<>();
  private int capacity;
  private DLinkedNode head, tail;

  // 哈希 + 双向链表
  class DLinkedNode {
    int key;
    int val;
    DLinkedNode prev;
    DLinkedNode next;
  }

  private void addNode(DLinkedNode node) {
    node.prev = head;
    node.next = head.next;

    head.next.prev = node;
    head.next = node;
  }

  private void removeNode(DLinkedNode node) {
    node.next.prev = node.prev;
    node.prev.next = node.next;
  }

  private void moveToHead(DLinkedNode node) {
    removeNode(node);
    addNode(node);
  }

  private DLinkedNode popTail() {
    if (tail.prev.prev == null) {
      return null;
    }
    DLinkedNode node = tail.prev;
    tail.prev.prev.next = tail;
    removeNode(node);
    return node;
  }


  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.head = new DLinkedNode();
    this.tail = new DLinkedNode();

    this.head.next = this.tail;
    this.tail.prev = this.head;
  }

  public int get(int key) {
    DLinkedNode node = this.cache.get(key);
    if (node == null) {
      return -1;
    }
    moveToHead(node);
    return node.val;
  }

  public void put(int key, int value) {
    DLinkedNode old = this.cache.get(key);
    if (null != old) {
      old.val = value;
      moveToHead(old);
    } else {
      old = new DLinkedNode();
      old.key = key;
      old.val = value;

      this.cache.put(key, old);
      addNode(old);

      if (this.cache.size() > this.capacity) {
        DLinkedNode rmNode = popTail();
        if (null != rmNode) {
          this.cache.remove(rmNode.key);
        }
      }
    }
  }
}

