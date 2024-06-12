package org.example;


public final class SequentialTree extends BinaryTree {
    public SequentialTree() {
        super();
    }

    public void add(final Integer e) {
        Node curr = this.root;
        if(curr == null) {
            this.root = new Node(e);
        }
        else {
            while(true) {
                if (curr.object.compareTo(e) > 0) {
                    if (curr.left == null) {
                        curr.left = new Node(e);
                        break;
                    } else curr = curr.left;
                } else if (curr.object.compareTo(e) < 0) {
                    if (curr.right == null) {
                        curr.right = new Node(e);
                        break;
                    } else curr = curr.right;
                } else break;
            }
        }
    }

    public boolean contains(final Integer e) {
        Node curr = this.root;
        if(curr == null) {return false;}
        while(true) {
            if (curr.object.compareTo(e) > 0) {
               if(curr.left == null) {return false;}
               curr = curr.left;
            }
            else if (curr.object.compareTo(e) < 0) {
                if(curr.right == null) {return false;}
                curr = curr.right;
            }
            else return true;
        }
    }

    public boolean remove(final Integer e) {
        Node curr = this.root;
        if(curr == null) {return false;}

        while(true) {
            if(curr.object.compareTo(e) > 0) {
                if(curr.left == null ) {return false;}
                if(curr.left.object.compareTo(e) == 0) {

                    if(curr.left.right == null && curr.left.left == null) { //leaft
                        curr.left = null;
                    }
                    else if(curr.left.right != null && curr.left.left != null) { //two different
                        //elimniar
                        Node cheap = find_mini(curr.left.right);

                        curr.left = cheap;
                        cheap.right = curr.left.right;
                        if(cheap == cheap.right) cheap.right = null;
                        cheap.left = curr.left.left;

                    }
                    else {
                        if(curr.left.right != null) curr.left = curr.left.right;
                        if(curr.left.left != null) curr.left = curr.left.left;
                    }
                    return true;
                }
                else curr = curr.left;
            }
            else if(curr.object.compareTo(e) < 0) {
                if(curr.right == null) {return false;}
                if(curr.right.object.compareTo(e) == 0) {
                    if(curr.right.left == null && curr.right.right == null) {
                        curr.right = null;
                    }
                    else if(curr.right.right != null && curr.right.left != null) {
                        Node cheap = find_mini(curr.right.right);
                        curr.right = cheap;
                        cheap.right = curr.right.right;
                        if(cheap == cheap.right) cheap.right = null;
                        cheap.left = curr.right.left;
                    }
                    else {
                        if(curr.right.left != null) curr.right = curr.right.left;
                        if(curr.right.right != null) curr.right = curr.right.right;
                    }
                    return true;
                }
                else curr = curr.right;
            }
        }
    }
    public static Node find_mini( Node root) {
        //retorno el root y de pasadita lo elimino

        if(root.left == null)  return root;

        while(root.left.left != null) {
            root = root.left;
        }
        Node ret = root.left;
        root.left = null;
        return ret;
    }
}