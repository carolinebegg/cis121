import java.util.*;

/**
 * <h1>QUESTION 2: ROOMMATE MATCHING</h1>
 * <p/>
 *
 * Class to implement the algorithm to select roommates from a 2d-array of pairs.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question2 {

    /**
     * Returns the set of roommates who mutually requested each other.
     *
     * @param requests the 2d-array of pairs of requests of the form (student, requested roommate).
     *                 Note: a student cannot request themself. 
     * @return a set of pairs of roommates who mutually requested each other
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static Set<Pair<String, String>> findMatches(String[][] requests) {
        Set<Pair<String, String>> matches = new HashSet<>();
        List<Pair<String, String>> pairs = new LinkedList<>();

        Arrays.sort(requests, Comparator.comparing(strings -> strings[0]));

        for (String[] request : requests) {
            String student = request[0];
            String roommate = request[1];
            pairs.add(new Pair<>(student, roommate));
        }

        int i = 0;
        for (Pair<String, String> p : pairs) {
            int index = binarySearch(pairs, p.getValue(), i, pairs.size() - 1);
            if (index != -1 && (pairs.get(index).getValue().compareTo(p.getKey()) == 0)) {
                matches.add(p);
            }
            i++;
        }
        return matches;
    }
    static int binarySearch(List<Pair<String, String>> pairs, String requested, int lo, int hi) {
        while (lo <= hi) {
            int middle = (lo + hi) / 2;
            if (requested.compareTo(pairs.get(middle).getKey()) == 0) {
                return middle;
            } else if (requested.compareTo(pairs.get(middle).getKey()) > 0) {
                lo = middle + 1;
            } else {
                hi = middle - 1;
            }
        }
        return -1;
    }
}
