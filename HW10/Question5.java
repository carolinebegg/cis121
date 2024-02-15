import java.util.List;

/**
 * <h1>QUESTION 5: ODD SIZED FAMILY TREES</h1>
 * <p/>
 *
 * Class to implement the algorithm to determine the number of odd sized sub-trees in a given
 * family tree.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question5 {

    /**
     * Returns the number of odd sized sub-trees in a given family tree.
     *
     * @param family an adjacency list of the family tree
     * @param root the favorite number of the root of the family tree
     * @return the number of odd sized sub-trees
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static int getNumberOfOddSubtrees(List<List<Integer>> family, int root) {
        if (family.isEmpty()) {
            return 0;
        }

        int[] subtreeLength = new int[family.size()];
        dfs(family, root, subtreeLength);

        int oddTreeCounter = 0;
        for (int subtree : subtreeLength) {
            if (subtree % 2 != 0) {
                oddTreeCounter++;
            }
        }
        return oddTreeCounter;
    }
    static void dfs(List<List<Integer>> family, int curr, int[] subtreeLength) {
        subtreeLength[curr] = 1;
        for (int child : family.get(curr)) {
            dfs(family, child, subtreeLength);
            subtreeLength[curr] += subtreeLength[child];
        }
    }
}