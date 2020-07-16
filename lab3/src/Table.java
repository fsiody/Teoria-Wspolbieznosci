public class Table {
    Boolean[][] table; // debug
    Boolean [] freeForks;
    int eating;
    int sits;

    Table(int sits){
        this.sits=sits;
        this.freeForks=new Boolean[sits];
        this.eating=0;
        this.table=new Boolean[sits][2];
        for(int i=0;i<sits;i++){
            this.freeForks[i]=true;
            this.table[i][0]=false;
            this.table[i][1]=false;
        }
    }

    int left(int id){
        if(id==0) return sits-1;
        else return id-1;
    }

    int right(int id){
        return id;
    }

    void takeLeft(int id){
        table[id][0] = true;
        freeForks[left(id)]=false;
    }

    void takeRight(int id){
        table[id][1] = true;
        freeForks[right(id)]=false;
    }

    boolean canEat(int id){
        if(freeForks[left(id)] && freeForks[right(id)]) return true;
        else return false;
    }

    synchronized boolean kelnerAllow(){
        if(eating<sits-1) {
            eating++;
            return true;
        }
        else {
            return false;
        }
    }

    void releaseForks(int id){

        freeForks[left(id)]=true;
        freeForks[right(id)]=true;

        table[id][0] = false;
        table[id][1] = false;
    }

    void getTableInformation() {
        String s = " ";
        for (int i = 0; i < sits; i++) {
            if (table[i][0]) s += "|*" + i;
            else s += "| " + i;
            if (table[i][1]) s += "*";
            else s += " ";
        }
        System.out.println(s);
    }

}
