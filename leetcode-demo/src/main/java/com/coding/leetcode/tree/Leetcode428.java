package com.coding.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/** @Author shu wj @Date 2020/6/7 14:39 @Description */
public class Leetcode428 {
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
    // 边界
    if (root == null) return "";

    // 将parent加入
    StringBuilder ans = new StringBuilder();

    ans.append(root.val);
    if (root.children.isEmpty()) {
      return ans.toString();
    }

    // 将children都加入，children的两侧用[]包裹
    ans.append("[");
    for (Node c : root.children) {
      // 重新利用serialize()函数的含义，把每一个child Node都序列化即可
      ans.append(serialize(c));
      ans.append(",");
    }
    ans.deleteCharAt(ans.length() - 1);
    ans.append("]");

    return ans.toString();
  }

  // Decodes your encoded data to tree.
  public Node deserialize(String data) {
    // 边界
    if (data.isEmpty()) return null;

    // 找到parent
    int idx = data.indexOf("[");
    // 如果没有children则返回
    if (idx == -1) return new Node(Integer.parseInt(data), new ArrayList<>());

    // 如果有children
    String val = data.substring(0, data.indexOf("["));
    Node root = new Node(Integer.parseInt(val), new ArrayList<>());
    // 解析紧跟着parent的[]中的字符串，将其分为一个个代表child的字符串
    List<String> cData = parse(data.substring(idx + 1, data.length() - 1));
    for (String c : cData) {
      // 重新利用deserialize()函数的含义，把每一个child的字符串都反序列化再加入parent的children中即可
      root.children.add(deserialize(c));
    }
    return root;
  }

  List<String> parse(String data) {
    List<String> ans = new ArrayList<>();
    int leftBracket = 0;
    StringBuilder sb = new StringBuilder();
    for (char c : data.toCharArray()) {
      if (c == '[') leftBracket++;
      else if (c == ']') leftBracket--;
      else if (c == ',') {
        if (leftBracket == 0) {
          ans.add(sb.toString());
          sb.setLength(0);
          continue;
        }
      }
      sb.append(c);
    }
    ans.add(sb.toString());
    return ans;
  }


  ////////  解法2，速度是解法1的几倍
  // Encodes a tree to a single string.
  public String serialize_solution2(Node root) {
    StringBuilder sb = new StringBuilder();
    serialize_solution2(root, sb);
    return sb.toString();
  }

  private void serialize_solution2(Node node, StringBuilder sb){
    //System.out.println(node.val);
    if(node == null){
      sb.append('#');
      return;
    }
    sb.append(node.val);
    sb.append(',');
    if(node.children == null){
      sb.append(-1);
      sb.append(',');
    } else {
      sb.append(node.children.size());
      sb.append(',');
      for(Node child: node.children){
        serialize_solution2(child, sb);
      }
    }
  }

  private int index;
  // Decodes your encoded data to tree.
  public Node deserialize_solution2(String data) {
    index = 0;
    return deserializeHelper(data);
  }

  private Node deserializeHelper(String data){
    if(data.charAt(index) == '#'){
      index++;
      return null;
    }
    Node node = new Node(getVal(data));
    int childNum = getVal(data);
    if(childNum != -1){
      List<Node> children = new ArrayList<>();
      for(int i = 0 ; i < childNum; i++){
        children.add(deserializeHelper(data));
      }
      node.children = children;
    }
    return node;
  }

  private int getVal(String data){
    int val = 0;
    while(data.charAt(index) != ','){
      val *= 10;
      val += data.charAt(index++) - '0';
    }
    index++;
    return val;
  }


  public static void main(String[] args) {
    Node root = new Node(1, new ArrayList<>());
    Node node3 = new Node(3, new ArrayList<>());
    node3.children.add(new Node(5, new ArrayList<>()));
    node3.children.add(new Node(6, new ArrayList<>()));
    root.children.add(node3);
    root.children.add(new Node(2, new ArrayList<>()));
    root.children.add(new Node(4, new ArrayList<>()));


    Leetcode428 leetcode428 = new Leetcode428();
    String s = leetcode428.serialize(root);
    System.out.println(s);
    Node newRoot = leetcode428.deserialize(s);
    System.out.println(leetcode428.serialize(newRoot));

    System.out.println(leetcode428.serialize_solution2(root));
    System.out.println(leetcode428.serialize_solution2(leetcode428.deserialize_solution2(leetcode428.serialize_solution2(root))));
  }
}
