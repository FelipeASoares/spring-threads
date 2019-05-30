package br.com.paxtecnologia.spring_threads.fac;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.paxtecnologia.spring_threads.runner.FactorialRunner;
import br.com.paxtecnologia.spring_threads.runner.LoopRunner;

@Component
public class RunnerFactory {
	
	@Value("${loop.count}")
	private Long loopCount;

	@Value("${loop.maxCount}")
	private Long maxCount;
	
	@Value("${factorial.number}")
	private Long factorialNumber;

	public Runnable getByStressMethod(String stressMethod) {
		switch (stressMethod) {
		case "fatorial":
			return new FactorialRunner(loopCount, factorialNumber);
		default:
			return new LoopRunner(loopCount, maxCount);
		}
	}

}
