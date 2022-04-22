package towers;

public class Tower {
    private int attack;
    private int cost;
    private int locationX;
    private int locationY;
    private int proximity;
    

    public Tower() {
        this.attack = 0;
        this.cost = 600;

    }

    public int getAttack() {
        return attack;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getLocationX() {
        return locationX;
    }
    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }
    public int getLocationY() {
        return locationY;
    }
    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }
    public int getProximity() {
        return proximity;
    }
    public void setProximity(int proximity) {
        this.proximity = proximity;
    }
}