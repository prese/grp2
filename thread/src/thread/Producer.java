package thread;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = {
            "Ana",
            "are",
            "mere",
            "si pere :)"
        };
        Random random = new Random();

        for (int i = 0;
             i < importantInfo.length;
             i++) {
        	//System.out.println("Message ready to be delivered: " + importantInfo[i]); 
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        drop.put("DONE");
    }

}
