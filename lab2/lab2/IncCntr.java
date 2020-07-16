public class IncCntr implements Runnable{
    MyСounter cntr;
    int num;

    IncCntr(MyСounter c, int n){ this.cntr=c; this.num=n;}

    public void run() {
        for (int i = 0; i < num; i++) {
            cntr.inc();
        }
        System.out.println("val="+cntr.val+" |  finished +++");

    }

}