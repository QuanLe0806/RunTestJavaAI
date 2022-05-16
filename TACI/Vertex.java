/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TACI;

import java.util.List;

/**
 *
 * @author minhd
 */
public class Vertex {
    private State state;
    private Path<Vertex> path;
    
    
    public Vertex(State state){
        this.state = state;        
        this.path = new Path<>();
    }
    
    
    public State getState(){
        return state;
    }        
    
    public Vertex GoTop(){
	return new Vertex(state.GoTop());
    }
    
    public Vertex GoBottom(){
	return new Vertex (state.GoBottom());
    }
    
    public Vertex GoLeft(){
	return new Vertex (state.GoLeft());
    }
    
    public Vertex GoRight(){
	return new Vertex(state.GoRight());
    }          
    
    public void addToPath() {
        this.path.addVertex(this);
    }
    
    public List<Vertex> getPath(){
        return path.getPath();
    }
    
    public void setPath(List<Vertex> path){
        this.path.setPath(path);
    }

    public void printPath(){
        path.printPath();
    }        

    @Override
    public boolean equals(Object obj) {
        Vertex v = (Vertex) obj;        
        
	if (!(v instanceof Vertex))
            return false;	
        return (this.getState().equals(v.getState()));
    }        
    
    @Override
    public String toString(){
        return state.toString();
    }
}
