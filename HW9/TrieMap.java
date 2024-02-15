import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Implementation of a {@link AbstractTrieMap} using a trie.
 * <p>
 * There are two main variables to keep in mind regarding a running time for a trie implementation:
 * N: the number of nodes in the trie
 * H: the height of the trie (length of the longest key)
 * <p>
 * Note that N is bounded by H * (# of keys/values in the mapping), but it will typically be
 * significantly smaller due to key overlap.
 * <p>
 * Keys are of type {@link CharSequence}. This allows the implementation to make assumptions as to
 * how data is stored, since we cannot break a key down into individual characters if the key is
 * not made up of characters to begin with. For simplicity, keys must consist entirely of lowercase
 * letters. The empty string is a valid key.
 * <p>
 * Null keys are not permitted because keys correspond directly to paths in a trie. Null values are
 * not permitted because rather than using a sentinel node, the implementation uses null to
 * indicate that a node does not have an associated value.
 *
 * @param <V> the type of mapped values
 */
public class TrieMap<V> extends AbstractTrieMap<V> {

    /**
     * The size of our key alphabet or character set. Here, we use 26 for the standard lowercase
     * alphabet. We might like to be more flexible and support full alphanumeric or even full ASCII
     * but that would increase our overhead. Since we know something about our use case, we can
     * stick to the lowercase alphabet and keep our overhead down.
     */
    private static final int BRANCH_FACTOR = 26;

    /**
     * The root node of the trie.
     */
    private Node<V> root;

    /**
     * Returns the root node that represents the TrieMap
     * Provides the visualizer access to the root
     */
    public Node<V> getRoot() {
        return root;
    }
    
    /**
     * The size of the trie.
     */
    private int size;

    /**
     * Constructs an empty TrieMap.
     */
    public TrieMap() {
        root = new Node<>(null);
    }

    /**
     * Converts a {@code char} into an array index.
     * <p>
     * Effectively maps {@code a -> 0, b -> 1, ..., z -> 25}.
     *
     * @param c the character
     * @return the array index corresponding to the specified character
     * @throws IllegalArgumentException if the specified character is not valid as an index
     */
    private static int convertToIndex(char c) {
        if (c < 'a' || c > 'z') {
            throw new IllegalArgumentException("Character must be in the range [a..z]");
        }
        return c - 'a';
    }

    /**
     * Converts an array index into a {@code char} in the key.
     * <p>
     * Effectively maps {@code 0 -> a, b -> 1, ..., 25 -> z}.
     *
     * @param i the index
     * @return the character corresponding to the specified array index
     * @throws IllegalArgumentException if the specified index is out of bounds
     */
    private static char convertToChar(int i) {
        if (i < 0 || i >= BRANCH_FACTOR) {
            throw new IllegalArgumentException("Index must be in the range [0..BRANCH_FACTOR]");
        }
        return (char) (i + 'a');
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     * @implSpec This method should run in O(1) time.
     */
    @Override
    public int size() {
        return size;
    }

    /* NOTE: Please do not modify anything above this line. */

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @implSpec This method should run in O(key length) time.
     * @implSpec This method should use O(1) additional space.
     * That is, O(1) space besides the nodes necessary to insert key into the trie.
     */
    @Override
    public V put(CharSequence key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("the key is null");
        }
        if (value == null) {
            throw new IllegalArgumentException("the value is null");
        }
        Node<V> curr = root;
        int index = 0;
        while (index < key.length()) {
            if (curr.children == null) {
                curr.children = new Node[26];
            }
            if (curr.getChild(key.charAt(index)) == null) {
                curr.setChild(key.charAt(index), new Node<>(null, curr));
            }
            curr = curr.getChild(key.charAt(index));
            index++;
        }
        if (curr.hasValue()) {
            V v = curr.value;
            curr.value = value;
            return v;
        }
        curr.value = value;
        size++;
        return null;
        // You'll want to use a Node reference to iteratively walk down the trie
        // to where you want to store the value. Remember, you can reach a
        // Node's child for a particular char value by using that char and the
        // convertToIndex helper to index into the node's children array.
        //
        // Don't forget to update the size appropriately!
        //
        // NOTE: you should return the previous value associated with key, or null if there
        // was no mapping for key.
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @implSpec This method should run in O(H) time.
     * @implSpec This method should use O(1) space.
     */
    @Override
    public V get(CharSequence key) {
        if (key == null) {
            throw new IllegalArgumentException("the key is null");
        }
        Node<V> curr = root;
        int index = 0;
        while (index < key.length()) {
            if (curr == null) {
                return null;
            }
            curr = curr.getChild(key.charAt(index));
            index++;
        }
        return curr.value;
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @implSpec This method should run in O(H) time.
     * @implSpec This method should use O(1) space.
     */
    @Override
    public boolean containsKey(CharSequence key) {
        if (key == null) {
            throw new IllegalArgumentException("the key is null");
        }
        Node<V> curr = root;
        int index = 0;
        while (index < key.length()) {
            if (curr == null) {
                return false;
            }
            curr = curr.getChild(key.charAt(index));
            index++;
        }
        if (curr == null) {
            return false;
        }
        return (curr.value != null);
    }

    /**
     * @throws IllegalArgumentException if the value provided is null
     * @implSpec This method should run in O(N) time.
     */
    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("the key is value");
        }
        if (!root.hasChildren()) {
            return false;
        }
        return containsV(root, value);

        // A simple tree search manages to be efficient by just
        // traversing the entire tree once. You can do this recursively, or
        // iteratively with a queue or a stack. If you use a stack, use the
        // Deque class because the Stack class is deprecated.
    }
    boolean containsV(Node<V> node, Object value) {
        if (node.getValue() == value) {
            return true;
        }
        if (node.hasChildren()) {
            for (Node<V> child : node.getChildren()) {
                if (child != null) {
                    if (containsV(child, value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @implSpec This method should run in O(H) time.
     * @implSpec This method should use O(1) space.
     */
    @Override
    public V remove(CharSequence key) {
        if (key == null) {
            throw new IllegalArgumentException("the key is null");
        }
        if (!containsKey(key)) {
            return null;
        }
        Node<V> curr = root;
        V v = null;
        int index = 0;
        while (index < key.length()) {
            if (curr.getChild(key.charAt(index)) == null) {
                return null;
            }
            curr = curr.getChild(key.charAt(index));
            index++;
        }
        v = curr.value;
        curr.value = null;
        size--;
        int k = key.length() - 1;
        while (k > -1 && !curr.hasValue() && !curr.hasChildren()) {
            curr.parent.setChild(key.charAt(k), null);
            curr = curr.parent;
            k--;
        }
        if (size == 0) {
            root.children = null;
        }
        return v;
    }

    /**
     * @implSpec This method should run in O(1) time.
     */
    @Override
    public void clear() {
        root.children = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The {@link CharSequence} keys of the {@link java.util.Map.Entry} instances in the resulting
     * iteration are mutable and should not be referenced directly. Instead, one should call
     * {@link CharSequence#toString()} on the key to get an immutable reference.
     * <p>
     * The iterator must produce entries in lexicographic order. For example, {@code ("party", 99),
     * ("pen", 24), ("penguin", 2), ("q", 17)}
     * <p>
     * This method is for extra kudos, which is worth no points (but many pats on the back).
     * To receive full kudos, your implementation must satisfy the asymptotic complexities for
     * running time and space. An optimal implementation will use space proportional to at most the
     * height of the trie.
     *
     * @implSpec This method should run in O(H) time.
     * @implSpec The returned iterator should have O(H) operations.
     * @implSpec The returned iterator should use O(H) space.
     * @implNote For partial kudos, the space usage of this method can be relaxed to O(V).
     * @implNote You will receive no kudos if you dump all of the elements of the trie into a
     * collection and return an iterator over that collection.
     */
    @Override
    public Iterator<Entry<CharSequence, V>> entryIterator() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Carrier for a value and an array of children.
     * You MAY modify this class if you want.
     */
    static class Node<V> {
        private Node<V>[] children;
        private V value;
        private Node<V> parent;

        Node(V value) {
            this.value = value;
            this.children = null;
        }
        Node(V value, Node<V> parent) {
            this.value = value;
            this.children = null;
            this.parent = parent;
        }

        @SuppressWarnings("unchecked")
        public void initChildren() {
            this.children = (Node<V>[]) new Node<?>[BRANCH_FACTOR];
        }

        /**
         * Returns the children array of the node
         * Used by the visualizer to draw out the trie
         */
        public Node<V>[] getChildren() {
            return children;
        }

        /**
         * @return {@code true} if this node has child nodes
         */
        public boolean hasChildren() {
            return children != null && Arrays.stream(children).anyMatch(Objects::nonNull);
        }

        /**
         * @param c the character
         * @return the child node for the specified character, or {@code null} if there is no such
         * child
         */
        public Node<V> getChild(char c) {
            if (children == null) {
                return null;
            }
            return children[convertToIndex(c)];
        }

        /**
         * Sets the child node corresponding to the specified character to the specified node.
         *
         * @param c    the character corresponding to the child to set
         * @param node the node to add as a child
         */
        public void setChild(char c, Node<V> node) {
            if (children == null) {
                initChildren();
            }
            children[convertToIndex(c)] = node;
        }

        /**
         * @return {@code true} if this node has a value
         */
        public boolean hasValue() {
            return value != null;
        }

        /**
         * @return the value at this node
         */
        public V getValue() {
            return value;
        }
    }
}
