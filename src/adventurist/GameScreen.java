/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class GameScreen extends Screen
{
    SolarSystemScreen solar;
    Font font;
    
    public GameScreen(TextureLoader textureLoader, SoundManager soundManager)
    {
        super(textureLoader,soundManager);
        solar = new SolarSystemScreen(textureLoader,soundManager);
        font = new Font(textureLoader);
    }

    public void updateWithDelta(long delta) 
    {
        solar.updateWithDelta(delta);       
    }
    
    public void render()
    {
        //solar.render(); 
        font.drawTextAt(0, 0, 0.5f,"hello world");
    }
    
    public void mouseEvent(int button,boolean state,int x, int y)
    {
        solar.mouseEvent(button, state, x, y);
    }
    
    public void keyEvent(int key,boolean state)
    {
        
    }
}
