import processing.core.PApplet;
import processing.core.PImage;

public class Fish {
    private PApplet p;
    private final float MINSPEED = (float)0.6;
    private final float MAXSPEED = (float)2;

    private PImage img;
    private String species;

    private float x, y;
    protected float xSpeed;
    private float ySpeed;
    protected float width, height;

    public Fish(){
    }

    public Fish(PApplet p, PImage img, String species){
        this.p = p;
        this.species = species;

        this.img = img;
        width = p.random(40, 70);
        height = width/this.getAspect();

        x = p.random(width);
        y = p.random(height);
        xSpeed = randomNeg(p.random(MINSPEED, MAXSPEED))*2;
        ySpeed = randomNeg(p.random(MINSPEED, MAXSPEED));
    }

    public Fish(PApplet p, PImage img, String species, int tankLeft, int tankRight, int tankTop, int tankBot){
        this.p = p;
        this.species = species;

        this.img = img;
        float aspectRatio = img.width/img.height;
        width = p.random(40, 70);
        height = width/aspectRatio;

        x = p.random(tankLeft + width, tankRight - width);
        y = p.random(tankTop + height, tankBot - height);
        xSpeed = randomNeg(p.random(MINSPEED, MAXSPEED))*2;
        ySpeed = randomNeg(p.random(MINSPEED, MAXSPEED));
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
    
    public float getMidX(){
        return x + width/2;
    }

    public float getMidY(){
        return y + height/2;
    }

    protected float getAspect(){
        return img.width/img.height;
    }

    public String getType(){
        return "Fish";
    }

    // Getters

    public PImage getImg() {
        return img;
    }
    
    public String getSpecies() {
        return species;
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
        return species;
    }
}
