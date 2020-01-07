package multithreading;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Tasker extends Thread {

    private volatile boolean runner = true;

    @Override
    public void run() {

        while (runner) {
            try {
                System.out.println("Futok");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tasker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void kill() {
        this.runner = false;
    }
}

public class VolatileDemo {

    public static void mainner(String[] args) {
        //Elindul a Main szál

        //Most elindítjuk a másik szálat
        Tasker t1 = new Tasker();
        t1.start();

        System.out.println("Nyomj entert a szunetelteteshez!");

        //A Main szál itt megáll, amíg entert nem nyomunk
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        //Entert nyomtak, megyünk tovább
        t1.kill();

    }

}
