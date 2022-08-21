import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main {
    public static void main(String[] args) throws IOException, WrongInputException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String answer = calc(input);
        System.out.println(answer);
    }
    public static String calc(String input) throws WrongInputException {
        if (input.contains("+")) {
            if (input.split("\\+").length>2){
                throw new WrongInputException();
            }
            UniversalNumber firstPart = new UniversalNumber(input.split("\\+")[0].trim());
            UniversalNumber secondPart = new UniversalNumber(input.split("\\+")[1].trim());
            if (firstPart.isWrongInput || secondPart.isWrongInput) {
                throw new WrongInputException();
            }
            UniversalNumber result = firstPart.addition(secondPart);
            if (result.isInteger) {
                return result.getValue().toString();
            }
            return integerToRoman(result.getValue());
        }
        if (input.contains("-")) {
            if (input.split("-").length>2){
                throw new WrongInputException();
            }
            UniversalNumber firstPart = new UniversalNumber(input.split("-")[0].trim());
            UniversalNumber secondPart = new UniversalNumber(input.split("-")[1].trim());
            if (firstPart.isWrongInput || secondPart.isWrongInput) {
                throw new WrongInputException();
            }
            UniversalNumber result = firstPart.subtraction(secondPart);
            if (result.isInteger) {
                return result.getValue().toString();
            }
            if (result.getValue() < 0) {
                throw new WrongInputException();
            }
            return integerToRoman(result.getValue());
        }
        if (input.contains("/")) {
            if (input.split("/").length>2){
                throw new WrongInputException();
            }
            UniversalNumber firstPart = new UniversalNumber(input.split("/")[0].trim());
            UniversalNumber secondPart = new UniversalNumber(input.split("/")[1].trim());
            if (firstPart.isWrongInput || secondPart.isWrongInput) {
                throw new WrongInputException();
            }
            UniversalNumber result = firstPart.division(secondPart);
            if (result.isInteger) {
                return result.getValue().toString();
            }
            return integerToRoman(result.getValue());
        }
        if (input.contains("*")) {
            if (input.split("\\*").length>2){
                throw new WrongInputException();
            }
            UniversalNumber firstPart = new UniversalNumber(input.split("\\*")[0].trim());
            UniversalNumber secondPart = new UniversalNumber(input.split("\\*")[1].trim());
            if (firstPart.isWrongInput || secondPart.isWrongInput) {
                throw new WrongInputException();
            }
            UniversalNumber result = firstPart.multiplication(secondPart);
            if (result.isInteger) {
                return result.getValue().toString();
            }
            return integerToRoman(result.getValue());
        }
        throw new WrongInputException();

    }


    static Map<String, Integer> romanToInteger = new HashMap<>();
    static String[] romanLetters = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
    static Integer[] numbers = new Integer[]{1, 4, 5, 9, 10, 40, 50, 90, 100};

    static {
        romanToInteger.put("I", 1);
        romanToInteger.put("II", 2);
        romanToInteger.put("III", 3);
        romanToInteger.put("IV", 4);
        romanToInteger.put("V", 5);
        romanToInteger.put("VI", 6);
        romanToInteger.put("VII", 7);
        romanToInteger.put("VIII", 8);
        romanToInteger.put("IX", 9);
        romanToInteger.put("X", 10);
    }

    static String integerToRoman(Integer a) {
        int value = a;
        StringBuilder roman = new StringBuilder();
        for (int i = romanLetters.length - 1; i >= 0; i--) {
            while (value >= numbers[i]) {
                value = value - numbers[i];
                roman.append(romanLetters[i]);

            }
        }
        return roman.toString();

    }


}

class WrongInputException extends Exception {
}

class UniversalNumber {
    boolean isInteger = true;
    boolean isWrongInput = false;
    Integer value;

    UniversalNumber(String s) {
        try {
            value = Integer.valueOf(s);
        } catch (NumberFormatException e) {
            value = Main.romanToInteger.get(s);
            if (value == null) {
                isWrongInput = true;
            }
            isInteger = false;
        }
    }

    Integer getValue() {
        return value;
    }

    UniversalNumber addition(UniversalNumber a) throws WrongInputException {
        if (this.isInteger != a.isInteger) {
            throw new WrongInputException();
        }
        this.value = this.getValue() + a.getValue();
        return this;
    }

    UniversalNumber subtraction(UniversalNumber a) throws WrongInputException {
        if (this.isInteger != a.isInteger) {
            throw new WrongInputException();
        }
        this.value = this.getValue() - a.getValue();
        return this;
    }

    UniversalNumber multiplication(UniversalNumber a) throws WrongInputException {
        if (this.isInteger != a.isInteger) {
            throw new WrongInputException();
        }
        this.value = this.getValue() * a.getValue();
        return this;
    }

    UniversalNumber division(UniversalNumber a) throws WrongInputException {
        if (this.isInteger != a.isInteger) {
            throw new WrongInputException();
        }
        this.value = this.getValue() / a.getValue();
        return this;
    }


}