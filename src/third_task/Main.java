package third_task;

import first_task.second_method.Sorter;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //Регистрирую игроков и количество бросков
        Player firstPlayer = new Player("Ivan", "123");
        Player secondPlayer = new Player("Liza", "321");
        int throwsCount = 100_000;

        //Создаю мапу, в нее закидываю результаты каждого раунда
        HashMap<String, Integer> resultMap = new HashMap<>();
        resultMap.put("DRAW", 0);
        resultMap.put("First player WIN!", 0);
        resultMap.put("Second player WIN!", 0);

        //Чтобы посчитать вероятность, мне нужна выборка из игр
        //Провожу 100_000 симуляций, записываю каждую в мапу
        for (int i = 0; i < 100_000; i++) {
            LetsPlay letsPlay = new LetsPlay(firstPlayer, secondPlayer, throwsCount);
            int result = letsPlay.gameResult();
            if (result == -1)
            resultMap.put("Second player WIN!", resultMap.get("Second player WIN!") + 1);
            if (result == 0)
            resultMap.put("DRAW", resultMap.get("DRAW") + 1);
            if (result == 1)
            resultMap.put("First player WIN!", resultMap.get("First player WIN!") + 1);

        }

        //Для красоты вывожу результат(ну и чтобы можно было сравнить похоже на правду или нет)
        for (String s: resultMap.keySet()) {
            System.out.println(s + " - " + resultMap.get(s));
        }

        //А тут считаю вероятность и вывожу на экран
        double draw = (double)resultMap.get("DRAW") / throwsCount;
        System.out.println("Вероятность того, что будет ничья - " + draw);
        double firstPlayerWins = (double) resultMap.get("First player WIN!") / throwsCount;
        System.out.println("Вероятность того, что победит первый игрок - " + firstPlayerWins);
        double secondPlayerWins = (double) resultMap.get("Second player WIN!") / throwsCount;
        System.out.println("Вероятность того, что победит второй игрок - " + secondPlayerWins);

    }
}
