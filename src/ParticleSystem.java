import processing.core.PApplet;

public class ParticleSystem {
    private PApplet p;
    private int count;
    private DeathParticle[] particles;

    ParticleSystem(PApplet p, float left, float right, float top, float bottom){
        this.p = p;
        count = (int)((right - left)/5);
        particles = new DeathParticle[count];

        for (int i = 0; i < count; i++){
            particles[i] = new DeathParticle(p, p.random(left, right), p.random(top, bottom));
        }
    }

    public void draw(){
        for (int i = 0; i < count; i++){
            if (particles[i].getTime() < particles[i].getLife()){
                particles[i].draw();
                particles[i].move();
            }
        }
    }
    
    public boolean exists(){
        for (int i = 0; i < count; i++){
            if (particles[i].getTime() < particles[i].getLife()){
                return true;
            }
        }
        return false;
    }
}
