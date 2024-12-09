import processing.core.PApplet;

public class FishSketch extends PApplet {
    Tank tank;

    public void settings(){
        size(570, 570);
    }
 
    public void setup(){
        tank = new Tank(this, this.loadImage("../data/tank.png"), 0, 570, 75, 390);
        tank.newFish(this.loadImage("../data/orangeFish.png"), "Orange Fish", false);
        tank.newFish(this.loadImage("../data/blueFish.png"), "Blue Fish", false);
        tank.newFish(this.loadImage("../data/greenFish.png"), "Green Fish", false);
        tank.newFish(this.loadImage("../data/meanFish.png"), "Green Fish", true);
    }

    public void draw(){
        tank.draw();
    }

    public void mouseClicked(){
        tank.mouseClicked();
    }
}
