public class Semafor {
    private boolean stan;

    public Semafor ( ) {
        stan=true;
    }

    public synchronized void P ( ) {
        while (!stan) {
            try { wait(); } catch (InterruptedException e) { }
        }
        stan = false;
    }

    public synchronized void V ( ) {
        if(!stan) stan=true;
        notify();
    }
}
