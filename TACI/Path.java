/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TACI;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author minhd
 */
public class Path<T> {
    private List<T> path;
    
    public Path(){
        path = new ArrayList<>();
    }
    
    public void addVertex(T vertex) {
        path.add(vertex);
    }
    
    public List<T> getPath(){
        return path;
    }
    
    public void setPath(List<T> path){
        this.path.addAll(path);
    }

    public void printPath(){
        for (int i = 0; i < path.size(); i++){
            System.out.print(path.get(i).toString() + "");
        }
        
        System.out.println();
    }    
}
