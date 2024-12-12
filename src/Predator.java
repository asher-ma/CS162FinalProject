import processing.core.PApplet;
import processing.core.PImage;

public class Predator extends Fish {
    private final float MAX_HUNGER = (float)1;
    private final float DEFAULT_HUNGER_TIME = 10; // Time fish will take to die if it doesn't eat

    private float baseXSpeed;
    private float hunger;
    private float hungerTic;

    public Predator(PApplet p, PImage img, String name){
        super(p, img, name);
        baseXSpeed = xSpeed;
        hunger = 0;
        width = p.random(100, 150);
        height = width/this.getAspect();

        hungerTic = MAX_HUNGER/(DEFAULT_HUNGER_TIME*p.frameRate);
    }

    public Predator(PApplet p, PImage img, String name, int type, int tankLeft, int tankRight, int tankTop, int tankBot){
        super(p, img, name, type, tankLeft, tankRight, tankTop, tankBot);
        baseXSpeed = xSpeed;
        hunger = 0;
        width = p.random(100, 150);
        height = width/this.getAspect();

        hungerTic = MAX_HUNGER/(DEFAULT_HUNGER_TIME*p.frameRate);
    }

    public void draw(){
        super.draw();
        updateHunger();
        xSpeed = baseXSpeed*2-hunger;
    }

    private void updateHunger(){
        hunger += 0.001;
    }

    public float getHunger(){
        return hunger;
    }

    public String getType(){
        return "Predator";
    }
}
