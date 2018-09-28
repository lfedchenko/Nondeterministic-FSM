package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String path = "";

    public static FSM read() {
        FSM fsm = null;
        int alphabetLength;
        int stateLength;
        int numberofFirst;
        int numberofFinals;
        int[] finals = null;
        List<String> trans = new ArrayList<>();

        System.out.println("Реализация недетерменированного конечного автомата.");
        //задан ли корректно путь к файлу
        boolean gotten = false;
        while (!gotten) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите путь к файлу: ");
            path = in.nextLine();

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                //зачем нам количество букв в алфавите?
                String line = br.readLine();
                alphabetLength = Integer.parseInt(line);

                //количество состояний
                line = br.readLine();
                stateLength = Integer.parseInt(line);

                //какое состояние первое
                line = br.readLine();
                numberofFirst = Integer.parseInt(line);

                //разделяем строку с финальными состояниями по пробелу
                line = br.readLine();
                String[] words = line.split("\\s");
                //количество финальных состояний
                numberofFinals = Integer.parseInt(words[0]);
                finals = new int[numberofFinals];
                //массив финальных состояний
                for (int i = 0; i < numberofFinals; i++) {
                    finals[i] = Integer.parseInt(words[i+1]);
                }

                //считываем переходы
                while ((line = br.readLine()) != null) {
                    trans.add(line);
                }
                gotten = true;
                fsm = new FSM(stateLength, numberofFirst, finals, trans);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return fsm;
    }

    public static void main(String[] args) {
        FSM fsm = read();
        Scanner in = new Scanner(System.in);
        System.out.println("При вводе пустой строки программа завершается");
        System.out.println("Введите Ваше слово: ");
        String word = in.nextLine();
        while (!word.isEmpty()) {
            System.out.println(fsm.acceptable(word));
            System.out.println("Введите Ваше слово: ");
            word = in.nextLine();
        }
    }
}
