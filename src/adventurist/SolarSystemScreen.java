/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SolarSystemScreen extends Screen {

    StarEntity star;
    ButtonEntity exitButton;
    ButtonEntity volcanoButton;
    
    ArrayList<PlanetEntity> planets;
    ArrayList<CometEntity> comets;
    ArrayList<MeteorEntity> meteors;
    
    Entity selectedEntity;
    
    public SolarSystemScreen(TextureLoader textureLoader, SoundManager soundManager) {
        super(textureLoader, soundManager);

        selectedEntity = null;                

        planets = new ArrayList<PlanetEntity>();
        meteors = new ArrayList<MeteorEntity>();   
        
        exitButton = new ButtonEntity("exitButton",this,textureLoader,soundManager,950,600,64,32,1.0f);
        volcanoButton = new ButtonEntity("volcanoButton",this,textureLoader,soundManager,700,600,128,32,1.0f);
    }

    public void updateWithDelta(long delta) 
    {
        star.updateWithDelta(delta);      

        for (PlanetEntity p : planets) {
            p.updateHeat(star.temperatureValueForEntity(p));            
            p.updateWithDelta(delta);
        }
        
        List<MeteorEntity> deleteList = new ArrayList<MeteorEntity>();
        
        for(MeteorEntity m : meteors) {
            m.updateWithDelta(delta);
            for(PlanetEntity p : planets) {
                if(p.collidesWithEntity(m)) {
                    deleteList.add(m);
                    p.hitWithMeteor(m);
                }
            }
            
            if(m.getX() < -5 || m.getY() < -5) {
                deleteList.add(m);                                
            }
            
            if(m.getX() > 1048 || m.getY() > 680) {
                deleteList.add(m);
            }
        }
        
        for(MeteorEntity m : deleteList) {
            meteors.remove(m);
        }
        
        if(meteors.size() < 4) {
            Random r = new Random();
            int size = r.nextInt(5) + 4;
            while(size-- > 0) {
            meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));
            }
        }
            
        
        exitButton.updateWithDelta(delta);
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
        
        exitButton.render();
        
        if(selectedEntity != null && selectedEntity.getClass().getName().equals("adventurist.PlanetEntity")) {
            volcanoButton.render();
        }
    }

    public void mouseEvent(int button, boolean state, int x, int y) 
    {
        if((selectedEntity != null && selectedEntity.getClass().getName().equals("adventurist.PlanetEntity")) 
                && volcanoButton.collidesWithPoint(x, y)) {
            if(state)
                volcanoButton.mouseDown();
            else
                volcanoButton.mouseUp();
            return;
        }
        
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
        if(exitButton.collidesWithPoint(x, y)) {
            if(state)
                exitButton.mouseDown();
            else
                exitButton.mouseUp();            
        }

    }

    public void keyEvent(int key, boolean state) {
    }

    public void generateSolarSystem(int seed) 
    {
        Random r = new Random();
        
        double matter = (double)(r.nextLong() % 100000) + 100000;
        
        double starSize = (matter / 2)/100000.0;
        matter = matter / 2;
        
        star = new StarEntity("Star", this, textureLoader, soundManager, 800, 20, 128, 128, (float)starSize+2.0f);
        
        int xpos = 600;
        while(matter > 2000) {
            int size = r.nextInt(4)+1;
            
            int ypos = r.nextInt(60);
            ypos *= (r.nextInt(10) > 5) ? -1 : 1;
            ypos += 70;
            
            switch(size) {
                case 1:
                    planets.add(new PlanetEntity("DesertPlanet", this, textureLoader, soundManager, xpos, ypos, 128, 128, size/2));
                    break;
                case 2:
                    planets.add(new PlanetEntity("GasGiantPlanet", this, textureLoader, soundManager, xpos, ypos, 128, 128, size/2));
                    break;
                case 3:
                    planets.add(new PlanetEntity("Planet", this, textureLoader, soundManager, xpos, ypos, 128, 128, size/2));
                    break;
                case 4:
                    planets.add(new PlanetEntity("Planet", this, textureLoader, soundManager, xpos, ypos, 128, 128, size/2));
                    break;
            }
            matter -= (1000 * size);
            xpos -= 190;
        }
        
        int size = r.nextInt(5) + 4;
        while(size-- > 0) {
            meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));
        }
    }
    
    public void tutorialSystem()
    {
        planets.clear();
        meteors.clear();
        
        star = new StarEntity("Star", this, textureLoader, soundManager, 800, 20, 128, 128, 3.0f);
        
        planets.add(new PlanetEntity("DesertPlanet", this, textureLoader, soundManager, 600, 130, 128, 128, 1.4f));
        planets.add(new PlanetEntity("GasGiantPlanet", this, textureLoader, soundManager, 400, 160, 128, 128, 1.1f));        
        planets.add(new PlanetEntity("Planet", this, textureLoader, soundManager, 200, 120, 128, 128, 1.0f));
        planets.add(new PlanetEntity("Planet", this, textureLoader, soundManager, 0, 100, 128, 128, 1.5f));
        
        meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));
        meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));
        meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));
        meteors.add(new MeteorEntity("Meteor", this, textureLoader, soundManager));  
        
        planets.get(0).hitWithMeteor(new MeteorEntity("Meteor", this, textureLoader, soundManager));
    }
            
    public void clicked(String name)
    {
        if(name.equals("exitButton")) {
            state = ScreenState.SCREEN_FINISHED;
        }
        
        if(name.equals("volcanoButton")) {
            if(selectedEntity != null && 
                    selectedEntity.getClass().getName().equals("adventurist.PlanetEntity")){
                ((PlanetEntity)selectedEntity).addVolcano();
            }            
        }
    }
}
