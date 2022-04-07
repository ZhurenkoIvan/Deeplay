package third_task;

public class Player {
    private String name;
    private String winStreak;

    public Player(String name, String winStreak) {
        this.name = name;
        this.winStreak = winStreak;
    }

    public String getWinStreak() {
        return winStreak;
    }

    public void setWinStreak(String winStreak) {
        this.winStreak = winStreak;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", winStreak='" + winStreak + '\'' +
                '}';
    }
}
