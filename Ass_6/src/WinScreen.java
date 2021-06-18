//322094111

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 17/06/21
 * Class that creates a game.
 * <p>
 * animation for the win screen
 **/

public class WinScreen implements Animation {

    private boolean stop;
    private int score;

    /**
     * constructor.
     *
     * @param s type int
     */
    public WinScreen(int s) {

        this.stop = false;
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.CYAN);
        d.fillRectangle(20, 20, 760, 560);
        d.setColor(Color.white);
        d.drawText(40, 100, "You Win!", 32);
        d.drawText(40, 130, "Your score is " + score, 32);
        //body
        d.setColor(Color.BLACK);
        d.fillCircle(500 + 50, 375, 150);
        d.fillCircle(500 + 50, 230, 125);
        //belly
        d.setColor(Color.white);
        d.fillCircle(500 + 50, 375, 100);
        d.fillCircle(500 + 50, 230, 85);
        //pupils
        d.setColor(Color.BLACK);
        d.fillOval(425 + 50, 190, 70, 50);
        d.fillOval(500 + 50, 190, 70, 50);
        d.setColor(Color.WHITE);
        d.fillOval(425 + 50, 200, 70, 50);
        d.fillOval(500 + 50, 200, 70, 50);
        //cheeks
        d.setColor(Color.PINK);
        d.fillCircle(450 + 50, 250, 10);
        d.fillCircle(545 + 50, 250, 10);
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
