package TSP;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static int[][]  graph;
    public static void main(String[] args){
        String fileName = "C:\\Users\\Admin\\Desktop\\AI\\Test\\TSP\\graph.txt"; // ở đây nếu không chạy được thỉ đổi lại cái path sang là đường đãn gốc trong máy để chạy 
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String firstLine = scanner.nextLine();
            String[] data = firstLine.split(" "); 
            int number = data.length;
            scanner.close();
            scanner = new Scanner(new File(fileName));
            graph = new int[number][number];
            for(int row =0; row < number; row++)
            {
                for(int column =0; column < number; column++)
                    graph[row][column] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Stack<Integer> bestPath;
        System.out.println("DFS: ");
        DFS DfsSols = new DFS(graph);
        System.out.println("Path cost: " + DfsSols.getShortestPathCost());
        bestPath = DfsSols.getShortestPath();
        System.out.print("Path Taken: " + bestPath.pop());
        while(!bestPath.isEmpty())
            System.out.print("-> " + bestPath.pop() );
        System.out.println();
        System.out.println();
        DfsSols = null;
    }
}
