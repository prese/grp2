package thread;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class SmartConsumer implements Runnable {

	private LinkedBlockingQueue<String> queue;

	public SmartConsumer(LinkedBlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	public void run() {
		take();
	}

	public void take() {
		try {
			String message;
			Random random = new Random();
			while (!"DONE".equals(message = queue.take())) {
				System.out.println("Consumed: " + message);

			}
		} catch (Exception e) {

		}
	}
}
