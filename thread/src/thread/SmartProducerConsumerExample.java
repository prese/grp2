package thread;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SmartProducerConsumerExample {
	public static void main(String[] args) {
		
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		
		
		
		(new Thread(new SmartProducer(queue))).start();
		(new Thread(new SmartConsumer(queue))).start();
	}
}
