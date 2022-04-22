package enemies;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

public class Enemy {
    private int attack;
    private long attackSpeed;
    private int health;
    private double speed;
    private int explosionDamage;
    private Integer locationX;
    private Integer locationY;
    private int stealingValue;
    private int money;

    public Enemy() {
        this.attack = 10;
        this.attackSpeed = 1000;
        this.health = 50;
        this.speed = 25000;
        this.explosionDamage = 10;
    }

    public int getStealingValue() {
        return stealingValue;
    }
    public void setStealingValue(int stealingValue) {
        this.stealingValue = stealingValue;
    }
    public Integer getLocationX() {
        return locationX;
    }
    public Integer getLocationY() {
        return locationY;
    }
    public void setLocationX(Integer locationX) {
        this.locationX = locationX;
    }
    public void setLocationY(Integer locationY) {
        this.locationY = locationY;
    }
    public int getExplosionDamage() {
        return explosionDamage;
    }
    public int getAttack() {
        return attack;
    }
    public long getAttackSpeed() {
        return attackSpeed;
    }
    public int getHealth() {
        return health;
    }
    public double getSpeed() {
        return speed;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setExplosionDamage(int explosionDamage) {
        this.explosionDamage = explosionDamage;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
}
