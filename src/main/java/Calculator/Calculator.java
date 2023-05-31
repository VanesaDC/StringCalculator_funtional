package Calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public Calculator() {
    }

    public int add(String expression) throws NegativeException {
        if (expression.isEmpty())
            return 0;

        boolean isAUniqueNumber = expression.length() == 1;
        if (isAUniqueNumber){
            return Integer.parseInt(expression);
        }
        final String defaultDelimiter = ",";

        String newDelimiter = "//";
        if (expression.contains(newDelimiter)) {
            expression = setDelimiters(expression, defaultDelimiter);
        }

        String newLine = "\n";
        if (expression.contains(newLine)){
            expression = expression.replace(newLine, defaultDelimiter);
        }

        String[] numbers= expression.split(defaultDelimiter);

        List<Integer> numbersParsed= Arrays.stream(numbers).map(Integer::parseInt).collect(Collectors.toList());
        String negativeNumbers= numbersParsed.stream()
                .filter(negative->negative<0)
                .map(Object::toString)
                .reduce("",(allNegativeNumbers, number) -> allNegativeNumbers + " " + number);
        if (!negativeNumbers.isEmpty()) {
            throw new NegativeException("Error: negative numbers not allowed:" + negativeNumbers);
        }
        return numbersParsed.stream().reduce(0,(subtotal, number)->{ if (number<1000){ return  subtotal + number;}
            return subtotal;});
    }

    private static String setDelimiters(String entry, String defaultDelimiter) {
        String newLine = "\n";
        String[] entrySubdivisions = entry.split(newLine,2);
        String command = entrySubdivisions[0].substring(2);
        String expression = entrySubdivisions[1];
        command = command.replace("[","");
        String [] delimiters = command.split("]");

        for (String delimiter : delimiters) {
            expression = expression.replace(delimiter, defaultDelimiter);
        }
        return expression;
    }

}