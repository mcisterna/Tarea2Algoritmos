import java.util.ArrayList;

public class StopWatch {
	
	private long startTime;
	private long finalTime;
	private ArrayList<Long> acumulados;
	
	public StopWatch(){
		acumulados = new ArrayList<Long>();
		startTime = 0;
		finalTime = 0;
	}


	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	public void pause(){
		long actual = System.currentTimeMillis() - startTime;
		acumulados.add(actual);
	}


	public void reset(){
		startTime = finalTime = 0;
		acumulados.clear();
		
	}

	public double getPromedio(){
		double x = 0.0;
		for(Long l : acumulados)
			x += l;
		return x/acumulados.size();
	}
	
	public double getDE(){
		double de = 0.0;
		double p = getPromedio();
		for(Long l : acumulados)
			de += Math.pow(p-l,2);
		return Math.sqrt(de)/acumulados.size();
	}
}
