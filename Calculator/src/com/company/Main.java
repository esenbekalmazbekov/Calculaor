package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String imputData = in.nextLine();
        Parsing parsing = new Parsing();
        parsing.setParcer(imputData);
        parsing.startPacer();
    }
}

class Parsing {
    private String parcer;
    private Integer firstNumber = 0;
    private Integer secondNumber = 0;
    private Character character;
    private FromRomanToArabian converter = new FromRomanToArabian();
    public void setParcer(String parcer) {
        this.parcer = parcer;
    }

    public void startPacer(){
        String[] substr = parcer.split(" ");

        if(substr.length != 3){Utils.throwException(new Exception("Incorrect inputs!"));}
        if(substr[1].length() != 1){Utils.throwException(new Exception("Incorrect!"));}
        character = substr[1].charAt(0);

        try {
            firstNumber = Integer.parseInt(substr[0]);
            secondNumber = Integer.parseInt(substr[2]);
            if(firstNumber <= 0 || firstNumber > 10 || secondNumber <= 0 || secondNumber > 10)
                Utils.throwException(new Exception("Incorrect!!!"));
            System.out.println(operation());
        }catch (NumberFormatException e){
            firstNumber = converter.getTransferdata().get(substr[0]);
            secondNumber = converter.getTransferdata().get(substr[2]);
            if(firstNumber <= 0 || firstNumber > 10 || secondNumber <= 0 || secondNumber > 10)
                Utils.throwException(new Exception("Incorrect!!!"));
            System.out.println(converter.convert(operation()));
        }
    }
    private Integer operation(){
        switch (character){
            case '+':return firstNumber + secondNumber;
            case '-':return firstNumber - secondNumber;
            case '*':return firstNumber * secondNumber;
            case '/':return firstNumber / secondNumber;
            default:Utils.throwException(new Exception("Incorrect!!!"));
        }
        return null;
    }
}

class FromRomanToArabian {
    private HashMap<String, Integer> transferdata = new HashMap<>();
    public FromRomanToArabian() {
        transferdata.put("I",1);
        transferdata.put("II",2);
        transferdata.put("III",3);
        transferdata.put("IV",4);
        transferdata.put("V",5);
        transferdata.put("VI",6);
        transferdata.put("VII",7);
        transferdata.put("VIII",8);
        transferdata.put("IX",9);
        transferdata.put("X",10);
    }

    public HashMap<String, Integer> getTransferdata() {
        return transferdata;
    }

    private String romanDigit(Integer n, String one, String five, String ten){

        if(n >= 1)
        {
            if(n == 1)
            {
                return one;
            }
            else if (n == 2)
            {
                return one + one;
            }
            else if (n == 3)
            {
                return one + one + one;
            }
            else if (n==4)
            {
                return one + five;
            }
            else if (n == 5)
            {
                return five;
            }
            else if (n == 6)
            {
                return five + one;
            }
            else if (n == 7)
            {
                return five + one + one;
            }
            else if (n == 8)
            {
                return five + one + one + one;
            }
            else if (n == 9)
            {
                return one + ten;
            }

        }
        return "";
    }
    public String convert(Integer number){
        String neg = "";
        if(number < 0){
            neg = "-";
            number *= (-1);
        }
        String romanOnes = romanDigit( number%10, "I", "V", "X");
        number /=10;
        String romanTens = romanDigit( number%10, "X", "L", "C");
        number /=10;

        String result = romanTens + romanOnes;
        return neg + result;

    }
}


class Utils {
    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwException(Throwable exception, Object dummy) throws T
    {
        throw (T) exception;
    }

    public static void throwException(Throwable exception)
    {
        Utils.<RuntimeException>throwException(exception, null);
    }
}