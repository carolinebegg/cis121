import java.util.*;

/**
 * <h1>QUESTION 3: BUNNY HOP</h1>
 * <p/>
 *
 * Class to implement the algorithm to find the minimum number of hops a bunny rabbit
 * must make to navigate from the bottom right corner to the top left corner of the city.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question3 {

    /**
     * Returns the minimum number of hops needed for the bunny rabbit to make its way from the
     * bottom right corner to the top left corner given the hopping constraints in the writeup,
     * or -1 if a path does not exist.
     *
     * @param city an n x n 2d-array representing the city where each entry is a positive integer
     *             which defines how many cells the bunny can hop up or to the left when that
     *             entry is reached.
     * @return the minimum number of hops the bunny needs to make, or -1 if there is no 
     *         possible path
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static int getMinimumNumberOfHops(int[][] city) {
        int n = city.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }

        int src = (n - 1) * n + (n - 1);

        Map<Integer, List<Integer>> cityGraph = getIntegerListMap(city);
        Map<Integer, Integer> hop = bfs(cityGraph, city);

        if (hop.isEmpty()) {
            return -1;
        }

        int curr = 0;
        int minHops = 0;

        while (hop.containsKey(curr)) {
            curr = hop.get(curr);
            minHops++;
        }
        if (curr == src) {
            return minHops;
        }
        return -1;
    }
    static int getIndex(int y, int x, int n) {
        return (n * y) + x;
    }
    private static Map<Integer, List<Integer>> getIntegerListMap(int[][] city) {
        int n = city.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = getIndex(i,j,n);
                int hops = city[i][j];
                List<Integer> list = new ArrayList<>();
                if (j - hops > -1) {
                    list.add(index - hops);
                }
                if (i - hops > -1) {
                    list.add(index - (n * hops));
                }
                map.put(index, list);
            }
        }
        return map;
    }
    static Map<Integer, Integer> bfs(Map<Integer, List<Integer>> map, int[][] city) {
        int n = city.length;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> discovered = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        int tgt = 0;
        int src = (n - 1) * n + (n - 1);

        queue.add(src);
        discovered.add(src);


        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            if (v == tgt) {
                return parent;
            }

            for (Integer u : map.get(v)) {
                if (!discovered.contains(u)) {
                    queue.add(u);
                    discovered.add(u);
                    parent.put(u, v);
                }
            }
        }
        return parent;
    }
}