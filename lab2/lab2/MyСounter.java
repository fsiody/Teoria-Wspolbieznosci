
public class MyСounter {
    int val;
    CountSem sem;

    MyСounter(){
        this.val=0;
        this.sem=new CountSem(3);
    }

    public void inc() {
        sem.P();
        this.val++;
        this.sem.V();
    }

    public void dec() {
        sem.P();
        this.val--;
        this.sem.V();
    }
}
