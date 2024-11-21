import processing.core.PApplet;
import processing.core.PImage;

public class FishSketch extends PApplet {
    Tank tank;

    public void settings(){
        size(570, 570);
    }
 
    public void setup(){
        tank = new Tank(this, "../data/tank.png", 0, 570, 30, 400);
    }

    public void draw(){
        tank.draw();
    }
}
