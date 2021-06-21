//322094111

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * @author Adira Weiss.
 * @version 2.0.1
 * @since: 13/06/21
 * Class that creates a game.
 * <p>
 * Animation that creates a pause screen
 **/


public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
       //frame
        d.setColor(Color.MAGENTA);
        d.fillRectangle(0, 0, 800, 600);
        //background
        d.setColor(Color.PINK);
        d.fillRectangle(20, 20, 760, 560);
        d.setColor(Color.white);
        d.drawText(40, 100, "You Called The Pause Penguin", 32);
        d.drawText(40, 130, "Press Space to Continue", 32);
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
        d.setColor(Color.BLUE);
        d.fillRectangle(450 + 50, 300, 30, 150);
        d.fillRectangle(510 + 50, 300, 30, 150);
        //pupils
        d.setColor(Color.BLACK);
        d.fillCircle(450 + 50, 210, 20);
        d.fillCircle(540 + 50, 210, 20);
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
