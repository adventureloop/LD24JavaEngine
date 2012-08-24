/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

import java.util.List;
import java.util.ArrayList;

public class Animation 
{
    List<Sprite> frames;
    List<Integer> timings;
    
    long lastrun;
    int index;
    AnimationStates state;
    
    public Animation()
    {
        this.frames = new ArrayList<Sprite>();
        this.timings = new ArrayList<Integer>();
        
        index = -1;
        state = AnimationStates.ANIMATION_STOPPED;
    }
    
    public void addFrame(Sprite frame,int duration)
    {
        this.frames.add(frame);
        this.timings.add(new Integer(duration));
        
        if(index < 0)
            index = 0;
    }
    
    public void updateWithDelta(long delta)
    {
        if(this.frames.isEmpty() || state == AnimationStates.ANIMATION_STOPPED)
            return;
        System.out.println("Animation delta " + delta);
        lastrun += delta;
        
        if(lastrun > this.timings.get(index).intValue()) {
            if(index+1 >= this.timings.size()) {         //Run to end of anitation, wrap round if non repeatable       
                index = 0;
                if(state == AnimationStates.ANIMATION_FINISHING)
                    state = AnimationStates.ANIMATION_STOPPED;
            } else {
                index++;
            }
            lastrun = 0;
        }
    }
    
    public Sprite frameToRender()
    {
        if(index > this.frames.size()-1) 
            return null;
        else 
            return this.frames.get(index);
    }
    
    public void startAnimation()
    {
        this.state = AnimationStates.ANIMATION_RUNNING;
    }
    
    public void stopAnimation()
    {
        this.state = AnimationStates.ANIMATION_STOPPED;        
    }
    
    public void finishAnimation()
    {
        this.state = AnimationStates.ANIMATION_FINISHING;
    }
    
    public void nextFrame()
    {
        index++;
        if(index >= this.frames.size()-1)
            index = 0;               
    }
    
    public void previousFrame()
    {
        index--;
        if(index < 0)
            index = this.frames.size()-1;
    }
    
    public Sprite frameForIndex(int index)
    {
        return (index > this.frames.size()-1) ? null : this.frames.get(index); 
    }
}
