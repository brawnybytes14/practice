import java.util.*;

class Graph {

    int V;
    ArrayList<ArrayList<Integer>> adj;

    public Graph(int V) {
        this.V = V;
        this.adj = new ArrayList<ArrayList<Integer>>(V);
        // System.out.println(adj.size());

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<Integer>());
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public int getDependencyCount() {
        int count = 0;
        for (int i = 0; i < V; i++) {
            count += this.adj.get(i).size();
        }
        return count;
    }

    public static void main(String[] args) {

        // code
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();

        while (t-- > 0) {
            int n = s.nextInt();
            int e = s.nextInt();
            Graph g = new Graph(n);
            // System.out.println(n);
            while (e-- > 0) {
                int u = s.nextInt();
                int v = s.nextInt();
                g.addEdge(u, v);
            }

            System.out.println(g.getDependencyCount());

        }
        s.close();
    }
}