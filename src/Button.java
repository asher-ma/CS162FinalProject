import processing.core.PApplet;
import processing.core.PImage;

public class Button {
    private static final float DEFAULT_WIDTH = 60;
    private static final float DEFAULT_HEIGHT = 60;
    private static final float BUFFER = 10;

    private PApplet p;
    private PImage img;
    private float x, y;
    private float width, height;
    private int type;
    private String name;

    // Use the default width and height
    // Use the sketch dimensions and fish number to place the button
    public Button(PApplet p, int buttonNum, PImage img, int type, String name){
        this.p = p;
        this.img = img;
        this.type = type;
        this.name = name;
        y = p.height - DEFAULT_HEIGHT - BUFFER;
        x = p.width - (DEFAULT_WIDTH + BUFFER)*buttonNum;
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
    }

    // Constructor allowing custom width and height
    public Button(PApplet p, PImage img, float x, float y, float width, float height){
        this.p = p;
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Drawing button image with clear rectangle on top
    // Rectangle lines grow larger when mouse is over button
    public void draw(){
        if (isInButton()){
            p.strokeWeight(5);
        } else {
            p.strokeWeight(2);
        }

        p.stroke(0, 30, 150, 100);
        p.fill(0, (float)0.5);
        p.image(img, x, y, width, height);
        p.rect(x, y, width, height);
    }

    // Returns true if the mouse coordinates are inside the button boundaries
    public boolean isInButton(){
        return p.mouseX >= x && p.mouseX <= x + width && p.mouseY >= y && p.mouseY <= y + height;
    }

    // Getters to make new fish from button
    public PImage getImg(){
        return img;
    }

    public String getName(){
        return name;
    }

    public int type(){
        return type;
    }
}
