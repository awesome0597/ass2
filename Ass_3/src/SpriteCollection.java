import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite> listOfSprites;

    public SpriteCollection() {
      this.listOfSprites = new ArrayList<Sprite>();
    }
    public void addSprite(Sprite s) {
        this.listOfSprites.add(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        for (Sprite x: listOfSprites){
            x.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        for (Sprite x: listOfSprites){
            x.drawOn(d);
        }
    }
}
