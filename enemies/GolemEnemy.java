package enemies;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GolemEnemy {
    private int attack;
    private int attackSpeed;
    private int health;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private ImageView imageView;

    public GolemEnemy() {
        this.attack = 5;
        this.attackSpeed = 105;
        this.health = 100;

        this.x = -100;
        this.y = 45;
        this.dx = 1;
        this.dy = 1;

        this.imageView = new ImageView(new Image("/images/golem.png"));
    }

    public void move() {
        if (x < 270 && y == 45) {
            x += dx;
        } else if (x >= 270 && x < 275 && y >= 45 && y < 155) {
            y += dy;
        } else if (x >= 270 && x < 475 && y >= 155 && y < 160) {
            x += dx;
        } else if (x >= 475 && x < 480 && y > 45 && y <= 160) {
            y -= dy;
        } else if (x >= 475 && x < 700 && y > 40 && y <= 45) {
            x += dx;
        } else if (x >= 700 && x < 705 && y > 40 && y < 290) {
            y += dy;
        } else if (x >= 490 && x < 705 && y >= 290 && y < 295) {
            x -= dx;
        } else if (x > 485 && x <= 490 && y >= 290 && y < 535) {
            y += dy;
        } else if (x > 485 && x < 710 && y > 530 && y <= 540) {
            x += dx;
        } else if (x >= 710 && x < 715 && y <= 540 && y > 420) {
            y -= dy;
        } else if (x >= 710 && x < 910 && y > 415 && y <= 420) {
            x += dx;
        } else if (x > 905 && x <= 915 && y > 415 && y < 530) {
            y += dy;
        } else if (x > 905 && x < 1110 && y > 525 && y <= 535) {
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