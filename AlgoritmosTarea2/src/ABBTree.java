
public class ABBTree {
	ABBTree right, left;
	int key;
	
	public ABBTree(){
		key = -1;
		right = left = null;
	}
	
	public void insert(int n){
		if(key < 0)
			key = n;
		else{
			if(n < key){
				if(left == null){
					left = new ABBTree();
					left.key = n;
				}else
					left.insert(n);
			}else if(n > key){
				if(right == null){
					right = new ABBTree();
					right.key = n;
				}else
					right.insert(n);
			}else
				key = n;
		}
	}
	
	boolean search(int s){
		if(s < key)
			return left != null ? left.search(s) : false;
		else if(s > key)
			return right != null ? right.search(s) : false;
		else /* s == key */
			return key >= 0;
	}
}
