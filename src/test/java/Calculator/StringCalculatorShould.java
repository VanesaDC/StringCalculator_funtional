package Calculator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/*
"" ->0
"1"->1
"1,2"->3
“1\n2,3”->6
“//[;]\n1;2”->3
"1,-2,-3"->Exception -2 -3
"1,1001"->1
"//[***]\n1***2***3"->6
"//[*][%]\n1*2%3"->6
 */

class AddShould {

 Calculator calculator=  new Calculator();

    @Test
    void empty_entry_results_0()throws NegativeException {
        assertEquals(0, calculator.add(""));
    }
    @Test
    void thr() throws NegativeException {
        assertEquals(1, calculator.add("1"));
    }
    @Test
    void two_numbers_results_the_sum_of_the_numbers() throws NegativeException {
        assertEquals(3, calculator.add("1,2"));
    }
    @Test
    void allow_new_line_between_numbers_instead_of_commas() throws NegativeException {
        assertEquals(6, calculator.add("1\n2,3"));
    }
    @Test
    void allow_different_delimiters() throws NegativeException {
        assertEquals(6, calculator.add("//[;]\n1;2\n3"));
    }
    @Test
    void Throw_a_exception_when_entry_contains_negative_number(){
       String message= assertThrows(NegativeException.class,()->calculator.add("1,-2,-3")).getMessage();
       assertEquals("Error: negative numbers not allowed: -2 -3",message);
    }
    @Test
    void allow_ignore_numbers_greater_than_1000() throws NegativeException {
        assertEquals(1, calculator.add("1,1001"));
    }
    @Test
    void allow_delimiter_with_different_length() throws NegativeException {
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }
    @Test
    void allow_multiples_delimiters_() throws NegativeException {
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }
}