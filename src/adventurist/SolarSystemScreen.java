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
    
    Entity selectedEntity;
    
    public SolarSystemScreen(TextureLoader textureLoader, SoundManager soundManager) {
        super(textureLoader, soundManager);

        selectedEntity = null;
        
        star = new StarEntity("Star", this, textureLoader, soundManager, 800, 20, 128, 128, 3.0f);

        planets = new ArrayList<PlanetEntity>();
        meteors = new ArrayList<MeteorEntity>();

        planets.add(new PlanetEntity("DesertPlanet", this, textureLoader, soundManager, 600, 130, 64, 64, 1.4f));
        planets.add(new PlanetEntity("GasGiantPlanet", this, textureLoader, soundManager, 400, 160, 64, 64, 1.1f));        
        planets.add(new PlanetEntity("Planet", this, textureLoader, soundManager, 200, 120, 64, 64, 0.8f));
        planets.add(new PlanetEntity("Planet", this, textureLoader, soundManager, 0, 100, 64, 64, 1.5f));
        
        meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));
        meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));
        meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));
        meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));
        
        planets.get(1).hitWithMeteor(meteors.get(0));
        
    }

    public void updateWithDelta(long delta) 
    {
        star.updateWithDelta(delta);      

        for (PlanetEntity p : planets) {
            p.updateHeat(star.temperatureValueForEntity(p));            
            p.updateWithDelta(delta);
        }
        
        for(MeteorEntity m : meteors) {
            m.updateWithDelta(delta);
        }
    }

    public void render() 
    {
        star.render();

        for (PlanetEntity p : planets) {
            p.render();
        }
        
        for(MeteorEntity m : meteors) {
            m.render();
        }
    }

    public void mouseEvent(int button, boolean state, int x, int y) 
    {
        if(state && selectedEntity != null) {            
                selectedEntity.clearClick();
                selectedEntity = null;
            }
        
        if(state) {
            if(star.collidesWithPoint(x, y)) {  
                star.clicked();
                star.mouseDown();
                selectedEntity = star;
            }
            for(PlanetEntity p : planets) {
                if(p.collidesWithPoint(x, y)) {
                    p.clicked();
                    p.mouseDown();                          
                    selectedEntity = p;
                }
                    
            }          
        }
    }

    public void keyEvent(int key, boolean state) {
    }

    public void generateSolarSystem(int seed) 
    {
    }
}
