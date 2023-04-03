package Calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
"" ->0
"1"->1
"1,2"->3
 */

class sumSould {

    public int add(String texto) {
        if (texto.length()==1){
            return Integer.parseInt(texto);
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





}