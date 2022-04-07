package first_task.first_method;

public class Sorter {

    private final int[] originArray;

    public int[] sort() {
        int[] unsortedArray = sortByEvenAndUneven();
        return bubbleSort(unsortedArray);
    }

    public Sorter(int[] array) {
        this.originArray = array;
    }

    private int[] sortByEvenAndUneven() {
        int length = originArray.length;
        int unevenIndex = 0;
        int evenIndex = length - 1;
        int[] tempArray = new int[length];
        //Получаю массив с нечетными слева и четными справа
        for (int i = 0; i < length; i++) {
            if (originArray[i] == 0) {
            } else if (originArray[i] % 2 == 0) {
                tempArray[evenIndex] = originArray[i];
                evenIndex--;
            } else {
                tempArray[unevenIndex] = originArray[i];
                unevenIndex++;
            }
        }
        if (unevenIndex < evenIndex) {
            for (int i = unevenIndex; i < evenIndex; i++) {
                tempArray[i] = 0;
            }
        }
        return tempArray;
    }

    private int[] bubbleSort(int[] array) {
        int[] unevenArray, evenArray;
        int zeroCount = 0;
        int firstEvenIndex = -1;
        int lastUnevenIndex = -1;
        //Вычисляю количество нулей, конец нечетной и начало четной части
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                zeroCount++;
            } else if (array[i] % 2 == 0 && lastUnevenIndex < 0) {
                lastUnevenIndex = i - zeroCount - 1;
                firstEvenIndex = i;
            }
        }
        //Делю целый массив на два маленьких
        unevenArray = getUnevenArray(array, lastUnevenIndex);
        evenArray = getEvenArray(array, firstEvenIndex);
        //Сортирую четный и нечетный массив
        unevenArray = sortUnevenArray(unevenArray);
        evenArray = sortEvenArray(evenArray);
        //Объединяю их в одной целое
        int evenCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (i <= lastUnevenIndex) {
                array[i] = unevenArray[i];
            }
            if (i >= firstEvenIndex) {
                array[i] = evenArray[evenCount];
                evenCount++;
            }
        }


        return array;
    }

    private int[] sortUnevenArray(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    private int[] sortEvenArray(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] < array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    private int[] getUnevenArray(int[] array, int lastUnevenIndex) {
        if (lastUnevenIndex == -1) {
            return array;
        }
        int[] newArray = new int[lastUnevenIndex + 1];

        for (int i = 0; i <= lastUnevenIndex; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    private int[] getEvenArray(int[] array, int firstEvenIndex) {
        if (firstEvenIndex == -1) {
            return array;
        }
        int[] newArray = new int[array.length - firstEvenIndex];
        int j = 0;
        for (int i = firstEvenIndex; i < array.length; i++) {
            newArray[j] = array[i];
            j++;
        }
        return newArray;
    }
}
