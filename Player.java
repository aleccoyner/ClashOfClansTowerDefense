public class Player {
    private String name;
    private String difficulty;
    private int money;
    private int towerMultiplier;
    private int monumentMultiplier;

    public Player() {
        this.name = name;
        this.difficulty = difficulty;
        this.money = 1000;
        this.towerMultiplier = 50;
        this.monumentMultiplier = 25;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public int getTowerMultiplier() {
        return towerMultiplier;
    }
    public void setTowerMultiplier(int towerMultiplier) {
        this.towerMultiplier = towerMultiplier;
    }
    public int getMonumentMultiplier() {
        return monumentMultiplier;
    }
    public void setMonumentMultiplier(int monumentMultiplier) {
        this.monumentMultiplier = monumentMultiplier;
    }
}
