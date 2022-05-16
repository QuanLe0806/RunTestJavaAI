/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TACI;

/**
 *
 * @author minhd
 */
public class State {
    private int[][] a;
    private int g,h,f;  

    public State(int[][] a, int g, int h, int f) {
        this.a = a;
        this.g = g;
        this.h = h;
        this.f = f;
    }

    public int[][] getA() {
        return a;
    }

    public void setA(int[][] a) {
        this.a = a;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
    
    public State GoTop(){
        int[][] b= new int[3][3];
        int kt=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (b[i][j] == 0){
                    if (i == 0){
                        kt=1;
                        break;
                    }
                    else{
                        b[i][j]=b[i-1][j];
                        b[i-1][j]=0;
                        kt=1;
                        break;
                    }
                }
            }
            if (kt==1)
                break;
        }
        return new State(b,g,h,f);
    }
    
    public State GoBottom(){
        int[][] b= new int[3][3];
        int kt=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (b[i][j] == 0){
                    if (i == 2){
                        kt=1;
                        break;
                    }
                    else{
                        b[i][j]=b[i+1][j];
                        b[i+1][j]=0;
                        kt=1;
                        break;
                    }
                }
            }
            if (kt==1)
                break;
        }
        return new State(b,g,h,f);
    }
    
    public State GoLeft(){
        int[][] b= new int[3][3];
        int kt=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (b[i][j] == 0){
                    if (j == 0){
                        kt=1;
                        break;
                    }
                    else{
                        b[i][j]=b[i][j-1];
                        b[i][j-1]=0;
                        kt=1;
                        break;
                    }
                }
            }
            if (kt==1)
                break;
        }
        return new State(b,g,h,f);
    }
    
    public State GoRight(){
        int[][] b= new int[3][3];
        int kt=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (b[i][j] == 0){
                    if (j == 2){
                        kt=1;
                        break;
                    }
                    else{
                        b[i][j]=b[i][j+1];
                        b[i][j+1]=0;
                        kt=1;
                        break;
                    }
                }
            }
            if (kt==1)
                break;
        }
        return new State(b,g,h,f);
    }
    public void Xuat(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                System.out.print(a[i][j] + " ");
            System.out.println();
        }
        System.out.println("(g= " + g + ", h= " + h + ", f= " + f + ")");
    }
    
    public boolean CheckArray(int[][] c,int[][] d){
        int d1=0,d2=9;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++)
                if (c[i][j] == d[i][j])
                    d1++;
        }
        if (d1==d2)
            return true;
        else
            return false;
    } 
    
    @Override
    public boolean equals(Object obj) {
        State s = (State) obj;        
        
	if (!(s instanceof State))
            return false;	

        return ((CheckArray(s.getA(), this.getA())) && (s.getF() == this.getF()) && (s.getG() == this.getG()) && (s.getH() == this.getH()));
    }
    
    @Override
    public String toString(){
        String s="";
//        for (int i=0;i<3;i++){
//            for (int j=0;j<3;j++)
//                s+=String.valueOf(a[i][j]) + " ";
//            s+="\n";
//        }
        return s + "(g= " + g + ", h=" + h + ", f=" + f +");";      
    }
}
