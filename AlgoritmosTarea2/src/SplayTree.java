
public class SplayTree {
	Node root;
	
    private class Node {
        int key;
        
        Node left, right;

        public Node(int key){
            this.key = key;
            right = left = null;
        }
    }


    public boolean search(int key) {
        root = splay(root, key);
        return key == root.key;
    }
    
    public void insert(int key){
        if (root == null) {
            root = new Node(key);
            return;
        }
        
        root = splay(root,key);
        
        if(key < root.key){
            Node n = new Node(key);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }else if(key > root.key){
            Node n = new Node(key);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }
        else
            root.key = key;
    }
    
    private Node splay(Node h, int key) {
        if (h == null)
        	return null;

        if (key < h.key){
            if (h.left == null)
                return h;
            
            if(key < h.left.key){
                h.left.left = splay(h.left.left, key);
                h = rotateLeft(h);
            }
            else if(key > h.left.key){
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null)
                    h.left = rotateRight(h.left);
            }
            
            if(h.left == null)
            	return h;
            else
            	return rotateLeft(h);
        }else if(key > h.key){ 
            if(h.right == null)
                return h;

            if(key < h.right.key){
                h.right.left  = splay(h.right.left, key);
                if (h.right.left != null)
                    h.right = rotateLeft(h.right);
            }
            else if(key > h.right.key){
                h.right.right = splay(h.right.right, key);
                h = rotateRight(h);
            }
            
            if(h.right == null)
            	return h;
            else
            	return rotateRight(h);
        }

        else
        	return h;
    }
    
    private Node rotateLeft(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }
}
