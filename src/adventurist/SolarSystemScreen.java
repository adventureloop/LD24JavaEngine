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
    SolarFlareEntity flare;
    
    ButtonEntity exitButton;    
    ButtonEntity solarflareButton;
    
    ButtonEntity volcanoButton;
    ButtonEntity lifeViewButton;
    ButtonEntity normalViewButton;
    ButtonEntity volcanoViewButton;
    
    ProgressEntity co2Bar;
    ProgressEntity o2Bar;
    ProgressEntity n2Bar;
    ProgressEntity tempBar;
    
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
        solarflareButton = new ButtonEntity("solarflareButton",this,textureLoader,soundManager,845,450,176,32,1.0f);
        
        volcanoButton = new ButtonEntity("volcanoButton",this,textureLoader,soundManager,100,610,128,32,1.0f);
                
        normalViewButton = new ButtonEntity("normalViewButton",this,textureLoader,soundManager,0,610,32,32,1.0f);
        lifeViewButton = new ButtonEntity("lifeViewButton",this,textureLoader,soundManager,32,610,32,32,1.0f);
        volcanoViewButton = new ButtonEntity("volcanoViewButton",this,textureLoader,soundManager,64,610,32,32,1.0f);
        
        o2Bar = new ProgressEntity("O2Level",this,textureLoader,soundManager,300,450,96,32,1.0f);
        n2Bar = new ProgressEntity("N2Level",this,textureLoader,soundManager,300,480,96,32,1.0f);
        co2Bar = new ProgressEntity("CO2Level",this,textureLoader,soundManager,300,510,96,32,1.0f);
        
        tempBar = new ProgressEntity("tempLevel",this,textureLoader,soundManager,300,540,96,32,1.0f);
        

        co2Bar.setLevel(0.75);
        o2Bar.setLevel(0.25);
        n2Bar.setLevel(0.6);
        tempBar.setLevel(150);
        
        this.state = ScreenState.SCREEN_RUNNING;
    }

    public void updateWithDelta(long delta) 
    {
        star.updateWithDelta(delta);      

        for (PlanetEntity p : planets) {
            if(p.getStage() == PlanetStage.PLANET_FINISHED) {
                this.state = ScreenState.SCREEN_COMPLETED;
            }
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
        
        if(flare != null && flare.getX() < 0) {
            flare = null;
        }
        
        exitButton.updateWithDelta(delta);
        volcanoButton.updateWithDelta(delta);
       
        lifeViewButton.updateWithDelta(delta);;
        normalViewButton.updateWithDelta(delta);;
        volcanoViewButton.updateWithDelta(delta);;
        
        
        if(flare != null) {
            flare.updateWithDelta(delta);
        } else {
            solarflareButton.updateWithDelta(delta);    
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
        
        exitButton.render();
        
        if(selectedEntity != null && selectedEntity.getClass().getName().equals("adventurist.PlanetEntity")) {
            volcanoButton.render();
            
            lifeViewButton.render();        
            normalViewButton.render();
            volcanoViewButton.render();
            
            co2Bar.updateProgress( ((PlanetEntity)selectedEntity).getCO2Level());
            o2Bar.updateProgress( ((PlanetEntity)selectedEntity).getO2Level());
            n2Bar.updateProgress( ((PlanetEntity)selectedEntity).getN2Level());
            
            tempBar.updateProgress( ((PlanetEntity)selectedEntity).getTemp());
            
            co2Bar.render();
            o2Bar.render();
            n2Bar.render();
            
            tempBar.render();
        }
        
        if(flare != null) {
            flare.render();
        }else {
            solarflareButton.render();
        }
    }

    public void mouseEvent(int button, boolean state, int x, int y) 
    {
        if(selectedEntity != null && selectedEntity.getClass().getName().equals("adventurist.PlanetEntity")){ 
            ButtonEntity tmpButton = null;
            if(volcanoButton.collidesWithPoint(x, y)) {
                tmpButton = volcanoButton;
            } else if(lifeViewButton.collidesWithPoint(x, y)) {
                 tmpButton = lifeViewButton;
            } else if(normalViewButton.collidesWithPoint(x, y)) {
                 tmpButton = normalViewButton;
            } else if(volcanoViewButton.collidesWithPoint(x, y)) {
                 tmpButton = volcanoViewButton;
            }
            
            if(tmpButton != null) {
                if(state) {
                        tmpButton.mouseDown();
                        return;
                } else {
                        tmpButton.mouseUp();
                        return;
                }
            }
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
            if(state) {
                exitButton.mouseDown();
            }
            else {
                exitButton.mouseUp();
            }            
        }
        
        if(flare == null && solarflareButton.collidesWithPoint(x, y)) {
            if(state) {
                solarflareButton.mouseDown();
            }
            else {
                solarflareButton.mouseUp();
            }            
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
                    planets.add(new PlanetEntity("DesertPlanet", this, textureLoader, soundManager, xpos, ypos, 128, 128, size/2));
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
                
    }
            
    public void clicked(String name)
    {
        if(name.equals("exitButton")) {
            state = ScreenState.SCREEN_FINISHED;
        }
        
        if(selectedEntity != null && 
                selectedEntity.getClass().getName().equals("adventurist.PlanetEntity")){
            if(name.equals("volcanoButton")) {
                ((PlanetEntity)selectedEntity).addVolcano();
            }            
            
            if(name.equals("volcanoViewButton")) {
                ((PlanetEntity)selectedEntity).setViewState(ViewState.VOLCANO_VIEW);
            }
            
            if(name.equals("normalViewButton")) {
                ((PlanetEntity)selectedEntity).setViewState(ViewState.NORMAL_VIEW);
            }
                        
            if(name.equals("lifeViewButton")) {
                ((PlanetEntity)selectedEntity).setViewState(ViewState.LIFE_VIEW);
            }
        }
        
        if(name.equals("solarflareButton")) {
            flare = new SolarFlareEntity(this,textureLoader,soundManager);
            flare.setScale(star.getScale() * 0.8f);
            
            flare.setX(star.getX());
            flare.setY(star.getY());
            
            System.out.println("Creating solarflare");
        }
    }
}
