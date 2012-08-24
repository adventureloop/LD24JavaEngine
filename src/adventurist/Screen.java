/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.*;

public abstract class Screen 
{
    ScreenState state;
    
     /**
     * The loader responsible for converting images into OpenGL textures
     */
    public TextureLoader textureLoader;
    public SoundManager soundManager;
    
    public Screen(TextureLoader textureLoader, SoundManager soundManager)
    {
        this.textureLoader = textureLoader;
        this.soundManager = soundManager;
    }
    
    private void initGL()
    {
        System.out.println("Initialising OpenGL");
    }
    
    public void updateWithDelta(long delta) 
    {
        System.out.println("Updating");
    }
    
    public void render()
    {
        System.out.println("Rendering");
    }
    
    public void mouseEvent(int button,boolean state,int x, int y)
    {
    
    }
    
    public void keyEvent(int key,boolean state)
    {
        
    }
    
    public void clicked(String name)
    {
        
    }
    
    public ScreenState getState()
    {
        return state;
    }
}
