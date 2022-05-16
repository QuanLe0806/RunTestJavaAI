
package HaNoiTower;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class State implements Comparable<State>{
    private ArrayList<Stack<Integer>> towers;
    private int ring;
    private State parent;
    private int f;
    private int total;

    public State(ArrayList<Stack<Integer>> towers, int ring) {
        this.towers = towers;
        parent = null;
        this.ring = ring;
        f = caculateF();
        total = f;
    }
    
    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public ArrayList<Stack<Integer>> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<Stack<Integer>> towers) {
        this.towers = towers;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int caculateF(){
        int m = 0, k = 0;
        for(int integer: towers.get(2)){
            int index = abs(towers.get(2).indexOf(integer) - ring) - 1;
            if(integer != index){
                k++;
            }else{
                m++;
            }
        } 
        f = ring - m + k;
        return f;
    }

    @Override
    protected State clone() {
        ArrayList<Stack<Integer>> towers = new ArrayList<>();
        Stack<Integer> tower1 = new Stack<>();
        Stack<Integer> tower2 = new Stack<>();
        Stack<Integer> tower3 = new Stack<>();
        this.towers.get(0).forEach((integer) -> {
            tower1.add(integer);
        });
        this.towers.get(1).forEach((integer) -> {
            tower2.add(integer);
        });
        this.towers.get(2).forEach((integer) -> {
            tower3.add(integer);
        });
        towers.add(tower1);
        towers.add(tower2);
        towers.add(tower3);
        return new State(towers, ring);
    }
    
    
    
    public State tower2tower(int t1, int t2){
        State newState;
        if(!towers.get(t1).empty()){
            if(!towers.get(t2).empty()){
                if(towers.get(t2).peek() > towers.get(t1).peek()){
                    newState =(State) this.clone();
                    newState.towers.get(t2).push(newState.towers.get(t1).pop());
                    newState.parent = this;
                    newState.f = newState.caculateF();
                    newState.total = this.total + newState.f;
                    return newState;
                }else{
                    return null;
                }
            }else{
                newState =(State) this.clone();
                newState.towers.get(t2).push(newState.towers.get(t1).pop());
                newState.parent = this;
                newState.f = newState.caculateF();
                newState.total = this.total + newState.f;
                return newState;
            }
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < 3; i++){
            s += (i) + ": ";
            for(int integer: towers.get(i)){
                s = s + integer + " ";
            }
            s += "\n";
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        return Objects.equals(this.towers, other.towers);
    }
    
    @Override
    public int compareTo(State state) {
        if (this.f > state.f) {
            return 1;
        } else if (this.f < state.f) {
            return -1;
        } else {
            return 0;
        }
    }
}
