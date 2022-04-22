package towers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet {

    private double x;
    private double y;
    private double dx;
    private double dy;

    private ImageView imageView;

    public Bullet() {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        this.imageView = new ImageView(new Image("/images/arrow.png"));
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void updateUI() {
        imageView.relocate(x, y);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }
    public double getDy() {
        return dy;
    }
    public void setDx(double dx) {
        this.dx = dx;
    }
    public void setDy(double dy) {
        this.dy = dy;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
