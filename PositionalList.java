import java.util.Iterator;

/**
 * Positional List ADT
 * a sequence of positions storing objects
 */

public interface PositionalList<E> extends Iterable<E> {

    /** Returns the number of elements. */
    public int size();

    /** Indicates whether the list is empty. */
    public boolean isEmpty();

    /** Returns the first position, or null if empty. */
    public Position<E> first() throws EmptyListException;

    /** Returns the last position, or null if empty. */
    public Position<E> last() throws EmptyListException;

    /** Returns the Position immedidately before p, or null if p is first. */
    public Position<E> before(Position<E> p)
            throws InvalidPositionException;

    /** Returns the Position immedidately after p, or null if p is last. */
    public Position<E> after(Position<E> p)
            throws InvalidPositionException;

    /** Add element e to the front and returns its new position. */
    public Position<E> addFirst(E e);

    /** Add element e to the end and returns its new position. */
    public Position<E> addLast(E e);

    /** Add element e immediately before p and returns its new position. */
    public Position<E> addBefore(Position<E> p, E e)
            throws InvalidPositionException;

    /** Add element e immediately after p and returns its new position. */
    public Position<E> addAfter(Position<E> p, E e)
            throws InvalidPositionException;

    /** Replaces the element at p and returns the old element. */
    public E set(Position<E> p, E e)
            throws InvalidPositionException;

    /** Remove the element at p and returns the removed element. */
    public E remove(Position<E> p)
            throws InvalidPositionException;

    /** Return an iterable instance for all the positions. */
    public Iterable<Position<E>> positionsIterable();

    /** Return an iterator for all the positions. */
    public Iterator<E> iterator();

    /** Return an iterator for all the elements. */
    public Iterator<Position<E>> positionsIterator();

}