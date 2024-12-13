import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Tank {
    PApplet p;
    private int leftX, rightX, topY, botY;
    private PImage background;

    ArrayList<Fish> fish = new ArrayList<Fish>();
    ArrayList<Button> buttons = new ArrayList<Button>();
    ArrayList<ParticleSystem> particleSystems = new ArrayList<ParticleSystem>();

    // Contructor for when tank is entire sketch
    public Tank(PApplet p, PImage background){
        this.p = p;
        this.background = background;
        leftX = 0;
        rightX = p.width;
        topY = 0;
        botY = p.height;
        buttons.add(new Button(p, buttons.size()+1, p.loadImage("../data/fish.png"), "fish", "All"));
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

        if (particleSystems.size() > 0){
            for (int i = particleSystems.size()-1; i >= 0; i--){
                if (particleSystems.get(i).exists()){
                    particleSystems.get(i).draw();
                } else particleSystems.remove(i);
            }
        }

        for (Button button: buttons){
            button.draw(); 
        }

        killHungry();
    }

    public void mouseClicked(){
        for (Button button: buttons){
            if (button.isInButton()){
                if (button.getSpecies().equals("All")){
                    for (int i = 1; i < buttons.size(); i++){
                        newFish(buttons.get(i).getImg(), buttons.get(i).getSpecies(), buttons.get(i).getType());
                    }
                    return;
                } else {
                newFish(button.getImg(), button.getSpecies(), button.getType());
                return;
                }
            }
        }
    }

    public void newFishType(PImage fishImg, String species, String type){
        buttons.add(new Button(p, buttons.size()+1, fishImg, type, species));
    }

    private void newFish(PImage fishImg, String species, String type){
        if (leftX + rightX + topY + botY == p.width + p.height) {
            if (type.equals("predator")){
                fish.add(new Predator(p, fishImg, species));
            } else if (type.equals("prey")){
                fish.add(new Prey(p, fishImg, species));
            } else fish.add(new Fish(p, fishImg, species));
        } else {
            if (type.equals("predator")){
                fish.add(new Predator(p, fishImg, species, leftX, rightX, topY, botY ));
            } else if (type.equals("prey")){
                fish.add(new Prey(p, fishImg, species, leftX, rightX, topY, botY));
            } else fish.add(new Fish(p, fishImg, species, leftX, rightX, topY, botY));
        }
    }

    private void killHungry(){
        for (int i = fish.size()-1; i >= 0; i--) {
            if (fish.get(i).getType().equals("predator")) {
                if (fish.get(i).getHunger() < 0){
                    fish.remove(i);
                }
            }
        }
    }

    // Function to bounce fish off of eachother
    // Determine if and which fish are colliding
    // determine which side of each fish is overlapping
    // then reverse speed based on which sides are colliding

    private void testAllCollisions(){
        ArrayList<Fish> deadFish = new ArrayList<Fish>();
        for (int i = 0; i < fish.size(); i++) {
            for (int j = i+1; j < fish.size(); j++) {
                Fish f1 = fish.get(i);
                Fish f2 = fish.get(j);
                if (isColliding(f1, f2)) {
                    if (f1.getType().equals("predator") && f2.getType().equals("prey")){
                        if(isFacingCollision(f1, f2)){
                            f1.resetHunger();
                            deadFish.add(f2);
                        } else {
                            f1.swimAway(collisionSide(f1, f2));
                            f2.swimAway(collisionSide(f2, f1));
                        }
                    } else if (f1.getType().equals("prey") && f2.getType().equals("predator")){
                        if(isFacingCollision(f2, f1)){
                            f2.resetHunger();
                            deadFish.add(f1);
                        } else {
                            f1.swimAway(collisionSide(f1, f2));
                            f2.swimAway(collisionSide(f2, f1));
                        }
                    } else {
                        f1.swimAway(collisionSide(f1, f2));
                        f2.swimAway(collisionSide(f2, f1));
                    }
                    
                }
            }
        }
        if (deadFish.size() > 0){
            for (Fish f: deadFish){
                particleSystems.add(new ParticleSystem(p, f.x, f.x + f.width, f.y, f.y + f.height));
                fish.remove(f);
            }
        }
    }

    private boolean isColliding(Fish f1, Fish f2){
        if (f1.getX() + f1.getWidth() >= f2.getX() && f1.getX() <= f2.getX() + f2.getWidth()
        && f1.getY() + f1.getHeight() >= f2.getY() && f1.getY() <= f2.getY() + f2.getHeight()) {
            return true;
        } else return false;
    }
    
    private boolean isFacingCollision(Fish f1, Fish f2) {
        if (Math.abs(Math.abs(f1.getMidX()) - Math.abs(f2.getMidX())) > Math.abs(Math.abs(f1.getMidY()) - Math.abs(f2.getMidY()))) {
            if (f1.getMidX() > f2.getMidX()) {
                if (f1.getXSpeed() < 0){
                    return true;
                }
            } else {
                if (f1.getXSpeed() > 0){
                    return true;
                }
            }
        } else {
            if (f1.getMidY() > f2.getMidY()) {
                if (f1.getYSpeed() < 0){
                    return true;
                }
            } else {
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
