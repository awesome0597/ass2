import biuoop.DrawSurface;


public class LoseScreen implements Animation {
    private boolean stop;
    private int score;

    public LoseScreen(int s) {
        this.stop = false;
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
