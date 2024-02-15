import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Question2Test {

    @Test
    public void publicTest() {
        String requests[][] = {
                {"Steven", "Will"}, {"Helen", "Caroline"}, {"Caroline", "Monal"}, {"Will", "Steven"}
        };
        Set<Pair<String, String>> x = new HashSet<>();
        x.add(new Pair("Steven", "Will"));
        Set<Pair<String, String>> y = new HashSet<>();
        y.add(new Pair("Will", "Steven"));
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertTrue((o.equals(x) && !o.equals(y)) || (!o.equals(x) && o.equals(y)));
    }

    @Test
    public void noPairs() {
        String requests[][] = {
                {"Steven", "Will"}, {"Helen", "Steven"}, {"Caroline", "Monal"}, {"Will", "Helen"}
        };
        Set<Pair<String, String>> x = new HashSet<>();
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertEquals(x, o);
    }

    @Test
    public void empty() {
        String requests[][] = new String[][]{};
        Set<Pair<String, String>> x = new HashSet<>();
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertEquals(x, o);
    }

    @Test
    public void onePair() {
        String requests[][] = {
                {"Steven", "Will"}
        };
        Set<Pair<String, String>> x = new HashSet<>();
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertEquals(x, o);
    }

    @Test
    public void samePair() {
        String requests[][] = {
                {"Steven", "Will"}, {"Will", "Steven"}
        };
        Set<Pair<String, String>> x = new HashSet<>();
        x.add(new Pair("Steven", "Will"));
        Set<Pair<String, String>> y = new HashSet<>();
        y.add(new Pair("Will", "Steven"));
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertTrue((o.equals(x) && !o.equals(y)) || (!o.equals(x) && o.equals(y)));
    }

    @Test
    public void multiplePairs() {
        String requests[][] = {
                {"Steven", "Will"}, {"Helen", "Caroline"}, {"Caroline", "Helen"}, {"Will", "Steven"}
        };
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertTrue(o.contains(new Pair("Steven", "Will")) && !o.contains(new Pair("Will", "Steven"))
                || (!o.contains(new Pair("Steven", "Will")) && o.contains(new Pair("Will", "Steven"))) &&
                (o.contains(new Pair("Helen", "Caroline")) && !o.contains(new Pair("Caroline", "Helen")) ||
                        (!o.contains(new Pair("Helen", "Caroline")) && o.contains(new Pair("Caroline", "Helen")))));
    }
    @Test
    public void noMatchesCircle() {
        String requests[][] = {
                {"Steven", "Will"}, {"Will", "Helen"}, {"Helen", "Caroline"}, {"Caroline", "Steven"}
        };
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertEquals(new HashSet<>(), o);
    }

    @Test
    public void oddNumberOfRequests() {
        String requests[][] = {
                {"Steven", "Will"}, {"Will", "Helen"}, {"Helen", "Will"}
        };
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertTrue(o.contains(new Pair("Helen", "Will")) && !o.contains(new Pair("Will", "Hellen")) ||
                (o.contains(new Pair("Will", "Helen")) || !o.contains(new Pair("Helen", "Will"))));
    }
    @Test
    public void allRequestOneMatch() {
        String requests[][] = {
                {"Steven", "Caroline"}, {"Will", "Caroline"}, {"Helen", "Caroline"}, {"Caroline", "Will"}
        };
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertTrue(o.contains(new Pair("Caroline", "Will")) && !o.contains(new Pair("Will", "Caroline")) ||
                (o.contains(new Pair("Will", "Caroline")) || !o.contains(new Pair("Caroline", "Will"))));
        assertEquals(1, o.size());
    }
    @Test
    public void allRequestOneNoMatch() {
        String requests[][] = {
                {"Steven", "Caroline"}, {"Will", "Caroline"}, {"Helen", "Caroline"}, {"Caroline", "Ella"}
        };
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertEquals(new HashSet<>(), o);
    }
    @Test
    public void repeatedRequest() {
        String requests[][] = {
                {"Steven", "Caroline"}, {"Steve", "Caroline"}, {"Caroline", "Steve"}
        };
        Set<Pair<String, String>> o = Question2.findMatches(requests);
        assertTrue(o.contains(new Pair("Caroline", "Steve")) && !o.contains(new Pair("Steve", "Caroline")) ||
                (o.contains(new Pair("Steve", "Caroline")) || !o.contains(new Pair("Caroline", "Steve"))));
        assertEquals(1, o.size());
    }

}