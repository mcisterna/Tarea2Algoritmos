import java.io.PrintWriter;
import java.util.Random;


public class Main {
	
	public static void main(String[] args) throws Exception{
		//primero inicializar archivos para guardar datos
		PrintWriter w_seq1 = new PrintWriter("seq1_data.txt", "UTF-8");
		PrintWriter w_seq2[] = new PrintWriter[3];
		PrintWriter w_seq3[] = new PrintWriter[3];
		w_seq2[0] = new PrintWriter("seq2_data(a=1.2).txt", "UTF-8");
		w_seq2[1] = new PrintWriter("seq2_data(a=1.5).txt", "UTF-8");
		w_seq2[2] = new PrintWriter("seq2_data(a=2.0).txt", "UTF-8");
		w_seq3[0] = new PrintWriter("seq3_data(a=1.2).txt", "UTF-8");
		w_seq3[1] = new PrintWriter("seq3_data(a=1.5).txt", "UTF-8");
		w_seq3[2] = new PrintWriter("seq3_data(a=2.0).txt", "UTF-8");
		
		w_seq1.println("n \t Arbol \t tiempo promedio construccion \t de \t tiempo promedio busqueda \t de");
		w_seq2[0].println("n \t Arbol \t tiempo promedio construccion \t de \t tiempo promedio busqueda \t de");
		w_seq2[1].println("n \t Arbol \t tiempo promedio construccion \t de \t tiempo promedio busqueda \t de");
		w_seq2[2].println("n \t Arbol \t tiempo promedio construccion \t de \t tiempo promedio busqueda \t de");
		w_seq3[0].println("n \t Arbol \t tiempo promedio construccion \t de \t tiempo promedio busqueda \t de");
		w_seq3[1].println("n \t Arbol \t tiempo promedio construccion \t de \t tiempo promedio busqueda \t de");
		w_seq3[2].println("n \t Arbol \t tiempo promedio construccion \t de \t tiempo promedio busqueda \t de");
		
		int U = 1024*1024*4 - 1;
		int Kn[], seq1[], seq2[][], seq3[][];
		double freq1[],
		freq2[][], freq3[][];
		Random r = new Random();
		
		String names[] = new String[5];
		names[0] = "ABBTree";
		names[1] = "AVLTree";
		names[2] = "vEBTree";
		names[3] = "SplayTree";
		names[4] = "Arbol Optimo";
		ABBTree abbtree = new ABBTree();
		AVLTree avltree = new AVLTree();
		vEBTree vebtree = new vEBTree(U);
		SplayTree splaytree = new SplayTree();
		
		double[] a = new double[3];
		a[0] = 1.2;
		a[1] = 1.5;
		a[2] = 2.0;
		int potencia;
		
		StopWatch watches[] = new StopWatch[5];
		
		for (int j = 0; j < 5; j++)
			watches[j] = new StopWatch();
		
		// resultados: se muestran en cada arreglo en el siguiente orden:
		// ABB, AVL, vEB, SplayTree, Arbol Optimo
		double construction_seq1[][] = new double[2][5];
		double construction_seq2[][][] = new double[2][3][5];
		double construction_seq3[][][] = new double[2][3][5];
					
		double search_seq1[][] = new double[2][5];
		double search_seq2[][][] = new double[2][3][5];
		double search_seq3[][][] = new double[2][3][5];
		
		for(int n=1024;n <= 1024*2; n*=2){
			potencia = (int)(Math.log(n)/Math.log(2));
			Kn = new int[n];
			seq1 = new int[100*n];
			seq2 = new int[3][100*n];
			seq3 = new int[3][100*n];
			//freq1 = new double[100*n];
			//freq2 = new double[3][100*n];
			//freq3 = new double[3][100*n];
			for (int i = 0; i < n; i++) {
				Kn[i] = r.nextInt(U) + 1;
			}
			
			// secuencia 1
			for (int i = 0; i < 100*n; i++) {
				seq1[i] = Kn[r.nextInt(n)];
				//freq1[i] = 1.0/(100*n);
			}
			
			// secuencia 2 
			int i = 0;
			int k = 0;
			for(int index=0;index<3;index++){
				double c = getC2(a[index],n);
				while(i <= 100*n){				
					double freq_k = c/Math.pow(k+1,a[index]); // frecuencia de Kn[k]
					int cant_k = (int)Math.ceil(100*n*freq_k); // cantidad de Kn[k]
					for(int j=i;j<(100*n) && j<(i+cant_k);j++){
						seq2[index][j] = Kn[k];
						//freq2[index][j] = freq_k;
					}
					k++;
					i += cant_k;
				}			
			// 	secuencia 3 
				i = 0;
				k = 0;
				c = getC3(a[index],n);
				while(i <= 100*n){
					double freq_k = c/Math.pow(a[index],k+1); // frecuencia de Kn[k]
					int cant_k = (int)Math.ceil(100*n*freq_k); // cantidad de Kn[k]
					for(int j=i;j<(100*n) && j<(i+cant_k);j++){
						seq3[index][j] = Kn[k];
						//freq3[index][j] = freq_k;
					}
					k++;
					i += cant_k;
				}
			}
			
			for (int l = 0; l < 100*n; l++){
				
				//construccion abb seq1
				watches[0].start();
				abbtree.insert(seq1[l]);
				watches[0].pause();
				
				//construccion avl seq1
				watches[1].start();
				avltree.insert(seq1[l]);
				watches[1].pause();
				
				//construccion vEB seq1
				watches[2].start();
				vebtree.insert(seq1[l]);
				watches[2].pause();
				
				//construccion splaytree seq1
				watches[3].start();
				splaytree.insert(seq1[l]);
				watches[3].pause();
			}
			System.out.println("fin construccion seq1 para n="+potencia);
			
			// obtencion de tiempos de construccion 
			for (int j = 0; j < 5; j++){
				construction_seq1[0][j] = watches[j].getPromedio();
				construction_seq1[1][j] = watches[j].getDE();
				watches[j].reset();
			}
			
			for (int l = 0; l < 100*n; l++) {
				
				//busqueda abb seq1
				watches[0].start();
				abbtree.search(seq1[l]);
				watches[0].pause();
				
				//busqueda avl seq1
				watches[1].start();
				avltree.search(seq1[l]);
				watches[1].pause();
				
				//busqueda vEB seq1
				watches[2].start();
				vebtree.findNext(seq1[l]);
				watches[2].pause();
				
				//busqueda splaytree seq1
				watches[3].start();
				splaytree.search(seq1[l]);
				watches[3].pause();
			}
			System.out.println("fin busqueda seq1 para n="+potencia);
			
			// obtencion de tiempos de busqueda 
			for (int j = 0; j < 5; j++){
				search_seq1[0][j] = watches[j].getPromedio();
				search_seq1[1][j] = watches[j].getDE();
				watches[j].reset();
			}
			// escribir resultados en archivo
			for (int j = 0; j < 5; j++)
				w_seq1.println(
						potencia+" \t "+names[j]+" \t "+
						construction_seq1[0][j]+
						" \t "+construction_seq1[1][j]+
						" \t "+search_seq1[0][j]+
						" \t "+search_seq1[1][j]);
			
			for (int index=0; index<3; index++){
			
			// resetear arboles para nuevas secuencias 
			abbtree = new ABBTree();
			avltree = new AVLTree();
			vebtree = new vEBTree(U);
			splaytree = new SplayTree();
			
			for (int l = 0; l < 100*n; l++) {
				
				//construccion abb seq2
				watches[0].start();
				abbtree.insert(seq2[index][l]);
				watches[0].pause();
				
				//construccion avl seq2
				watches[1].start();
				avltree.insert(seq2[index][l]);
				watches[1].pause();
				
				//construccion vEB seq2
				watches[2].start();
				vebtree.insert(seq2[index][l]);
				watches[2].pause();
				
				//construccion splaytree seq2
				watches[3].start();
				splaytree.insert(seq2[index][l]);
				watches[3].pause();
			}
			System.out.println("fin construccion seq2 para n="+potencia+" y a="+a[index]);
			
			// obtencion de tiempos de construccion 
			for (int j = 0; j < 5; j++){
				construction_seq2[0][index][j] = watches[j].getPromedio();
				construction_seq2[1][index][j] = watches[j].getDE();
				watches[j].reset();
			}
			
			for (int l = 0; l < 100*n; l++) {
				
				//busqueda abb seq2
				watches[0].start();
				abbtree.search(seq2[index][l]);
				watches[0].pause();
				
				//busqueda avl seq2
				watches[1].start();
				avltree.search(seq2[index][l]);
				watches[1].pause();
				
				//busqueda vEB seq2
				watches[2].start();
				vebtree.findNext(seq2[index][l]);
				watches[2].pause();
				
				//busqueda splaytree seq2
				watches[3].start();
				splaytree.search(seq2[index][l]);
				watches[3].pause();
			}
			System.out.println("fin busqueda seq2 para n="+potencia+" y a="+a[index]);
			
			// obtencion de tiempos de busqueda 
			for (int j = 0; j < 5; j++){
				search_seq2[0][index][j] = watches[j].getPromedio();
				search_seq2[1][index][j] = watches[j].getDE();
				watches[j].reset();
			}
			
			// escribir resultados en archivo
			for (int j = 0; j < 5; j++)
				w_seq2[index].println(
						potencia+" \t "+names[j]+" \t "+
						construction_seq2[0][index][j]+
						" \t "+construction_seq2[1][index][j]+
						" \t "+search_seq2[0][index][j]+
						" \t "+search_seq2[1][index][j]);
			
			// resetear arboles para nuevas secuencias 
			abbtree = new ABBTree();
			avltree = new AVLTree();
			vebtree = new vEBTree(U);
			splaytree = new SplayTree();
			
			for (int l = 0; l < 100*n; l++) {
				
				//construccion abb seq3
				watches[0].start();
				abbtree.insert(seq3[index][l]);
				watches[0].pause();
				
				//construccion avl seq3
				watches[1].start();
				avltree.insert(seq3[index][l]);
				watches[1].pause();
				
				//construccion vEB seq3
				watches[2].start();
				vebtree.insert(seq3[index][l]);
				watches[2].pause();
				
				//construccion splaytree seq3
				watches[3].start();
				splaytree.insert(seq3[index][l]);
				watches[3].pause();
			}
			System.out.println("fin construccion seq3 para n="+potencia+" y a="+a[index]);
			
			// obtencion de tiempos de construccion 
			for (int j = 0; j < 5; j++){
				construction_seq3[0][index][j] = watches[j].getPromedio();
				construction_seq3[1][index][j] = watches[j].getDE();
				watches[j].reset();
			}
			
			for (int l = 0; l < 100*n; l++) {
				
				//busqueda abb seq3
				watches[0].start();
				abbtree.search(seq3[index][l]);
				watches[0].pause();
				
				//busqueda avl seq3
				watches[1].start();
				avltree.search(seq3[index][l]);
				watches[1].pause();
				
				//busqueda vEB seq3
				watches[2].start();
				vebtree.findNext(seq3[index][l]);
				watches[2].pause();
				
				//busqueda splaytree seq3
				watches[3].start();
				splaytree.search(seq3[index][l]);
				watches[3].pause();
			}
			System.out.println("fin busqueda seq3 para n="+potencia+" y a="+a[index]);
			
			// obtencion de tiempos de busqueda
			for (int j = 0; j < 5; j++){
				search_seq3[0][index][j] = watches[j].getPromedio();
				search_seq3[1][index][j] = watches[j].getDE();
				watches[j].reset();
			}
			
			// escribir resultados en archivo
			for (int j = 0; j < 5; j++)
				w_seq3[index].println(
						potencia+" \t "+names[j]+" \t "+
						construction_seq3[0][index][j]+
						" \t "+construction_seq3[1][index][j]+
						" \t "+search_seq3[0][index][j]+
						" \t "+search_seq3[1][index][j]);
			}
			
			
			System.out.println(potencia);
		}
		w_seq1.close();
		w_seq2[0].close();
		w_seq2[1].close();
		w_seq2[2].close();
		w_seq3[0].close();
		w_seq3[1].close();
		w_seq3[2].close();
	}
	
	static public double getC2(double a, int n){
		double sum = 0.0;
		for (int i = 1; i <= n; i++){
			sum += 1/Math.pow(i,a);
		}
		return 1.0/sum;
	}
	
	static public double getC3(double a, int n){
		double sum = 0.0;
		for (int i = 1; i <= n; i++){
			sum += 1/Math.pow(a,i);
		}
		return 1.0/sum;
	}
}
