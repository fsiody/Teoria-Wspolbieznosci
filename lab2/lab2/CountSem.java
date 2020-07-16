import java.util.ArrayList;

public class CountSem {
    private int resources=0;
    private int available=0;
    private ArrayList<Semafor> semafors;

    CountSem(int r){
        resources=r;
        semafors=new ArrayList<>();
        for(int i=0;i<r;i++){
            Semafor sem=new Semafor();
            semafors.add(sem);
            available++;
        }
    }

    public synchronized void P ( ) {
        while (available==0) {
            try { wait(); } catch (InterruptedException e) { }
        }
        available--;
        semafors.get(available).P();
    }

    public synchronized void V ( ) {
        if(available!=resources) {
            semafors.get(available).V();
            available++;
        }
    }
}
