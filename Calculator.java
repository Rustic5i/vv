package ru.geekbrains.java1.lesson1.calculator;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Calculator {
    static TreeMap<String,Integer> treeRim;
    static String [] arrayMatText;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        readFail(); // запускаем метод для считывани файла и записи данных TreeMap
        dataCheck(); // Запускаем метод который проверяет что ввел пользователь.
    }
    public static void dataCheck(){
        System.out.println("Введите пример через пробел, пример \n 1 + 2");
        while (true) { // Это бесконечный цикл. мы не выйдем от сюда пока пользователь не введет коректно данные
            try {
                arrayMatText = new String[3]; // Тут создаем массив с 3мя ячейка так как "2 + 3" 2-пойдет в первую ячейку, "+" - пойдет во вторую ячеку массива и 3 - пойдет в третью ячейку
                String matLevel = scanner.nextLine(); // тут ждем что введет пользователь
                arrayMatText = matLevel.split(" "); // то что пользователь ввел делим по пробелу  и записываем в массив
                if (checkRim()){ // Тут проверяем ввел ли пользователь римские цифры. ЕСЛИ НЕТ ИДЕМ ДАЛЬШЕ
                    rimAritmetichOperations(arrayMatText[1]); // если да то передаем в метод rimAritmetichOperations ячейку массива под номер 1. так как там храниться знак +-/или*
                    return; // Выходим из безконечного цикла
                }
                // раз мы дошли до сюда, значит пользователь ввел простые цифры или какую ту не понятную нам фигню. Нужно нам это проверить !!!
                int number1 = Integer.parseInt(arrayMatText[0]);// тут конвертируем из сторокового значения в int. Если не получится, значит пользователь ввел какую ту фигню и мы выкеним исключение тоесть ошибку
                int number2 = Integer.parseInt(arrayMatText[2]); // тут аналогично
                if (number1 > 10 || number2 > 10) { // тут уж понятно
                    System.out.println("Ошибка: превышен числовой диапазон \n Введите чесло от 1 до 10");
                }else {
                    aritmetichOperations(number1,arrayMatText[1],number2); // запускаем метод калькулятор для простых чисел
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
            read = new BufferedInputStream(new FileInputStream("Rim.txt")); // указываем от куда будет считывать. Место имени можно указать путь к файлу

            int length = read.available(); // Узнаем длину файла
            byte [] bytes = new byte[length]; // Записываем все в байтовый массив
            read.read(bytes);
            String text = new String(bytes); // Переводим с байтов в строковое значение.
            String [] arrayRim = text.split("\r\n"); // Строку разделиле на массив. Тоесть теперь каждая Римская цифра в отдельной ячейки массива
            int i = 0;
            for (String c:arrayRim) { // Пробегаемся по массиву
                treeRim.put(c,i); // И записываем в Map с = Римская цифра- он же будет у нас ключом. i = это простые арабские цифры. начинаем нуля
                i++;

            }
        } catch (Exception e) {
            System.out.println("Файл не найден");
        }
    }

    public static boolean checkRim (){ // Этот метод проверяет, есть ли то что ввел клиент в TreeMape. Если то что он ввел нету. Возвращаем fols
        try {
            if (treeRim.containsKey(arrayMatText[0]) && treeRim.containsKey(arrayMatText[2])){
                return true;
            }
        }catch (Exception e){
            System.out.println("");
        }
        return false;
    }
    // Этот метод арефметических операций только для римских чисел
    public static void rimAritmetichOperations (String aritmOper){ // на вход методу подаем знак +,-,или*, и тд
        int operation;
        switch (aritmOper){
            case "+": // если "aritmOper = +" мы заедем сюда
                operation = treeRim.get(arrayMatText[0])+treeRim.get(arrayMatText[2]); // int operation записываем значение которое получилось в
                System.out.println(getKeyTreeRim(operation)); // и это значение передаем методу. getKeyTreeRim этот метод найдет по значение operation римскую цифру в TreeMapе
                break;
            case "-": // если "aritmOper = -" мы заедем сюда
                operation = treeRim.get(arrayMatText[0])-treeRim.get(arrayMatText[2]); // Ниже аналогично
                System.out.println(getKeyTreeRim(operation));
                break;
            case "*": // если "aritmOper = *" мы заедем сюда
                operation = treeRim.get(arrayMatText[0])*treeRim.get(arrayMatText[2]);
                System.out.println(getKeyTreeRim(operation));
                break;
            case "/":// если "aritmOper = /" мы заедем сюда
                operation = treeRim.get(arrayMatText[0])/treeRim.get(arrayMatText[2]);
                System.out.println(getKeyTreeRim(operation));
                break;
            default: // если из выше перечисленых не подошло по значение. выводим ошибку
                System.out.println("Ошибка: некоректная команда");

        }
    }
// Это метод арефмит операций уже для арабских цифр
    public static void aritmetichOperations (int namber1,String aritmOper, int namber2){ // на вход поступает первая цифра, потом знак "/"или"*", "-" и вторая цифра
        int operation;
        switch (aritmOper){
            case "+":
                System.out.println(namber1+namber2); // тут уже просто прибовляем то что ввел клиент
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
    // Этот метод ищет значение в TreeMap по параметру которые в него передаем
    public static String getKeyTreeRim(int c){
        for (Map.Entry<String, Integer> entry :treeRim.entrySet()) {
            if (entry.getValue().equals(c)){
                return entry.getKey(); // выводим римскую цифру если нашли ее

            }
        }
        String error = "Ошибка операции";
        return error;
    }
}