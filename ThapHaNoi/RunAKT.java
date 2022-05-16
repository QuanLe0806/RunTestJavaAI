/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThapHaNoi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author minhd
 */
public class RunAKT {
    public static int n,g=0;
    public static int[][] a;
    
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
    
    public static boolean CheckArray(int[][] c,int[][] d){
        int d1=0,d2=n*3;
        for (int i=0;i<n;i++){
            for (int j=0;j<3;j++)
                if (c[i][j] == d[i][j])
                    d1++;
        }
        if (d1==d2)
            return true;
        else
            return false;
    }
    
    public static void main(String[] args) {
        
        Scanner sc= new Scanner(System.in);
        System.out.print("Nhập n= ");
        n=sc.nextInt();
//        n=3;

        a = new int[n][3];
        for (int i=0;i<n;i++)
            a[i][0]=i+1;
        for (int i=0;i<n;i++)
            a[i][1]=0;
        for (int i=0;i<n;i++)
            a[i][2]=0;
        int h = n,d=1;        

	Vertex initialVertex = new Vertex(new State(a,n,g,h,g+h));
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
            
            newVertices.add(currentVertex.FormAToB());
            newVertices.add(currentVertex.FormAToC());
            newVertices.add(currentVertex.FormBToA());
            newVertices.add(currentVertex.FormBToC()); 
            newVertices.add(currentVertex.FormCToA());
            newVertices.add(currentVertex.FormCToB());
            
            
            for (Vertex newVertex : newVertices){                  
                if(!currentVertex.getPath().contains(newVertex)){
                    newVertex.setPath(currentVertex.getPath());
                    if (!visited.contains(newVertex)){   
                        int k=0;
                        newVertex.getState().setG(currentVertex.getState().getG()+1);
                        newVertex.getState().setH(newVertex.getState().H());
                        newVertex.getState().setF(newVertex.getState().getG()+newVertex.getState().getH());
                        for (Vertex v : open){
                            if (v.equals(newVertex) || (CheckArray(v.getState().getA(), newVertex.getState().getA())==true))
                                k++;
                        }
                        if (k==0)
                            open.add(newVertex);                       
                    }
                    visited.add(newVertex);                                        
                }                                             
            }      
	}

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
