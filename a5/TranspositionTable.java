package a5.ai;

import cms.util.maybe.Maybe;

import java.util.Optional;

/**
 * A transposition table for an arbitrary game. It maps a game state
 * to a search depth and a heuristic evaluation of that state to the
 * recorded depth. Unlike a conventional map abstraction, a state is
 * associated with a depth, so that clients can look for states whose
 * entry has at least the desired depth.
 *
 * @param <GameState> A type representing the state of a game.
 */
public class TranspositionTable<GameState> {

    /**
     * Information about a game state, for use by clients.
     */
    public interface StateInfo {

        /**
         * The heuristic value of this game state.
         */
        int value();

        /**
         * The depth to which the game tree was searched to determine the value.
         */
        int depth();
    }

    /**
     * A Node is a node in a linked list of nodes for a chaining-based implementation of a hash
     * table.
     *
     * @param <GameState>
     */
    static private class Node<GameState> implements StateInfo {
        /**
         * The state
         */
        GameState state;
        /**
         * The depth of this entry. >= 0
         */
        int depth;
        /**
         * The value of this entry.
         */
        int value;
        /**
         * The next node in the list. May be null.
         */
        Node<GameState> next;

        Node(GameState state, int depth, int value, Node<GameState> next) {
            this.state = state;
            this.depth = depth;
            this.value = value;
            this.next = next;
        }

        public int value() {
            return value;
        }

        public int depth() {
            return depth;
        }
    }

    /**
     * The number of entries in the transposition table.
     */
    private int size;

    /**
     * The buckets array may contain null elements.
     * Class invariant:
     * All transposition table entries are found in the linked list of the
     * bucket to which they hash, and the load factor is no more than 1.
     */
    private Node<GameState>[] buckets;

    // TODO 1: implement the classInv() method. You may also
    // strengthen the class invariant. The classInv()
    // method is likely to be expensive, so you may want to turn
    // off assertions in this file, but only after you have the transposition
    // table fully tested and working.
    boolean classInv() {
        double load_factor = (double)size/buckets.length;
        boolean found = true;

        for (int i = 0; i < buckets.length; i++){
            Node<GameState> n = buckets[i];

            while (n != null){
                int hash = n.state.hashCode();
                int index = Math.abs(hash) % buckets.length;
                if(index != i) found = false;
                n = n.next;
            }
        }

        return found && (load_factor <= 1);

    }

    @SuppressWarnings("unchecked")
    /** Creates: a new, empty transposition table. */
    TranspositionTable() {
        size = 0;
        // TODO 2
        buckets = (Node<GameState>[]) new Node<?>[5];
    }

    /** The number of entries in the transposition table. */
    public int size() {
        return size;
    }

    /**
     * Returns: the information in the transposition table for a given
     * game state, package in an Optional. If there is no information in
     * the table for this state, returns an empty Optional.
     */
    public Maybe<StateInfo> getInfo(GameState state) {
        // TODO 3
        assert classInv();
        int hash = state.hashCode();
        int i = Math.abs(hash) % buckets.length;
        Node<GameState> n = buckets[i];

        while (n != null){
            if (n.state.equals(state)) return Maybe.some(n);
            n = n.next;
        }

        return Maybe.none();
    }

    /**
     * Effect: Add a new entry in the transposition table for a given
     * state and depth, or overwrite the existing entry for this state
     * with the new state and depth. Requires: if overwriting an
     * existing entry, the new depth must be greater than the old one.
     */
    public void add(GameState state, int depth, int value) {
        // TODO 4
        assert classInv();
        if(buckets.length == size) {
            grow(buckets.length*2);
        }

        int hash = state.hashCode();
        int i = Math.abs(hash) % buckets.length;
        Node<GameState> n = buckets[i];
        boolean in = false;
        boolean dep = true;
        //overwriting existing entry w new state and depth

        while (n!=null){
            if ((n.state.equals(state)) && (depth > n.depth)){
                n.value = value;
                n.depth = depth;
                in = true;
            }
            if ((n.state.equals(state)) && (depth <= n.depth)) dep = false;
            n = n.next;
        }

        Node<GameState> add = new Node<GameState>(state, depth, value, null);
        //if n not in buckets[i] or buckets[i] is empty, in == false
        if ((in == false) && dep) {
            if (buckets[i] != null) buckets[i].next = add;
            else buckets[i] = add;
            size++;
        }

        assert classInv();

    }

    /**
     * Effect: Make sure the hash table has at least {@code target} buckets.
     * Returns true if the hash table actually resized.
     */
    private boolean grow(int target) {
        // TODO 5
        assert classInv();
        if (buckets.length > target) return false;
        else {
            Node<GameState>[] ret = (Node<GameState>[]) new Node<?>[buckets.length*2];
            for (int i = 0; i < buckets.length; i++){
                Node<GameState> n = buckets[i];
                while (n!=null){
                    int hash = n.state.hashCode();
                    int index = Math.abs(hash) % ret.length;
                    ret[index] = n;

                    n = n.next;
                }
            }
            buckets = ret;
            assert classInv();
            return true;
        }

    }

    // You may want to write some additional helper methods.


    /**
     * Estimate clustering. With a good hash function, clustering
     * should be around 1.0. Higher values of clustering lead to worse
     * performance.
     */
    double estimateClustering() {
        final int N = 500;
        int m = buckets.length, n = size;
        double sum2 = 0;
        for (int i = 0; i < N; i++) {
            int j = Math.abs((i * 82728353) % buckets.length);
            int count = 0;
            Node<GameState> node = buckets[j];
            while (node != null) {
                count++;
                node = node.next;
            }
            sum2 += count*count;
        }
        double alpha = (double)n/m;
        return sum2/(N * alpha * (1 - 1.0/m + alpha));
    }
}
