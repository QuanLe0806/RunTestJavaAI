/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TACI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author minhd
 */
public class RunAKT {
    public static int g=0;
    public static int[][] a;
    public static int[][] goal;
    
    public static PriorityQueue<Vertex> open = new PriorityQueue<Vertex>(new Sort());
    public static PriorityQueue<Vertex> closed = new PriorityQueue<Vertex>(new Sort());
    
    public static Set<Vertex> visited = new HashSet<Vertex>(){
        public boolean contains(Object obj) {
            Vertex vertex = (Vertex) obj;
        
            for (Vertex v : this) {
                if ((vertex.equals(v)) && (vertex.getPath().equals(v.getPath()))) {
                    return true;
                }
            }
        
            return false;
        }
    };
    
    public static int H(int[][] c, int[][] d){
        int d1=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                if (c[i][j] != d[i][j] && c[i][j] != 0)
                    d1++;
        }
        return d1;
        
    }
    
    public static void main(String[] args) {

        a = new int[3][3];
        goal = new int[3][3];
        int[][] b = {               // trạng thái bắt đầu
                    {2,8,3},
                    {1,6,4},
                    {7,0,5}
                    };
        int[][] c = {               // trạng thái kết thúc
                    {1,2,3},
                    {8,0,4},
                    {7,6,5}
                    };
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                a[i][j]=b[i][j];
        }
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                goal[i][j]=c[i][j];
        }
        
        int h,d=1; 
        h = H(a,goal);

	Vertex initialVertex = new Vertex(new State(a,g,h,g+h));
        open.add(initialVertex);      
        visited.add(initialVertex);
        
	while(!open.isEmpty()){     
            
            System.out.println("Step " + String.valueOf(d));
            d++;
            Iterator iterator = open.iterator();
            System.out.print("OPEN = { ");
            while(iterator.hasNext()){            
                System.out.print(iterator.next().toString()+" ");
            }
            System.out.print("}\n");
            
            Iterator iterator1 = closed.iterator();
            System.out.print("CLOSED = { ");
            while(iterator1.hasNext()){            
                System.out.print(iterator1.next().toString()+" ");
            }
            System.out.print("}\n");
            System.out.println();
            
            closed.add(open.peek());                        
                        
            Vertex currentVertex = open.remove();                                              
            currentVertex.addToPath();
                        
            
            if(currentVertex.getState().getH() == 0){
                currentVertex.printPath(); 
                break;
            }             
            
//            currentVertex.getState().Xuat();
//            System.out.println();
            
            ArrayList<Vertex> newVertices = new ArrayList<>();
            
            newVertices.add(currentVertex.GoTop());
            newVertices.add(currentVertex.GoBottom());
            newVertices.add(currentVertex.GoLeft());
            newVertices.add(currentVertex.GoRight());
            
            
            for (Vertex newVertex : newVertices){                  
                if(!currentVertex.getPath().contains(newVertex)){
                    newVertex.setPath(currentVertex.getPath());
                    if (!visited.contains(newVertex)){   
                        int k=0;
                        newVertex.getState().setG(currentVertex.getState().getG()+1);
                        newVertex.getState().setH(H(newVertex.getState().getA(),goal));
                        newVertex.getState().setF(newVertex.getState().getG()+newVertex.getState().getH());
                        for (Vertex v : open){
                            if (v.equals(newVertex))
                                k++;
                            if (k>0)
                                break;
                        }
                        if (k==0)
                            open.add(newVertex);                       
                    }
                    visited.add(newVertex);                                        
                }                                             
            }      
	}
//        System.out.println("\nKet qua: " + dem);
    }        
}

class Sort implements Comparator<Vertex>{
    @Override
    public int compare(Vertex a, Vertex b){
        if (a.getState().getF() > b.getState().getF())
            return 1;
        else{
            if (a.getState().getF() < b.getState().getF())
                return -1;
            return 0;
        }
    }
}
