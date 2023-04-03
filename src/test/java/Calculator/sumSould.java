package Calculator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
"" ->0
"1"->1
"1,2"->3
 */

class sumSould {

    public int add(String texto) {
        boolean is_a_unique_number = texto.length() == 1;

        if (is_a_unique_number){
            return Integer.parseInt(texto);
        }
        if (texto.length()==3){
            String[] numbers= texto.split(",");
            return Integer.parseInt(numbers[0])+ Integer.parseInt(numbers[1]);
        }
        return 0;
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





}