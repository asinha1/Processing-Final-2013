import java.util.*; 

import processing.core.PApplet;
  
  
public class Coin { 
    int x,y, col, w, h; 
    private PApplet parent; 
    private ArrayList<Platform> platforms = new ArrayList<Platform>(); 
    private Sprite mySprite;  
    
    public Coin(PApplet parent2, ArrayList<Platform> platforms, int x, int y) { 
        this.platforms = platforms; 
        this.parent = parent2; 
        this.col = 0xffd700; 
        this.w = 25; 
        this.h = 25; 
        this.x = x; 
        this.y = y;  
        mySprite = new Sprite(parent, "spinning_coin_gold.png", 8);
    }
    
    public void renderSprite()
    {
    	mySprite.update();
    	mySprite.render(x, y);
    }
      
    boolean wallCollision(ArrayList<Platform> platforms, Coin c) { 
          
        for(int i =0; i < platforms.size(); i++) { 
            Platform temp = platforms.get(i); 
              
            if(c.x > temp.x + temp.w || temp.x > c.x + c.w || 
                       c.y > temp.y + temp.h || temp.y > c.y + c.h) 
                return true; 
        } 
          
        return false; 
    } 
      
    void update() { 
        //parent.stroke(col); 
        /*parent.fill(255,215,0); 
        parent.rectMode(parent.CORNER); 
        parent.rect(x,y,w,h); */
    	
    	renderSprite();
        //Call the check for collision with player 
    } 
      
    void render() { 
        update(); 
    } 
      
      
} 
