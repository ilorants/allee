package multithreading;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//Ez az osztály több Threadet indít, amelyek közös erőforrásokon dolgoznak
class Locker {

    private Object lockOne = new Object();
    private Object lockTwo = new Object();

    public ArrayList<Integer> numbers1 = new ArrayList<>();
    public ArrayList<Integer> numbers2 = new ArrayList<>();

    public void doorOne() throws InterruptedException {
        synchronized (lockOne) {
            numbers1.add(10);
            Thread.sleep(1);
        }
    }

    public void doorTwo() throws InterruptedException {
        synchronized (lockTwo) {
            numbers2.add(10);
            Thread.sleep(1);
        }
    }

    public void doWork() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            doorOne();
            doorTwo();
        }
    }
}

//Ő csak elindít
public class LockDemo {

    public static void mainer(String[] args) throws InterruptedException {
        System.out.println("Kezdjük el!");
        long start = System.currentTimeMillis();
        Locker locker = new Locker();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    locker.doWork();
                } catch (InterruptedException ex) {
                    Logger.getLogger(LockDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    locker.doWork();
                } catch (InterruptedException ex) {
                    Logger.getLogger(LockDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println("numbers1 size: " + locker.numbers1.size() + " numbers2 size: " + locker.numbers2.size());
    }

}
