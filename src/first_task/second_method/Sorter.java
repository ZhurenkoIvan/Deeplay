package first_task.second_method;

import java.util.ArrayList;

public class Sorter {
    private final int[] array;

    public int[] sort() {
        //Создаю два массива, чтобы записать в них четные и нечетные числа
        ArrayList<Integer> evenList = new ArrayList<>();
        ArrayList<Integer> unevenList = new ArrayList<>();
        for(int number : array) {
            if (number == 0) {
                continue;
            } else if (number % 2 == 0) {
                evenList.add(number);
            } else {
                unevenList.add(number);
            }
        }

        //Сортирую массивы
        evenList.sort(new ReversedComparator());
        unevenList.sort(Integer::compareTo);

        //Возвращаю массив, в который я вставил отсортированные значения
        return getSortedArray(evenList, unevenList);
    }

    private int[] getSortedArray(ArrayList<Integer> evenList, ArrayList<Integer> unevenList) {
        int evenCount = 0;
        //Собираю массив заново. Слева вставляю отсортированные нечетные, проверяю, есть ли нули
        //и вставляю отсортированные четные справа
        for (int i = 0; i < array.length; i++) {
            if (i < unevenList.size()) {
                array[i] = unevenList.get(i);
            } else if (i < array.length - evenList.size()) {
                array[i] = 0;
            }else {
                array[i] = evenList.get(evenCount);
                evenCount++;
            }
        }
        return array;
    }

    public Sorter(int[] array){
        this.array = array;
    }
}
