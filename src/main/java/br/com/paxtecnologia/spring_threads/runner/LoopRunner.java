package br.com.paxtecnologia.spring_threads.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoopRunner implements Runnable {

	private static Logger log = LoggerFactory.getLogger(LoopRunner.class);

	private Long loopCount;
	private Long maxCount;
	
	public LoopRunner(Long loopCount, Long maxCount) {
		this.loopCount = loopCount;
		this.maxCount = maxCount;
	}

	public void run() {
		log.debug("Thread " + Thread.currentThread().getName() + " iniciando");

		long count = 0;
		for (long i = 0; i < loopCount; i++) {
			count += i;
			if (count >= maxCount)
				break;
		}

		log.debug("Thread " + Thread.currentThread().getName() + " terminou o job com a contagem de {}", count);
	}

}
