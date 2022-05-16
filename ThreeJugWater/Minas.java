/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThreeJugWater;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Minas {

	static ArrayList<State> nodes = new ArrayList<State>(); 
	static Stack<State> stack = new Stack<State>(); 
	static State currentState; 

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Nhập thể tích bình A: "); 
		int jug_A_Limit = reader.nextInt();
		
		System.out.println("Nhập thể tích bình B: ");
		int jug_B_Limit = reader.nextInt();
		
		System.out.println("Nhập thể tích bình C: ");
		int jug_C_Limit = reader.nextInt();
		System.out.println(" Nhập vào lượng nước cần đong: ");
		int target = reader.nextInt();
		
		reader.close();
				
		State startState = new State(0,0,0); 
		
		nodes.add(startState); 
		stack.push(startState); 
		
		State stateOne = new State(jug_A_Limit,0,0); 
		
		nodes.add(stateOne); 
		stack.push(stateOne);
		
		while(!stack.isEmpty()) { 

			currentState = (State) stack.peek(); 

			if(currentState.getA()==target || currentState.getB() == target || currentState.getC()==target) break;
			
			if(pour("a","b",jug_A_Limit,jug_B_Limit,jug_C_Limit)) continue;
			
			if(pour("a","c",jug_A_Limit,jug_B_Limit,jug_C_Limit)) continue; 
				
			if(pour("b","a",jug_A_Limit,jug_B_Limit,jug_C_Limit)) continue;
			
			if(pour("b","c",jug_A_Limit,jug_B_Limit,jug_C_Limit)) continue;
			
			if(pour("c","a",jug_A_Limit,jug_B_Limit,jug_C_Limit)) continue;
			
			if(pour("c","b",jug_A_Limit,jug_B_Limit,jug_C_Limit)) continue;
			
			if(fill(jug_A_Limit, jug_B_Limit, jug_C_Limit)) continue;
			
			if(empty(jug_A_Limit, jug_B_Limit, jug_C_Limit)) continue;

			stack.pop(); 
		}

		for (State obj : nodes) {
			System.out.println(obj);
		}

	}
	
	
	public static boolean empty(int aLimit, int bLimit, int cLimit) { 
		int a = currentState.getA();
		int b = currentState.getB();
		int c = currentState.getC();
		
		if(addNewState(0,b,c)) { return true; }
		if(addNewState(a,0,c)) { return true; }
		if(addNewState(a,b,0)) { return true; }
		return false; 
	}
	
	
	public static boolean fill(int aLimit, int bLimit, int cLimit) {
		int a = currentState.getA();
		int b = currentState.getB();
		int c = currentState.getC();
		
		if(addNewState(aLimit,b,c)) { return true; }
		if(addNewState(a,bLimit,c)) { return true; }
		if(addNewState(a,b,cLimit)) { return true; }
		return false; 
	}
	
	
	public static boolean pour(String jugOne, String jugTwo, int aLimit, int bLimit, int cLimit) {
		int a = currentState.getA(); 
		int b = currentState.getB();
		int c = currentState.getC();
		
		switch(jugOne) {
			case "a": 
				if(a > 0 && jugTwo == "b") { 
					if(a+b <= bLimit) { 
						return addNewState(0,a+b,c); 
							
					}else if(b < bLimit) {
						int newA, newB;
						int freeSpace = bLimit - b; 
						if(a >= freeSpace) { 
							newA = a - freeSpace;
							newB = bLimit;
							return addNewState(newA, newB, c);
						}else { 
							newA = 0;
							newB = a + b;
							return addNewState(newA, newB, c);
						}					
					}
					return false;
				}
				
				if(a>0 && jugTwo == "c") { 
					if(a+c <= cLimit) { 
						return addNewState(0,b,a+c);

					}else if(c < cLimit) { 
						int newA, newC;
						int freeSpace = cLimit - c; 
						if(a >= freeSpace) { 
							newA = a-freeSpace;
							newC = cLimit;
							return addNewState(newA,b,newC);		
						}else { 
							newA = 0;
							newC = a+c;
							return addNewState(newA,b,newC); 
						}
					}
					return false;
				}
				break;
				
			case "b": 
				if(b > 0 && jugTwo == "a") { 
					if(b+a <= aLimit) { 
						return addNewState(a+b,0,c); 
						
					}else if(a < aLimit) { 
						int newA, newB;
						int freeSpace = aLimit - a; 
						if(b >= freeSpace) { 
							newA = aLimit;
							newB = b - freeSpace;
							return addNewState(newA,newB,c); 
							
						}else { 
							newA = a+b;
							newB = 0;
							return addNewState(newA,newB,c); 
						}	
					}
					return false;
					
				}
				if(b>0 && jugTwo == "c") { 
					if(b+c <= cLimit) { 
						return addNewState(a,0,b+c); 
					}else if(c < cLimit) { 
						int newB, newC;
						int freeSpace = cLimit - c;
						if(b >= freeSpace) {
							newB = b-freeSpace;
							newC = cLimit;
							return addNewState(a, newB, newC);
						}else {
							newB = 0;
							newC = b+c;
							return addNewState(a, newB, newC);
						}
					}
					return false;
				}
				break;

			case "c":
				if(c > 0 && jugTwo == "a") {
					if(c+a <= aLimit) {
						return addNewState(a+c,b,0);
						
					}else if(a < aLimit) {
						int newA, newC;
						int freeSpace = aLimit - a;
						if(c >= freeSpace) {
							newA = aLimit;
							newC = c - freeSpace;
							return addNewState(newA, b, newC);
						}else {
							newA = a+c;
							newC = 0;
							return addNewState(newA, b, newC);
						}
					}
					return false;
				}
				if(c > 0 && jugTwo == "b") { 
					if(c+b <= bLimit) {
						return addNewState(a,b+c,0);
						
					}else if(b < bLimit) {
						int newB, newC;
						int freeSpace = bLimit - b;
						if(c >= freeSpace) {
							newB = bLimit;
							newC = c - freeSpace;
							return addNewState(a, newB, newC);
						}else {
							newB = b+c;
							newC = 0;
							return addNewState(a, newB, newC);
						}
					}
				}
				return false;
		}
		return false;
	}
	
	public static boolean addNewState(int a, int b, int c) {
		State newState = new State(a, b, c);
		if(nodes.contains(newState) == false) { 
			nodes.add(newState);
			stack.push(newState);
			return true; 
		}else { 
			return false;
		}
	}
}