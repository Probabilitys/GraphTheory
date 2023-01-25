import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphTraversal {

    /**
     * Performs depth-first search of the unknown portion of Graph g starting at Vertex u.
     *
     * @param g Graph instance
     * @param u Vertex of graph g that will be the source of the search
     * @param known is a set of previously discovered vertices
     * @param forest is a map from nonroot vertex to its discovery edge in DFS forest
     *
     * As an outcome, this method adds newly discovered vertices (including u) to the known set,
     * and adds discovery graph edges to the forest.
     */
    public static <V,E> void DFS(
            Graph<V,E> g,
            Vertex<V> u,
            Set<Vertex<V>> known,
            Map<Vertex<V>, Edge<E>> forest
    ) {
        known.add(u);                              // u has been discovered
        for (Edge<E> e : g.outgoingEdges(u)) {     // for every outgoing edge from u
            Vertex<V> v = g.opposite(u, e);
            if (!known.contains(v)) {
                forest.put(v, e);                      // e is the tree edge that discovered v
                DFS(g, v, known, forest);              // recursively explore from v
            }
        }
    }

    /**
     * Performs DFS for the entire graph and returns the DFS forest as a map.
     *
     * @return map such that each nonroot vertex v is mapped to its discovery edge
     * (vertices that are roots of a DFS trees in the forest are not included in the map).
     */
    public static <V,E> Map<Vertex<V>, Edge<E>> DFSComplete(Graph<V,E> g) {
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Edge<E>> forest = new HashMap<>();
        for (Vertex<V> u: g.vertices())
            if (!known.contains(u))
                DFS(g, u, known, forest);
        return forest;
    }

    /**
     * Performs depth-first search of the entire Graph g starting at Vertex u.
     * @param g Graph instance
     * @param u Vertex of graph g that will be the starting point of the search
     */
    public static <V,E> Map<Vertex<V>, Edge<E>> DFSStartFrom(Graph<V,E> g, Vertex<V> u){
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Edge<E>> forest = new HashMap<>();
        known.add(u);                              // u has been discovered
        for (Edge<E> e : g.outgoingEdges(u)) {     // for every outgoing edge from u
            Vertex<V> v = g.opposite(u, e);
            if (!known.contains(v)) {
                forest.put(v, e);                      // e is the tree edge that discovered v
                DFS(g, v, known, forest);              // recursively explore from v
            }
        }
        return forest;
    }

    /**
     * Performs breadth-first search of the undiscovered portion of Graph g starting at Vertex s.
     *
     * @param g Graph instance
     * @param s Vertex of graph g that will be the source of the search
     * @param known is a set of previously discovered vertices
     * @param forest is a map from nonroot vertex to its discovery edge in DFS forest
     *
     * As an outcome, this method adds newly discovered vertices (including s) to the known set,
     * and adds discovery graph edges to the forest.
     */
    public static <V,E> void BFS(
            Graph<V,E> g,
            Vertex<V> s,
            Set<Vertex<V>> known,
            Map<Vertex<V>,Edge<E>> forest
    ) {
        PositionalList<Vertex<V>> level = new LinkedPositionalList<>();
        known.add(s);
        level.addLast(s);  // first level includes only s
        while (!level.isEmpty()) {
            PositionalList<Vertex<V>> nextLevel = new LinkedPositionalList<>();
            for (Vertex<V> u: level)
                for (Edge<E> e: g.outgoingEdges(u)) {
                    Vertex<V> v = g.opposite(u, e);
                    if (!known.contains(v)) {
                        known.add(v);
                        forest.put(v,e);
                        nextLevel.addLast(v);
                    }
                }
            level = nextLevel;
        }
    }

    /**
     * Performs BFS for the entire graph and returns the BFS forest as a map.
     *
     * @return map such that each nonroot vertex v is mapped to its discovery edge
     * (vertices that are roots of a BFS trees in the forest are not included in the map).
     */
    public static <V,E> Map<Vertex<V>,Edge<E>> BFSComplete(Graph<V,E> g) {
        Map<Vertex<V>,Edge<E>> forest = new HashMap<>();
        Set<Vertex<V>> known = new HashSet<>();
        for (Vertex<V> u : g.vertices())
            if (!known.contains(u))
                BFS(g, u, known, forest);
        return forest;
    }

    public static <V,E> PositionalList<Edge<E>> path(
            Graph<V,E> g,
            Vertex<V> u,
            Vertex<V> v
    ) {
        PositionalList<Edge<E>> path = new LinkedPositionalList<>();
        Map<Vertex<V>, Edge<E>> forest = DFSStartFrom(g, u);
        if (forest.get(v) != null) {           // v was discovered during the search
            Vertex<V> walk = v;                  // we construct the path from back to front
            while (walk != u) {
                Edge<E> edge = forest.get(walk);
                path.addFirst(edge);               // add edge to *front* of path
                walk = g.opposite(walk, edge);     // repeat with opposite endpoint
            }
        }
        return path;
    }

    /** a main method to demonstrate the algorithms*/
    public static void main(String[] args) {
        AdjacencyMapGraph<String, String> graph =
                new AdjacencyMapGraph<>(false);
        Vertex<String> u = graph.insertVertex("u");
        Vertex<String> v = graph.insertVertex("v");
        Vertex<String> w = graph.insertVertex("w");
        Vertex<String> z = graph.insertVertex("z");
        Edge<String> e = graph.insertEdge(u, v, "e");
        Edge<String> g = graph.insertEdge(u, w, "g");
        Edge<String> f = graph.insertEdge(v, w, "f");
        Edge<String> h = graph.insertEdge(w, z, "h");
        Map<Vertex<String>,Edge<String>> m = BFSComplete(graph);
        for (Vertex<String> k: m.keySet()) {
            System.out.println(k.getElement());
        }
        for (Edge<String> k: m.values()) {
            System.out.println(k.getElement());
        }
        System.out.println("path from u to z:");
        for (Edge<String> k: path(graph, u, z)) {
            System.out.println(k.getElement());
        }
    }

}
