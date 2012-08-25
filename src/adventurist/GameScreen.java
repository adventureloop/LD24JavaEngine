/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class GameScreen extends Screen
{
    SolarSystemScreen solar;
    
    public GameScreen(TextureLoader textureLoader, SoundManager soundManager)
    {
        super(textureLoader,soundManager);
        solar = new SolarSystemScreen(textureLoader,soundManager);
    }

    public void updateWithDelta(long delta) 
    {
        solar.updateWithDelta(delta);
    }
    
    public void render()
    {
        solar.render();
    }
    
    public void mouseEvent(int button,boolean state,int x, int y)
    {
    
    }
    
    public void keyEvent(int key,boolean state)
    {
        
    }
}
