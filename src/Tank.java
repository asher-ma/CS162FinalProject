import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Tank {
    PApplet p;
    private int leftX, rightX, topY, botY;
    private PImage background;

    ArrayList<Fish> fish = new ArrayList<Fish>();

    // Contructor for when tank is entire sketch
    public Tank(PApplet p, PImage background){
        this.p = p;
        this.background = background;
        leftX = 0;
        rightX = p.width;
        topY = 0;
        botY = p.height;
    }

    // Constructor for when tank doesnt take up entire sketch
    public Tank(PApplet p, PImage background, int leftX, int rightX, int topY, int botY){
        this.p = p;
        this.background = background;
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
        testAllCollisions();
        for (Fish fish: fish){
            testSides(fish);
            fish.move();
            fish.draw();
        }
    }

    public void mouseClicked(){
        for (Fish fish: fish){
            Button button = fish.getButton();
            if (button.isInButton()){
                this.fish.add(new Fish(p, fish.getImg(), 0, fish.getName(), fish.isPredator(), leftX, rightX, topY, botY));
                return;
            }
        }
    }

    public void newFish(PImage fishImg, String name, boolean predator){
        fish.add(new Fish(p, fishImg, fish.size()+1, name, predator, leftX, rightX, topY, botY));
    }

    // Function to bounce fish off of eachother
    // Determine if and which fish are colliding
    // determine which side of each fish is overlapping
    // then reverse speed based on which sides are colliding

    private void testAllCollisions(){
        for (int i = 0; i < fish.size(); i++) {
            for (int j = i+1; j < fish.size(); j++) {
                if (isColliding(fish.get(i), fish.get(j))) {
                    fish.get(i).swimAway(collisionSide(fish.get(i), fish.get(j)));
                    fish.get(j).swimAway(collisionSide(fish.get(j), fish.get(i)));
                }
            }
        }
    }

    private boolean isColliding(Fish f1, Fish f2){
        if (f1.getX() + f1.getWidth() >= f2.getX() && f1.getX() <= f2.getX() + f2.getWidth()
        && f1.getY() + f1.getHeight() >= f2.getY() && f1.getY() <= f2.getY() + f2.getHeight()) {
            return true;
        } else return false;
    }
    
    private String collisionSide(Fish f1, Fish f2) {
        if (Math.abs(Math.abs(f1.getMidX()) - Math.abs(f2.getMidX())) > Math.abs(Math.abs(f1.getMidY()) - Math.abs(f2.getMidY()))) {
            if (f1.getMidX() > f2.getMidX()) {
                return "left";
            } else {
                return "right";
            }
        } else {
            if (f1.getMidY() > f2.getMidY()) {
                return "top";
            } else {
                return "bottom";
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
