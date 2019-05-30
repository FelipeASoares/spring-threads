package br.com.paxtecnologia.spring_threads.runner;

public class FactorialRunner implements Runnable {
	
	private Long factorialNum;
	
	public FactorialRunner(Long factorialNum) {
		this.factorialNum = factorialNum;
	}

	@Override
	public void run() {
		
	}

}
