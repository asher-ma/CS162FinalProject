import processing.core.PApplet;
import processing.core.PImage;

public class Fish {
    private PApplet p;
    private final float MINSPEED = (float)0.2;
    private final float MAXSPEED = (float)0.6;

    private PImage img;
    private String name;
    private boolean predator;
    private float x, y;
    private float xSpeed, ySpeed;
    private float width, height;
    private Button button;
    private int num;

    public Fish(PApplet p, PImage img, int num, String name, boolean predator, int tankLeft, int tankRight, int tankTop, int tankBot){
        this.p = p;
        this.name = name;
        this.num = num;
        this.predator = predator;

        this.img = img;
        float aspectRatio = img.width/img.height;
        width = p.random(40, 70);
        height = width*aspectRatio;

        x = p.random(tankLeft + width, tankRight - width);
        y = p.random(tankTop + height, tankBot - height);
        xSpeed = randomNeg(p.random(MINSPEED, MAXSPEED))*(float)1.5;
        ySpeed = randomNeg(p.random(MINSPEED, MAXSPEED));

        if (num > 0) button = new Button(p, img, num);
    }

    public void draw(){
        if (xSpeed < 0) {
            p.pushMatrix();
            p.translate(x + width, y);
            p.scale(-1, 1);
            p.image(img, 0, 0, width, height);
            p.popMatrix();
        } else {
            p.image(img, x, y, width, height);
        }
        
        if (num > 0) button.draw();
    }

    public void move(){
        x += xSpeed;
        y += ySpeed;
    }

    public void reverseX(){
        xSpeed = xSpeed*-1;
    }

    public void reverseY(){
        ySpeed = ySpeed*-1;
    }

    private float randomNeg(float num){
        if(p.random(1) > 0.5){
            return -num;
        } else return num;
    }

    public void swimAway(String side) {
        if (side.equals("left") && xSpeed < 0 || side.equals("right") && xSpeed > 0) {
            reverseX();
        } else if (side.equals("top") && ySpeed < 0 || side.equals("bottom") && ySpeed > 0) {
            reverseY();
        }
    }


    public void stop(){
        xSpeed = 0;
        ySpeed = 0;
    }

    // Special getters

    public boolean isPredator(){
        return predator;
    }
    
    public float getMidX(){
        return x + width/2;
    }

    public float getMidY(){
        return y + height/2;
    }

    // Getters

    public PImage getImg() {
        return img;
    }

    public Button getButton() {
        return button;
    }

    public String getName() {
        return name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public float getYSpeed() {
        return ySpeed;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setXSpeed(float newXspeed) {
        xSpeed = newXspeed;
    }

    public void setYSpeed(float newYspeed) {
        ySpeed = newYspeed;
    }

    public String toString(){
        return name;
    }
}
