import java.util.*; 
  
  
public class Coin { 
    int x,y, col, w, h; 
    private PlatformerMain parent; 
    private ArrayList<Platform> platforms = new ArrayList<Platform>(); 
      
    public Coin(PlatformerMain parent, ArrayList<Platform> platforms, int x, int y) { 
        this.platforms = platforms; 
        this.parent = parent; 
        this.col = 0xffd700; 
        this.w = 25; 
        this.h = 25; 
        this.x = x; 
        this.y = y; 
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
        parent.fill(255,215,0); 
        parent.rectMode(parent.CORNER); 
        parent.rect(x,y,w,h); 
        //Call the check for collision with player 
    } 
      
    void render() { 
        update(); 
    } 
      
      
} 
