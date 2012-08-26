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
    StarFieldEntity stars;
    
    GameScreen game;
    
    public MainMenu(TextureLoader textureLoader,SoundManager soundManager,Game game)
    {
        super(textureLoader,soundManager);
        
        this.game = new GameScreen(textureLoader,soundManager);
        
        menuItems = new ArrayList<ButtonEntity>();
        menuItems.add(new ButtonEntity("playMenuItem",this,textureLoader,soundManager,250,300,512,128,1.0f));
        menuItems.add(new ButtonEntity("playMenuTutorialItem",this,textureLoader,soundManager,250,400,512,128,1.0f));
        menuItems.add(new ButtonEntity("exitMenuItem",this,textureLoader,soundManager,250,500,512,128,1.0f));
        
        menuItems.add(new ButtonEntity("mainTitleItem",this,textureLoader,soundManager,285,0,592,336,0.75f));
        
        stars = new StarFieldEntity(this,textureLoader,soundManager);
        
        this.state = ScreenState.SCREEN_RUNNING;
    }
    
    public void updateWithDelta(long delta) 
    {
        if(state == ScreenState.SCREEN_BACKGROUND) {
            if(game.getState() == ScreenState.SCREEN_FINISHED) {
                state = ScreenState.SCREEN_RUNNING;
                this.game = null;
                this.game = new GameScreen(textureLoader,soundManager);
                return;
            } else if(game.getState() == ScreenState.SCREEN_COMPLETED) {
                this.game = null;
                this.game = new GameScreen(textureLoader,soundManager);
                return;
            }
                
            game.updateWithDelta(delta);
        } else {
            for(ButtonEntity e : menuItems)
                e.updateWithDelta(delta);
            stars.updateWithDelta(delta);
        }        
    }
    
    public void render()
    {
        if(state == ScreenState.SCREEN_BACKGROUND)
            game.render();
        else {
            stars.render();
            for(ButtonEntity e : menuItems)
                e.render();            
        }
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
        
        if(name.contains("playMenuItem")) {
            state = ScreenState.SCREEN_BACKGROUND;
            game.clicked(name);
        }
        
        if(name.equals("playMenuTutorialItem")) {
            state = ScreenState.SCREEN_BACKGROUND;
            game.clicked(name);            
        }
    }
}
