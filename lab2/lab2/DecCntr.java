public class DecCntr implements Runnable{
    MyСounter cntr;
    int num;

    DecCntr(MyСounter c, int n){ this.cntr=c; this.num=n; }

    public void run() {
        for (int i = 0; i < num; i++) {
            cntr.dec();
        }
        System.out.println("val="+cntr.val+" |  finished ---");
    }
}