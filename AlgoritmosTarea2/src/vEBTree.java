public class vEBTree {
	
	/* Van Emde Boas Tree*/
	vEBTree bottom[];
	vEBTree top;
	int size, min, max, sqrtU;
	
	public vEBTree(int U){
		sqrtU = (int)Math.sqrt(U);
		top = new vEBTree(sqrtU);
		bottom = new vEBTree[sqrtU];
		for (int i=0; i<sqrtU;i++){
			bottom[i] = new vEBTree(sqrtU);
		}
		size = 0;
	}
	
	public void insert(int k){
		if(size == 0){
			min = max = k;
			size = 1;
			return;
		}
		if(k == min){
			int aux = min;
			min = k;
			k = aux;
		}
		int a = k / sqrtU;
		int b = k - a*sqrtU;
		if(bottom[a].size == 0)
			top.insert(a);
		bottom[a].insert(b);
		size++;
		max = k > max ? k : max;
		min = k < min ? k : min;
	}
	
	public int findNext(int k){
		if(k <= min)
			return min;
		int a = k / sqrtU;
		int b = k - a*sqrtU;
		if(bottom[a].size > 0 && bottom[a].max >= b)
			return bottom[a].findNext(b) + a*sqrtU;
		int s = top.findNext(a+1);
		return s*sqrtU + bottom[s].min;
	}
}
