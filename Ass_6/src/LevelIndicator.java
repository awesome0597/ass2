//322094111

import biuoop.DrawSurface;

import java.awt.Color;

public class LevelIndicator implements Sprite{
    private String levelName;
    public LevelIndicator (String s){
        this.levelName = s;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(600, 15,
                "Level Name: " + levelName, 15);
    }

    @Override
    public void timePassed() {

    }
}
