import processing.core.PApplet;
import processing.core.PImage;

public class Predator extends Fish {
    private final float HUNGER_TIME = 15;

    private float maxHunger;
    private float hunger;
    private float hungerTic;

    public Predator(PApplet p, PImage img, String species){
        super(p, img, species);
        width = p.random(100, 150);
        height = width/this.getAspect();

        x = p.random(p.width - width);
        y = p.random(p.height - height);

        maxHunger = width;
        hunger = maxHunger;
        hungerTic = maxHunger/(HUNGER_TIME*p.frameRate);
    }

    public Predator(PApplet p, PImage img, String species, int tankLeft, int tankRight, int tankTop, int tankBot){
        super(p, img, species, tankLeft, tankRight, tankTop, tankBot);
        width = p.random(100, 150);
        height = width/this.getAspect();

        maxHunger = width;
        hunger = maxHunger;
        hungerTic = maxHunger/(HUNGER_TIME*p.frameRate);
    }

    public void draw(){
        super.draw();
        drawHunger();
        updateHunger();
    }

    private void drawHunger(){
        p.noStroke();
        p.fill(255, 0, 0, 100);
        p.rect(x, y + height, hunger, width/15);
        p.noFill();
        p.strokeWeight((float)0.5);
        p.stroke(0, 0, 0);
        p.rect(x, y + height, width, width/15);
    }

    private void updateHunger(){
        if (hunger > 0) {
            hunger -= hungerTic;
        } else {
            System.out.println(species + " died from hunger");
        }
    }

    public void resetHunger(){
        hunger = maxHunger;
    }

    public float getHunger(){
        return hunger;
    }

    public String getType(){
        return "predator";
    }
}
