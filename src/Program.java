import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Program {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        var matrix = new ArrayList<String>();
        while(sc.hasNextLine()){
            matrix.add(sc.nextLine());
        }
        Graph g = new Graph(Integer.parseInt(matrix.get(0)));
        matrix.remove(0);
        g.MakeGraph(matrix);

        try(FileWriter writer = new FileWriter("result.txt", false))
        {
            String text = breadthFirstSearch(Graph.edges.get(0));
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


    }

    public static String breadthFirstSearch(Edge startEdge){
        var stack = new Stack<Edge>();
        var visited = new ArrayList<Edge>();

        stack.push(startEdge);

        while (stack.size() != 0){

            var edge = stack.pop();
            if (visited.contains(edge)){
                var tempEdges = visited.get(visited.indexOf(edge));
                var array = new HashSet<Integer>();
                while (tempEdges.ancestor.number != edge.number){
                    array.add(tempEdges.ancestor.number);
                    tempEdges = tempEdges.ancestor;
                }
                array.add(edge.number);
                var str = "A" + " " + array.toString();
                return str;

            }
            visited.add(edge);
            for (Edge e : edge.neighbor) {
                if (edge.ancestor != null && edge.ancestor.number == e.number)
                    continue;
                e.ancestor = edge;
                stack.push(e);
            }
        }
        return "N";
    }
}