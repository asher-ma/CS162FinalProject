import processing.core.PApplet;

public class FishSketch extends PApplet {
    Tank tank;

    public void settings(){
        size(1280, 720);
    }
 
    public void setup(){
        tank = new Tank(this, this.loadImage("../data/sotBg.png"));
        tank.newFish(this.loadImage("../data/rubySplashtail.png"), "Ruby Splashtail", 0);
        tank.newFish(this.loadImage("../data/almondAncientScale.png"), "Almond AncientScale", 0);
        tank.newFish(this.loadImage("../data/russetWildsplash.png"), "Russet Wildsplash", 0);
        tank.newFish(this.loadImage("../data/stoneIslehopper.png"), "Stone Islehopper", 2);
        tank.newFish(this.loadImage("../data/ashenDevilFish.png"), "Ashen Devilfish", 1);
        tank.newFish(this.loadImage("../data/ancientStormfish.png"), "Ancient Stormfish", 1);
    }

    public void draw(){
        tank.draw();
    }

    public void mouseClicked(){
        tank.mouseClicked();
    }
}
