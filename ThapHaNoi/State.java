/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThapHaNoi;

/**
 *
 * @author minhd
 */
public class State {
    private int[][] a;
    private int n,g,h,f;    

    public State(int[][] a, int n, int g, int h, int f) {
        this.a = a;
        this.n = n;
        this.g = g;
        this.h = h;
        this.f = f;
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

    public int[][] getA() {
        return a;
    }

    public void setA(int[][] a) {
        this.a = a;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    
    public int H(){
        int m=0,k=0;
        for (int i=0;i<n;i++){
            if (a[i][2] == i+1)
                m++;
            else{
                if (a[i][2] != 0)
                    k++;
            }                
        }
        return n-m+k;
    }
    
    public State FormAToB(){
        int[][] b=new int[n][3];
        for (int i=0;i<n;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        int kt=0;
        for (int i=0;i<n;i++){
            if (b[i][0] != 0){
                for (int k=n-1;k>=0;k--){
                    if (b[k][1] == 0){
                        if (k==n-1){
                            b[k][1]=b[i][0];
                            b[i][0]=0;
                            kt=1;
                            break;
                        }
                        else{
                            if (b[k+1][1] > b[i][0]){
                                b[k][1]=b[i][0];
                                b[i][0]=0;
                                kt=1;
                                break;
                            }                                
                        }
                    }
                }
                if (kt==1){
                    break;
                }                    
            }
        }
        return new State(b,n,g,h,f);
    }
    
    public State FormAToC(){
        int[][] b=new int[n][3];
        for (int i=0;i<n;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        int kt=0;
        for (int i=0;i<n;i++){
            if (b[i][0] != 0){
                for (int k=n-1;k>=0;k--){
                    if (b[k][2] == 0){
                        if (k==n-1){
                            b[k][2]=b[i][0];
                            b[i][0]=0;
                            kt=1;
                            break;
                        }
                        else{
                            if (b[k+1][2] > b[i][0]){
                                b[k][2]=b[i][0];
                                b[i][0]=0;
                                kt=1;
                                break;
                            }                                
                        }
                    }
                }
                if (kt==1){
                    break;
                }                    
            }
        }
        return new State(b,n,g,h,f);
    }
    
    public State FormBToC(){
        int[][] b=new int[n][3];
        for (int i=0;i<n;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        int kt=0;
        for (int i=0;i<n;i++){
            if (b[i][1] != 0){
                for (int k=n-1;k>=0;k--){
                    if (b[k][2] == 0){
                        if (k==n-1){
                            b[k][2]=b[i][1];
                            b[i][1]=0;
                            kt=1;
                            break;
                        }
                        else{
                            if (b[k+1][2] > b[i][1]){
                                b[k][2]=b[i][1];
                                b[i][1]=0;
                                kt=1;
                                break;
                            }                                
                        }
                    }
                }
                if (kt==1){
                    break;
                }                    
            }
        }
        return new State(b,n,g,h,f);
    }
    
    public State FormBToA(){
        int[][] b=new int[n][3];
        for (int i=0;i<n;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        int kt=0;
        for (int i=0;i<n;i++){
            if (b[i][1] != 0){
                for (int k=n-1;k>=0;k--){
                    if (b[k][0] == 0){
                        if (k==n-1){
                            b[k][0]=b[i][1];
                            b[i][1]=0;
                            kt=1;
                            break;
                        }
                        else{
                            if (b[k+1][0] > b[i][1]){
                                b[k][0]=b[i][1];
                                b[i][1]=0;
                                kt=1;
                                break;
                            }                                
                        }
                    }
                }
                if (kt==1){
                    break;
                }                    
            }
        }
        return new State(b,n,g,h,f);
    }
    
    public State FormCToB(){
        int[][] b=new int[n][3];
        for (int i=0;i<n;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        int kt=0;
        for (int i=0;i<n;i++){
            if (b[i][2] != 0){
                for (int k=n-1;k>=0;k--){
                    if (b[k][1] == 0){
                        if (k==n-1){
                            b[k][1]=b[i][2];
                            b[i][2]=0;
                            kt=1;
                            break;
                        }
                        else{
                            if (b[k+1][1] > b[i][2]){
                                b[k][1]=b[i][2];
                                b[i][2]=0;
                                kt=1;
                                break;
                            }                                
                        }
                    }
                }
                if (kt==1){
                    break;
                }                    
            }
        }
        return new State(b,n,g,h,f);
    }
    
    public State FormCToA(){
        int[][] b=new int[n][3];
        for (int i=0;i<n;i++){
            for (int j=0;j<3;j++)
                b[i][j]=a[i][j];
        }
        int kt=0;
        for (int i=0;i<n;i++){
            if (b[i][2] != 0){
                for (int k=n-1;k>=0;k--){
                    if (b[k][0] == 0){
                        if (k==n-1){
                            b[k][0]=b[i][2];
                            b[i][2]=0;
                            kt=1;
                            break;
                        }
                        else{
                            if (b[k+1][0] > b[i][2]){
                                b[k][0]=b[i][2];
                                b[i][2]=0;
                                kt=1;
                                break;
                            }                                
                        }
                    }
                }
                if (kt==1){
                    break;
                }                    
            }
        }
        return new State(b,n,g,h,f);
    }
    
    public void Xuat(){
        for (int i=0;i<n;i++){
            for (int j=0;j<3;j++)
                System.out.print(a[i][j] + " ");
            System.out.println();
        }
        System.out.println("(g= " + g + ", h= " + h + ", f= " + f + ")");
    }
    
    public boolean CheckArray(int[][] c,int[][] d){
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
//        for (int i=0;i<n;i++){
//            for (int j=0;j<3;j++)
//                s+=String.valueOf(a[i][j]) + " ";
//            s+="\n";
//        }
        return s + "(g= " + g + ", h=" + h + ", f=" + f +")";      
    }
}
