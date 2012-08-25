/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

import java.util.List;
import java.util.ArrayList;

public class SolarSystemScreen extends Screen {

    StarEntity star;
    ArrayList<PlanetEntity> planets;
    ArrayList<CometEntity> comets;
    ArrayList<MeteorEntity> meteors;
    PlanetEntity planet1;
    PlanetEntity planet2;
    PlanetEntity planet3;

    public SolarSystemScreen(TextureLoader textureLoader, SoundManager soundManager) {
        super(textureLoader, soundManager);

        star = new StarEntity("Star", this, textureLoader, soundManager, 600, 120, 128, 128, 1.0f);

        planets = new ArrayList<PlanetEntity>();

        planets.add(new PlanetEntity("DesertPlanet", this, textureLoader, soundManager, 450, 130, 64, 64, 1.0f));
        planets.add(new PlanetEntity("GasGiantPlanet", this, textureLoader, soundManager, 250, 160, 64, 64, 1.5f));
        planets.add(new PlanetEntity("Planet", this, textureLoader, soundManager, 150, 155, 64, 64, 0.5f));
    }

    public void updateWithDelta(long delta) 
    {
        star.updateWithDelta(delta);

        for (PlanetEntity p : planets) {
            p.updateWithDelta(delta);
        }
    }

    public void render() 
    {
        star.render();

        for (PlanetEntity p : planets) {
            p.render();
        }
    }

    public void mouseEvent(int button, boolean state, int x, int y) 
    {
        if(state) {
            if(star.collidesWithPoint(x, y))        
                star.mouseDown();
        }
    }

    public void keyEvent(int key, boolean state) {
    }

    public void generateSolarSystem(int seed) 
    {
    }
}
