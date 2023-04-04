package Calculator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
"" ->0
"1"->1
"1,2"->3
"1\n2,3"->6
 */

class sumSould {

    public int add(String numberSeries) {

        if (numberSeries.isEmpty()){
            return 0;
        }

        boolean isAUniqueNumber = numberSeries.length() == 1;
        if (isAUniqueNumber){
            return Integer.parseInt(numberSeries);
        }

        if (numberSeries.contains("\n")){
            numberSeries = numberSeries.replace("\n",",");
        }

        String[] numbers = numberSeries.split(",");
        int sum=0;
        for (String number:
             numbers) {
            sum+=Integer.parseInt(number);
        }
        return sum;
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
    void add_all_number_from_the_text(){
        assertEquals(10, add("1,2,3,4"));
    }
    @Test
    void allow_new_line_between_numbers(){
        assertEquals(6, add("1\n2,3"));
    }





}