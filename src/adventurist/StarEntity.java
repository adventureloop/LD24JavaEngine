/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adventurist;

/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
public class StarEntity extends Entity 
{
    Sprite clicked;
    
    long age;
    int baseTemp;
    
    boolean he;
    int heDiff;
    
    long lastFlare;
    
    StarType type;

    public StarEntity(String name, Screen parent, TextureLoader textureLoader,
            SoundManager soundManager, int x, int y, int width, int height, float scale) {
        super(name, parent, textureLoader, soundManager, x, y, width, height, scale);
        
        this.ani.addFrame(new Sprite(textureLoader,name + ".png"),0);
        clicked = new Sprite(textureLoader,"Clicked.png");
        
        type = StarType.DWARF_STAR;
        
    }

    public void updateWithDelta(long delta) {
        this.age += delta;
        //Also animate
    }
    
    public double temperatureValueForEntity(PlanetEntity p)
    {
        int x = p.getX();
        int y = p.getY();
        int heatValue;
        
        double dist = Math.sqrt(Math.pow((double)this.x - (double)x,2) + Math.pow((double)this.y - (double)y,2))/10;
       /* if(type == StarType.SUPERNOVA_STAR) {
            double ageValue = Math.pow(age/1000,3) + (4 * Math.pow(age/1000, 2)) + (3 * (age/1000)) + 0.6;
            double distValue = (2 * Math.pow(4, -dist+4));
            return baseTemp * ageValue * distValue;
        } else if (type == StarType.DWARF_STAR) {
            double ageValue = -Math.pow(age/1000,3) + (Math.pow(age/1000, 2)) + (3 * (age/1000));
            double distValue = (2 * Math.pow(4, -dist+4));
            return baseTemp * ageValue * distValue;
        }*/
        
        return 100 - dist;              
    }

    public void render() 
    {
        this.ani.frameToRender().draw(this.x, this.y, this.scale,0.0f);
        
        if(state == ButtonState.BUTTON_DOWN) {
            clicked.draw(this.x, this.y, this.scale,0.0f);
            this.renderDetail();
        }
    }
    
    public void renderDetail()
    {
        this.ani.frameToRender().draw(0, 320, this.scale * 1.5f,0.0f);
    }
}
