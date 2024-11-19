import processing.core.PApplet;
import java.util.ArrayList;

public class Tank {
    PApplet p;
    int leftX, rightX, topY, botY;

    ArrayList<Fish> fish = new ArrayList<Fish>();

    public Tank(PApplet p, int leftX, int rightX, int topY, int botY){
        this.p = p;
        this.leftX = leftX;
        this.rightX = rightX;
        this.topY = topY;
        this.botY = botY;
    }


    // Tank side getters
    public int getLeftX(){
        return leftX;
    }
    
    public int getRightX(){
        return rightX;
    }

    public int getTopY(){
        return leftX;
    }

    public int getBotY(){
        return leftX;
    }

    // Tank toString prints all fish in tank
    public String toString(){
        String fishString = String.join(", ", fish.toString());
        return fishString;
    }
}
