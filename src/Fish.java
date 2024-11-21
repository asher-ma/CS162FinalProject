import processing.core.PApplet;
import processing.core.PImage;

public class Fish {
    PApplet p;
    private final float MAXSPEED = 1;

    PImage fishImg;
    String type;
    float x, y;
    float xSpeed, ySpeed;
    float width, height;

    public Fish(PApplet p, String fishImgData, String type, int tankLeft, int tankRight, int tankTop, int tankBot){
        this.p = p;
        this.type = type;

        fishImg = p.loadImage(fishImgData);
        float aspectRatio = fishImg.width/fishImg.height;
        width = p.random(40, 70);
        height = width*aspectRatio;

        x = p.random(tankLeft + width, tankRight - width);
        y = p.random(tankTop + height, tankBot - height);
        xSpeed = p.random(-MAXSPEED, MAXSPEED);
        ySpeed = p.random(-MAXSPEED, MAXSPEED);
    }

    public void draw(){
        p.image(fishImg, x, y, width, height);
    }

    public void move(){
        x += xSpeed;
        y += ySpeed;
    }

    public void flipFish(){
        
    }

    public void turnX(){

    }

    public void turnY(){

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
