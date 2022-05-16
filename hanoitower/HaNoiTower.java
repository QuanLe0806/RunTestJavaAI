
package HaNoiTower;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author admin
 */
public class HaNoiTower {
    static int ring = 3;
    static State START = createState(ring, 0);
    static State GOAL = createState(ring, 2);
    
    public static Queue<State> open = new PriorityQueue<State>();
    
    public static Set<State> close = new HashSet<State>(){
        @Override
        public boolean contains(Object obj) {
            State state = (State) obj;

            for(State t: this){
                if(t.equals(state)){
                    return true;
                }
            }

            return false;
        }
    };
    
    public static void main(String[] args) throws CloneNotSupportedException{
        int k = 1;
 open.add(START);
        printfPathQueue(open, ring);
        while(!open.isEmpty()){
            State state = open.poll();
            close.add(state);
            System.out.println(state.toString() + "f = " + state.getF() + "  ");
            
            System.out.println("Duyet lan " + k + "\n");
            k++;
            state.tower2tower(0, 2);
            ArrayList<State> newList = new ArrayList<>();
            newList.add(state.tower2tower(0, 1));
            newList.add(state.tower2tower(0, 2));
            newList.add(state.tower2tower(1, 0));
            newList.add(state.tower2tower(1, 2));
            newList.add(state.tower2tower(2, 1));
            newList.add(state.tower2tower(2, 0));
            
            boolean x = false;
            for(State t: newList){
                if(t != null && !close.contains(t)){
                    for(State state1: open){
                        if(state1.equals(t)){
                            //Doan nay la A*
//                            if(state1.getTotal() > t.getTotal()){
//                                state1 = t;
//                            }
                            x = true;
                            break;
                        }
                    }
                    if(x){
                        x = false;
                    }else{
                        if (t.equals(GOAL)) {
                            System.out.println("\nDuong dan toi dich: ");
                            System.out.println("\nTong chi phi: " + t.getTotal());
                            ArrayList<State> list = new ArrayList<State>();
                            while (t.getParent() != null) {
                                list.add(t);
                                t = t.getParent();
                            }
                            list.add(t);

                            System.out.println("Tong so lan duyet: " + k);
                            printfPath(list, ring);
                            return;
                        }
                        open.add(t);
                        
                    }
                }
            }System.out.println("Open");
            printfPathQueue(open, ring);
        }
    }
    
    public static State createState(int ring, int tower){
        ArrayList<Stack<Integer>> towers = new ArrayList<>();
        towers.add(new Stack<>());
        towers.add(new Stack<>());
        towers.add(new Stack<>());
        for(int i = ring - 1; i >= 0; i--){
            towers.get(tower).push(i);
        }
        State state = new State(towers, ring);
        return state;
    }
    
    public static void printfPath(ArrayList<State> path, int ring){
        String s = "";
        for(int i = 0; i <= ring; i++){
            for(State t: path){
                for(int j = 0; j < 3; j++){
                    s += "|";
                    if(i != ring){
                        Stack<Integer> st = t.getTowers().get(j);
                        try{
                            int index = abs(i - ring) - 1;
                            int in =(int) st.get(index);
                            s += in;
                        }catch(ArrayIndexOutOfBoundsException e){
                            s += " ";
                        }
                    }else{
                        s += "-";
                    }
                }
                s += "|  ";
            }
            s += "\n";
        }
        System.out.println(s);
    }
    
    public static void printfPathQueue(Queue<State> path, int ring){
        String s = "";
        for(int i = 0; i <= ring; i++){
            for(State t: path){
                for(int j = 0; j < 3; j++){
                    s += "|";
                    if(i != ring){
                        Stack<Integer> st = t.getTowers().get(j);
                        try{
                            int index = abs(i - ring) - 1;
                            int in =(int) st.get(index);
                            s += in;
                        }catch(ArrayIndexOutOfBoundsException e){
                            s += " ";
                        }
                    }else{
                        s += "-";
                    }
                }
                s += "|  ";
            }
            
            s += "\n";
        }
        System.out.println(s);
    }
}
