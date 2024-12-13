import processing.core.PApplet;

public class DeathParticle {
    private final float GRAVITY = (float)0.01;
    private final int G = 0;
    private final int B = 0;
    private final int A = 170;
    
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

        xSpeed = p.random((float)-0.5, (float)0.5);
        ySpeed = p.random(-1, 1);

        time = 0;
        life = p.random(50, 300);

        r = p.random(150, 250);

        size = p.random(3, 8);
    }

    public void draw(){
        p.noStroke();
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
