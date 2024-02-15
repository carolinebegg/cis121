import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Question4Test {

    @Test
    public void publicTest() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add("MIA");
        codes.add("JFK");
        codes.add("MLA");
        codes.add("MLE");
        codes.add("BLE");
        List<String> expected = new ArrayList<>();
        expected.add("MIA");
        expected.add("MLA");
        expected.add("MLE");
        expected.add("BLE");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        assertEquals(expected, output);
    }

    @Test
    public void noCode1() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add("JFK");
        codes.add("MLA");
        codes.add("MLE");
        codes.add("BLE");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        assertNull(output);
    }

    @Test
    public void noCode2() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add("JFK");
        codes.add("MLA");
        codes.add("MLE");
        codes.add("MIA");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        assertNull(output);
    }

    @Test
    public void sameCode() {
        String code1 = "MIA";
        String code2 = "MIA";
        Set<String> codes = new HashSet<>();
        codes.add("JFK");
        codes.add("MLA");
        codes.add("MLE");
        codes.add("MIA");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        List<String> expected = new ArrayList<>();
        assertEquals(expected, output);
    }

    @Test
    public void sameCodeNotIn() {
        String code1 = "MIA";
        String code2 = "MIA";
        Set<String> codes = new HashSet<>();
        codes.add("JFK");
        codes.add("MLA");
        codes.add("MLE");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        assertNull(output);
    }

    @Test
    public void impossible() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add("JFK");
        codes.add("MIA");
        codes.add("BLE");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        assertNull(output);
    }

    @Test
    public void noSolution() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add("MLA");
        codes.add("MLP");
        codes.add("MIA");
        codes.add("BLE");
        codes.add("XLP");
        codes.add("XLL");
        codes.add("XIL");
        codes.add("BIL");
        codes.add("MIP");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        assertNull(output);
    }

    @Test
    public void rearrange() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add("MIA");
        codes.add("IAE");
        codes.add("BAE");
        codes.add("BLE");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        assertNull(output);
    }

    @Test
    public void backward() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add("BLE");
        codes.add("MIA");
        codes.add("MXB");
        codes.add("BLL");
        codes.add("MXL");
        codes.add("BXL");
        codes.add("MIB");
        codes.add("BIB");
        codes.add("BXB");
        codes.add("MLE");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        List<String> expected = new ArrayList<>();
        expected.add(code1);
        expected.add("MIB");
        expected.add("MXB");
        expected.add("MXL");
        expected.add("BXL");
        expected.add("BLL");
        expected.add("BLE");
        assertEquals(expected, output);
    }

    @Test
    public void shorter() {
        String code1 = "MIA";
        String code2 = "BLE";
        Set<String> codes = new HashSet<>();
        codes.add(code2);
        codes.add("BLA");
        codes.add(code1);
        codes.add("BIA");
        codes.add("LIA");
        codes.add("LLA");
        codes.add("LLE");
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        List<String> expected = new ArrayList<>();
        expected.add(code1);
        expected.add("BIA");
        expected.add("BLA");
        expected.add(code2);
        assertEquals(expected, output);
    }
    @Test
    public void simple() {
        String code1 = "ABC";
        String code2 = "ADC";
        Set<String> codes = new HashSet<>();
        codes.add(code2);
        codes.add(code1);
        List<String> output = Question4.getSmallestChain(code1, code2, codes);
        List<String> expected = new ArrayList<>();
        expected.add(code1);
        expected.add(code2);
        assertEquals(expected, output);
    }

}