package Calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/*
"" ->0
"1"->1
"1,2"->3
"1\n2,3"->6
"//[;]\n1;2"->3
"1,-2,-3"->exception with all negative numbers
 */

class sumSould {

    public int add(String entry) throws NegativeException {

        if (entry.isEmpty()) {
            return 0;
        }

        boolean isAUniqueNumber = entry.length() == 1;
        if (isAUniqueNumber) {
            return Integer.parseInt(entry);
        }

        String newDelimiterCommand = "//";
        if (entry.contains (newDelimiterCommand)) {
            entry = setNewDelimiter(entry);
        }

        String newLine = "\n";
        if (entry.contains(newLine)) {
            entry = entry.replace(newLine, ",");
        }

        String[] numbers = entry.split(",");
        int sum = 0;
        for (String number :
                numbers) {
            if (Integer.parseInt(number) < 0) {
                throw new NegativeException("Error: Negatives numbers not allowed " + number);
            } else {
                sum += Integer.parseInt(number);
            }
        }

        return sum ;
    }

    private String setNewDelimiter(String entry) {
        String[] entrySubdivisions= entry.split("\n",0);
        String delimiter= entrySubdivisions[0].substring(2);
        delimiter= delimiter.replace("[","");
        delimiter = delimiter.replace("]", "");
        entry = entrySubdivisions[1].replace(delimiter, ",");
        return entry;
    }




    @Test
    void empty_entry_results_0() throws NegativeException {
        assertEquals(0, add(""));
    }

    @Test
    void a_number_results_the_same_number() throws NegativeException {
        assertEquals(1, add("1"));
    }

    @Test
    void two_numbers_results_the_sum_of_the_numbers() throws NegativeException {
        assertEquals(3, add("1,2"));
    }

    @Test
    void add_all_number_from_the_text() throws NegativeException {
        assertEquals(10, add("1,2,3,4"));
    }

    @Test
    void allow_new_line_between_numbers() throws NegativeException {
        assertEquals(6, add("1\n2,3"));
    }

    @Test
    void allow_support_different_delimiter() throws NegativeException {
        assertEquals(6, add("//[;]\n1;2;3"));
    }


   @Test
    void throw_exception_if_entry_contain_negatives_number() {
        assertThatExceptionOfType(NegativeException.class).isThrownBy(() -> {
            add("1,-2");
        }).withMessage("Error: Negatives numbers not allowed -2");
    }

}


