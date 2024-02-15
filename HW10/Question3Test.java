import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Question3Test {

    @Test
    public void publicTest() {
        int[][] city = new int[][] {
                {1, 6, 2},
                {1, 6, 4},
                {1, 9, 2}
        };
        assertEquals(2, Question3.getMinimumNumberOfHops(city));
    }
    @Test
    public void big() {
        int[][] city = new int[][] {
                {1, 6, 2, 4, 5},
                {1, 6, 4, 3, 2},
                {2, 9, 2, 1, 2},
                {1, 9, 2, 1, 2},
                {1, 3, 2, 1, 2}

        };
        assertEquals(4, Question3.getMinimumNumberOfHops(city));
    }
    @Test
    public void threeByThree() {
        int[][] city = new int[][] {
                {1, 6, 2},
                {1, 6, 1},
                {2, 1, 1},

        };
        assertEquals(3, Question3.getMinimumNumberOfHops(city));
    }

    @Test
    public void noPath() {
        int[][] city = new int[][] {
                {1, 6, 4},
                {5, 6, 4},
                {1, 9, 2}
        };
        assertEquals(-1, Question3.getMinimumNumberOfHops(city));
    }

    @Test
    public void single() {
        int[][] city = new int[][] {
                {1}
        };
        assertEquals(0, Question3.getMinimumNumberOfHops(city));
    }
    @Test
    public void empty() {
        int[][] city = new int[][]{};
        assertEquals(-1, Question3.getMinimumNumberOfHops(city));
    }

    @Test
    public void two() {
        int[][] city = new int[][] {
                {1, 1},
                {1, 1}
        };
        assertEquals(2, Question3.getMinimumNumberOfHops(city));
    }

    @Test
    public void none() {
        int[][] city = new int[][] {
                {1, 1},
                {1, 2}
        };
        assertEquals(-1, Question3.getMinimumNumberOfHops(city));
    }

    @Test
    public void zero() {
        int[][] city = new int[][] {
                {1, 0},
                {0, 1}
        };
        assertEquals(-1, Question3.getMinimumNumberOfHops(city));
    }

    @Test
    public void impossible() {
        int[][] city = new int[][] {
                {1, 6, 4},
                {1, 6, 4},
                {3, 9, 2}
        };
        assertEquals(-1, Question3.getMinimumNumberOfHops(city));
    }

    @Test
    public void options() {
        int[][] city = new int[][] {
                {1, 2, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        assertEquals(4, Question3.getMinimumNumberOfHops(city));
    }

}
