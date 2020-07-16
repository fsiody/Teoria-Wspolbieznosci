/*

import java.util.concurrent.Semaphore;

public class main {
    public static void main(String args[]){
        Semaphore fork1=new Semaphore(1);
        Semaphore fork2=new Semaphore(1);
        Semaphore fork3=new Semaphore(1);
        Semaphore fork4=new Semaphore(1);
        Semaphore fork5=new Semaphore(1);

        int h=500;
        boolean[] phs=new boolean[5];
        phs[0]=false; phs[1]=false; phs[2]=false; phs[3]=false; phs[4]=false;
        Boolean[][] forks=new Boolean[5][2];
        long[] waitingTimes=new long[5];

        for(int i=0;i<5;i++){
            for(int j=0;j<2;j++){
                forks[i][j]=false;
            }
            waitingTimes[i]=0;
        }
        Philosopher ph0=new Philosopher(0,fork1,fork2,h,phs,forks,waitingTimes);
        Philosopher ph1=new Philosopher(1,fork2,fork3,h,phs,forks,waitingTimes);
        Philosopher ph2=new Philosopher(2,fork3,fork4,h,phs,forks,waitingTimes);
        Philosopher ph3=new Philosopher(3,fork4,fork5,h,phs,forks,waitingTimes);
        Philosopher ph4=new Philosopher(4,fork5,fork1,h,phs,forks,waitingTimes);

        ph1.start();
        ph2.start();
        ph3.start();
        ph4.start();
        ph0.start();
    }
}

*/
