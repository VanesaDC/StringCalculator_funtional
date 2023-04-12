package Calculator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/*
"" ->0
"1"->1
"1,2"->3
“1\n2,3”->6
“//[;]\n1;2”->3
"1,-2,-3"->Exception -2 -3
"1,1001"->1
"//[***]\n1***2***3"->6
 */

class sumSould {

    public int add(String entry) throws NegativeException {
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
        List<Integer> negatives= numbers.stream().filter(negative->negative<0).collect(Collectors.toList());
        List<String> negativesLetter= negatives.stream().map(neg->neg.toString()).collect(Collectors.toList());
        String negativesSentence= negativesLetter.stream().reduce("",(sentence,word)->sentence +" "+ word);
        if (!negativesSentence.isEmpty()) {
            throw new NegativeException("Error: negative numbers not allowed:" + negativesSentence);
        }
        return numbers.stream().reduce(0,(subtotal, number)->{ if (number<1000){ return  subtotal + number;}
            return subtotal;});
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
    void empty_entry_results_0()throws NegativeException {
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
    void allow_new_line_between_numbers_instead_of_commas() throws NegativeException {
        assertEquals(6, add("1\n2,3"));

    }
    @Test
    void allow_different_delimiters() throws NegativeException {
        assertEquals(3, add("//[;]\n1;2"));
    }
    @Test
    void Throw_a_exception_when_entry_contains_negative_number(){
       String message= assertThrows(NegativeException.class,()->add("1,-2,-3")).getMessage();
       assertEquals("Error: negative numbers not allowed: -2 -3",message);
    }
    @Test
    void allow_ignore_numbers_greater_than_1000() throws NegativeException {
        assertEquals(1, add("1,1001"));
    }
    @Test
    void allow_delimiter_with_different_length() throws NegativeException {
        assertEquals(6, add("//[***]\n1***2***3"));
    }





}