import processing.core.PApplet;

public class FishSketch extends PApplet {
    Tank tank;

    public void settings(){
        size(570, 570);
    }
 
    public void setup(){
        tank = new Tank(this, "../data/tank.png", 0, 570, 75, 390);
        tank.addFish("../data/orangeFish.png", "Orange Fish");
        tank.addFish("../data/blueFish.png", "Blue Fish");
        tank.addFish("../data/greenFish.png", "Green Fish");
    }

    public void draw(){
        tank.draw();
    }
}
