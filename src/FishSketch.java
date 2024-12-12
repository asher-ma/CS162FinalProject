import processing.core.PApplet;

public class FishSketch extends PApplet {
    Tank tank;

    public void settings(){
        size(1280, 720);
    }
 
    public void setup(){
        tank = new Tank(this, this.loadImage("../data/sotBg.png"));
        tank.newFishType(this.loadImage("../data/rubySplashtail.png"), "Ruby Splashtail", "prey");
        tank.newFishType(this.loadImage("../data/almondAncientScale.png"), "Almond AncientScale", "prey");
        tank.newFishType(this.loadImage("../data/russetWildsplash.png"), "Russet Wildsplash", "prey");
        tank.newFishType(this.loadImage("../data/stoneIslehopper.png"), "Stone Islehopper", "fish");
        tank.newFishType(this.loadImage("../data/ashenDevilFish.png"), "Ashen Devilfish", "predator");
        tank.newFishType(this.loadImage("../data/ancientStormfish.png"), "Ancient Stormfish", "predator");
    }

    public void draw(){
        tank.draw();
    }

    public void mouseClicked(){
        tank.mouseClicked();
    }
}
