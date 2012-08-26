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
        super("starField", parent, textureLoader, soundManager, 0, 0, 512, 512, 2.0f);   
        
        this.x = (int)-50;
        this.y = (int)-50;
        
        this.setSpeed(0.001);
        
        this.ani.addFrame(new Sprite(textureLoader,name + ".png"),500);
        this.ani.addFrame(new Sprite(textureLoader,name + "1.png"),500); 
        
        this.ani.startAnimation();
        this.sign = 1;
    }
    
    public void updateWithDelta(long delta) {
        super.updateWithDelta(delta);
        
/*        if(this.x > -2 || this.y > -2)
            sign = -1;
        if(this.x < -50 || this.y < -50)
            sign = 1;
        
        this.x += (int)((double)sign * (speed * (double)delta));
        this.y += (int)((double)sign * (speed * (double)delta));
        System.out.println("Star field updating " + x + " " + y);*/
    }

    public void render() {      
        super.render();
    }
}
