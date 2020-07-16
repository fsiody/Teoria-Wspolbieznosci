
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class main {
    static void finished(Philosopher[] phs,int sits){
        String s="************************************************\nTIME: \n";
        long wt=0;
        for (int i = 0; i < sits; i++) {
            wt+=phs[i].waitingTime;
            s += i+" | " + String.format("%,12d", phs[i].waitingTime) + " ns\n";
        }
        wt/=sits;
        System.out.println(String.format("SITS: %d, HUNGER: %d   AVG: %,12d",sits, phs[0].startHunger , wt));
        System.out.println(s);

        boolean f=true;
        for (int i = 0; i < sits; i++) {
            if(phs[i].hunger>0) f=false;
        }
        if(f) System.out.println("FINISHED!");
        else {
            s="Smb is hungry... (";
            for (int i = 0; i < sits; i++) if(phs[i].hunger>0) s+="Ph "+i+" ";
            s+=")";
            System.out.println(s);
            phs[0].table.getTableInformation();
            System.out.println("************************************************");
        }
    }


    public static void main(String args[]){

        /***************************/
        int startHunger=50        ;
        int sits=50      ;
        /***************************/

        Table table=new Table(sits);
        Philosopher[] phs=new Philosopher[sits];
        phs[0]=new Philosopher(0,new Semaphore(1),new Semaphore(1),startHunger,table);
        for(int i=1;i<sits-1;i++){
            phs[i]=new Philosopher(i,phs[i-1].right,new Semaphore(1),startHunger,table);
        }
        phs[sits-1]=new Philosopher(sits-1,phs[sits-2].right,phs[0].left,startHunger,table);
        for(int i=0;i<sits;i++) phs[i].start();

        try { sleep(10*sits*startHunger); } catch (InterruptedException e) {}
        finished(phs,sits);

    }
}

