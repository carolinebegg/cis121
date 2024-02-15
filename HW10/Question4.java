import java.util.*;

/**
 * <h1>QUESTION 4: AIRPORT CODE GAME -- OFF BY ONE!</h1>
 * <p/>
 *
 * Class to implement the algorithm to find the smallest chain between two 3 letter
 * airport codes.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question4 {

    /**
     * Solves the "Airport Code Change" game. See complete problem description above.
     * <p/>
     * 
     * You may assume that all codes contain only UPPERCASE alphabetic (A-Z) characters.
     *
     * @param code1 the 3 letter airport code you want to start from
     * @param code2 the 3 letter airport code you want to end at
     * @param codes the set of 3 letter airport codes you can use to get from code1 -> code2
     * @return the smallest chain of airport codes to get from {@code code1 -> code2}. {@code code1}
     *         should be the first element, and {@code code2} should be the last. If no valid
     *         solution exists, return {@code null}. If {@code code1.equals(code2)}, return an empty
     *         list.
     *
     * @implSpec you may assume that all inputs are valid. Do not throw any exceptions.
     */
    public static List<String> getSmallestChain(String code1, String code2, Set<String> codes) {
        List<String> list = new ArrayList<>();
        if (!codes.contains(code1) || !codes.contains(code2)) {
            return null;
        }
        if (code1.equals(code2)) {
            return list;
        }
        Map<String, List<String>> chain = new HashMap<>();
        for (String c : codes) {
            chain.put(c, new ArrayList<>());
            for (String x : codes) {
                if (c.charAt(0) == x.charAt(0)) {
                    if (c.charAt(1) == x.charAt(1)) {
                        if (c.charAt(2) != x.charAt(2)) {
                            chain.get(c).add(x);
                        }
                    } else if (c.charAt(2) == x.charAt(2)) {
                        chain.get(c).add(x);
                    }
                } else if (c.charAt(1) == x.charAt(1)) {
                    if (c.charAt(2) == x.charAt(2)) {
                        if (c.charAt(0) != x.charAt(0)) {
                            chain.get(c).add(x);
                        }
                    }
                }
            }
        }
        Map<String, String> code = bfs(chain, code1);
        if (code.isEmpty()) {
            return null;
        }
        list.add(code2);
        String curr = code2;
        while (!curr.equals(code1)) {
            list.add(code.get(curr));
            curr = code.get(curr);
            if (curr == null) {
                return null;
            }
        }
        Collections.reverse(list);
        return list;
    }
    static Map<String, String> bfs(Map<String, List<String>> map, String code1) {
        Queue<String> queue = new LinkedList<>();

        Map<String, String> parent = new HashMap<>();
        Set<String> discovered = new HashSet<>();

        queue.add(code1);
        discovered.add(code1);

        while (!queue.isEmpty()) {
            String v = queue.poll();
            for (String u : map.get(v)) {
                if (!discovered.contains(u)) {
                    discovered.add(u);
                    queue.add(u);
                    parent.put(u, v);
                }
            }
        }
        return parent;
    }
}