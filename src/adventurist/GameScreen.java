/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class GameScreen extends Screen
{
    SolarSystemScreen solar;
    FinishedScreen finished;
    Font font;
    
    public GameScreen(TextureLoader textureLoader, SoundManager soundManager)
    {
        super(textureLoader,soundManager);
        solar = new SolarSystemScreen(textureLoader,soundManager);
        font = new Font(textureLoader);
        finished = new FinishedScreen(textureLoader,soundManager);
    }

    public void updateWithDelta(long delta) 
    {
        if(solar.getState() == ScreenState.SCREEN_FINISHED) {
            this.state = ScreenState.SCREEN_FINISHED;
        }else if (finished.getState() == ScreenState.SCREEN_FINISHED) {
            this.state = ScreenState.SCREEN_FINISHED;
        }else if(solar.getState() == ScreenState.SCREEN_COMPLETED) {  
            this.state = ScreenState.SCREEN_COMPLETED;
        }else {
            solar.updateWithDelta(delta);
        }       
    }
    
    public void render()
    {
        if(solar.getState() == ScreenState.SCREEN_RUNNING) {
            solar.render(); 
        }else if(solar.getState() == ScreenState.SCREEN_COMPLETED) {
            finished.render();
        }
        //font.drawTextAt(0, 0, 0.5f,"ABCDEFGHIJKLMNOPQRSTUVWXZY");
    }
    
    public void mouseEvent(int button,boolean state,int x, int y)
    {
        solar.mouseEvent(button, state, x, y);
    }
    
    public void keyEvent(int key,boolean state)
    {
        
    }
    
    public void clicked(String name)
    {
         if(name.contains("playMenuItem")) {            
            solar.generateSolarSystem(0);
        }
        
        if(name.equals("playMenuTutorialItem")) {
            solar.tutorialSystem();            
        }
    }
}
