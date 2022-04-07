package fourth_task;

public class NumberGenerator {
    int size;

    public NumberGenerator(int size) {
        this.size = size;
    }

    public int[] generateNumbers() {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            if (Math.random() > 0.5) {
                int number = (int) (Math.random() * 100) - 1;
                array[i] = number;
            } else {
                int number = (int) (Math.random() * -100) + 1;
                array[i] = number;
            }
        }
        return array;
    }
}
