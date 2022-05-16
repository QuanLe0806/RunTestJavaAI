
package ThuatToanAT;

/**
 *
 * @author Admin
 */
import java.util.*;  
import java.io.*;  
import java.lang.*;  
  
public class AT   
{  
 
static final int totalVertex = 9;  
int minimumDistance(int distance[], Boolean spSet[])  
{  

int m = Integer.MAX_VALUE, m_index = -1;  
  
for (int vx = 0; vx < totalVertex; vx++)  
{  
if (spSet[vx] == false && distance[vx] <= m)   
{  
m = distance[vx];  
m_index = vx;  
}  
}  
return m_index;  
  
}  
  

void printSolution(int distance[], int n)  
{  
System.out.println("Khoảng cách từ 0 đến các điểm khác: ");  
for (int j = 0; j < n; j++)  
System.out.println("đến " + j + " khoảng cách ngắn nhất là: " + distance[j]);  
}  
  

void dijkstra(int graph[][], int s)  
{  
int distance[] = new int[totalVertex]; 
Boolean spSet[] = new Boolean[totalVertex];  
  
for (int j = 0; j < totalVertex; j++)   
{  
distance[j] = Integer.MAX_VALUE;  
spSet[j] = false;  
}  
distance[s] = 0;  
   
for (int cnt = 0; cnt < totalVertex - 1; cnt++)   
{  

int ux = minimumDistance(distance, spSet);  
  
// the choosed vertex is marked as true  
// it means it is processed  
spSet[ux] = true;  
  
for (int vx = 0; vx < totalVertex; vx++)  
  

if (!spSet[vx] && graph[ux][vx] != -1 && distance[ux] != Integer.MAX_VALUE && distance[ux] + graph[ux][vx] < distance[vx])  
{  
distance[vx] = distance[ux] + graph[ux][vx];  
}  
}  
 
printSolution(distance, totalVertex);  
}  
  
// main method  
public static void main(String argvs[])  
{  

int grph[][] = new int[][] { 
    { -1, 3, -1, -1, -1, -1, -1, 7, -1 },  
    { 3, -1, 7, -1, -1, -1, -1, 10, 4 },  
    { -1, 7, -1, 6, -1, 2, -1, -1, 1 },  
    { -1, -1, 6, -1, 8, 13, -1, -1, 3 },  
    { -1, -1, -1, 8, -1, 9, -1, -1, -1 },  
    { -1, -1, 2, 13, 9, -1, 4, -1, 5 },  
    { -1, -1, -1, -1, -1, 4, -1, 2, 5 },  
    { 7, 10, -1, -1, -1, -1, 2, -1, 6 },  
    { -1, 4, 1, 3, -1, 5, 5, 6, -1 } };  
      
// creating an object of the class DijkstraExample  
AT obj = new AT();  
obj.dijkstra(grph, 0);  
}  
} 
