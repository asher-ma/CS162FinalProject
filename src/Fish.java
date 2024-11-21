import processing.core.PApplet;
import processing.core.PImage;

public class Fish {
    PApplet p;
    private final int MAXSPEED = 10;

    PImage fishImg;
    String type;
    float x, y;
    float xSpeed, ySpeed;
    float width, height;

    public Fish(PApplet p, String fishImgData, String type, int minX, int maxX, int minY, int maxY){
        this.p = p;
        fishImg = p.loadImage(fishImgData);
        x = p.random(minX + width, maxX - width);
        y = p.random(minY + height, maxY - height);
        xSpeed = p.random(-MAXSPEED, MAXSPEED);
        ySpeed = p.random(-MAXSPEED, MAXSPEED);
    }

    public void draw(){
        p.image(fishImg, x, y, width, height);
    }

    private boolean testWalls(){
        return false;
    }

    private void changeDirection(){

    }

    private void faceForward(){

    }

    public String getType() {
        return type;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String toString(){
        return type;
    }
}
