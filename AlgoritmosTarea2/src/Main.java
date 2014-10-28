import java.util.Random;


public class Main {
	
	public static void main(String[] args){
		int U = 1024*1024*4 - 1;
		int Kn[], seq1[], seq2[], seq3[];
		double freq1[], freq2[], freq3[];
		Random r = new Random();
		
		ABBTree abbtree = new ABBTree();
		AVLTree avltree = new AVLTree();
		vEBTree vebtree = new vEBTree(U);
		SplayTree splaytree = new SplayTree();
		
		for(int n=1024;n <= Math.pow(2,20); n*=2){
			Kn = new int[n];
			seq1 = new int[100*n];
			seq2 = new int[100*n];
			seq3 = new int[100*n];
			freq1 = new double[100*n];
			freq2 = new double[100*n];
			freq3 = new double[100*n];
			for (int i = 0; i < n; i++) {
				Kn[i] = r.nextInt(U) + 1;
			}
			
			/* secuencia 1*/
			for (int i = 0; i < 100*n; i++) {
				seq1[i] = Kn[r.nextInt(n)];
				freq1[i] = 1.0/(100*n);
			}
			
			/* secuencia 2 */
			int i = 0;
			int k = 0;
			while(i <= 100*n){
				double a = 1.2;
				double c = calcC2(a,100*n);
				double freq_k = c/Math.pow(a,k+1); /* frecuencia de Kn[k] */
				int cant_k = (int)(100*n*freq_k); /* cantidad de Kn[k] */
				int j = i;
				while(j < 100*n && j < i+cant_k){
					seq2[j] = Kn[k];
					freq2[j] = freq_k;
					j++;
				}
				k++;
				i += cant_k;
			}
			
			/* secuencia 3 */
			i = 0;
			k = 0;
			while(i <= 100*n){
				double a = 1.2;
				double c = calcC3(a,100*n);
				double freq_k = c/Math.pow(k+1,a); /* frecuencia de Kn[k] */
				int cant_k = (int)(100*n*freq_k); /* cantidad de Kn[k] */
				int j = i;
				while(j < 100*n && j < i+cant_k){
					seq3[j] = Kn[k];
					freq3[j] = freq_k;
					j++;
				}
				k++;
				i += cant_k;
			}
			
			/* resultados: se muestran en cada arreglo en el siguiente orden:
			 * ABB, AVL, vEB, SplayTree, Arbol Optimo */
			long construction_seq1[] = new long[5];
			long construction_seq2[] = new long[5];
			long construction_seq3[] = new long[5];
			
			long search_seq1[] = new long[5];
			long search_seq2[] = new long[5];
			long search_seq3[] = new long[5];
			
			StopWatch watches[] = new StopWatch[5];
			
			for (int j = 0; j < 5; j++)
				watches[j] = new StopWatch();
			
			for (int l = 0; l < 100*n; l++) {
				
				/*construccion abb seq1*/
				watches[0].start();
				abbtree.insert(seq1[n]);
				watches[0].pause();
				
				/*construccion avl seq1*/
				watches[1].start();
				avltree.insert(seq1[n]);
				watches[1].pause();
				
				/*construccion vEB seq1*/
				watches[2].start();
				vebtree.insert(seq1[n]);
				watches[2].pause();
				
				/*construccion splaytree seq1*/
				watches[3].start();
				splaytree.insert(seq1[n]);
				watches[3].pause();
			}
			
			for (int j = 0; j < 5; j++){
				construction_seq1[j] = watches[j].getElapsedTime();
				watches[j].reset();
			}
			
			for (int l = 0; l < 100*n; l++) {
				
				/*construccion abb seq2*/
				watches[0].start();
				abbtree.insert(seq2[n]);
				watches[0].pause();
				
				/*construccion avl seq2*/
				watches[1].start();
				avltree.insert(seq2[n]);
				watches[1].pause();
				
				/*construccion vEB seq2*/
				watches[2].start();
				vebtree.insert(seq2[n]);
				watches[2].pause();
				
				/*construccion splaytree seq2*/
				watches[3].start();
				splaytree.insert(seq2[n]);
				watches[3].pause();
			}
			
			for (int j = 0; j < 5; j++){
				construction_seq2[j] = watches[j].getElapsedTime();
				watches[j].reset();
			}
			
			for (int l = 0; l < 100*n; l++) {
				
				/*construccion abb seq3*/
				watches[0].start();
				abbtree.insert(seq3[n]);
				watches[0].pause();
				
				/*construccion avl seq3*/
				watches[1].start();
				avltree.insert(seq3[n]);
				watches[1].pause();
				
				/*construccion vEB seq3*/
				watches[2].start();
				vebtree.insert(seq3[n]);
				watches[2].pause();
				
				/*construccion splaytree seq3*/
				watches[3].start();
				splaytree.insert(seq3[n]);
				watches[3].pause();
			}
			
			for (int j = 0; j < 5; j++){
				construction_seq3[j] = watches[j].getElapsedTime();
				watches[j].reset();
			}
			
			
			for (int l = 0; l < 100*n; l++) {
				
				/*busqueda abb seq1*/
				watches[0].start();
				abbtree.search(seq1[n]);
				watches[0].pause();
				
				/*busqueda avl seq1*/
				watches[1].start();
				avltree.search(seq1[n]);
				watches[1].pause();
				
				/*busqueda vEB seq1*/
				watches[2].start();
				vebtree.findNext(seq1[n]);
				watches[2].pause();
				
				/*busqueda splaytree seq1*/
				watches[3].start();
				splaytree.search(seq1[n]);
				watches[3].pause();
			}
			
			for (int j = 0; j < 5; j++){
				search_seq1[j] = watches[j].getElapsedTime();
				watches[j].reset();
			}
			
			for (int l = 0; l < 100*n; l++) {
				
				/*busqueda abb seq2*/
				watches[0].start();
				abbtree.search(seq2[n]);
				watches[0].pause();
				
				/*busqueda avl seq2*/
				watches[1].start();
				avltree.search(seq2[n]);
				watches[1].pause();
				
				/*busqueda vEB seq2*/
				watches[2].start();
				vebtree.findNext(seq2[n]);
				watches[2].pause();
				
				/*busqueda splaytree seq2*/
				watches[3].start();
				splaytree.search(seq2[n]);
				watches[3].pause();
			}
			
			for (int j = 0; j < 5; j++){
				search_seq2[j] = watches[j].getElapsedTime();
				watches[j].reset();
			}
			
			for (int l = 0; l < 100*n; l++) {
				
				/*busqueda abb seq3*/
				watches[0].start();
				abbtree.search(seq3[n]);
				watches[0].pause();
				
				/*busqueda avl seq3*/
				watches[1].start();
				avltree.search(seq3[n]);
				watches[1].pause();
				
				/*busqueda vEB seq3*/
				watches[2].start();
				vebtree.findNext(seq3[n]);
				watches[2].pause();
				
				/*busqueda splaytree seq3*/
				watches[3].start();
				splaytree.search(seq3[n]);
				watches[3].pause();
			}
			
			for (int j = 0; j < 5; j++){
				search_seq2[j] = watches[j].getElapsedTime();
				watches[j].reset();
			}
		}
	}
	
	static public double calcC2(double a, int n){
		double sum = 0.0;
		for (int i = 1; i <= n; i++){
			sum += 1/Math.pow(i,a);
		}
		return 1.0/sum;
	}
	
	static public double calcC3(double a, int n){
		double sum = 0.0;
		for (int i = 1; i <= n; i++){
			sum += 1/Math.pow(a,i);
		}
		return 1.0/sum;
	}
}
