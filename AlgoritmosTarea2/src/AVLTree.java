
public class AVLTree {
	IAVLNode root;
	
	public AVLTree(){
		root = new NullAVLNode();
	}
	
	void insert(int x){
		root = insert(x,root);
	}
	
	private IAVLNode insert(int x, IAVLNode n){
		if(n instanceof NullAVLNode)
			n = new AVLNode(x);
		if(x < n.key){
			n.left = insert(x,n.left);
			if(n.getLeftHeight() - n.getRightHeight() == 2){
				if(x < n.left.key) /* rotacion simple */
					return rotateLeft(n);
				else{ /* rotacion doble */
					n.left = rotateRight(n.left);
					return rotateLeft(n);
				}
			}
		}else if(x > n.key){
			n.right = insert(x,n.right);
			if(n.getRightHeight() - n.getLeftHeight() == 2){
				if(x < n.right.key) /* rotacion simple */
					return rotateRight(n);
				else{
					n.right = rotateLeft(n.right);
					return rotateRight(n);
				}
			}
		}
		return n;
	}
	
	private IAVLNode rotateLeft(IAVLNode n){
		IAVLNode aux = n.left;
		n.left = aux.right;
		aux.right = n;
		n.height = Math.max(n.getLeftHeight(), n.getRightHeight()) + 1;
		aux.height = Math.max(aux.getLeftHeight(), n.getHeight()) + 1;
		return aux;
	}
	
	private IAVLNode rotateRight(IAVLNode n){
		IAVLNode aux = n.right;
		n.right = aux.left;
		aux.left = n;
		n.height = Math.max(n.getLeftHeight(), n.getRightHeight()) + 1;
		aux.height = Math.max(aux.getRightHeight(), n.getHeight()) + 1;
		return aux;
	}
	
	boolean search(int s){
		return search(s,root);
	}
	
	private boolean search(int s, IAVLNode n){
		if(n instanceof NullAVLNode)
			return false;
		if(s < root.key)
			return search(s,root.left);
		else if(s > root.key)
			return search(s,root.right);
		else /* s == root.key */
			return true;
	}
}
