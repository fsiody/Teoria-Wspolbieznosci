/*


import java.sql.Time;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class Philosopher extends Thread {
    int id;
    int startHunger;
    int hunger;
    Semaphore left;
    Semaphore right;

    long[] waitingTimes;
    boolean[] phs;     // debug
    Boolean[][] forks; // debug


    Philosopher(int id, Semaphore left, Semaphore right, int h, boolean[] phs, Boolean[][] forks, long[] waitingTimes) {
        this.id = id;
        this.startHunger = h;
        this.hunger = h;
        this.left = left;
        this.right = right;


        this.phs = phs;
        this.forks = forks;
        this.waitingTimes = waitingTimes;
    }

    synchronized void getForkInformation() {
        String s = " ";
        for (int i = 0; i < 5; i++) {
            if (forks[i][0]) s += "|*" + i;
            else s += "| " + i;
            if (forks[i][1]) s += "*";
            else s += " ";
        }
        System.out.println(s);
    }

    void COUTforcs() {
        for (int i = 0; i < 5; i++) {
            System.out.println(forks[i][0] + " | " + forks[i][1]);
        }
        System.out.println("");
    }

    void eating1() throws InterruptedException {

        left.acquire();
        forks[id][0] = true;
        right.acquire();
        forks[id][1] = true;
        getForkInformation();
        if (forks[id][0] && forks[id][1]) this.hunger--;
        left.release();
        right.release();
        forks[id][0] = false;
        forks[id][1] = false;
        sleep(10);
    }
    void printPhs(){
        String s = "";
        for (int i = 0; i < 5; i++) {
            s += " | Ph " + i + " " + phs[this.id];
        }
        System.out.println(s);
        finished();

    }
    void printTime(){
        System.out.println("Ph" + this.id + " -------------***** ok *****-------------- ");
        String s = "";
        for (int i = 0; i < 5; i++) {
            s += " | " + String.format("%,12d", (waitingTimes[i]-startHunger*10000000) / startHunger) + " ns";
        }
        System.out.println(s + " WAIT");
    }
    void finished(){
        Boolean f=true;
        for (int i = 0; i < 5; i++) {
            if(!phs[i]) f=false;
        }
        if(f) System.out.println("FINISHED!");
        else {
            String s="Smb is hungry... (";
            for (int i = 0; i < 5; i++) {
                if(!phs[i]) {
                    s+="Ph "+i+" ";
                }
            }
            s+=")";
            System.out.println(s);
        }
    }

    public void run() {
        System.out.println("Ph" + this.id + " sat at the table");
        long startTime = System.nanoTime();
        try {
            while (hunger > 0) {
                eating1();
                waitingTimes[id] = waitingTimes[id] + System.nanoTime() - startTime;
                startTime = System.nanoTime();
            }
            phs[id] = true;
            printPhs();
            printTime();
        } catch (InterruptedException ie) {
            System.out.println("Interrupted!" + ie.getMessage());
        }

    }
}


*/