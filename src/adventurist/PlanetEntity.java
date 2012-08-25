/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

public class PlanetEntity extends Entity 
{
    Sprite clicked;
    PlanetType type;
    
    double nitrogen;
    double oxygen;
    double co2;
    
    double water;
    
    long lifePop;
    long intLifePop;
    
    int volcanoes;
    
    double heat;

    public PlanetEntity(String name, Screen parent, TextureLoader textureLoader,
            SoundManager soundManager, int x, int y, int width, int height, float scale) {
        
        super(name, parent, textureLoader, soundManager, x, y, width, height, scale);
        
        
        clicked = new Sprite(textureLoader,"Clicked.png");
        this.ani.addFrame(new Sprite(textureLoader,name + ".png"),0);
    }

    public void updateWithDelta(long delta) {
        //ani.updateWithDelta(delta);
    }

    public void render() {
        //super.render();       
        this.ani.frameToRender().draw(this.x, this.y, this.scale);
        
        if(state == ButtonState.BUTTON_DOWN) {
            clicked.draw(this.x, this.y, this.scale);
            this.renderDetail();
        }
    }
    
    public void renderDetail()
    {
        this.ani.frameToRender().draw(0, 320, this.scale * 1.5f);
    }
}
