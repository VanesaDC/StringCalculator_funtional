package Calculator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/*
"" ->0
"1"->1
"1,2"->3
“1\n2,3”->6
 */

class sumSould {

    public int add(String texto) {
        if (texto.isEmpty())
            return 0;

        boolean is_a_unique_number = texto.length() == 1;
        if (is_a_unique_number){
            return Integer.parseInt(texto);
        }

        if (texto.contains("\n")){
            texto= texto.replace("\n", ",");
        }
            String[] numberSeries= texto.split(",");
            List<Integer> numbers= Arrays.stream(numberSeries).map((num)->Integer.parseInt(num)).collect(Collectors.toList());
            return numbers.stream().reduce(0,(subtotal, number)->subtotal + number);
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





}