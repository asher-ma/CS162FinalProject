import processing.core.PApplet;

public class FishSketch extends PApplet {
    Tank tank;

    public void settings(){
        size(570, 570);
    }
 
    public void setup(){
        tank = new Tank(this, "../data/tank.png", 0, 570, 75, 390);
        tank.addFish("../data/clownFish.png", "Clown Fish");
    }

    public void draw(){
        tank.draw();
    }
}
