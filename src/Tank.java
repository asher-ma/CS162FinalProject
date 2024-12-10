import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Tank {
    PApplet p;
    private int leftX, rightX, topY, botY;
    private PImage background;

    ArrayList<Fish> fish = new ArrayList<Fish>();
    ArrayList<Button> buttons = new ArrayList<Button>();

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

        for (Button button: buttons){
            button.draw(); 
        }
    }

    public void mouseClicked(){
        for (Button button: buttons){
            if (button.isInButton()){
                fish.add(new Fish(p, button.getImg(), button.getName(), button.type(), leftX, rightX, topY, botY));
                return;
            }
        }
    }

    public void newFish(PImage fishImg, String name, int type){
        fish.add(new Fish(p, fishImg, name, type, leftX, rightX, topY, botY));
        buttons.add(new Button(p, buttons.size()+1, fishImg, type, name));
    }

    // Function to bounce fish off of eachother
    // Determine if and which fish are colliding
    // determine which side of each fish is overlapping
    // then reverse speed based on which sides are colliding

    private void testAllCollisions(){
        for (int i = fish.size()-1; i >= 0; i--) {
            for (int j = i-1; j >= 0; j--) {
                Fish f1 = fish.get(i);
                Fish f2 = fish.get(j);
                if (isColliding(f1, f2)) {
                    if (f1.getType() == 1 && f2.getType() != 1){
                        System.out.println("Pred x prey collision");
                        if(isFacingCollision(f1, f2)){
                            System.out.println(f1.getName() + " ate " + f2.getName());
                            fish.remove(j);
                        }
                    } else if (f1.getType() != 1 && f2.getType() == 1){
                        System.out.println("Pred x prey collision");
                        if(isFacingCollision(f2, f1)){
                            System.out.println(f2.getName() + " ate " + f1.getName());
                            fish.remove(i);
                        }
                    }
                    f1.swimAway(collisionSide(f1, f2));
                    f2.swimAway(collisionSide(f2, f1));
                }
            }
        }
    }

/*
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
*/

    private boolean isColliding(Fish f1, Fish f2){
        if (f1.getX() + f1.getWidth() >= f2.getX() && f1.getX() <= f2.getX() + f2.getWidth()
        && f1.getY() + f1.getHeight() >= f2.getY() && f1.getY() <= f2.getY() + f2.getHeight()) {
            return true;
        } else return false;
    }
    
    private boolean isFacingCollision(Fish f1, Fish f2) {
        if (Math.abs(Math.abs(f1.getMidX()) - Math.abs(f2.getMidX())) > Math.abs(Math.abs(f1.getMidY()) - Math.abs(f2.getMidY()))) {
            if (f1.getMidX() > f2.getMidX()) {
                System.out.println("left");
                System.out.println(f1.getXSpeed());
                if (f1.getXSpeed() < 0){
                    return true;
                }
            } else {
                System.out.println("right");
                System.out.println(f1.getXSpeed());
                if (f1.getXSpeed() > 0){
                    return true;
                }
            }
        } else {
            if (f1.getMidY() > f2.getMidY()) {
                System.out.println("top");
                if (f1.getYSpeed() < 0){
                    return true;
                }
            } else {
                System.out.println("bottom");
                if (f1.getYSpeed() > 0){
                    return true;
                }
            }
        }
        return false;
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
