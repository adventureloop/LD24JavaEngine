/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class ButtonEntity extends Entity
{
    ButtonState state;
    private int SOUND_CLICK;
    
    
    public ButtonEntity(String name,Screen parent,TextureLoader textureLoader,SoundManager soundManager,int x, int y,int width,int height) 
    {
       super(name,parent,textureLoader,soundManager,x,y,width,height);
       
       this.ani.addFrame(new Sprite(textureLoader,name + ".png"),0);
       this.ani.addFrame(new Sprite(textureLoader,name + "1.png"),0);
       
       //Load a sound for key presses.
       SOUND_CLICK = soundManager.addSound("click.wav");
       
       state = ButtonState.BUTTON_UP;
    }
    
    public void mouseDown()
    {
        state = ButtonState.BUTTON_DOWN;
    }
    
    public void mouseUp()
    {
        if(state == ButtonState.BUTTON_DOWN) {
            this.clicked();
            state = ButtonState.BUTTON_UP;
        }
    }
    
    public ButtonState getState()
    {
        return state;
    }
    
    public void clearClick()
    {
        state = ButtonState.BUTTON_UP;
    }
    
    public void clicked()
    {
        sm.playSound(SOUND_CLICK);
        parent.clicked(name);
    }
    
    public void render()
    {
        if(state == ButtonState.BUTTON_UP)
            this.ani.frameForIndex(0).draw(this.x, this.y);
        else 
            this.ani.frameForIndex(1).draw(this.x, this.y);
    }
}
