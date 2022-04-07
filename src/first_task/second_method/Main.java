package first_task.second_method;


import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        NumberGenerator numberGenerator = new NumberGenerator(100);
        int[] array = numberGenerator.generateNumbers();
        System.out.println(Arrays.toString(array));
        Sorter sorter = new Sorter(array);
        array = sorter.sort();
        System.out.println(Arrays.toString(array));
    }

}
