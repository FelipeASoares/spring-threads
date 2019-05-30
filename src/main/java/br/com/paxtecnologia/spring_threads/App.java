package br.com.paxtecnologia.spring_threads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.paxtecnologia.spring_threads.fac.RunnerFactory;

@SpringBootApplication
public class App implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(App.class);

	@Value("${app.num-threads}")
	private Integer numThreads;

	@Value("${app.stress-method}")
	private String stressMethod;

	@Autowired
	private RunnerFactory runnerFac;

	public static void main(String[] args) {
		log.debug("Aplicação inicializando...");
		SpringApplication.run(App.class, args);
		log.debug("Serviço finalizado");
	}

	public void run(String... args) throws Exception {
		ExecutorService taskExecutor = Executors.newFixedThreadPool(numThreads);

		Collection<Callable<Object>> tasks = new ArrayList<Callable<Object>>();

		IntStream.range(0, numThreads)
				.forEach(i -> tasks.add(Executors.callable(runnerFac.getByStressMethod(stressMethod))));

		taskExecutor.invokeAll(tasks).forEach(f -> {
			try {
				f.get();
			} catch (Exception e) {
				throw new Error(e);
			}
		});

		taskExecutor.shutdown();
	}

}
