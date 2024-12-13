import processing.core.PApplet;

public class ParticleSystem {
    private PApplet p;
    private int count;
    private DeathParticle[] particles;

    ParticleSystem(PApplet p, float left, float right, float top, float bottom){
        this.p = p;
        count = (int)((right - left)/5);
        particles = new DeathParticle[count];
    }
}
