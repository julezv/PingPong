import processing.core.PApplet;

/**
 * Created by yuliav on 01/04/2019.
 */
public class Deck {
    private int gameWidth;
    private int gameHeight;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int width = 100;
    private int height = 20;
    private int x; // upper left corner => in[0; gameWidth - width]
    private int y; // upper left corner => = gameHeight - height
    private int speed = 15;
    private PApplet canvas;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Deck(int gameWidth, int gameHeight, PApplet canvas) {
        this.canvas = canvas;

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        x = gameWidth / 2 - width / 2;
        y = gameHeight - height;
    }

    public void moveLeft() {
        x -= speed;
        if (x < 0)
            x = 0;
    }

    public void moveRight() {
        x += speed;
        if(x > gameWidth - width){
            x = gameWidth - width;
        }
    }

    public void draw() {
        canvas.fill(255);
        canvas.rect(x, y, width, height);
    }
}
