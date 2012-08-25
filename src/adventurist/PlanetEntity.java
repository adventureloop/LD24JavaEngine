/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

public class PlanetEntity extends Entity 
{
    Sprite clicked;
    PlanetType type;
    PlanetStage stage;
    
    double nitrogen;
    double oxygen;
    double co2;
    
    double nitrogenPer;
    double oxygenPer;
    double co2Per;
    
    double water;
    
    long lifePop;
    long intLifePop;
    long age;
    
    int volcanoes;
    
    double heat;
    double externalHeat;

    public PlanetEntity(String name, Screen parent, TextureLoader textureLoader,
            SoundManager soundManager, int x, int y, int width, int height, float scale) {
        
        super(name, parent, textureLoader, soundManager, x, y, width, height, scale);
        
        
        clicked = new Sprite(textureLoader,"Clicked.png");
        this.ani.addFrame(new Sprite(textureLoader,name + ".png"),0);
        
        type = PlanetType.PLANET_M_CLASS;
        stage = PlanetStage.DEAD;
        
        this.co2 = 0.5;
        this.oxygen = 25.5;
        this.nitrogen = 74;
    }

    public void updateWithDelta(long delta) {
        this.age += delta;                
        
        switch(type)
        {
            case PLANET_DEATH:
                break;
            case PLANET_ICE:
                break;                    
            case PLANET_GAS:
                break;
            case PLANET_DESERT:
                break;
            case PLANET_M_CLASS:
                break;                       
        }
        
        switch(stage)
        {
            case DEAD:
                if(lifePop > 0)
                    stage = PlanetStage.BACTERIAL;
                break;
            case BACTERIAL:
                if(co2Per > 0.052 || (heat > 68 || heat < 20))
                    stage = PlanetStage.DEAD;
                
                lifePop += delta;
                
                oxygen += delta;                                
                
                if(water > 0.65 && age > 20000)
                    stage = PlanetStage.AQUATIC;
                break;            
            case AQUATIC:
                if(co2Per > 0.045)
                    stage = PlanetStage.BACTERIAL;
                
                if((heat > 35) && age > 45000)
                    stage = PlanetStage.REPTILIAN;
                break;
            case REPTILIAN:
                if(co2Per > 0.044 || heat < 30 || water < 0.55)
                    stage = PlanetStage.AQUATIC;
                co2 += delta;
                oxygen += delta;
                
                if(age > 60000)
                    stage = PlanetStage.MAMMALIAN;
                break;
            case MAMMALIAN:  
                if(co2Per > 0.041)
                    stage = PlanetStage.BACTERIAL;
                co2 += delta * 1.2;
                oxygen += delta;
                nitrogen -= delta * 0.1;
                                                
                if(age > 80000)
                    stage = PlanetStage.CAVEMAN;
                break;                
            case CAVEMAN:
                if(co2Per > 0.0399)
                    stage = PlanetStage.BACTERIAL;
                co2 += delta * 1.2;
                oxygen += delta;
                nitrogen -= delta * 0.1;                
                
                if(age > 100000)
                    stage = PlanetStage.NOMADIC;
                break;
            case NOMADIC:
                if(co2Per > 0.0395)
                    stage = PlanetStage.BACTERIAL;
                co2 += delta * 1.5;
                oxygen += delta;
                nitrogen -= delta * 0.15;         
                
                if(age > 120000)
                    stage = PlanetStage.SETTLED;
                break;
            case SETTLED:
                if(co2Per > 0.0395)
                    stage = PlanetStage.BACTERIAL;
                co2 += delta * 1.5;
                oxygen += delta;
                nitrogen -= delta * 0.15;        
                
                if(age > 140000)
                    stage = PlanetStage.MAMMALIAN;
                break;
            case MEDIEVAL:
                if(co2Per > 0.0395)
                    stage = PlanetStage.BACTERIAL;
                co2 += delta * 1.5;
                oxygen += delta;
                nitrogen -= delta * 0.15;             
                
                if(age > 160000)
                    stage = PlanetStage.MEDIEVAL;
                break;
            case INDUSTRIAL:
                if(co2Per > 0.0395)
                    stage = PlanetStage.BACTERIAL;
                co2 += delta * 1.5;
                oxygen += delta;
                nitrogen -= delta * 0.15;           
                
                if(age > 180000)
                    stage = PlanetStage.INDUSTRIAL;
                break;
            case FUTURISTIC:
                if(co2Per > 0.0395)
                    stage = PlanetStage.BACTERIAL;
                co2 += delta * 1.5;
                oxygen += delta;
                nitrogen -= delta * 0.15;     
                
                break;
        }
        
        this.calcPercentages();
        
        heat = heat * (co2Per / 0.00399);
        heat += externalHeat;
    }
    
    public void hitWithMeteor(MeteorEntity m)
    {        
        this.nitrogen += m.getNitrogen();
        this.oxygen += m.getOxygen();
        this.water += m.getWater();
        this.lifePop += m.getLife();
        
        this.calcPercentages();
    }
    
    public void updateHeat(double heat)
    {
        this.externalHeat = heat;
    }

    public void render() {
        //super.render();       
        this.ani.frameToRender().draw(this.x, this.y, this.scale,0.0f);
        
        if(state == ButtonState.BUTTON_DOWN) {
            clicked.draw(this.x, this.y, this.scale,0.0f);
            this.renderDetail();
        }
    }
    
    public void renderDetail()
    {
        this.ani.frameToRender().draw(0, 320, this.scale * 1.5f,0.0f);
    }
    
    @Override
    public String toString()
    {
        String desc = "";
        switch(type)
        {
            case PLANET_DEATH:
                desc = desc + "Death Planet, ";
                break;
            case PLANET_ICE:
                desc = desc + "Ice Planet, ";
                break;                    
            case PLANET_GAS:
                desc = desc + "Gas Giant, ";
                break;
            case PLANET_DESERT:
                desc = desc + "Desert Planet, ";
                break;
            case PLANET_M_CLASS:
                desc = desc + "M Class Planet, ";
                break;                      
        }
        
        switch(stage)
        {
            case DEAD:
                desc = desc + "dead ";
                break;
            case BACTERIAL:
                desc = desc + " bacterial stage.";
                break;
            case AQUATIC:
                desc = desc + "aquatic stage.";
                break;
            case REPTILIAN:
                desc = desc + "dinosaur time.";
                break;
            case MAMMALIAN:
                desc = desc + "1000's of kittens.";
                break;
            case CAVEMAN:
                desc = desc + "cavemen hug kitty.";
                break;
            case NOMADIC:
                desc = desc + "nomadic stage.";
                break;
            case SETTLED:
                desc = desc + "house cat stage.";
                break;
            case MEDIEVAL:
                desc = desc + "battle cat stage.";
                break;
            case INDUSTRIAL:
                desc = desc + "Fat cat stage.";
                break;
            case FUTURISTIC:
                desc = desc + "space cat stage.";
                break;
        }
        
        desc = desc + " Planet at X:" + this.x + " Y:" + this.y + ". Planet is age " + this.age +
                " and " + this.heat + " degress."; 
        return desc;
    }
    
    public void calcPercentages()
    {
        //Recalculate gas levels PERCENTAGES
        double max = nitrogen + oxygen + co2;
        nitrogenPer = nitrogen / max;
        oxygenPer = oxygen / max;
        co2Per = co2 / max;
    }
}
