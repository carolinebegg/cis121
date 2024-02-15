import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Question1Test {

    @Test
    public void publicTest() {
        int numFlights = 4;
        int[][] conditions = new int[][] {
                {3, 2}, {1, 2}, {0, 3}, {0, 1}
        };
        ArrayList<Integer> x = new ArrayList<>();
        x.add(new Integer(2));
        x.add(new Integer(3));
        x.add(new Integer(1));
        x.add(new Integer(0));
        ArrayList<Integer> y = new ArrayList<>();
        y.add(new Integer(2));
        y.add(new Integer(1));
        y.add(new Integer(3));
        y.add(new Integer(0));
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        System.out.println(l);
        assertTrue(x.equals(l) || y.equals(l));
    }

    @Test
    public void cycleInvalid() {
        int numFlights = 4;
        int[][] conditions = new int[][] {
                {3, 1}, {2, 0}, {1, 2}, {0, 3}, {0, 1}
        };
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertEquals(x, l);
    }

    @Test
    public void cycle() {
        int numFlights = 4;
        int[][] conditions = new int[][] {
                {3, 2}, {2, 0}, {1, 2}, {0, 3}, {0, 1},
        };
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertEquals(x, l);
    }
    @Test
    public void noFlights() {
        int numFlights = 0;
        int[][] conditions = new int[][] {
                {3, 2}, {2, 0}, {1, 2}, {0, 3}, {0, 1},
        };
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertEquals(x, l);
    }

    @Test
    public void longCycle() {
        int numFlights = 4;
        int[][] conditions = new int[][] {
                {1, 0}, {2, 1}, {3, 2}, {0, 3}
        };
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertEquals(x, l);
    }

    @Test
    public void bidirectionalContradiction() {
        int numFlights = 2;
        int[][] conditions = new int[][]{
                {0, 1}, {1, 0}
        };
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertEquals(x, l);
    }

    @Test
    public void noConditions() {
        int numFlights = 2;
        int[][] conditions = new int[][]{};
        ArrayList<Integer> x = new ArrayList<>();
        x.add(new Integer(0));
        x.add(new Integer(1));
        ArrayList<Integer> y = new ArrayList<>();
        y.add(new Integer(1));
        y.add(new Integer(0));
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        System.out.println(l);
        assertTrue(x.equals(l) || y.equals(l));
    }

    @Test
    public void single() {
        int numFlights = 1;
        int[][] conditions = new int[][]{};
        ArrayList<Integer> x = new ArrayList<>();
        x.add(new Integer(0));
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertEquals(x, l);
    }

    @Test
    public void empty() {
        int numFlights = 0;
        int[][] conditions = new int[][]{};
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertEquals(x, l);
    }

    @Test
    public void oneSolution() {
        int numFlights = 4;
        int[][] conditions = new int[][] {
                {3, 2}, {1, 2}, {0, 3}, {0, 1}, {1, 3}
        };
        ArrayList<Integer> x = new ArrayList<>();
        x.add(new Integer(2));
        x.add(new Integer(3));
        x.add(new Integer(1));
        x.add(new Integer(0));
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertEquals(x, l);
    }

    @Test
    public void disjoint() {
        int numFlights = 4;
        int[][] conditions = new int[][] {
                {1, 0}, {3, 2}
        };
        ArrayList<Integer> x = new ArrayList<>();
        x.add(new Integer(0));
        x.add(new Integer(1));
        x.add(new Integer(2));
        x.add(new Integer(3));
        ArrayList<Integer> y = new ArrayList<>();
        y.add(new Integer(2));
        y.add(new Integer(3));
        y.add(new Integer(0));
        y.add(new Integer(1));
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertTrue(x.equals(l) || y.equals(l));
    }
    @Test
    public void normal() {
        int numFlights = 4;
        int[][] conditions = new int[][] {
                {1, 0}, {2, 1}, {3, 2}
        };
        ArrayList<Integer> x = new ArrayList<>();
        x.add(new Integer(0));
        x.add(new Integer(1));
        x.add(new Integer(2));
        x.add(new Integer(3));
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertTrue(x.equals(l));
    }
    @Test
    public void multiple() {
        int numFlights = 3;
        int[][] conditions = new int[][] {
                {1, 0}
        };
        ArrayList<Integer> x = new ArrayList<>();
        x.add(new Integer(0));
        x.add(new Integer(1));
        x.add(new Integer(2));

        ArrayList<Integer> y = new ArrayList<>();
        y.add(new Integer(2));
        y.add(new Integer(0));
        y.add(new Integer(1));

        ArrayList<Integer> z = new ArrayList<>();
        z.add(new Integer(0));
        z.add(new Integer(2));
        z.add(new Integer(1));
        ArrayList<Integer> l = (ArrayList<Integer>) Question1.getFlightDepartureSchedule(numFlights, conditions);
        assertTrue(x.equals(l) || y.equals(l) || z.equals(l));
    }
}