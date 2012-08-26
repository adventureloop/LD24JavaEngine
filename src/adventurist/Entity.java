/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

public abstract class Entity {

    Animation ani;
    String name;
    SoundManager sm;
    Screen parent;
    
    ButtonState state;
    TextureLoader textureLoader;
    
    int x;
    int y;
    
    int destX;
    int destY;
    
    boolean moving;
    float rotation;
    
    double maxSpeed;  //Max Speed per second
    double speed;      //Current Speed;
    
    int width;
    int height;
    float scale;
    
    public Entity(String name,Screen parent, TextureLoader textureLoader, SoundManager soundManager, int x, int y, int width, int height,float scale) {
        this.name = name;
        this.sm = soundManager;
        this.parent = parent;

        this.ani = new Animation();
        
        this.x = x;
        this.y = y;
        
        this.width = width;
        this.height = height;
        
        this.maxSpeed = 20;
        
        this.moving = false;
        this.scale = scale;
        
        this.textureLoader = textureLoader;
    }

    public boolean collidesWithEntity(Entity e) {
        if(this.collidesWithPoint(e.getX(), e.getY()))
            return true;
        if(this.collidesWithPoint(e.getX() + e.getWidth(), e.getY()))
            return true;
        if(this.collidesWithPoint(e.getX(), e.getY() + e.getHeight()))
            return true;           
        if(this.collidesWithPoint(e.getX() + e.getWidth(), e.getY() + e.getHeight()))
            return true;
        
        return false;
    }

    public boolean collidesWithPoint(int x, int y) {
        
        if (x < this.x || x > this.x + (width * scale)) {
            return false;
        }
        if (y < this.y || y > this.y + (height * scale)) {
            return false;
        }
        return true;
    }
    
    public void setDest(int x,int y)
    {
        this.destX = x;
        this.destY = y;
    }
    
    public void setSpeed(double speed)
    {
        if(speed > this.maxSpeed)
            this.speed = this.maxSpeed;
        else
            this.speed = speed;
    }
    
    public void startMoving()
    {
        this.moving = true;
    }
    
    public void move(long delta)
    {
        this.x += (int)((speed * delta) * Math.cos(Math.toRadians(rotation)));
        this.y += (int)((speed * delta) * Math.sin(Math.toRadians(rotation)));
    }

    public void updateWithDelta(long delta) {
        ani.updateWithDelta(delta);
        if(this.moving)
            this.move(delta);
    }

    public void render() {
        this.ani.frameToRender().draw(this.x, this.y,this.scale,0.0f);
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
        this.state = ButtonState.BUTTON_CLICKED;
        parent.clicked(name);
        
        System.out.println(this.toString());
    }
      
    public String toString()
    {
        return "Implement to string please...";
    }
    
    public int getX()
    {
        return x;        
    }
    
    public int getY()
    {
        return y;        
    }
    
    public int getWidth()
    {
        return this.width;        
    }
    
    public int getHeight()
    {
        return height;
    }
}