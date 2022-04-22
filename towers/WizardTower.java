package towers;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WizardTower {
    private int attack;
    private int cost;

    int x;
    int y;

    private ImageView imageView;

    public WizardTower() {
        this.attack = 25;
        this.cost = 600;

        this.x = x;
        this.y = y;

        this.imageView = new ImageView(new Image("/images/wizardtower.png"));
    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
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

    public ImageView getImageView() {
        return imageView;
    }
}