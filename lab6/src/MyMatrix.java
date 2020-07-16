import Jama.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.dumpStack;
import static java.lang.Thread.sleep;

/**
 * @author macwozni
 */
public class MyMatrix {
    int size;
    Double[][] lhs;
    Double[][] rhs;

    public MyMatrix(BufferedReader in) throws IOException{
        // in first line there is a integer with matrix size
        String s = in.readLine();

        // parse integer
        size = Integer.parseInt(s);
        //create data structures for reading matrix and RHS vector
        lhs = new Double[size][size];
        rhs = new Double[size][1];

        // read matrix line by line
        for (int i = 0; i < size; i++) {
            // read line
            s = in.readLine();
            // split line along space signs
            String[] sp = s.split(" ");
            // parse each string to double
            for (int j = 0; j < size; j++) {
                lhs[i][j] = Double.parseDouble(sp[j]);
            }
        }
        // read RHS vector line
        s = in.readLine();
        // split line along space signs
        String[] sp = s.split(" ");
        // parse each string to double
        for (int j = 0; j < size; j++) {
            rhs[j][0] = Double.parseDouble(sp[j]);
        }
    }

    public void printMatrix(){
        String matr="\n__________________\n";
        //System.out.println(String.format ("%.1f", number));
        for(int ii=0;ii<this.size;ii++){
            for(int jj=0;jj<this.size;jj++){
                if(Math.abs(this.lhs[ii][jj])>0.00001) matr+=String.format("%.1f",this.lhs[ii][jj])+"   ";
                else matr+=0.0+"   ";
            }
            matr+="   |  "+String.format("%.1f",this.rhs[ii][0])+"\n";
        }
        matr+="__________________\n";
        System.out.println(matr);
    }


    public void solve(){
        System.out.println("Lets try to solve it!");
        int n=this.size;
        Integer [][] elementsChangedBy=new Integer[n][n+1];
        for(int i =0;i<n;i++){
            for(int j=0;j<=n;j++){
                elementsChangedBy[i][j]=-1;
            }
        }
        List<ThreadA> threads = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int k = i+1; k < n; k++){
                if(i!=k) threads.add(new ThreadA(this,i,k,elementsChangedBy));
            }
        }
        System.out.println("Preparations are finished, lets go D:");
        threads.forEach(ThreadA::start);
        try { for(ThreadA t : threads) t.join(); }
        catch(InterruptedException ex) { System.out.println("Exception has been caught" + ex); }
        printMatrix();
        System.out.println("LETS SOLVE GAUSSE");
        this.solveGauss();
        System.out.println("----------------FINISHED-----------------");
    }

    public void solveGauss(){
        try{sleep(500);}catch (InterruptedException e){}
        System.out.println("----------------SOLVE-----------------");
        for(int i=this.size-1;i>=0;i--){
            double d=this.lhs[i][i];
            if(Math.abs(d)>=0.00001) {
                for (int j = this.size - 1; j >= i; j--) {
                    this.lhs[i][j] /= d;
                }
                this.rhs[i][0] /= d;
            }
        }
        System.out.println("поделили");
        printMatrix();
        for(int i=this.size-2;i>=0;i--){
            for(int j=this.size-1;j>i;j--){
                double m=this.lhs[i][j];
                this.lhs[i][j]-=m*this.lhs[j][j];
                this.rhs[i][0]-=m*this.rhs[j][0];
            }
        }
        printMatrix();

    }


    /**
     * @param a first variable for comparisson
     * @param b second variable for comparisson
     * @param epsilon machine precission for floating point
     * @return true if equals or within bounds of epsilon precission
     */
    static boolean compare(double a, double b, double epsilon) {
        double c = Math.abs(a - b);
        return c < epsilon;
    }
}