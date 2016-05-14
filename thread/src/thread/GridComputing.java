package thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Shows the usage of the object monitor.
 * 
 * Calculates the sum of the numbers lower than a given value splitting the
 * compute operation between multiple threads.
 * 
 * @author sebi
 *
 */
public class GridComputing {

	// we need one thread changing the value. Thus we can use the totalSum as a
	// monitor
	private Long totalSum;

	private Random random = new Random();

	public long computeSum(int maxNumber, int numberOfWorkers) throws Exception {
		totalSum = 0l;
		List<Thread> threads = new LinkedList<>();
		int start = 0;
		int step = maxNumber / numberOfWorkers;
		int i = 0;
		for (i = 0; i < numberOfWorkers; i++) {
			Thread th = new Thread(new Worker("Worker-" + i, start, start + step));
			threads.add(th);
			th.start();
			start += step;
		}

		// do not forget about the rest :)
		if (step < maxNumber) {
			Thread th = new Thread(new Worker("Worker-" + i, start, maxNumber));
			threads.add(th);
			th.start();
		}

		for (Thread thread : threads) {
			// just wait to be sure that all the threads made their computation
			thread.join();
		}

		return totalSum;
	}

	public static void main(String[] args) throws Exception {

		GridComputing grid = new GridComputing();
		int largeNumber = 100000;
		int numberOfThreads = 3;
		long result = grid.computeSum(largeNumber, numberOfThreads);
		System.out.println("Sum of numbers lower than: " + largeNumber + ", is: " + result);

	}

	/**
	 * Knows how to sum-up some numbers between a range. Still it is slow and
	 * needs some time to perform the compute.
	 * 
	 * @author sebi
	 *
	 */
	private class Worker implements Runnable {

		private String threadName;
		private int start;
		private int end;

		public Worker(String threadName, int start, int end) {
			super();
			this.threadName = threadName;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			Thread.currentThread().setName(threadName);

			System.out.println(Thread.currentThread().getName() + " asking for the monitor Lock.");
			synchronized (totalSum) {
				System.out.println(Thread.currentThread().getName() + " received the lock.");
				System.out.println("Compute partial sum: " + start + " - " + end);
				long partialResult = 0;
				for (int i = start; i < end; i++) {
					partialResult = partialResult + i;
				}

				 try {
					 //just simulating a long operation
					Thread.sleep(100 + random.nextInt(5 * 1000));
				 } catch (Exception ex) {
				
				 }

				// we need a synchronized on a lock, because we MUST BE SURE
				// that only one thread changes the total sum at the time

				totalSum = totalSum + partialResult;

				System.out.println(Thread.currentThread().getName() + " releasing the lock.");

			}
		}

	}
}
