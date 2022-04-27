package fourth_task;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Генерация массива
        int size = scanner.nextInt();
        int groupsCount = scanner.nextInt();
        NumberGenerator numberGenerator = new NumberGenerator(size);
        Integer[] array =  numberGenerator.generateNumbers();
        //Расчеты для последующей работы
        int arraySum = Arrays.stream(array).mapToInt(Integer::intValue).sum();
        int currentSum;
        int requiredGroupSum = arraySum / groupsCount;
        Optional<Integer> maxOpt = Arrays.stream(array).max(Integer::compareTo);
        //Переменная, обозначающая последний элемент, прибавив который наша сумма была не больше необходимой
        int lastJ = 0;

        //Сортирую массив в порядке убывания
        Arrays.sort(array, Collections.reverseOrder());
        System.out.println(Arrays.toString(array));
        //Первоочередные проверки, что такой перебор может существовать
        if (size == groupsCount && arraySum % groupsCount == 0) {
            for (int number: array) {
                System.out.println(number);
            }
            return;
        }else if (arraySum % groupsCount != 0 || groupsCount > size || maxOpt.get() > requiredGroupSum) {
            System.out.println("Невозможно");
            return;
        } else if (groupsCount == 1) {
            System.out.println(Arrays.toString(array));
            return;
        }
        //Начинаю перебор элементов
        for (int i = 0; i < array.length; i++) {
            ArrayList<ArrayList<Integer>> resultArrays = new ArrayList<>();
            for (int d = 0; d < groupsCount; d++) {
                resultArrays.add(new ArrayList<>());
            }
            int currentGroupCount = 0;
            int currentArraySize = 1;
            boolean[] numberUsed = new boolean[size];
            //Беру первый элемент
            currentSum = array[i];
            //Ставлю флаг, что взял его
            numberUsed[i] = true;
            boolean[] tempArray = numberUsed.clone();
            //Создаю такой же массив, чтобы далее отслеживать какие элементы взяты в данной итерации, чтобы заполнить ими результирующий массив
            boolean[] numbersUsedInCurrentIteration = numberUsed.clone();
            //Начинаю перебор всех вариантов при array[i]
            for (int j = 0; j < array.length; j++) {
                if (currentSum == requiredGroupSum && currentArraySize == 1) {
                    resultArrays.get(currentGroupCount).add(currentSum);
                    currentGroupCount++;
                    currentSum=0;
                    currentArraySize=0;
                    numbersUsedInCurrentIteration = new boolean[size];
                    continue;
                }
                //Если счетчик будет равен нужному количеству групп - 1, то оставшиеся числа в сумме дадут нужное число
                if (currentGroupCount == groupsCount - 1) {
                    for (int k = 0; k < numberUsed.length; k++) {
                        if (!tempArray[k]) {
                            resultArrays.get(currentGroupCount).add(array[k]);
                        }
                    }
                    resultArrays.forEach(System.out::println);
                    return;
                }
                //Если число под этим индексом НЕ взято, то продолжаю работу
                if (!tempArray[j]) {
                    //Суммирую значения
                    currentSum += array[j];
                    //Так как массив отсортирован и сумма уже больше, чем нужная, то дальше идти смысла нет
                    if (currentSum > requiredGroupSum) {
                        //Если это последний элемент, то ничего вычитать не нужно, а просто запускать цикл i заново
                        if (j == array.length - 1) {
                            continue;
                        }
                        else if (j - 1 == i && j - 2 > -1) {
                            //Помечаю, что предыдущий элемент не будет участовать в результирующем массиве
                            numbersUsedInCurrentIteration[j - 2] = false;
                            //Помечаю, что этот элемент нужно пропустить.
                            tempArray[j - 2] = true;
                            //Вычитаю из текущей суммы значение предыдущего и текущего элемента и возвращаю j на 1 назад
                            currentSum = currentSum - array[j] - array[j - 2];
                            j = j - 2;
                        } else {
                            //Отдельно обрабатываю ситуацию, где в массиве всего 2 элемента
                            if (currentArraySize <= 2) {
                                currentSum = currentSum - array[j];
                                lastJ = j;
                            }
                            else {
                                //Помечаю, что предыдущий элемент не будет участовать в результирующем массиве
                                numbersUsedInCurrentIteration[lastJ] = false;
                                //Вычитаю из текущей суммы значение предыдущего и текущего элемента и возвращаю j на 1 назад
                                currentSum = currentSum - array[j] - array[lastJ];
                            }
                                //Помечаю, что этот элемент нужно пропустить.
                            tempArray[lastJ] = true;
                            j--;
                        }
                        continue;
                    }
                    //Если сумма равна нужной, то заполняю массив, увеличиваю счетчик, заполняю массив used данными из usedJ, сбрасываю usedJ
                    if (currentSum == requiredGroupSum) {
                        //Отмечаю в массиве для последующего заполнения, что в данной итерации я использую текущий j элемент
                        numbersUsedInCurrentIteration[j] = true;
                        //Заполнение массива
                        for (int k = 0; k < numbersUsedInCurrentIteration.length; k++) {
                            if (numbersUsedInCurrentIteration[k]) {
                                resultArrays.get(currentGroupCount).add(array[k]);
                            }
                        }
                        //Увеличил счетчик
                        currentGroupCount++;
                        //Обозначил взятые элементы
                        for (int k = 0; k < numberUsed.length; k++) {
                            if (!numberUsed[k] && numberUsed[k] != numbersUsedInCurrentIteration[k]){
                                numberUsed[k] = true;
                            }
                        }
                        tempArray = numberUsed.clone();
                        //Сбросил usedJ
                        numbersUsedInCurrentIteration = new boolean[size];
                        //Сбросил сумму
                        currentSum = 0;
                        //Сбросил j
                        j = -1;
                        continue;
                    }
                    //Отмечаю в массиве для последующего заполнения, что в данной итерации я использую текущий j элемент
                    numbersUsedInCurrentIteration[j] = true;
                    lastJ = j;
                    currentArraySize++;
                }
            }
        }
        System.out.println("Невозможно");
    }
}
