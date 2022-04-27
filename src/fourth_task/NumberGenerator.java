package fourth_task;

public class NumberGenerator {
    int size;

    public NumberGenerator(int size) {
        this.size = size;
    }

    public Integer[] generateNumbers() {
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
                int number = (int) (Math.random() * 90) + 1;
                array[i] = number;
        }
        return array;
    }
}
