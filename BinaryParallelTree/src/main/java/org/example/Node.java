package org.example;

public final class Node {
    public final Integer object;

    public Node right;
    public Node left;

    Node(final Integer object) {
        this.object = object;
        this.right = null;
        this.left = null;
    }
}
