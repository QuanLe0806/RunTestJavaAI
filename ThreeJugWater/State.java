/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThreeJugWater;

import java.util.Objects;

public class State {
	int jug_1;
	int jug_2;
	int jug_3;

	
	public State(int a,int b,int c) {
	    super();
	    this.jug_1 = a;
	    this.jug_2 = b;
	    this.jug_3 = c;
	}
	
	public int getA() { return jug_1; }
	public int getB() { return jug_2; }
	public int getC() { return jug_3; }
	public void fillA(int aLimit) { jug_1 = aLimit; }
	public void fillB(int bLimit) { jug_2 = bLimit; }
	public void fillC(int cLimit) { jug_3 = cLimit; }
	
	@Override
	public String toString() { return ("("+this.getA()+", "+this.getB()+", "+this.getC()+")"); }
	
	@Override
	public boolean equals(Object object) { 
	    if (this == object) return true;
	    if (!(object instanceof State)) return false;
	    State state = (State) object;
	    return jug_1 == state.jug_1 && jug_2 == state.jug_2 && jug_3 == state.jug_3;
	}

	@Override
	public int hashCode() { return Objects.hash(jug_1, jug_2, jug_3); }
}
