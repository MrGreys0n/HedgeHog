package Objects;

import DataClasses.Variables;

import java.awt.*;

public class GameObject {
    private int x, y;
    private Image img;

    public GameObject(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public GameObject(Image img) {
        this.x = 0; this.y = 0;
        while (this.x == 0 && this.y == 0) {
            this.x = Variables.CELL_SIZE * (int) (Math.random() * Variables.CELLS_W);
            this.y = Variables.CELL_SIZE * (int) (Math.random() * Variables.CELLS_H);
        }
        this.img = img;
    }

    public void draw(Graphics gr) {
        gr.drawImage(img, x, y, Variables.CELL_SIZE, Variables.CELL_SIZE, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinates(int x, int y) {
        if (0 <= x && x < Variables.FIELD_WIDTH && 0 <= y && y < Variables.FIELD_HEIGHT){
            setX(x);
            setY(y);
        }
    }

    public boolean collisionWith(GameObject other) {
        return collisionWith(other.getX(), other.getY());
    }

    public boolean collisionWith(int x, int y) {
        return this.x == x && this.y == y;
    }
}
