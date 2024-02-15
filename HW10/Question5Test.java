import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Question5Test {

    @Test
    public void publicTest() {
        List<List<Integer>> family = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(new Integer(1));
        a.add(new Integer(3));
        List<Integer> b = new ArrayList<>();
        b.add(new Integer(2));
        b.add(new Integer(4));
        b.add(new Integer(5));
        List<Integer> c = new ArrayList<>();
        c.add(new Integer(6));
        family.add(a);
        family.add(b);
        family.add(new ArrayList<>());
        family.add(c);
        family.add(new ArrayList<>());
        family.add(new ArrayList<>());
        family.add(new ArrayList<>());
        int n = Question5.getNumberOfOddSubtrees(family, 0);
        assertEquals(5, n);
    }

    @Test
    public void single() {
        List<List<Integer>> family = new ArrayList<>();
        family.add(new ArrayList<>());
        int n = Question5.getNumberOfOddSubtrees(family, 0);
        assertEquals(1, n);
    }

    @Test
    public void empty() {
        List<List<Integer>> family = new ArrayList<>();
        int n = Question5.getNumberOfOddSubtrees(family, 0);
        assertEquals(0, n);
    }

    @Test
    public void oneSided() {
        List<List<Integer>> family = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(new Integer(1));
        family.add(a);
        List<Integer> b = new ArrayList<>();
        b.add(new Integer(2));
        family.add(b);
        List<Integer> c = new ArrayList<>();
        c.add(new Integer(3));
        family.add(c);
        family.add(new ArrayList<>());
        int n = Question5.getNumberOfOddSubtrees(family, 0);
        assertEquals(2, n);
    }

    @Test
    public void onlyLeaves() {
        List<List<Integer>> family = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(new Integer(1));
        a.add(new Integer(5));
        a.add(new Integer(7));
        family.add(a);
        List<Integer> b = new ArrayList<>();
        b.add(new Integer(2));
        b.add(new Integer(3));
        b.add(new Integer(4));
        family.add(b);
        family.add(new ArrayList<>());
        family.add(new ArrayList<>());
        family.add(new ArrayList<>());
        List<Integer> c = new ArrayList<>();
        c.add(new Integer(6));
        family.add(c);
        family.add(new ArrayList<>());
        family.add(new ArrayList<>());
        int n = Question5.getNumberOfOddSubtrees(family, 0);
        assertEquals(5, n);
    }
}