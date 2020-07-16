

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    int id;
    int startHunger;
    int hunger;
    Semaphore left;
    Semaphore right;
    long waitingTime;
    Table table;


    Philosopher(int id, Semaphore left, Semaphore right, int h, Table table) {
        this.id = id;
        this.startHunger = h;
        this.hunger = h;
        this.left = left;
        this.right = right;
        this.waitingTime = 0;
        this.table=table;
    }


    void eating1() throws InterruptedException {
        table.getTableInformation();
        left.acquire();
        table.table[id][0] = true;

        table.getTableInformation();
        right.acquire();
        table.table[id][1] = true;

        table.getTableInformation();
        hunger--;

        left.release();
        right.release();
        table.table[id][0] = false;
        table.table[id][1] = false;
        table.getTableInformation();

   //     sleep(10);
    }

    void eating2() throws InterruptedException {
        boolean flag=this.table.canEat(id);
        if(flag) {

            table.getTableInformation();
            left.acquire();
            table.takeLeft(id);
            right.acquire();
            table.takeRight(id);

            table.getTableInformation();
            hunger--;

            left.release();
            right.release();
            table.releaseForks(id);
            table.getTableInformation();
        }
        //     sleep(10);
    }

    void eating3() throws InterruptedException {
        if(id%2==0){
            table.getTableInformation();
            right.acquire();
            table.table[id][1] = true;

            table.getTableInformation();
            left.acquire();
            table.table[id][0] = true;
        }
        else {
            table.getTableInformation();
            left.acquire();
            table.table[id][0] = true;

            table.getTableInformation();
            right.acquire();
            table.table[id][1] = true;
        }

        table.getTableInformation();
        hunger--;

        left.release();
        right.release();
        table.table[id][0] = false;
        table.table[id][1] = false;
        table.getTableInformation();

        //     sleep(10);
    }

    void eating4()throws InterruptedException{
        if(this.table.kelnerAllow()) {
            eating1();
            this.table.eating--;
        }
    }

    public void run() {
        System.out.println("Ph" + this.id +
                String.format(" sat at the table. Left %d  Right %d",
                        this.table.left(id),this.table.right(id)));
        long startTime = System.nanoTime();
        try {
            while (hunger > 0) {
                eating4();
                waitingTime = waitingTime + System.nanoTime() - startTime;
                startTime = System.nanoTime();
            }
        } catch (InterruptedException ie) {
            System.out.println("Interrupted!" + ie.getMessage());
        }
      //  waitingTime=(waitingTime-startHunger*10000000);
        waitingTime/= startHunger;
        System.out.println("Ph" + this.id + " -------------***** ok *****-------------- ");


    }
}