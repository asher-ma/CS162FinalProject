import processing.core.PApplet;
import processing.core.PImage;

public class Fish {
    private PApplet p;
    private final float MINSPEED = (float)0.2;
    private final float MAXSPEED = (float)0.6;

    private PImage fishImg;
    private String type;
    private float x, y;
    private float xSpeed, ySpeed;
    private float width, height;
    private int num;
    private Button button;

    public Fish(PApplet p, String fishImgData, int num, String type, int tankLeft, int tankRight, int tankTop, int tankBot){
        this.p = p;
        this.type = type;
        this.num = num;

        fishImg = p.loadImage(fishImgData);
        float aspectRatio = fishImg.width/fishImg.height;
        width = p.random(40, 70);
        height = width*aspectRatio;

        x = p.random(tankLeft + width, tankRight - width);
        y = p.random(tankTop + height, tankBot - height);
        xSpeed = randomNeg(p.random(MINSPEED, MAXSPEED))*(float)1.5;
        ySpeed = randomNeg(p.random(MINSPEED, MAXSPEED));

        button = new Button(p, fishImg, num);
    }

    public void draw(){
        if (xSpeed < 0) {
            p.pushMatrix();
            p.translate(x + width, y);
            p.scale(-1, 1);
            p.image(fishImg, 0, 0, width, height);
            p.popMatrix();
        } else {
            p.image(fishImg, x, y, width, height);
        }
        button.draw();
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

    // Getters
    public String getType() {
        return type;
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
        return type;
    }
}
