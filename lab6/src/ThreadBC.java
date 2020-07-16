import java.util.concurrent.Semaphore;

public class ThreadBC extends Thread{
    MyMatrix matrix;
    int i;
    int k;
    int j;
    double m;
    Integer [][] elementsChangedBy;

    public ThreadBC(MyMatrix matrix,int i, int k, int j, double m, Integer [][] ecb){
        this.matrix=matrix;
        this.i=i;
        this.k=k;
        this.j=j;
        this.m=m;
        this.elementsChangedBy=ecb;
    }

    public void run(){
        System.out.println("[ " + this.k + "-" + this.i + "("+ j+ ") ]  in BC..."+Thread.currentThread().getName());
        Double a=0.0;
        if(j!=matrix.size) a = matrix.lhs[k][j];
        if(m!=0.0) while (!multiply(i,k,j,m)) multiply(i,k,j,m);
        if(j!=matrix.size) System.out.println("[ " + this.k + "-" + this.i + "("+ j+ ") *"+String.format("%.1f",m) + " ] "+ a +" ----> "+matrix.lhs[k][j]);

        this.elementsChangedBy[k][j]=i;
        if(j==4) this.matrix.printMatrix();
    }

    private boolean multiply(int i, int k, int j, double m){
        if(this.elementsChangedBy[k][j]==i-1) {
            if(j!=matrix.size) matrix.lhs[k][j] = matrix.lhs[k][j] * m;
            else matrix.rhs[k][0] = matrix.rhs[k][0] * m;
            System.out.println("[ " + this.k + "-" + this.i + "("+ j+ ")]  *****  "+Thread.currentThread().getName());
            while (!subtract(i, k, j))subtract(i, k, j);
            return true;
        }
        if(this.elementsChangedBy[k][j]<i-1){
            try {
                sleep(100);
                System.out.println("[ " + this.k + "-" + this.i + "("+ j+ ") ] waiting...");
            }catch (InterruptedException e){ System.out.println(Thread.currentThread().getName() +"  Interrupted B "+e.getMessage()); }
            return false;
        }
        return true;

    }
    private boolean subtract(int i, int k, int j){
        if(this.elementsChangedBy[i][j]==i-1) {
            if(j!=matrix.size){
                matrix.lhs[k][j]=matrix.lhs[k][j]-matrix.lhs[i][j];
            }
            else matrix.rhs[k][0]=matrix.rhs[k][0]-matrix.rhs[i][0];
            System.out.println("[ " + this.k + "-" + this.i + "("+ j+ ") ]  -----  "+Thread.currentThread().getName());
            return true;
        }
        if(this.elementsChangedBy[k][j]<i-1) {
            try {
                sleep(100);
                System.out.println("[ " + this.k + "-" + this.i + "(" + j + ") ] waiting...");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "  Interrupted C " + e.getMessage());
            }
            return false;
        }
        return true;

    }
}
