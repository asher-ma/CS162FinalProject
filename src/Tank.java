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
        testCollisions();
        for (Fish fish: fish){
            testSides(fish);
            fish.move();
            fish.draw();
        }
    }

    public void addFish(String fishImgData, String type){
        fish.add(new Fish(p, fishImgData, type, leftX, rightX, topY, botY));
    }

    // Function to bounce fish off of eachother
    // TODO First determine which side of fish is overlapping with any other fish
    // TODO then reverse speed

    private void testCollisions() {
        for (int i = 0; i < fish.size(); i++) {
            Fish f1 = fish.get(i);
            float f1L = f1.getX();
            float f1R = f1.getX() + f1.getWidth();
            float f1T = f1.getY();
            float f1B = f1.getY() + f1.getHeight();

            for (int j = i+1; j < fish.size(); j++) {
                Fish f2 = fish.get(j);
                float f2L = f2.getX();
                float f2R = f2.getX() + f2.getWidth();
                float f2T = f2.getY();
                float f2B = f2.getY() + f2.getHeight();

                if (f1R >= f2L && f1L <= f2L && f1T < f2B && f1B > f2T) {
                    f1.setXSpeed(-Math.abs(f1.getXSpeed()));
                    f2.setXSpeed(Math.abs(f2.getXSpeed()));
                } else if (f2R >= f1L && f2L <= f1L && f1T < f2B && f1B > f2T) {
                    f1.setXSpeed(Math.abs(f1.getXSpeed()));
                    f2.setXSpeed(-Math.abs(f2.getXSpeed()));
                }
            }
        }
    }

    // Determines if fish is touching side of tank
    // Reverses direction fish is traveling
    private void testSides(Fish fish){
        if (fish.getX() <= leftX || fish.getX() + fish.getWidth() >= rightX) {
            fish.reverseX();
        }
        if (fish.getY() <= topY || fish.getY() + fish.getHeight() >= botY){
            fish.reverseY();
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
