package ammo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Arrow {

    private double x;
    private double y;
    private double dx;
    private double dy;

    private Image image;
    private ImageView imageView;

    public Arrow() {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        this.image = new Image("/images/arrow.png");
        this.imageView = new ImageView(image);
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

    public Image getImage() {
        return image;
    }
    public ImageView getImageView() {
        return imageView;
    }
}
