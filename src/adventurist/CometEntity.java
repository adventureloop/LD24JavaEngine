/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

public class CometEntity extends Entity {

    public CometEntity(String name, Screen parent, TextureLoader textureLoader,
            SoundManager soundManager, int x, int y, int width, int height, float scale) {
        super(name, parent, textureLoader, soundManager, x, y, width, height, scale);

    }

    public void updateWithDelta(long delta) {
        //ani.updateWithDelta(delta);
    }

    public void render() {
        super.render();

        //this.ani.frameToRender().draw(this.x, this.y, this.scale);
    }
}
