package thread;

import java.util.Queue;
import java.util.Random;

public class SmartProducer implements Runnable {
	private Queue<String> queue;

	
	public SmartProducer(Queue<String> queue) {
		super();
		this.queue = queue;
	}

	public void run() {
		Random random = new Random();
		String importantInfo[] = { "Ana", "are", "mere", "si pere :)" };
		for (String string : importantInfo) {
			put(string);
			try {
				Thread.sleep(random.nextInt(5000));
			} catch (InterruptedException e) {
			}
		}
		put("DONE");
	}

	public void put(String message) {
		queue.add(message);
	}
}
