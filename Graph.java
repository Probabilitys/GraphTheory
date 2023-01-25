/**
 * The Graph ADT
 */

public interface Graph<V, E> {

    /** Returns the number of vertices of the graph */
    int numVertices();

    /** Returns the number of edges of the graph */
    int numEdges();

    /** Returns the vertices of the graph as an iterable collection */
    Iterable<Vertex<V>> vertices();

    /** Returns the edges of the graph as an iterable collection */
    Iterable<Edge<E>> edges();

    /**
     * Returns the number of edges leaving vertex v
     * @throws IllegalArgumentException
     */
    int outDegree(Vertex<V> v) throws IllegalArgumentException;

    /**
     * Returns the number of edges for which vertex v is the destination
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    int inDegree(Vertex<V> v) throws IllegalArgumentException;

    /**
     * Returns an iterable collection of edges for which vertex v is the origin
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException;

    /**
     * Returns an iterable collection of edges for which vertex v is the destination
     * @throws IllegalArgumentException if v is not a valid vertex
     */
    Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException;

    /** Returns the edge from u to v, or null if they are not adjacent. */
    Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException;

    /**
     * Returns an array containing the two endpoint vertices of edge e
     * @throws IllegalArgumentException
     */
    Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException;

    /** Returns the vertex that is opposite vertex v on edge e. */
    Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException;

    /** Inserts and returns a new vertex with the given element. */
    Vertex<V> insertVertex(V element);

    /**
     * Inserts and returns a new edge between vertices u and v, storing given element.
     * @throws IllegalArgumentException if u or v are invalid vertices,
     * or if an edge already exists between u and v.
     */
    Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException;

    /** Removes a vertex and all its incident edges from the graph. */
    void removeVertex(Vertex<V> v) throws IllegalArgumentException;

    /** Removes an edge from the graph. */
    void removeEdge(Edge<E> e) throws IllegalArgumentException;
}
