import processing.core.PApplet;
import processing.core.PImage;

public class Button {
    private static final float DEFAULT_BUTTON_WIDTH = 40;
    private static final float DEFAULT_BUTTON_HEIGHT = 40;

    private PApplet p;
    private PImage image;
    private float x, y;
    private float width;
    private float height;

    // Use the default width and height
    public Button(PApplet p, PImage image, float x, float y){
        this.p = p;
        this.image = image;
        this.x = x;
        this.y = y;
        width = DEFAULT_BUTTON_WIDTH;
        height = DEFAULT_BUTTON_HEIGHT;
    }

    // Constructor allowing custom width and height
    public Button(PApplet p, PImage image, float x, float y, float width, float height){
        this.p = p;
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Drawing button image with clear rectangle on top
    // Rectangle lines grow larger when mouse is over button
    public void draw(){
        if (isInButton(x, y)){
            p.strokeWeight(10);
        } else {
            p.strokeWeight(2);
        }

        p.fill(0, (float)0.5);
        p.image(image, x, y, width, height);
        p.rect(x, y, width, height);
    }

    // Returns true if the mouse coordinates are inside the button boundaries
    public boolean isInButton(float x, float y){
        return p.mouseX >= x && p.mouseX <= x + width && p.mouseY <= y + height;
    }
}
