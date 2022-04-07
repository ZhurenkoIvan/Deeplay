package third_task;

import java.util.Arrays;
import java.util.HashMap;

public class LetsPlay {
    private Player firstPlayer;
    private Player secondPlayer;
    private int throwsCount;

    private final HashMap<Player, Integer> playerScore = new HashMap<>();

    public int gameResult() {
        countPoints();
        int result = playerScore.get(firstPlayer) - playerScore.get(secondPlayer);
        if (result > 0) return 1;
        if (result < 0) return -1;
        else return 0;
    }

    private void countPoints() {
        //Генерирую массив значений
        int[] resultArray = new NumberGenerator(throwsCount).generateNumbers();

        //Заполняю мапу значениями (Игрок - его очки)
        playerScore.put(firstPlayer, 0);
        playerScore.put(secondPlayer, 0);

        //Так как часто будет использоваться конкатенация
        //решил пользоваться стрингбилдером
        StringBuilder firstStringBuilder = new StringBuilder();
        StringBuilder secondStringBuilder = new StringBuilder();
        //Логика такая: сначала я суммирую три подряд числа, после чего проверяю
        //совпадает ли эти три числа с выигрышной комбинацией
        //если не совпадает, значит удаляю первое число, и в конец добавляю следующее
        //если совпадает, значит я полностью очищаю стрингбилдер. Таким образом
        //я избегаю дублирования
        for (int j : resultArray) {
            if (firstStringBuilder.toString().length() == 3) {
                firstStringBuilder.delete(0, 1);
            }
            if (secondStringBuilder.toString().length() == 3) {
                secondStringBuilder.delete(0, 1);
            }
            firstStringBuilder.append(j);
            secondStringBuilder.append(j);

            if (firstStringBuilder.toString().equals(firstPlayer.getWinStreak())) {
                playerScore.put(firstPlayer, playerScore.get(firstPlayer) + 1);
                firstStringBuilder.delete(0, 3);
            }
            if (secondStringBuilder.toString().equals(secondPlayer.getWinStreak())) {
                playerScore.put(secondPlayer, playerScore.get(secondPlayer) + 1);
                secondStringBuilder.delete(0, 3);
            }
        }
    }

    public LetsPlay(Player firstPlayer, Player secondPlayer, int throwsCount) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.throwsCount = throwsCount;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public int getThrowsCount() {
        return throwsCount;
    }

    public void setThrowsCount(int throwsCount) {
        this.throwsCount = throwsCount;
    }
}
