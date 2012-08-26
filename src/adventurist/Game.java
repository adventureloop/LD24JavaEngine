/**
 *
 * @author Tom Jones <jones@sdf.org> adventurist.me
 */
package adventurist;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Game 
{

    /**
     * The normal title of the window
     */
    private String WINDOW_TITLE = "adventureloop, LD24: Literally, \"Earth-shaping\"";
    /**
     * The dimensions of the game display area
     */
    private int width = 1024;
    private int height = 640;
    /**
     * Variables for managing fps recording and game loop execution
     */
    private long lastFpsTime;
    private long lastLoopTime;
    /**
     * The recorded fps
     */
    private float fps;
    private static long timerTicksPerSecond = Sys.getTimerResolution();
    /**
     * True if the game is currently "running", i.e. the game loop is looping
     */
    public static boolean gameRunning = true;
    /**
     * Whether we're running in fullscreen mode
     */
    private boolean fullscreen;
    /**
     * Mouse movement on x axis
     */
    private int mouseX;
    /**
     * The loader responsible for converting images into OpenGL textures
     */
    private TextureLoader textureLoader;
    /**
     * SoundManager reference
     */
    private SoundManager soundManager;
    /**
     * ID of sounds effects
     */
    private int SOUND_CLICK;
    /**
     * Keep a sprite around to hold a message
     */
    private Sprite message;
    /**
     * Is this an application or applet
     */
    private static boolean isApplication;

    private MainMenu mainMenu;
    
    /**
     * Construct our game and set it running.
     *
     * @param fullscreen
     *
     */
    public Game(boolean fullscreen) 
    {
        this.fullscreen = fullscreen;
        initialize();
    }

    /**
     * Get the high resolution time in milliseconds
     *
     * @return The high resolution time in milliseconds
     */
    public static long getTime() 
    {
        // we get the "timer ticks" from the high resolution timer
        // multiply by 1000 so our end result is in milliseconds
        // then divide by the number of ticks in a second giving
        // us a nice clear time in milliseconds
        return (Sys.getTime() * 1000) / timerTicksPerSecond;
    }

    /**
     * Sleep for a fixed number of milliseconds.
     *
     * @param duration The amount of time in milliseconds to sleep for
     */
    public static void sleep(long duration) 
    {
        try {
            Thread.sleep((duration * timerTicksPerSecond) / 1000);
        } catch (InterruptedException inte) {
        }
    }

    /**
     * Intialise the common elements for the game
     */
    public void initialize() 
    {
        // initialize the window beforehand
        try {
            setDisplayMode();
            Display.setTitle(WINDOW_TITLE);
            Display.setFullscreen(fullscreen);
            Display.create();

     /*       // grab the mouse, dont want that hideous cursor when we're playing!
            if (isApplication) {
                Mouse.setGrabbed(true);
            }*/

            // enable textures since we're going to use these for our sprites
            glEnable(GL_TEXTURE_2D);
            
            //Enable alpha blending
            glEnable (GL_BLEND);
            glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            // disable the OpenGL depth test since we're rendering 2D graphics
            glDisable(GL_DEPTH_TEST);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();

            glOrtho(0, width, height, 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            glPushMatrix();
            
            glClearColor(0.0f,0.0f,0.1f,1.0f);
            
            glViewport(0, 0, width, height);

            textureLoader = new TextureLoader();

            // create our sound manager, and initialize it with 7 channels
            // 1 channel for sounds, 6 for effects - this should be enough
            // since we have a most 4 shots on screen at any one time, which leaves
            // us with 2 channels for explosions.
            soundManager = new SoundManager();
            soundManager.initialize(8);
            
        } catch (LWJGLException le) {
            System.out.println("Game exiting - exception in initialization:");
            le.printStackTrace();
            Game.gameRunning = false;
            return;
        }

        // get our sprites
        //message = getSprite("message.gif"); TODO

        // setup the initial game state
        startGame();
        
        this.mainMenu = new MainMenu(textureLoader,soundManager,this);
    }

    /**
     * Sets the display mode for full screen mode
     */
    private boolean setDisplayMode() 
    {
        try {
            // get modes
            DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(width, height, -1, -1, -1, -1, 60, 60);

            org.lwjgl.util.Display.setDisplayMode(dm, new String[]{
                        "width=" + width,
                        "height=" + height,
                        "freq=" + 60,
                        "bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
                    });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to enter fullscreen, continuing in windowed mode");
        }

        return false;
    }

    /**
     * Start a fresh game, this should clear out any old data and create a new
     * set.
     */
    private void startGame() 
    {
    }

    /**
     * Run the main game loop. This method keeps rendering the scene and
     * requesting that the callback update its screen.
     */
    private void gameLoop() 
    {
        while (Game.gameRunning) {
            
            // Clear the screen
            glClear(GL_COLOR_BUFFER_BIT);
            
            //Depricated
            glLoadIdentity();
            
            // GAME LOOP
            Display.sync(60);

            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long delta = getTime() - lastLoopTime;
            lastLoopTime = getTime();
            lastFpsTime += delta;
            fps++;

            // update our FPS counter if a second has passed
            if (lastFpsTime >= 1000) {
                Display.setTitle(WINDOW_TITLE + " (FPS: " + fps + ")");
                lastFpsTime = 0;
                fps = 0;
            }

            mainMenu.updateWithDelta(delta);
            mainMenu.render();
            
            // if escape has been pressed, stop the game            
            if ((Display.isCloseRequested() || mainMenu.getState() == ScreenState.SCREEN_FINISHED)) {
                sleep(500);         //Block for half a second to allow our sound to finish playing    
		Game.gameRunning = false;
            }                        
            
            //Catch any mouse event
            if(Mouse.next()) {                
                //Forward Mouse event to the main screen. We are only getting 
                //on mouse event a frame with this method.
                mainMenu.mouseEvent(Mouse.getEventButton(),
                                    Mouse.isButtonDown(Mouse.getEventButton()),
                                    Mouse.getX(),
                                    height - Mouse.getY()); //We need to invert the height as the window system and opengl differ.
            }
            
            if(Keyboard.next()) {
                mainMenu.keyEvent(Keyboard.getEventCharacter(), Keyboard.getEventKeyState());
            }
            
            // Finally mark our rendering as finished
            Display.update();
        }

        //Clean up our managers and tidy away the display
        soundManager.destroy();
        Display.destroy();
    }

    /**
     * @param direction
     * @return
     */
    private boolean hasInput(int direction) 
    {
        switch (direction) {
            case Keyboard.KEY_LEFT:
                return Keyboard.isKeyDown(Keyboard.KEY_LEFT)
                        || mouseX < 0;

            case Keyboard.KEY_RIGHT:
                return Keyboard.isKeyDown(Keyboard.KEY_RIGHT)
                        || mouseX > 0;

            case Keyboard.KEY_SPACE:
                return Keyboard.isKeyDown(Keyboard.KEY_SPACE)
                        || Mouse.isButtonDown(0);
        }
        return false;
    }

    /**
     * The entry point into the game. We'll simply create an instance of class
     * which will start the display and game loop.
     *
     * @param argv The arguments that are passed into our game
     */
    public static void main(String argv[]) 
    {
        isApplication = true;
        System.out.println("Use -fullscreen for fullscreen mode");
        new Game((argv.length > 0 && "-fullscreen".equalsIgnoreCase(argv[0]))).execute();
        System.exit(0);
    }

    /**
     *
     */
    public void execute() 
    {
        gameLoop();
    }

    /**
     * Create or get a sprite which displays the image that is pointed to in the
     * classpath by "ref"
     *
     * @param ref A reference to the image to load
     * @return A sprite that can be drawn onto the current graphics context.
     */
    public Sprite getSprite(String ref) 
    {
        return new Sprite(textureLoader, ref);
    }
}
