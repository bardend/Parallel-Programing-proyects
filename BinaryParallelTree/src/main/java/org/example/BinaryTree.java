package org.example;

public abstract class BinaryTree {
     protected Node root;
     public BinaryTree() {
        this.root = null;
     }

     abstract void add(Integer value);
     abstract boolean remove(Integer value);
     abstract boolean contains(Integer value);

}
