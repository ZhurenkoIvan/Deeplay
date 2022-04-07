package first_task.first_method;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new NumberGenerator(100);
        int[] array = numberGenerator.generateNumbers();
        System.out.println(Arrays.toString(array));
        Sorter sorter = new Sorter(array);
        int[] newArray = sorter.sort();
        System.out.println(Arrays.toString(newArray));
    }
}
