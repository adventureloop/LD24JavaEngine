/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

import java.util.Random;

public class MeteorEntity extends Entity 
{
    double water;
    double nitrogen;
    double oxygen;
    
    double life;
    

    public MeteorEntity(String name, Screen parent, TextureLoader textureLoader,
            SoundManager soundManager) {
        super(name, parent, textureLoader, soundManager, 0, 0, 64, 64, 1.0f);

        Random r = new Random();
        
        water = r.nextDouble() % 100 + 20;
        nitrogen = r.nextDouble() % 200 + 10;
        oxygen = r.nextDouble() % 250 + 5;              
        
        life = r.nextDouble() % 50 + 5;     
        
        this.x = 50;//r.nextInt() % 700 + 20;
        this.y = 50;//r.nextInt() % 340 + 5;
        
        this.ani.addFrame(new Sprite(textureLoader,name + ".png"),0);
    }
    
    public double getWater()
    {
        return water;
    }
    
    public double getNitrogen()
    {
        return nitrogen;        
    }
    
    public double getOxygen() 
    {
        return oxygen;
    }
    
    public double getLife()
    {
        return life;
    }

    public void updateWithDelta(long delta) {
        //ani.updateWithDelta(delta);
    }

    public void render() {
        super.render();

        //this.ani.frameToRender().draw(this.x, this.y, this.scale);
    }
}
