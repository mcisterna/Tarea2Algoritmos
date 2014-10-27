
public abstract class IAVLNode {
	
	int key, height;
	IAVLNode left, right;
	
	public abstract int getRightHeight();
	public abstract int getLeftHeight();
	public abstract int getHeight();

}
