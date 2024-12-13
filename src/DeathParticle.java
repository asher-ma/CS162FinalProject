import processing.core.PApplet;

public class DeathParticle {
    private final float GRAVITY = (float)0.01;
    private final int G = 0;
    private final int B = 0;
    private final int A = 150;
    
    private PApplet p;
    private float r;
    private float time, life;
    private float x, y;
    private float ySpeed, xSpeed;
    private float size;
    
    public DeathParticle(PApplet p, float x, float y){
        this.p = p;
        this.x = x;
        this.y = y;

        ySpeed = p.random(-1, 1);
        ySpeed = p.random(-1, 1);

        time = 0;
        life = p.random(300);

        r = p.random(70, 170);

        size = p.random(3, 6);
    }

    public void draw(){
        p.stroke(0, 0, 0, 20);
        //p.strokeWeight(1);
        p.fill(r, G, B, A);
        p.circle(x, y, size);
    }

    public void move(){
        ySpeed += GRAVITY;
        x += xSpeed;
        y += ySpeed;
        time++;
    }

    public float getTime(){
        return time;
    }

    public float getLife(){
        return life;
    }
}
