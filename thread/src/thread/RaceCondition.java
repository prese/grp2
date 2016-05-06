package thread;

public class RaceCondition {

	private long counter;
	
	public static void main(String[] args) {
		RaceCondition raceCondition = new RaceCondition();
		
		for (int i = 0; i < 10; i++) {
			new Thread(raceCondition.new CounterIncrementer()).start();
		}
		
		System.out.println("Counter: " + raceCondition.getCounter());
		
		
	}
	
	long getCounter() {
		return counter;
	}
	
	class CounterIncrementer implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			}catch(Exception ex) {
				
			}
			counter = counter + 1;
			
		}
		
	}
}
