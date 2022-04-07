package first_task.second_method;

import java.util.Comparator;

public class ReversedComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
       return o2 - o1;
    }
}
