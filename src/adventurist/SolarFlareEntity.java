/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class SolarFlareEntity extends Entity
{
    public SolarFlareEntity(Screen parent, TextureLoader textureLoader,
            SoundManager soundManager) {
        super("solarflare", parent, textureLoader, soundManager, 0, 0, 64, 64, 1.0f);
        
        
        this.x = 850;
        this.y = 20;
        this.rotation = 180;
        
        this.ani.addFrame(new Sprite(textureLoader,"solarflare.png"),125);
        this.ani.addFrame(new Sprite(textureLoader,"solarflare1.png"),125);
        this.ani.addFrame(new Sprite(textureLoader,"solarflare2.png"),125);
        this.ani.addFrame(new Sprite(textureLoader,"solarflare3.png"),125);
        this.ani.addFrame(new Sprite(textureLoader,"solarflare4.png"),125);
        this.ani.addFrame(new Sprite(textureLoader,"solarflare5.png"),125);
        this.ani.addFrame(new Sprite(textureLoader,"solarflare6.png"),125);
        
        this.ani.startAnimation();
        
        this.setSpeed(0.3);
        this.startMoving();
        
        
    }
    
    public void updateWithDelta(long delta) {
        super.updateWithDelta(delta);
    }
    
    public void render() {
        super.render();
    }
}
