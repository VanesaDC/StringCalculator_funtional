package Calculator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/*
"" ->0
"1"->1
"1,2"->3
“1\n2,3”->6
“//[;]\n1;2”->3
 */

class sumSould {

    public int add(String entry) {
        if (entry.isEmpty())
            return 0;

        boolean isAUniqueNumber = entry.length() == 1;
        if (isAUniqueNumber){
            return Integer.parseInt(entry);
        }

        if (entry.contains("//")) {
            entry = setDelimiter(entry);
        }

        String newLine = "\n";
        if (entry.contains(newLine)){
            entry= entry.replace(newLine, ",");
        }

        String[] numberSeries= entry.split(",");
        List<Integer> numbers= Arrays.stream(numberSeries).map((num)->Integer.parseInt(num)).collect(Collectors.toList());
        return numbers.stream().reduce(0,(subtotal, number)->subtotal + number);
    }

    private static String setDelimiter(String entry) {
        entry = entry.replace("//", "");
        entry = entry.replace("[", "");
        entry = entry.replace("]", "");
        String delimiter= String.valueOf(entry.charAt(0));
        char delimit = entry.charAt(0);
        entry = entry.replaceFirst(String.valueOf(delimit), "");
        entry = entry.replaceFirst("\n","");
        entry = entry.replace(delimiter,",");
        return entry;
    }


    @Test
    void empty_entry_results_0(){
        assertEquals(0, add(""));
    }
    @Test
    void a_number_results_the_same_number(){
        assertEquals(1, add("1"));
    }
    @Test
    void two_numbers_results_the_sum_of_the_numbers(){
        assertEquals(3, add("1,2"));
    }
    @Test
    void allow_new_line_between_numbers_instead_of_commas(){
        assertEquals(6, add("1\n2,3"));
    }
    @Test
    void allow_different_delimiters(){
        assertEquals(3, add("//[;]\n1;2"));
    }






}