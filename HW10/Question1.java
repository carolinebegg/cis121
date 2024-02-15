import java.util.*;

/**
 * <h1>QUESTION 1: FLIGHT SCHEDULES</h1>
 * <p/>
 *
 * Class to implement the algorithm to determine a possible flight schedule given the conditions.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question1 {

    /**
     * Given the number of flights to schedule and conditions for departure, returns one possible
     * departure schedule.
     *
     * @param numFlights the number of flights waiting to take off
     * @param conditions the list of condition pairs (no duplicate pairs may exist)
     * @return one possible valid flight departure ordering, or an empty list if none exists
     * 
     * @implSpec you may assume that input is valid (well formatted, mx2 2d-array) and non-null.
     * Do not throw any exceptions.
     */
    public static List<Integer> getFlightDepartureSchedule(int numFlights, int[][] conditions) {
        List<Integer> schedule = new ArrayList<>();
        if (numFlights < 1) {
            return schedule;
        }
        schedule = dfs(numFlights, conditions);
        Collections.reverse(schedule);
        return schedule;
    }

    static List<Integer> dfs(int numFlights, int[][] conditions) {
        ArrayList<LinkedList<Integer>> schedule = new ArrayList<>();
        for (int i = 0; i < numFlights; i++) {
            schedule.add(new LinkedList<>());
        }
        ArrayList<Integer> finish = new ArrayList<>();
        for (int[] ints : conditions) {
            schedule.get(ints[1]).add(ints[0]);
        }
        int[] color = new int[numFlights];
        int index = 0;
        boolean[] cycle = new boolean[1];
        for (LinkedList<Integer> condition : schedule) {
            if (color[index] == 0) {
                dfsVisit(cycle, color, index, condition, finish, schedule);
            }
            index++;
        }
        if (cycle[0]) {
            return new ArrayList<>();
        }
        return finish;
    }
    static void dfsVisit(boolean[] cycle, int[] color, int index, LinkedList<Integer> condition,
                         ArrayList<Integer> finish, ArrayList<LinkedList<Integer>> schedule) {
        color[index] = 1;
        for (int v : condition) {
            if (color[v] == 0) {
                dfsVisit(cycle, color, v, schedule.get(v), finish, schedule);
            } else if (color[v] == 1) {
                cycle[0] = true;
            }
        }
        color[index] = 2;
        finish.add(index);
    }
}