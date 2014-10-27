
public class ABBTree {
	ABBTree right, left;
	int key;
	
	public ABBTree(int k){
		key = k;
		right = left = null;
	}
	
	void insert(int n){
		if(n < key){
			if(left == null)
				left = new ABBTree(n);
			else
				left.insert(n);
		}else if(n > key){
			if(right == null)
				right = new ABBTree(n);
			else
				right.insert(n);
		}else
			key = n;
	}
	
	boolean search(int s){
		if(s < key)
			return left != null ? left.search(s) : false;
		else if(s > key)
			return right != null ? right.search(s) : false;
		else /* s == key */
			return true;
	}
}
