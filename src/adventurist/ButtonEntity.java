/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class ButtonEntity extends Entity
{
    private int SOUND_CLICK;
    
    
    public ButtonEntity(String name,Screen parent,TextureLoader textureLoader,SoundManager soundManager,int x, int y,int width,int height,float scale) 
    {
       super(name,parent,textureLoader,soundManager,x,y,width,height,scale);
       
       this.ani.addFrame(new Sprite(textureLoader,name + ".png"),0);
       this.ani.addFrame(new Sprite(textureLoader,name + "1.png"),0);
       
       //Load a sound for key presses.
       SOUND_CLICK = soundManager.addSound("click.wav");
       
       state = ButtonState.BUTTON_UP;
    }
    
    public void clicked()
    {
        sm.playSound(SOUND_CLICK);
        super.clicked();
    }
    
    public void render()
    {
        if(state == ButtonState.BUTTON_UP)
            this.ani.frameForIndex(0).draw(this.x, this.y);
        else 
            this.ani.frameForIndex(1).draw(this.x, this.y);
    }
}
