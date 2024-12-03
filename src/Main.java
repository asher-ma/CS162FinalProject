import processing.core.PApplet;

/**
 * This program draws fish swimming around a fish tank
 * 
 * 
 * @author Asher Mangel
 * @version 2.0
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        PApplet mySketch = new FishSketch();
	    String[] processingArgs = {"Asher's Sketch"};
	    PApplet.runSketch(processingArgs, mySketch);
    }
}