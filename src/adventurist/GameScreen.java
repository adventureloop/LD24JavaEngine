/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class GameScreen extends Screen
{
    ButtonEntity bm;
    
    public GameScreen(TextureLoader textureLoader, SoundManager soundManager)
    {
        super(textureLoader,soundManager);
        
        bm = new ButtonEntity("playMenuItem",this,textureLoader,soundManager,0,0,512,128);
        
        bm.setDest(100, 100);
        
        bm.setSpeed(1);
        bm.startMoving();
    }

    public void updateWithDelta(long delta) 
    {
        bm.updateWithDelta(delta);
    }
    
    public void render()
    {
        bm.render();
    }
    
    public void mouseEvent(int button,boolean state,int x, int y)
    {
    
    }
    
    public void keyEvent(int key,boolean state)
    {
        
    }
}
