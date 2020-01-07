package multithreading;

public class SynchronizationDemo {
    
   private int count;
    
   public static void mainer(String[] args) throws InterruptedException {
      SynchronizationDemo sD = new SynchronizationDemo();
      sD.threadWork();
   }
   
   public synchronized void addToCount(){
       count++;
   }
    
   private void threadWork() throws InterruptedException{
       
      Thread t1 = new Thread(new Runnable() {
           public void run() {
               for(int i = 0; i<12000;i++){
                   addToCount(); 
               }
           }
       });
       
      Thread t2 = new Thread(new Runnable() {
           public void run() {
               for(int i = 0; i<12000;i++){
                   addToCount(); 
               }
           }
       });
       
       t1.start();
       t2.start();
       
       t1.join();
       t2.join();
       
       System.out.println("Count értéke: " + count);
   }
    
}
