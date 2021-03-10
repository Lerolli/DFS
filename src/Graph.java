import java.util.ArrayList;

public class Graph {
    int size;
    ArrayList<Edge> edges;

    public Graph(int size){
        this.size = size;
        edges = new ArrayList<>();
        for (int i = 0; i < size; i++){
            var edge = new Edge();
            edge.number = i;
            edge.neighbor = new ArrayList<>();
            edges.add(edge);
        }
    }

    public void MakeGraph(ArrayList<String> matrix){
        var array = new int[size][size];
        var k = 0;
        for (String str: matrix) {
            var tempArray = str.split(" ");
            for (var i = 0; i < size; i++){
                array[k][i] = Integer.parseInt(tempArray[i]);
            }
            k++;
        }
        var c = 0;

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++){
                if (array[i][j] == 1){
                    edges.get(i).neighbor.add(edges.get(j));
                }
            }
    }
}
