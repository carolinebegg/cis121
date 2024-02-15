import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TrieMapTest {
    TrieMap<String> trie = new TrieMap<>();
    TrieMap<String> trieOne = new TrieMap<>();
    TrieMap<String> trieTwo = new TrieMap<>();
    TrieMap<String> trieThree = new TrieMap<>();
    TrieMap<String> abc = new TrieMap<>();

    @Before
    public void setup() {
        trieOne.put("pen", "1");

        trieTwo.put("pen", "1");
        trieTwo.put("penguin", "2");

        trieThree.put("pen", "1");
        trieThree.put("penguin", "2");
        trieThree.put("pencil", "3");

        abc.put("a", "1");
        abc.put("ab", "2");
        abc.put("abc", "3");
    }
    /** convertToIndex() tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testConvertToIndex() {
        trie.put("!", "1");
    }

    /** convertToChar() tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testConvertToChar() {
        trie.put("!", "1");
    }

    /** put() tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testPutNullKey() {
        trie = new TrieMap<>();
        trie.put(null, "$");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPutNullValue() {
        trie = new TrieMap<>();
        trie.put("penn", null);
    }
    @Test
    public void testPutEmptyStringKey() {
        trie = new TrieMap<>();
        trie.put("", "1");
        assertEquals(1, trie.size());
        assertEquals("1", trie.get(""));
    }
    @Test
    public void testPutEmptyStringValue() {
        trie = new TrieMap<>();
        trie.put("penn", "");
        assertEquals(1, trie.size());
        assertEquals("", trie.get("penn"));
    }
    @Test
    public void testPutEmptyTrie() {
        trie = new TrieMap<>();
        assertNull(trie.put("pen", "1"));
        assertEquals(1, trie.size());
    }
    @Test
    public void testPutSubString() {
        trie = new TrieMap<>();
        assertNull(trie.put("penguin", "2"));
        assertNull(trie.put("pen", "1"));
        assertEquals(2, trie.size());
    }
    @Test
    public void testPutSuperString() {
        trie = new TrieMap<>();
        assertNull(trie.put("pen", "1"));
        assertNull(trie.put("penguin", "2"));
        assertEquals(2, trie.size());
    }
    @Test
    public void testPutThreeStrings() {
        trie = new TrieMap<>();
        assertNull(trie.put("pen", "1"));
        assertNull(trie.put("penguin", "2"));
        assertNull(trie.put("pencil", "3"));
        assertEquals(3, trie.size());
    }
    @Test
    public void testPutReplaceString() {
        trie = new TrieMap<>();
        assertNull(trie.put("pen", "1"));
        assertEquals("1", trie.put("pen", "2"));
        assertEquals(1, trie.size());
    }
    /** get() tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testGetNullKey() {
        trie = new TrieMap<>();
        trie.get(null);
    }
    @Test
    public void testGetKeyInTrieBasic() {
        trie = new TrieMap<>();
        trie.put("pen", "1");
        assertEquals("1", trie.get("pen"));
    }
    @Test
    public void testGetKeyNotInTrieBasic() {
        trie = new TrieMap<>();
        trie.put("pen", "1");
        assertNull(trie.get("penguin"));
    }
    @Test
    public void testGetKeyInTrieSubString() {
        trie = new TrieMap<>();
        trie.put("pen", "1");
        trie.put("penguin", "2");
        assertEquals("1", trie.get("pen"));
        assertEquals("2", trie.get("penguin"));
    }
    @Test
    public void testGetKeyNotInTrieSubString() {
        trie = new TrieMap<>();
        trie.put("penguin", "2");
        assertNull(trie.get("pen"));
    }

    /** containsKey() tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testContainsKeyNullKey() {
        trie = new TrieMap<>();
        trie.containsKey(null);
    }
    @Test
    public void testContainsKeyEmptyTrie() {
        trie = new TrieMap<>();
        assertEquals(0, trie.size());
        assertFalse(trie.containsKey(""));
    }
    @Test
    public void testContainsKeyOne() {
        assertTrue(trieOne.containsKey("pen"));
        assertFalse(trieThree.containsKey("en"));
    }
    @Test
    public void testContainsKeyTwo() {
        assertTrue(trieTwo.containsKey("pen"));
        assertTrue(trieTwo.containsKey("penguin"));
        assertFalse(trieThree.containsKey("en"));
        assertFalse(trieThree.containsKey("guin"));
    }
    @Test
    public void testContainsKeyThree() {
        assertTrue(trieThree.containsKey("pen"));
        assertTrue(trieThree.containsKey("penguin"));
        assertTrue(trieThree.containsKey("pencil"));
        assertFalse(trieThree.containsKey("en"));
        assertFalse(trieThree.containsKey("guin"));
        assertFalse(trieThree.containsKey("cil"));
    }

    /** containsValue() tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testContainsValueNullKey() {
        trie = new TrieMap<>();
        trie.containsValue(null);
    }
    @Test
    public void testContainsValueEmptyTrie() {
        trie = new TrieMap<>();
        assertFalse(trie.containsValue(""));
    }
    @Test
    public void testContainsValueA() {
        trie = new TrieMap<>();
        trie.put("a", "1");
        assertTrue(trie.containsValue("1"));
        assertFalse(trie.containsValue("0"));
    }
    @Test
    public void testContainsValueAbc() {
        assertTrue(abc.containsValue("1"));
        assertTrue(abc.containsValue("2"));
        assertTrue(abc.containsValue("3"));
        assertFalse(abc.containsValue("0"));
    }
    @Test
    public void testContainsValueOne() {
        assertTrue(trieOne.containsValue("1"));
        assertFalse(trieOne.containsValue("2"));
    }
    @Test
    public void testContainsValueTwo() {
        assertTrue(trieTwo.containsValue("1"));
        assertTrue(trieTwo.containsValue("2"));
        assertFalse(trieTwo.containsValue("3"));
    }
    @Test
    public void testContainsValueThree() {
        assertTrue(trieThree.containsValue("1"));
        assertTrue(trieThree.containsValue("2"));
        assertTrue(trieThree.containsValue("3"));
        assertFalse(trieThree.containsValue("4"));
    }

    /** remove() tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullKey() {
        trie = new TrieMap<>();
        trie.remove(null);
    }
    @Test
    public void testRemoveNotInMap() {
        assertNull(trieOne.remove("penguin"));
    }
    @Test
    public void testRemoveOne() {
        trieOne.remove("pen");
        assertEquals(0, trieOne.size());
        assertFalse(trieOne.containsKey("pen"));
        assertFalse(trieOne.getRoot().hasChildren());
    }
    @Test
    public void testRemoveBranches() {
        trie = new TrieMap<>();
        trie.put("hello", "1");
        assertEquals(1, trie.size());
        trie.put("my", "2");
        assertEquals(2, trie.size());
        trie.put("name", "3");
        assertEquals(3, trie.size());
        trie.put("is", "4");
        assertEquals(4, trie.size());
        trie.put("caroline", "5");
        assertEquals(5, trie.size());
        trie.remove("hello");
        assertEquals(4, trie.size());
        assertFalse(trie.containsKey("hello"));
        trie.remove("caroline");
        assertEquals(3, trie.size());
        assertFalse(trie.containsKey("caroline"));
        trie.remove("name");
        assertEquals(2, trie.size());
        assertFalse(trie.containsKey("name"));
        trie.remove("is");
        assertEquals(1, trie.size());
        assertFalse(trie.containsKey("is"));
        trie.remove("my");
        assertEquals(0, trie.size());
        assertFalse(trie.containsKey("my"));
        assertFalse(trie.getRoot().hasChildren());
    }
    @Test
    public void testRemoveTwoBottomUp() {
        trieTwo.remove("penguin");
        assertEquals(1, trieTwo.size());
        assertFalse(trieTwo.containsKey("penguin"));
        assertTrue(trieTwo.containsKey("pen"));
        trieTwo.remove("pen");
        assertEquals(0, trieTwo.size());
        assertFalse(trieTwo.containsKey("pen"));
    }
    @Test
    public void testRemoveTwoTopDown() {
        trieTwo.remove("pen");
        assertFalse(trieTwo.containsKey("pen"));
        assertEquals(1, trieTwo.size());
        assertTrue(trieTwo.containsKey("penguin"));
        trieTwo.remove("penguin");
        assertEquals(0, trieTwo.size());
        assertFalse(trieTwo.containsKey("penguin"));
    }
    @Test
    public void testRemoveThreePen() {
        trieThree.remove("pen");
        assertEquals(2, trieThree.size());
        assertFalse(trieThree.containsKey("pen"));
        assertTrue(trieThree.containsKey("penguin"));
        assertTrue(trieThree.containsKey("pencil"));
    }
    @Test
    public void testRemoveThreePencil() {
        trieThree.remove("pencil");
        assertEquals(2, trieThree.size());
        assertFalse(trieThree.containsKey("pencil"));
        assertTrue(trieThree.containsKey("penguin"));
        assertTrue(trieThree.containsKey("pen"));
    }
    @Test
    public void testRemoveThreePenguin() {
        trieThree.remove("penguin");
        assertEquals(2, trieThree.size());
        assertFalse(trieThree.containsKey("penguin"));
        assertTrue(trieThree.containsKey("pen"));
        assertTrue(trieThree.containsKey("pencil"));
    }
    @Test
    public void testRemovePencilPenguin() {
        trie = new TrieMap<>();
        trie.put("penguin", "1");
        trie.put("pencil", "2");
        trie.remove("penguin");
        assertEquals(1, trie.size());
        assertFalse(trie.containsKey("penguin"));
        assertTrue(trie.containsKey("pencil"));
    }
    @Test
    public void testRemoveABC() {
        trie = new TrieMap<>();
        trie.put("a", "1");
        trie.put("ab", "2");
        trie.put("ac", "3");
        trie.remove("ab");
        assertEquals(2, trie.size());
        assertFalse(trie.containsKey("ab"));
        assertTrue(trie.containsKey("a"));
        assertTrue(trie.containsKey("ac"));
    }

    /** clear() tests **/

    @Test
    public void testClearEmptyTrieAddNew() {
        trie.put("pen", "1");
        trie.put("penguin", "2");
        trie.put("pencil", "3");
        trie.clear();
        trie.put("pen", "1");
        trie.put("penguin", "2");
        trie.put("pencil", "3");
        assertEquals(3, trie.size());
        assertTrue(trie.getRoot().hasChildren());
    }
    @Test
    public void testClearBasicTrie() {
        trie = new TrieMap<>();
        trie.put("penn", "$");
        trie.clear();
        assertFalse(trie.containsKey("penn"));
        assertEquals(0, trie.size());
        assertFalse(trie.getRoot().hasChildren());
    }
    @Test
    public void testClearBasicTrieAddNew() {
        trie = new TrieMap<>();
        trie.put("penn", "$");
        trie.clear();
        trie.put("pen", "1");
        trie.put("penguin", "2");
        trie.put("pencil", "3");
        assertEquals(3, trie.size());
        assertTrue(trie.getRoot().hasChildren());
    }
    @Test
    public void testClearPrefixTrie() {
        trie = new TrieMap<>();
        trie.put("penn", "$");
        trie.put("penguin", "!");
        trie.clear();
        assertFalse(trie.containsKey("penguin"));
        assertFalse(trie.containsKey("penn"));
        assertEquals(0, trie.size());
        assertFalse(trie.getRoot().hasChildren());
    }
    @Test
    public void testClearPrefixTrieAddNew() {
        trie = new TrieMap<>();
        trie.put("penn", "$");
        trie.put("penguin", "!");
        trie.clear();
        trie.put("pen", "1");
        trie.put("penguin", "2");
        trie.put("pencil", "3");
        assertEquals(3, trie.size());
        assertTrue(trie.getRoot().hasChildren());
    }
}
