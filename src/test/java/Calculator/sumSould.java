package Calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
"" ->0
"1"->1
"1,2"->3
"1\n2,3"->6
"//[;]\n1;2"->3
"1,-2,-3"->exception with all negative numbers
 */

class sumSould {

    public int add(String entry) {

        if (entry.isEmpty()){
            return 0;
        }

        boolean isAUniqueNumber = entry.length() == 1;
        if (isAUniqueNumber){
            return Integer.parseInt(entry);
        }
        if (entry.contains("//")){
            int indexForSplit = positionOfFirthsDigit(entry);
            String numberSeries = entry.substring(indexForSplit);
            String delimiter = entry.substring(2,indexForSplit).trim();
            delimiter = delimiter.replace("[","");
            delimiter = delimiter.replace("]","");
            numberSeries = numberSeries.replace(delimiter,",");

            return resultOfSum(numberSeries);

        }

        if (entry.contains("\n")){
            entry = entry.replace("\n",",");
        }

        return resultOfSum(entry);
    }

    private static int resultOfSum(String entry) {
        String[] numbers = entry.split(",");
        int sum = 0;
        for (String number:
             numbers) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }

    private int positionOfFirthsDigit(String text) {
        int place = -1;
        char caracter;
        int position=0;
        while(place==-1){

            caracter=text.charAt(position);
            if (Character.isDigit(caracter)){
                place=position;
            }else{
                position++;
            }
        }
        return place;
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
    @Test
    void allow_support_different_delimiter(){
        assertEquals(3, add("//[;]\n1;2"));
    }
    @Test
    void throw_exception_if_entry_contain_negatives_number(){
        String errorMessage = "Error: Negatives numbers not allwed";
        NegativeNumberException exceptionCapture = assertThrows(NegativeNumberException.class, ()->
                add("1,-2"));
        assertEquals(errorMessage,exceptionCapture.getMessage);
    }




}