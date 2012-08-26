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
    
    Random r = new Random();

    public MeteorEntity(String name, Screen parent, TextureLoader textureLoader,
            SoundManager soundManager) {
        super(name, parent, textureLoader, soundManager, 0, 0, 64, 64, 1.0f);
        
        water = r.nextDouble() % 100 + 20;
        nitrogen = r.nextDouble() % 200 + 10;
        oxygen = r.nextDouble() % 250 + 5;              
        
        life = r.nextDouble() % 50 + 5;     
        
        this.x = r.nextInt(800);
        this.y = r.nextInt(320);
        
        this.rotation = r.nextInt(360);
        
        this.ani.addFrame(new Sprite(textureLoader,name + ".png"),0);
        
        this.setSpeed(0.1);
        this.startMoving();
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

    public void setRotation(float rotation)
    {
        this.rotation = rotation;
    }
    
    public void updateWithDelta(long delta) {
        super.updateWithDelta(delta);
        
        if(this.x < 0 || this.y < 0)
            this.rotation = 275;
        if(this.x > 1024 || this.y > 640)
            this.rotation = 135;
    }

    public void render() {      
        this.ani.frameToRender().draw(this.x, this.y, this.scale,this.rotation);
    }
}
