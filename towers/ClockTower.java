package towers;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClockTower {
    private int boost;
    private int cost;

    int x;
    int y;

    private ImageView imageView;

    public ClockTower() {
        this.boost = 50;
        this.cost = 800;

        this.x = x;
        this.y = y;

        this.imageView = new ImageView(new Image("/images/clocktower.png"));
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

    public int getBoost() {
        return boost;
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