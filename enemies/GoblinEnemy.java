package enemies;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GoblinEnemy {
    private int attack;
    private int attackSpeed;
    private int health;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private ImageView imageView;

    public GoblinEnemy() {
        this.attack = 2;
        this.attackSpeed = 45;
        this.health = 20;

        this.x = -100;
        this.y = 45;
        this.dx = 2;
        this.dy = 2;

        this.imageView = new ImageView(new Image("/images/goblin.png"));
    }

    public void move() {
        if (x < 250 && y == 45) {
            x += dx;
        } else if (x >= 250 && x < 255 && y >= 45 && y < 155) {
            y += dy;
        } else if (x >= 250 && x < 455 && y >= 155 && y < 160) {
            x += dx;
        } else if (x >= 455 && x < 460 && y > 45 && y <= 160) {
            y -= dy;
        } else if (x >= 455 && x < 680 && y > 40 && y <= 45) {
            x += dx;
        } else if (x >= 680 && x < 685 && y > 40 && y < 290) {
            y += dy;
        } else if (x >= 470 && x < 685 && y >= 290 && y < 295) {
            x -= dx;
        } else if (x > 465 && x <= 470 && y >= 290 && y < 535) {
            y += dy;
        } else if (x > 465 && x < 690 && y > 530 && y <= 540) {
            x += dx;
        } else if (x >= 690 && x < 695 && y <= 540 && y > 420) {
            y -= dy;
        } else if (x >= 690 && x < 890 && y > 415 && y <= 420) {
            x += dx;
        } else if (x > 885 && x <= 890 && y > 415 && y < 530) {
            y += dy;
        } else if (x > 885 && x < 1110 && y > 525 && y <= 535) {
            x += dx;
        }
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

    public int getAttack() {
        return attack;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getAttackSpeed() {
        return attackSpeed;
    }

    public ImageView getImageView() {
        return imageView;
    }
}