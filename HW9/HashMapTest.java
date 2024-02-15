import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class HashMapTest {
    HashMap<Integer, String> hash;
    HashMap.Entry<Integer, String>[] table;
    HashMap<Integer, String> helloWorld = new HashMap<>(4, 1);
    HashMap<Integer, String> nameCapTwoLoadFive = new HashMap<>(2, 5);

    @Before
    public void setup() {
        helloWorld.put(0, "hello");
        helloWorld.put(1, "world");

        nameCapTwoLoadFive.put(0, "my");
        nameCapTwoLoadFive.put(1, "name");
        nameCapTwoLoadFive.put(2, "is");
        nameCapTwoLoadFive.put(3, "caroline");
    }


    /** size() tests **/
    @Test
    public void testSizeZeroElements() {
        hash = new HashMap<>(4);
        assertEquals(0,hash.size());
        assertEquals(4,hash.getTable().length);
    }
    @Test
    public void testSizeOneElement() {
        hash = new HashMap<>(4);
        hash.put(0,"hello");
        assertEquals(1,hash.size());
        assertEquals(4,hash.getTable().length);
    }
    @Test
    public void testSizeTwoElements() {
        assertEquals(2,helloWorld.size());
        assertEquals(4,helloWorld.getTable().length);
    }

    /** get() tests **/
    @Test
    public void testGetValueNullKey() {
        hash = new HashMap<>(4);
        hash.put(null,"hello");
        assertNull(hash.get(0));
    }
    @Test
    public void testGetValueInMapNoCollision() {
        assertEquals("hello", helloWorld.get(0));
        assertEquals("world", helloWorld.get(1));
    }
    @Test
    public void testGetValueNotInMapNoCollision() {
        assertNull(helloWorld.get(2));
        assertNull(helloWorld.get(3));
    }
    @Test
    public void testGetValueInMapCollision() {
        assertEquals("my", nameCapTwoLoadFive.get(0));
        assertEquals("name", nameCapTwoLoadFive.get(1));
        assertEquals("is", nameCapTwoLoadFive.get(2));
        assertEquals("caroline", nameCapTwoLoadFive.get(3));
    }

    /** containsKey() tests **/
    @Test
    public void testContainsKeyTrueNullKey() {
        hash = new HashMap<>(4);
        hash.put(null,"hello");
        assertTrue(hash.containsKey(null));
    }
    @Test
    public void testContainsKeyFalseNullKey() {
        hash = new HashMap<>(4);
        hash.put(0,"hello");
        assertFalse(hash.containsKey(null));
    }
    @Test
    public void testContainsKeyTrueNoCollision() {
        assertTrue(helloWorld.containsKey(0));
        assertTrue(helloWorld.containsKey(1));
    }
    @Test
    public void testContainsKeyFalseNoCollision() {
        assertFalse(helloWorld.containsKey(2));
        assertFalse(helloWorld.containsKey(3));
    }
    @Test
    public void testContainsKeyTrueCollision() {
        assertTrue(nameCapTwoLoadFive.containsKey(0));
        assertTrue(nameCapTwoLoadFive.containsKey(1));
        assertTrue(nameCapTwoLoadFive.containsKey(2));
        assertTrue(nameCapTwoLoadFive.containsKey(3));
    }
    @Test
    public void testContainsKeyFalseCollision() {
        assertFalse(nameCapTwoLoadFive.containsKey(4));
        assertFalse(nameCapTwoLoadFive.containsKey(5));
    }

    /** put() tests **/
    @Test
    public void testPutNewEntryEmptyBucketNoResize() {
        hash = new HashMap<>(8);
        hash.put(0, "my");
        hash.put(1, "name");
        hash.put(2, "is");
        hash.put(3, "caroline");
        assertEquals("my", hash.get(0));
        assertEquals("name", hash.get(1));
        assertEquals("is", hash.get(2));
        assertEquals("caroline", hash.get(3));
        assertEquals(4,hash.size());
        assertEquals(8, hash.getTable().length);
    }
    @Test
    public void testPutNewEntryNonEmptyBucketNoResize() {
        assertEquals("my", nameCapTwoLoadFive.get(0));
        assertEquals("name", nameCapTwoLoadFive.get(1));
        assertEquals("is", nameCapTwoLoadFive.get(2));
        assertEquals("caroline", nameCapTwoLoadFive.get(3));
        assertEquals(4,nameCapTwoLoadFive.size());
        assertEquals(2,nameCapTwoLoadFive.getTable().length);
    }
    @Test
    public void testPutReplaceValueNoResize() {
        helloWorld.put(1, "caroline");
        assertEquals("hello", helloWorld.get(0));
        assertEquals("caroline", helloWorld.get(1));
        assertEquals(2, helloWorld.size());
        assertEquals(4, helloWorld.getTable().length);
    }
    @Test
    public void testPutReplaceValueCollisionNoResize() {
        nameCapTwoLoadFive.put(0, "your");
        nameCapTwoLoadFive.put(2, "isn't");
        assertEquals("your", nameCapTwoLoadFive.get(0));
        assertEquals("name", nameCapTwoLoadFive.get(1));
        assertEquals("isn't", nameCapTwoLoadFive.get(2));
        assertEquals("caroline", nameCapTwoLoadFive.get(3));
        assertEquals(4,nameCapTwoLoadFive.size());
        assertEquals(2,nameCapTwoLoadFive.getTable().length);
    }
    @Test
    public void testPutNewEntryResize() {
        hash = new HashMap<>(2, 1);
        hash.put(0, "hello");
        hash.put(1, "world");

        table = hash.getTable();
        assertEquals("hello",table[0].getValue());
        assertEquals("world",table[1].getValue());
        assertEquals(4,table.length);
        assertEquals(2,hash.size());
    }
    @Test
    public void testPutNewEntryCollisionResize() {
        hash = new HashMap<>(2,2);
        hash.put(0, "my");
        hash.put(1, "name");
        hash.put(2, "is");
        hash.put(3, "caroline");

        assertEquals(4, hash.getTable().length);
        assertEquals("my", hash.get(0));
        assertEquals("name", hash.get(1));
        assertEquals("is", hash.get(2));
        assertEquals("caroline", hash.get(3));
        assertEquals(4, hash.size());
    }

    /** remove() tests **/
    @Test
    public void testRemoveNoCollision() {
        helloWorld.remove(0);
        helloWorld.remove(1);

        assertFalse(helloWorld.containsValue("hello"));
        assertFalse(helloWorld.containsValue("world"));
        assertEquals(0, helloWorld.size());
    }
    @Test
    public void testRemoveNoCollisionReverse() {
        helloWorld.remove(1);
        helloWorld.remove(0);

        assertFalse(helloWorld.containsValue("hello"));
        assertFalse(helloWorld.containsValue("world"));
        assertEquals(0, helloWorld.size());
    }
    @Test
    public void testRemoveCollisionDepthTwo() {
        nameCapTwoLoadFive.remove(0);
        nameCapTwoLoadFive.remove(1);
        nameCapTwoLoadFive.remove(2);
        nameCapTwoLoadFive.remove(3);

        assertFalse(nameCapTwoLoadFive.containsValue("my"));
        assertFalse(nameCapTwoLoadFive.containsValue("name"));
        assertFalse(nameCapTwoLoadFive.containsValue("is"));
        assertFalse(nameCapTwoLoadFive.containsValue("caroline"));
        assertEquals(0, nameCapTwoLoadFive.size());
    }
    @Test
    public void testRemoveCollisionDepthTwoReverse() {
        nameCapTwoLoadFive.remove(3);
        nameCapTwoLoadFive.remove(2);
        nameCapTwoLoadFive.remove(1);
        nameCapTwoLoadFive.remove(0);

        assertFalse(nameCapTwoLoadFive.containsValue("my"));
        assertFalse(nameCapTwoLoadFive.containsValue("name"));
        assertFalse(nameCapTwoLoadFive.containsValue("is"));
        assertFalse(nameCapTwoLoadFive.containsValue("caroline"));
        assertEquals(0, nameCapTwoLoadFive.size());
    }
    @Test
    public void testRemoveCollisionDepthTwoByBucket() {
        nameCapTwoLoadFive.remove(0);
        nameCapTwoLoadFive.remove(2);
        nameCapTwoLoadFive.remove(3);
        nameCapTwoLoadFive.remove(1);

        assertFalse(nameCapTwoLoadFive.containsValue("my"));
        assertFalse(nameCapTwoLoadFive.containsValue("name"));
        assertFalse(nameCapTwoLoadFive.containsValue("is"));
        assertFalse(nameCapTwoLoadFive.containsValue("caroline"));
        assertEquals(0, nameCapTwoLoadFive.size());
    }
    @Test
    public void testRemoveCollisionDepthThree() {
        nameCapTwoLoadFive.put(4, "moran");
        nameCapTwoLoadFive.put(5, "begg");
        assertEquals(6, nameCapTwoLoadFive.size());

        nameCapTwoLoadFive.remove(0);
        nameCapTwoLoadFive.remove(1);
        nameCapTwoLoadFive.remove(2);
        nameCapTwoLoadFive.remove(3);
        nameCapTwoLoadFive.remove(4);
        nameCapTwoLoadFive.remove(5);

        assertFalse(nameCapTwoLoadFive.containsValue("my"));
        assertFalse(nameCapTwoLoadFive.containsValue("name"));
        assertFalse(nameCapTwoLoadFive.containsValue("is"));
        assertFalse(nameCapTwoLoadFive.containsValue("caroline"));
        assertFalse(nameCapTwoLoadFive.containsValue("moran"));
        assertFalse(nameCapTwoLoadFive.containsValue("begg"));
        assertEquals(0, nameCapTwoLoadFive.size());
    }
    @Test
    public void testRemoveCollisionDepthThreeReverse() {
        nameCapTwoLoadFive.put(4, "moran");
        nameCapTwoLoadFive.put(5, "begg");

        nameCapTwoLoadFive.remove(5);
        nameCapTwoLoadFive.remove(4);
        nameCapTwoLoadFive.remove(3);
        nameCapTwoLoadFive.remove(2);
        nameCapTwoLoadFive.remove(1);
        nameCapTwoLoadFive.remove(0);

        assertFalse(nameCapTwoLoadFive.containsValue("my"));
        assertFalse(nameCapTwoLoadFive.containsValue("name"));
        assertFalse(nameCapTwoLoadFive.containsValue("is"));
        assertFalse(nameCapTwoLoadFive.containsValue("caroline"));
        assertFalse(nameCapTwoLoadFive.containsValue("moran"));
        assertFalse(nameCapTwoLoadFive.containsValue("begg"));
        assertEquals(0, nameCapTwoLoadFive.size());
    }
    @Test
    public void testRemoveCollisionDepthThreeByBucket() {
        nameCapTwoLoadFive.put(4, "moran");
        nameCapTwoLoadFive.put(5, "begg");
        assertTrue(nameCapTwoLoadFive.containsValue("moran"));
        assertTrue(nameCapTwoLoadFive.containsValue("begg"));

        nameCapTwoLoadFive.remove(2);
        nameCapTwoLoadFive.remove(3);
        nameCapTwoLoadFive.remove(4);
        nameCapTwoLoadFive.remove(5);
        nameCapTwoLoadFive.remove(0);
        nameCapTwoLoadFive.remove(1);

        assertFalse(nameCapTwoLoadFive.containsValue("my"));
        assertFalse(nameCapTwoLoadFive.containsValue("name"));
        assertFalse(nameCapTwoLoadFive.containsValue("is"));
        assertFalse(nameCapTwoLoadFive.containsValue("caroline"));
        assertFalse(nameCapTwoLoadFive.containsValue("moran"));
        assertFalse(nameCapTwoLoadFive.containsValue("begg"));
        assertEquals(0, nameCapTwoLoadFive.size());
    }

    /** containsValue() tests **/

    @Test
    public void testContainsValueTrueNoCollisionNoResize() {
        assertTrue(helloWorld.containsValue("hello"));
        assertTrue(helloWorld.containsValue("world"));
    }
    @Test
    public void testContainsValueFalseNoCollisionNoResize() {
        hash = new HashMap<>(4);
        hash.put(0, "hi");
        hash.put(1, "earth");
        assertFalse(hash.containsValue("hello"));
        assertFalse(hash.containsValue("world"));
    }
    @Test
    public void testContainsValueTrueCollisionNoResize() {
        hash = new HashMap<>(2, 4);
        hash.put(0, "my");
        hash.put(1, "name");
        hash.put(2, "is");
        hash.put(3, "caroline");
        assertTrue(hash.containsValue("my"));
        assertTrue(hash.containsValue("name"));
        assertTrue(hash.containsValue("is"));
        assertTrue(hash.containsValue("caroline"));
    }
    @Test
    public void testContainsValueReplaceValueNoResize() {
        hash = new HashMap<>(2,4);
        hash.put(0, "my");
        hash.put(1, "name");
        hash.put(2, "is");
        hash.put(3, "caroline");
        hash.put(0, "your");
        hash.put(2, "isn't");
        assertTrue(hash.containsValue("your"));
        assertFalse(hash.containsValue("my"));
        assertTrue(hash.containsValue("name"));
        assertTrue(hash.containsValue("isn't"));
        assertFalse(hash.containsValue("is"));
        assertTrue(hash.containsValue("caroline"));
    }

    /** clear() tests **/

    @Test
    public void testClearHelloWorld() {
        helloWorld.clear();
        assertEquals(0, helloWorld.size());
        assertEquals(16, helloWorld.getTable().length);
    }
    @Test
    public void testClearNameCapTwoLoadFive() {
        nameCapTwoLoadFive.clear();
        assertEquals(0, nameCapTwoLoadFive.size());
        assertEquals(16, nameCapTwoLoadFive.getTable().length);
    }

    /** iterator tests **/
    @Test
    public void testIterator() {
        hash = new HashMap<>();
        hash.put(1, "hi");
        Iterator<Map.Entry<Integer, String>> iterator = hash.entryIterator();
        assertTrue(iterator.hasNext());
        Map.Entry<Integer, String> one = iterator.next();
        assertEquals(1, (int) one.getKey());
        assertEquals("hi", one.getValue());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }
    @Test
    public void testIteratorHelloWorld() {
        Iterator<Map.Entry<Integer, String>> iterator = helloWorld.entryIterator();
        assertTrue(iterator.hasNext());
        Map.Entry<Integer, String> one = iterator.next();
        assertEquals(0, (int) one.getKey());
        assertEquals("hello", one.getValue());
        assertTrue(iterator.hasNext());
        Map.Entry<Integer, String> two = iterator.next();
        assertEquals(1, (int) two.getKey());
        assertEquals("world", two.getValue());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }
    @Test
    public void testIteratorNameCapTwoLoadFive() {
        Iterator<Map.Entry<Integer, String>> iterator = nameCapTwoLoadFive.entryIterator();
        assertTrue(iterator.hasNext());
        Map.Entry<Integer, String> one = iterator.next();
        assertEquals(2, (int) one.getKey());
        assertEquals("is", one.getValue());
        assertTrue(iterator.hasNext());
        Map.Entry<Integer, String> two = iterator.next();
        assertEquals(0, (int) two.getKey());
        assertEquals("my", two.getValue());
        assertTrue(iterator.hasNext());
        Map.Entry<Integer, String> three = iterator.next();
        assertEquals(3, (int) three.getKey());
        assertEquals("caroline", three.getValue());
        assertTrue(iterator.hasNext());
        Map.Entry<Integer, String> four = iterator.next();
        assertEquals(1, (int) four.getKey());
        assertEquals("name", four.getValue());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }
    /** hashMap() tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testHashMapIllegalCap() {
        hash = new HashMap<>(-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testHashMapIllegalLoad() {
        hash = new HashMap<>(4, -1);
    }
    @Test
    public void testHashMap() {
        hash = new HashMap();
        assertEquals(0, hash.size());
    }

}
