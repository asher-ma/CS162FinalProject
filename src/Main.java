import processing.core.PApplet;

/**
 * This program draws fish swimming around a fish tank
 * 
 * 
 * @author Asher Mangel
 * @version 1.0
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        PApplet mySketch = new FishSketch();
	    String[] processingArgs = {"Asher's Sketch"};
	    PApplet.runSketch(processingArgs, mySketch);
    }
}