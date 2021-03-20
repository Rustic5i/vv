package ru.geekbrains.java1.lesson1.calculator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
        BufferedInputStream read = null;
        try {
            read = new BufferedInputStream(new FileInputStream("Rim.txt"));

            int length = read.available();
            byte [] bytes = new byte[length];
            read.read(bytes);
            String text = new String(bytes);
            String [] arrayRim = text.split("\r\n");
            int i = 0;
            for (String c:arrayRim) {
                treeRim.put(c,i);
                i++;

            }
        } catch (Exception e) {
            System.out.println("Файл не найден");
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
