import processing.core.PApplet;

/**
 * Created by yuliav on 01/04/2019.
 */
public class Ball {
    private int gameWidth;
    private int gameHeight;
    private int r = 30;
    private int x;
    private int y;
    private int dirX = 1;
    private int dirY = 1;
    private int speed = 2;
    private PingPongGame canvas;
    private Deck deck;
    byte count = 0;
    boolean blinkingMode;
    byte signChanged = 0;

    public Ball(int gameWidth, int gameHeight, Deck deck, PingPongGame canvas) {
        this.canvas = canvas;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        this.deck = deck;

        x = (int)canvas.random(gameWidth);
        y = (int)canvas.random(3 * r, gameHeight / 3);

    }

    public void move() {
        x += speed * dirX;
        y += speed * dirY;
        if (x < r / 2) {
            x = r / 2;
            dirX *= -1;
        }
        if(x > gameWidth - r / 2) {
            x = gameWidth - r / 2;
            dirX *= -1;
        }

        if (y < r / 2 ) {
            y = r / 2;
            dirY *= -1;
        }

        if(y > gameHeight - r / 2 - deck.getHeight()) {
            if (x > deck.getX() && x < deck.getX() + deck.getWidth()) {  //TODO: а что, если центр шара не над платформой?
                y = gameHeight - r / 2 - deck.getHeight();
                dirY *= -1;
                canvas.incGameScore();
            } else {
                blinkingMode = true;
                //canvas.setGameOver();
            }
        }
    }

    public void draw(boolean blink) {
        if(!blink) {
            move();
            canvas.ellipse(x, y, r, r);
        } else {
            int prevCount = count;
            count+= 10;
            if (count * prevCount < 0) {
                signChanged++;
            }
            if (count > 0)
                canvas.fill(0);
            else {
                canvas.fill(255);
            }
            canvas.ellipse(x, y, r, r);
            if (signChanged > 6) {
                canvas.setGameOver();
            }
        }
    }

    public void draw() {
        draw(blinkingMode);
    }
}
