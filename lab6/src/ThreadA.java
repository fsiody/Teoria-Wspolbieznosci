import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ThreadA extends Thread{
    MyMatrix matrix;
    int i;
    int k;
    Integer [][] elementsChangedBy;
    List<ThreadBC> threadsBC;

    public ThreadA(MyMatrix matrix,int i, int k, Integer [][] ecb){
        this.matrix=matrix;
        this.i=i;
        this.k=k;
        this.elementsChangedBy=ecb;
        this.threadsBC = new LinkedList<>();
    }

    public void run(){
        System.out.println("[ " + this.k + "-" + this.i + " ]    We are in Thread A "+Thread.currentThread().getName() );

        while (!getMultiplier(i,k,elementsChangedBy))getMultiplier(i,k,elementsChangedBy);
        this.threadsBC.forEach(ThreadBC::start);
        try { for(ThreadBC t : threadsBC) t.join(); }
        catch(InterruptedException ex) { System.out.println("Exception has been caught" + ex); }
    }


    private boolean getMultiplier(int i, int k, Integer[][] elementsChangedBy){
        double m;
        if(elementsChangedBy[i][k]==i-1 && elementsChangedBy[k][k]==i-1) {
            if (Math.abs(matrix.lhs[k][i]) <= 0.00001) m = 0.0;
            else m = matrix.lhs[i][i] / matrix.lhs[k][i];
            System.out.println("[ " + this.k + "-" + this.i + " ]   m = "+m +"  "+Thread.currentThread().getName());
            for (int j = 0; j < matrix.size; j++) {
                this.threadsBC.add(new ThreadBC(matrix, i, k, j, m, elementsChangedBy));
            }
            this.threadsBC.add(new ThreadBC(matrix, i, k, matrix.size, m, elementsChangedBy));
            return true;
        }
        else{
            try {
                sleep(100);
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"  Interrupted A "+e.getMessage());
            }
            return false;
        }
    }
}
