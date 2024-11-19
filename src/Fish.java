import processing.core.PApplet;

public class Fish {
    PApplet p;
    private final int MAXSPEED = 10;

    String type;
    float x, y;
    float xSpeed, ySpeed;
    float width, height;

    public Fish(PApplet p, String type, int minX, int maxX, int minY, int maxY){
        this.p = p;
        x = p.random(minX + width, maxX - width);
        y = p.random(minY + height, maxY - height);
        xSpeed = p.random(-MAXSPEED, MAXSPEED);
        ySpeed = p.random(-MAXSPEED, MAXSPEED);
    }

    public void draw(){

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
