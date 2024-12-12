import processing.core.PApplet;
import processing.core.PImage;

public class Predator extends Fish {
    private float baseXSpeed;
    private float hunger;

    public Predator(PApplet p, PImage img, String name){
        super(p, img, name);
        baseXSpeed = xSpeed;
        hunger = 0;
        width = p.random(100, 150);
        height = width/this.getAspect();
    }

    public Predator(PApplet p, PImage img, String name, int type, int tankLeft, int tankRight, int tankTop, int tankBot){
        super(p, img, name, type, tankLeft, tankRight, tankTop, tankBot);
        baseXSpeed = xSpeed;
        hunger = 0;
        width = p.random(100, 150);
        height = width/this.getAspect();
    }

}
