import processing.core.*; 
import java.util.*; 
  
public class PlatformerMain extends PApplet{ 
      
    public KeyReader reader; 
    private Player player1; 
    ArrayList<Platform> platforms = new ArrayList<Platform>(); 
    ArrayList<Coin> coins = new ArrayList<Coin>(); 
      
    /* Uncomment Later for background */
    //public PImage bg; 
      
    public void setup() { 
        noCursor(); 
        smooth(); 
        size(800,800); 
          
        /* Initialize key reader */
        reader = KeyReader.getInstance(this); 
          
        /* Initialize Background */
           
        //bg = loadImage("concrete.jpg"); 
        //size(bg.width, bg.height); 
          
        /* Initialize player */
        player1 = new Player(this,platforms,coins, 400, 400,50,50, 400, 400); 
          
        /* Create Boundary Platforms */
        Platform floor = new Platform(this,0,750,800,50); 
        platforms.add(floor); 
        Platform ceil = new Platform(this,0,0,800,20); 
        platforms.add(ceil); 
        Platform leftWall = new Platform(this,0,0,20,800); 
        platforms.add(leftWall); 
        Platform rightWall = new Platform(this,780,0,20,800); 
        platforms.add(rightWall); 
          
        /* Create other Platforms (for maze) */
        Platform wall1 = new Platform(this,20,85,370,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,370,85,20,600); 
        platforms.add(wall1); 
        wall1 = new Platform(this,460,85,20,330); 
        platforms.add(wall1); 
        wall1 = new Platform(this,460,480,370,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,460,395,370,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,460,85,250,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,690,85,20,250); 
        platforms.add(wall1); 
        wall1 = new Platform(this,550,315,160,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,550,170,20,165); 
        platforms.add(wall1); 
        wall1 = new Platform(this,550,170,70,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,460,480,20,180); 
        platforms.add(wall1); 
        wall1 = new Platform(this,700,480,20,270); 
        platforms.add(wall1); 
        wall1 = new Platform(this,460,660,170,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,100,665,290,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,100,185,20,500); 
        platforms.add(wall1); 
        wall1 = new Platform(this,100,185,200,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,280,185,20,300); 
        platforms.add(wall1); 
        wall1 = new Platform(this,280,555,20,130); 
        platforms.add(wall1); 
        wall1 = new Platform(this,280,555,110,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,180,465,120,20); 
        platforms.add(wall1); 
        wall1 = new Platform(this,180,295,20,170); 
        platforms.add(wall1); 
          
        /* Coins */
          
        Coin temp = new Coin(this,platforms, 40, 30); 
        coins.add(temp); 
        temp = new Coin(this,platforms, 600, 240); 
        coins.add(temp); 
        temp = new Coin(this,platforms, 600, 550); 
        coins.add(temp); 
        temp = new Coin(this,platforms, 230, 375); 
        coins.add(temp); 
      
    } 
      
    public void draw() { 
        background(255); 
        fill(100); 
        for(int i = 0; i < platforms.size(); i++) { 
            platforms.get(i).render(); 
        } 
          
        for (int i = 0; i < coins.size(); i++) { 
            coins.get(i).render(); 
        } 
      
        player1.render(); 
          
    } 
      
    public void keyPressed() 
    { 
      reader.onKeyPress(); 
    } 
      
    public void keyReleased() 
    { 
      reader.onKeyRelease(); 
    } 
  
} 
