package third_task;

public class NumberGenerator {
    int size;

    public NumberGenerator(int size) {
        this.size = size;
    }

    public int[] generateNumbers() {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 6) + 1;
        }
        return array;
    }
}
