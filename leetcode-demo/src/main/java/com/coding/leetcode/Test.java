package com.coding.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** @Author shu wj @Date 2020/6/14 22:02 @Description */
public class Test {
  public static class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  };

  // Encodes a tree to a single string.
  public String serialize(Node root) {
    if (null == root) return "";
    StringBuilder sb = new StringBuilder();

    sb.append(root.val);
    if (null == root.children || root.children.isEmpty()) {
      return sb.toString();
    }

    sb.append("[");
    for (Node node : root.children) {
      sb.append(serialize(node)).append("#");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");

    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public Node deserialize(String data) {
    if (null == data || Objects.equals("", data)) {
      return null;
    }

    int firstBracket = data.indexOf('[');

    if (-1 == firstBracket) {
      return new Node(Integer.parseInt(data), new ArrayList<>());
    }

    Node node = new Node(Integer.parseInt(data.substring(0, firstBracket)), new ArrayList<>());
    List<String> children = parseChildren(data, firstBracket + 1, data.length() - 1);

    for (String child : children) {
      node.children.add(deserialize(child));
    }

    return node;
  }

  private List<String> parseChildren(String data, int start, int end) {
    List<String> children = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    // 包含 start, end
    int leftBracket = 0;
    for (int i = start; i < end; i++) {
      char ch = data.charAt(i);
      if ('[' == ch) {
        leftBracket++;
      } else if (']' == ch) {
        leftBracket--;
      }

      if ('#' == ch && leftBracket == 0) {
        children.add(sb.toString());
        sb.setLength(0);
        continue;
      }

      sb.append(ch);
    }

    children.add(sb.toString());
    return children;
  }


  public static void main(String[] args) {
    Node root = new Node(1, new ArrayList<>());
    Node node3 = new Node(3, new ArrayList<>());
    node3.children.add(new Node(5, new ArrayList<>()));
    node3.children.add(new Node(6, new ArrayList<>()));
    root.children.add(node3);
    root.children.add(new Node(2, new ArrayList<>()));
    root.children.add(new Node(4, new ArrayList<>()));

    Test test = new Test();
    String ser = test.serialize(root);
    System.out.println(ser);

    System.out.println(test.serialize(test.deserialize(ser)));
  }
}
