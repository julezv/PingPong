/**
 * Created by yuliav on 01/04/2019.
 */

import processing.core.PApplet;
import processing.core.PFont;

public class PingPongGame extends PApplet {
    int gameWidth = 500;
    int gameHeight = 500;
    PFont f;

    int lives = 3;
    int gameScore = 0;
    boolean gameOver = false;

    Deck deck = new Deck(gameWidth, gameHeight, this);
    Ball ball = new Ball(gameWidth, gameHeight, deck, this);

    //public static void main(String[] args) {
//        PApplet.main("PingPongGame");
//    }

    @Override
    public void setup() {
        background(0);
        size(gameWidth, gameHeight);
        frameRate(80);
        f = createFont("Arial",16,true);
    }

    void setGameOver() {
        lives--;
        if (lives <= 0)
            gameOver = true;
        else
            ball = new Ball(gameWidth, gameHeight, deck, this);
    }

    boolean gameIsOver() {
        return gameOver;
    }

    void incGameScore() {
        gameScore++;
    }

    @Override
    public void draw() {
        background(0);
        deck.draw();
        ball.draw();

       if (gameOver) {
           textAlign(CENTER, CENTER);
           textFont(f,16);
           fill(255, 0 , 0 );
           text("Game over!",gameWidth * 0.5f,gameHeight * 0.5f);
        }
        textFont(f,16);
        textAlign(LEFT);
        fill(255, 0 , 0 );
        text("Score: " + gameScore , gameWidth * 0.75f,gameHeight * 0.125f);
    }


    @Override
    public void keyPressed() {
        if (!gameIsOver()) {
            if (keyCode == LEFT) {
                deck.moveLeft();
            } else if (keyCode == RIGHT) {
                deck.moveRight();
            }
        }
    }
}
