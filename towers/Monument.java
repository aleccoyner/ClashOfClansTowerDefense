package towers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monument {
    private int health;
    private ImageView imageView;

    public Monument() {
        this.health = 200;
        this.imageView = new ImageView(new Image("/images/monument.png"));
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public ImageView getImageView() {
        return imageView;
    }
}
