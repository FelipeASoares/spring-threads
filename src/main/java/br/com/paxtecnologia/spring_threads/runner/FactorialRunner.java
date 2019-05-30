package br.com.paxtecnologia.spring_threads.runner;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactorialRunner implements Runnable {

	private Logger log = LoggerFactory.getLogger(FactorialRunner.class);

	private Long loopCount;
	private Long factorialNum;

	public FactorialRunner(Long loopCount, Long factorialNum) {
		this.loopCount = loopCount;
		this.factorialNum = factorialNum;
	}

	@Override
	public void run() {
		for (long i = 0; i < loopCount; i++) {
			Long factorial = this.factorialNum;
			
			log.debug("Iniciando o fatorial de {}", factorial);
			BigInteger result = BigInteger.valueOf(1);

			if (factorial == 0) {
				log.debug("O resultado do fatorial é {}", result);
				return;
			}

			while (factorial > 0) {
				result = result.multiply(BigInteger.valueOf(factorial));
				factorial--;
			}

			log.debug("O resultado do fatorial é {}", result);
		}
	}

}
