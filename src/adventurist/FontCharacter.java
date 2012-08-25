/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;


public class FontCharacter 
{
    char character;
    float xstart;
    float xend;
    float ystart;
    float yend;
    
    public FontCharacter(char character, float xstart,float ystart, float xend,float yend)
    {
        this.character = character;
        this.xstart = xstart;
        this.xend = xend;
        this.ystart = ystart;
        this.yend = yend;
    }
    
    public char getChar()
    {
        return character;
    }
    
    public float getXStart()
    {
        return xstart;    
    }
    
    public float getXEnd()
    {
        return xend;    
    }
        
    public float getYStart()
    {
        return ystart;    
    }
            
    public float getYEnd()
    {
        return yend;    
    }
}
