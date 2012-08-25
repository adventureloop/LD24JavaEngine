/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class GameScreen extends Screen
{
    StarEntity star;
    
    GameEntity planet1;
    GameEntity planet2;
    GameEntity planet3;
    
    public GameScreen(TextureLoader textureLoader, SoundManager soundManager)
    {
        super(textureLoader,soundManager);
        
        star = new StarEntity("Star",this,textureLoader,soundManager,600,120,128,128,1.0f);
        
        planet1 = new GameEntity("DesertPlanet",this,textureLoader,soundManager,450,130,64,64,1.0f);
        planet2 = new GameEntity("GasGiantPlanet",this,textureLoader,soundManager,250,160,64,64,1.5f);
        planet3 = new GameEntity("Planet",this,textureLoader,soundManager,150,155,64,64,0.5f);
    }

    public void updateWithDelta(long delta) 
    {
        star.updateWithDelta(delta);
    }
    
    public void render()
    {
        star.render();
        
        planet1.render();               
        planet2.render();        
        planet3.render();
    }
    
    public void mouseEvent(int button,boolean state,int x, int y)
    {
    
    }
    
    public void keyEvent(int key,boolean state)
    {
        
    }
}
