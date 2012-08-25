
/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

import java.util.List;
import java.util.ArrayList;

public class Font 
{
    Sprite spriteSheet;
    List<FontCharacter> characters;
    
    public Font(TextureLoader textureLoader)
    {
        spriteSheet = new Sprite(textureLoader,"FontWhite.png");
        characters = new ArrayList<FontCharacter>();
        
        characters.add(new FontCharacter('A',5/144f,0,10/144f,15/64f));
        characters.add(new FontCharacter('B',10/144f,0,18/144f,15/64f));
        characters.add(new FontCharacter('C',24/144f,0,34/144f,15/64f));
        characters.add(new FontCharacter('D',36/144f,0,48/144f,15/64f));
        characters.add(new FontCharacter('E',48/144f,0,58/144f,15/64f));
        characters.add(new FontCharacter('F',58/144f,0,68/144f,15/64f));
        characters.add(new FontCharacter('G',72/144f,0,82/144f,15/64f));
        characters.add(new FontCharacter('H',84/144f,0,94/144f,15/64f));
        characters.add(new FontCharacter('I',96/144f,0,106/144f,15/64f));
        characters.add(new FontCharacter('J',108/144f,0,118/144f,15/64f));
        characters.add(new FontCharacter('L',120/144f,0,130/144f,15/64f));
        characters.add(new FontCharacter('M',132/144f,0,142/144f,15/64f));
        
        characters.add(new FontCharacter('K',105/144f,50/64f,115/144f,62/64f));
        
        characters.add(new FontCharacter('N',0/144f,20/64f,9/144f,32/64f));
        characters.add(new FontCharacter('O',10/144f,20/64f,20/144f,32/64f));
        characters.add(new FontCharacter('P',20/144f,20/64f,24/144f,32/64f));
        characters.add(new FontCharacter('Q',24/144f,20/64f,33/144f,32/64f));
        characters.add(new FontCharacter('R',36/144f,20/64f,46/144f,32/64f));
        characters.add(new FontCharacter('S',48/144f,20/64f,58/144f,32/64f));
        characters.add(new FontCharacter('T',60/144f,20/64f,70/144f,32/64f));
        characters.add(new FontCharacter('U',72/144f,20/64f,82/144f,32/64f));
        characters.add(new FontCharacter('V',84/144f,20/64f,94/144f,32/64f));
        characters.add(new FontCharacter('W',96/144f,20/64f,106/144f,32/64f));
        characters.add(new FontCharacter('X',108/144f,20/64f,118/144f,32/64f));
        characters.add(new FontCharacter('Y',120/144f,20/64f,130/144f,32/64f));
        characters.add(new FontCharacter('Z',132/144f,20/64f,142/144f,32/64f));
    }
    
    public void drawTextAt(int x,int y,double size,String message)
    {
        message = message.toUpperCase();
        
        for(int i = 0;i < message.length();i++) {
            char c = message.charAt(i);
             
            if(c == ' ')
                continue;
            
            FontCharacter fc = null;
            
            for(FontCharacter f : characters)
                if(f.getChar() == c) {
                    fc = f;
                }            
            
            if(fc == null)
                continue;
            
            spriteSheet.draw(x + (i * 30),y,fc.getXStart(),fc.getXEnd(),fc.getYStart(),fc.getYEnd(),1.0f);
        }
    }
}
