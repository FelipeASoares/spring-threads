package br.com.paxtecnologia.spring_threads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.paxtecnologia.spring_threads.runner.LoopRunner;

@SpringBootApplication
public class App implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(App.class);

	@Value("${app.numThreads}")
	private Integer numThreads;

	@Value("${loop.count}")
	private Long loopCount;

	@Value("${loop.maxCount}")
	private Long maxCount;

	public static void main(String[] args) {
		log.debug("Aplicação inicializando...");
		SpringApplication.run(App.class, args);
	}

	public void run(String... args) throws Exception {
		ExecutorService taskExecutor = Executors.newFixedThreadPool(numThreads);
		
		Collection<Callable<Object>> list = new ArrayList<Callable<Object>>();
		
		IntStream.range(0, numThreads).forEach(i -> list.add(Executors.callable(new LoopRunner(loopCount, maxCount))));		
		List<Future<Object>> futures = taskExecutor.invokeAll(list);

		futures.forEach(f -> {
			try {
				f.get();
			} catch (Exception e) {
				throw new Error(e);
			}
		});

		taskExecutor.shutdown();
		log.debug("Serviço completado");
	}

}
