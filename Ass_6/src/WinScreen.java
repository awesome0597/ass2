import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class WinScreen implements Animation {

    private boolean stop;
    private int score;

    public WinScreen(int s) {

        this.stop = false;
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
