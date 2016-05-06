package thread;

import java.util.LinkedList;
import java.util.List;

public class RaceCondition {

	private long counter;

	public static void main(String[] args) {
		RaceCondition raceCondition = new RaceCondition();
		List<Thread> threads = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(raceCondition.new CounterIncrementer());
			// thread.setDaemon(true);
			threads.add(thread);
			thread.start();
		}

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (Exception ex) {

			}
		}

		System.out.println("Counter: " + raceCondition.getCounter());

	}

	synchronized long getCounter() {
		return counter;
	}

	void increment() {
		synchronized (this) {
			counter = counter + 1;
		}

	}

	class CounterIncrementer implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {

			}
			System.out.println(Thread.currentThread().getName() + ":" + getCounter());
			increment();
			System.out.println(Thread.currentThread().getName() + ":" + getCounter());

		}

	}
}
