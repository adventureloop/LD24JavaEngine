/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;
import java.util.ArrayList;

public class MainMenu extends Screen 
{
    List<ButtonEntity> menuItems;
    
    GameScreen game;
    
    public MainMenu(TextureLoader textureLoader,SoundManager soundManager,Game game)
    {
        super(textureLoader,soundManager);
        
        this.game = new GameScreen(textureLoader,soundManager);
        
        menuItems = new ArrayList<ButtonEntity>();
        menuItems.add(new ButtonEntity("playMenuItem",this,textureLoader,soundManager,150,50,512,128,1.0f));
        menuItems.add(new ButtonEntity("exitMenuItem",this,textureLoader,soundManager,150,350,512,128,1.0f));
    }
    
    public void updateWithDelta(long delta) 
    {
        if(state == ScreenState.SCREEN_BACKGROUND)
            game.updateWithDelta(delta);
        else
            for(ButtonEntity e : menuItems)
                e.updateWithDelta(delta);
    }
    
    public void render()
    {
        if(state == ScreenState.SCREEN_BACKGROUND)
            game.render();
        else
            for(ButtonEntity e : menuItems)
                e.render();
    }
    
    public void mouseEvent(int button,boolean state,int x, int y)
    {
        if(this.state == ScreenState.SCREEN_BACKGROUND) {
            game.mouseEvent(button, state, x, y);
            return;
        }
        
        if(button == 0) {
            for(ButtonEntity e : menuItems)
                if(e.collidesWithPoint(x,y)) {
                    if(state)
                        e.mouseDown();
                    else
                        e.mouseUp();
                } else if (e.getState() == ButtonState.BUTTON_DOWN) {
                    e.clearClick();
                }
        
        }
    }
    
    public void keyEvent(int key,int state)
    {
        
    }
    
    public void clicked(String name)
    {
        System.out.println("Button " + name + " was clicked");
        
        if(name.equals("exitMenuItem"))
            state = ScreenState.SCREEN_FINISHED;
        
        if(name.contains("playMenuItem"))
            state = ScreenState.SCREEN_BACKGROUND;
    }
}
