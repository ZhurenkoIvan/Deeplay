package second_task;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new NumberGenerator(100);
        int[] array = numberGenerator.generateNumbers();
        System.out.println(Arrays.toString(array));
        //Делаю заготовку, чтобы понять, какие числа в принципе существуют в массиве
        HashSet<Integer> allDigits = new HashSet<>();
        for (int digit : array) {
            allDigits.add(digit);
        }
        //Создаю мапу, в которой будут храниться (числа - количество упоминаний)
        HashMap<Integer, Integer> digitsCount = new HashMap<>();
        for (Integer digit : allDigits) {
            digitsCount.put(digit, 0);
        }
        //Заполняю мапу всеми существующими числами
        for (int digit : array) {
            digitsCount.put(digit, digitsCount.get(digit) + 1);
        }
        //Получаю максимальное value
        int maxCount = Collections.max(digitsCount.values());

        //Cоздаю массив, в который буду класть все key у которых value = maxCount и вывожу его на экран
        ArrayList<Integer> digitsWithMaxCount = new ArrayList<>();
        for (int digit : digitsCount.keySet()) {
            int count = digitsCount.get(digit);
            if (count == maxCount) {
                digitsWithMaxCount.add(digit);
            }
        }
        System.out.println(digitsWithMaxCount);
    }
}
