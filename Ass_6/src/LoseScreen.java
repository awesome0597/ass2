//322094111

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 17/06/21
 * Class that creates a game.
 * <p>
 * Draws the Lose Screen
 **/

public class LoseScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * constructor.
     *
     * @param s type String
     */
    public LoseScreen(int s) {
        this.stop = false;
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.RED);
        d.fillRectangle(20, 20, 760, 560);
        d.setColor(Color.white);
        d.drawText(40, 100, "Game Over.", 32);
        d.drawText(40, 130, "Your score is " + score, 32);
        //body
        d.setColor(Color.BLACK);
        d.fillCircle(500 + 50, 375, 150);
        d.fillCircle(500 + 50, 230, 125);
        //eyes
        d.setColor(Color.white);
        d.fillCircle(450 + 50, 210, 50);
        d.fillCircle(540 + 50, 210, 50);
        //belly
        d.fillCircle(500 + 50, 375, 100);
        //pupils
        d.setColor(Color.BLACK);
        d.fillCircle(450 + 50, 210, 45);
        d.fillCircle(540 + 50, 210, 45);
        //beak
        d.setColor(Color.ORANGE);
        d.fillOval(475 + 50, 230, 40, 60);
        //feet
        d.fillOval(425 + 50, 495, 80, 40);
        d.fillOval(495 + 50, 495, 80, 40);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
