/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

import java.util.List;
import java.util.ArrayList;

public class TileSet 
{
    List<Sprite> tiles;
    TextureLoader textureLoader;
    
    int[][] tileSet;
    
    public TileSet(TextureLoader textureLoader)
    {
        tiles = new ArrayList<Sprite>();
        this.textureLoader = textureLoader;
        
        tiles.add(new Sprite(this.textureLoader,"tile.gif"));
    }
    
    public void render()
    {
        for(int i = 0;i < 10;i++) {
            for(int j = 0;j < 10;j++) {
                tiles.get(0).draw(i * 128,j * 128);
            }
        }
    }
}
