package multithreading;

import java.util.logging.Level;
import java.util.logging.Logger;

class TaskerB implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println("Mellékszál " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {

            }
        }

    }
}

public class MultiThreading {

    public static void mainer(String[] args) {
        Thread t1 = new Thread(new TaskerB());
        Thread t2 = new Thread(new TaskerB());

        t1.start();
        t2.start();
    }

}
