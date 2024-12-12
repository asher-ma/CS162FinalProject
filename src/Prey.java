import processing.core.PApplet;
import processing.core.PImage;

public class Prey extends Fish {
    
    public Prey(PApplet p, PImage img, String species){
        super(p, img, species);
    }

    public Prey(PApplet p, PImage img, String species, int tankLeft, int tankRight, int tankTop, int tankBot){
        super(p, img, species, tankLeft, tankRight, tankTop, tankBot);
    }

    public String getType(){
        return "prey";
    }
}
