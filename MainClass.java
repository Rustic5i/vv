package ru.geekbrains.java1.lesson1.calculator;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MainClass {
   static TreeMap<String,Integer> treeRim;
   static String [] arrayMatText;
   static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        readFail();
        dataCheck();
    }
    public static void dataCheck(){
        System.out.println("Введите пример через пробел, пример \n 1 + 2");
        while (true) {
            try {
                arrayMatText = new String[3];
                String matLevel = scanner.nextLine();
                arrayMatText = matLevel.split(" ");
                if (checkRim()){
                    aritmetichOperations(arrayMatText[1]);
                    return;
                }
                int number1 = Integer.parseInt(arrayMatText[0]);
                int number2 = Integer.parseInt(arrayMatText[2]);
                if (number1 > 10 || number2 > 10) {
                    System.out.println("Ошибка: превышен числовой диапазон \n Введите чесло от 1 до 10");
                }else {
                    aritmetichOperations(number1,arrayMatText[1],number2);
                    return;
                }
            }catch (NumberFormatException nfe){
                System.out.println("Вы ввели не корректно данные \n Введите чесло от 1 до 10");
            }
        }
    }
    public static void readFail(){
        treeRim = new TreeMap<>();
            String text = "ноль\n" +
                    "I\n" +
                    "II\n" +
                    "III\n" +
                    "IV\n" +
                    "V\n" +
                    "VI\n" +
                    "VII\n" +
                    "VIII\n" +
                    "IX\n" +
                    "X\n" +
                    "XI\n" +
                    "XII\n" +
                    "XIII\n" +
                    "XIV\n" +
                    "XV\n" +
                    "XVI\n" +
                    "XVII\n" +
                    "XVIII\n" +
                    "XIX\n" +
                    "XX\n" +
                    "XXI\n" +
                    "XXII\n" +
                    "XXIII\n" +
                    "XXIV\n" +
                    "XXV\n" +
                    "XXVI\n" +
                    "XXVII\n" +
                    "XXVIII\n" +
                    "XXIX\n" +
                    "XXX\n" +
                    "XXXI\n" +
                    "XXXII\n" +
                    "XXXIII\n" +
                    "XXXIV\n" +
                    "XXXV\n" +
                    "XXXVI\n" +
                    "XXXVII\n" +
                    "XXXVIII\n" +
                    "XXXIX\n" +
                    "XL\n" +
                    "XLI\n" +
                    "XLII\n" +
                    "XLIII\n" +
                    "XLIV\n" +
                    "XLV\n" +
                    "XLVI\n" +
                    "XLVII\n" +
                    "XLVIII\n" +
                    "XLIX\n" +
                    "L\n" +
                    "LI\n" +
                    "LII\n" +
                    "LIII\n" +
                    "LIV\n" +
                    "LV\n" +
                    "LVI\n" +
                    "LVII\n" +
                    "LVIII\n" +
                    "LIX\n" +
                    "LX\n" +
                    "LXI\n" +
                    "LXII\n" +
                    "LXIII\n" +
                    "LXIV\n" +
                    "LXV\n" +
                    "LXVI\n" +
                    "LXVII\n" +
                    "LXVIII\n" +
                    "LXIX\n" +
                    "LXX\n" +
                    "LXXI\n" +
                    "LXXII\n" +
                    "LXXIII\n" +
                    "LXXIV\n" +
                    "LXXV\n" +
                    "LXXVI\n" +
                    "LXXVII\n" +
                    "LXXVIII\n" +
                    "LXXIX\n" +
                    "LXXX\n" +
                    "LXXXI\n" +
                    "LXXXII\n" +
                    "LXXXIII\n" +
                    "LXXXIV\n" +
                    "LXXXV\n" +
                    "LXXXVI\n" +
                    "LXXXVII\n" +
                    "LXXXVIII\n" +
                    "LXXXIX\n" +
                    "XC\n" +
                    "XCI\n" +
                    "XCII\n" +
                    "XCIII\n" +
                    "XCIV\n" +
                    "XCV\n" +
                    "XCVI\n" +
                    "XCVII\n" +
                    "XCVIII\n" +
                    "XCIX\n" +
                    "C";
            String [] arrayRim = text.split("\n");
            int i = 0;
            for (String c:arrayRim) {
                treeRim.put(c,i);
                i++;

            }
    }

    public static boolean checkRim (){
        try {
            if (treeRim.containsKey(arrayMatText[0]) && treeRim.containsKey(arrayMatText[2])){
                return true;
            }
        }catch (Exception e){
            System.out.println("");
        }
        return false;
    }

    public static void aritmetichOperations (String aritmOper){
        int operation;
        switch (aritmOper){
            case "+":
                operation = treeRim.get(arrayMatText[0])+treeRim.get(arrayMatText[2]);
                System.out.println(getKeyTreeRim(operation));
                break;
            case "-":
                operation = treeRim.get(arrayMatText[0])-treeRim.get(arrayMatText[2]);
                System.out.println(getKeyTreeRim(operation));
                break;
            case "*":
                operation = treeRim.get(arrayMatText[0])*treeRim.get(arrayMatText[2]);
                System.out.println(getKeyTreeRim(operation));
                break;
            case "/":
                operation = treeRim.get(arrayMatText[0])/treeRim.get(arrayMatText[2]);
                System.out.println(getKeyTreeRim(operation));
                break;
            default:
                System.out.println("Ошибка: некоректная команда");

        }
    }

    public static void aritmetichOperations (int namber1,String aritmOper, int namber2){
        int operation;
        switch (aritmOper){
            case "+":
                System.out.println(namber1+namber2);
                break;
            case "-":
                System.out.println(namber1-namber2);
                break;
            case "*":
                System.out.println(namber1 * namber2);
                break;
            case "/":
                System.out.println(namber1 / namber2);
                break;
            default:
                System.out.println("Ошибка: некоректная команда");

        }
    }
    public static String getKeyTreeRim(int c){
        for (Map.Entry<String, Integer> entry :treeRim.entrySet()) {
            if (entry.getValue().equals(c)){
                return entry.getKey();
            }
        }
        String error = "Ошибка операции";
        return error;
    }
}
