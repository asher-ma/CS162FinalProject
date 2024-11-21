import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Tank {
    PApplet p;
    private int leftX, rightX, topY, botY;
    private PImage background;

    ArrayList<Fish> fish = new ArrayList<Fish>();

    // Contructor for when tank is entire sketch
    public Tank(PApplet p, String backgroundFile){
        this.p = p;
        background = p.loadImage(backgroundFile);
        leftX = 0;
        rightX = p.width;
        topY = 0;
        botY = p.height;
    }

    // Constructor for when tank doesnt take up entire sketch
    public Tank(PApplet p, String backgroundFile, int leftX, int rightX, int topY, int botY){
        this.p = p;
        background = p.loadImage(backgroundFile);
        this.leftX = leftX;
        this.rightX = rightX;
        this.topY = topY;
        this.botY = botY;
    }

    public void draw(){
        p.image(background, 0, 0, p.width, p.height);
        /*
        use to find tank edges when tank isnt fullscreen
        p.fill(0, 0);
        p.strokeWeight(5);
        p.rect(leftX, topY, rightX-leftX, botY-topY);
        */
        for (Fish fish: fish){
            testSides(fish);
            fish.move();
            fish.draw();
        }
    }

    public void addFish(String fishImgData, String type){
        fish.add(new Fish(p, fishImgData, type, leftX, rightX, topY, botY));
    }

    // Determines if fish is touching side of tank
    // Reverses direction fish is traveling
    private void testSides(Fish fish){
        if (fish.getX() <= leftX || fish.getX() + fish.getWidth() >= rightX) {
            fish.setXSpeed(fish.getXSpeed() * -1);
        }
        if (fish.getY() <= topY || fish.getY() + fish.getHeight() >= botY){
            fish.setYSpeed(fish.getYSpeed() * -1);
        }
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
