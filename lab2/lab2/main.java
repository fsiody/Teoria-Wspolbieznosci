import static java.lang.Thread.sleep;

public class main {


    public static void main(String args[]){
        MyСounter counter=new MyСounter();
        int num=10000;
        DecCntr dc=new DecCntr(counter,num);
        IncCntr ic=new IncCntr(counter,num);
        System.out.println("START!");
        Thread d1=new Thread(dc); d1.setName(" [DEC 1 ] "); d1.start();
        Thread i1=new Thread(ic); i1.setName(" [INC 1 ] "); i1.start();
        Thread d2=new Thread(dc); d2.setName(" [DEC 2 ] "); d2.start();
        Thread i2=new Thread(ic); i2.setName(" [INC 2 ] "); i2.start();
        Thread d3=new Thread(dc); d3.setName(" [DEC 1 ] "); d3.start();
        Thread i3=new Thread(ic); i3.setName(" [INC 1 ] "); i3.start();
        Thread d4=new Thread(dc); d4.setName(" [DEC 2 ] "); d4.start();
        Thread i4=new Thread(ic); i4.setName(" [INC 2 ] "); i4.start();
        try { sleep(5000); } catch (InterruptedException e) { }
        System.out.println("~~~~~~~~~~  "+counter.val+"  ~~~~~~~~~~");
    }
}