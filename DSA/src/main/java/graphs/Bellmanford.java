package graphs;
import java.util.Arrays;
import java.util.Scanner;


public class Bellmanford {
    public int[][] edges;
    private int V ;
    private static int src;
    private static int[] dist;

    public Bellmanford() {
        Scanner sc = new Scanner(System.in);
        // Get number of vertices
        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();
        // Get source vertex
        System.out.print("Enter source vertex: ");
        src = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        edges = new int[E][3]; // each edge: u, v, wt

        System.out.println("Enter edges in format: u v wt");
        for (int i = 0; i < E; i++) {
            edges[i][0] = sc.nextInt(); // u
            edges[i][1] = sc.nextInt(); // v
            edges[i][2] = sc.nextInt(); // wt
        }
        dist = bellmanFordDistanceSet(V ,  edges , src);
    }

    public static int[] bellmanFordDistanceSet(int V, int[][] edges, int src) {

        // create a list of array for holding the distances

         int [] dist = new int[V];

         Arrays.fill(dist, Integer.MAX_VALUE); // acts as infinit

         // set source distance as 0

         dist[src] = 0 ;


         // relaxation part

        // if ( dist[src] + cost[src-dest] < dist[dest])  then relax


        for(int i=0; i<V; i++){ // relax n-1 times where n is the number of vertices
            // start from 0 so last should be n-2 , if n-1 is reached then negative cycle exists.
            for(int[] edge : edges){
                 int u = edge[0];
                 int v = edge[1];
                 int wt = edge[2]; // weight of u-v
                 if(dist[u] != Integer.MAX_VALUE  && dist[u] + wt < dist[v] ){
                     // relax

                     if( i == V-1){
                         return new int[]{-1}; // array with 1 element with -1
                     }

                     // else relax

                     dist[v] = dist[u] + wt;

                 }
            }
        }

        return dist; // return the distance array
    }

    public static void run(){
        // Output result
        if (dist.length == 1 && dist[0] == -1) {
            System.out.println("Negative weight cycle detected!");
        } else {
            System.out.println("Shortest distances from source " + src + ":");
            for (int i = 0; i < dist.length; i++) {
                System.out.println("Vertex " + i + " → " +
                        (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
            }
        }
    }



}
