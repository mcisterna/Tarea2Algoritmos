
public class AVLNode extends IAVLNode{
	
	public AVLNode(int k){
		key = k;
		height = 1;
		left = new NullAVLNode();
		right = new NullAVLNode();
	}

	@Override
	public int getRightHeight(){
		return right.getHeight();
	}

	@Override
	public int getLeftHeight(){
		return left.getHeight();
	}

	@Override
	public int getHeight(){
		return height;
	}

}
