/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

public class GameEntity extends Entity
{
    public GameEntity(String name,Screen parent,TextureLoader textureLoader,SoundManager soundManager,int x, int y,int width,int height) 
    {
       super(name,parent,textureLoader,soundManager,x,y,width,height);
       
       this.ani.addFrame(new Sprite(textureLoader,name + ".png"),0);
    }    
 
    public void updateWithDelta(long delta) {
        //ani.updateWithDelta(delta);
               
        
    }

    public void render() {
        this.ani.frameToRender().draw(this.x, this.y);
    }
}
