/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class StarFieldEntity extends Entity 
{
    int sign;
    
    public StarFieldEntity(Screen parent, TextureLoader textureLoader,
            SoundManager soundManager) {
        super("StarField", parent, textureLoader, soundManager, 0, 0, 512, 512, 2.0f);   
        
        this.x = (int)-50;
        this.y = (int)-50;
        
        this.setSpeed(0.001);
        
        this.ani.addFrame(new Sprite(textureLoader,name + ".png"),500);
        this.ani.addFrame(new Sprite(textureLoader,name + "1.png"),500); 
        
        this.ani.startAnimation();
        //this.startMoving();
    }
    
    public void updateWithDelta(long delta) {
        super.updateWithDelta(delta);
    }

    public void render() {      
        super.render();
    }
}
