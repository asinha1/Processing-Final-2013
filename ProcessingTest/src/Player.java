import java.util.ArrayList; 
  
import processing.core.*; 
  
public class Player 
{ 
    public PVector origin; 
    int x,y,w,h, startx,starty; 
      
    //Sprite spr; 
    //Flashlight light; 
    private PlatformerMain parent; 
    private ArrayList<Platform> platforms = new ArrayList<Platform>(); 
    private ArrayList<Coin> coins = new ArrayList<Coin>(); 
    private ArrayList<Coin> coins_old = new ArrayList<Coin>(); 
    public Player(PlatformerMain parent,ArrayList<Platform> platforms,ArrayList<Coin> coins, int x, int y,int w, int h, int startx, int starty) 
    { 
        this.parent = parent; 
        this.x = x; 
        this.y = y; 
        this.w = w; 
        this.h = h; 
        this.platforms = platforms; 
        this.startx = startx; 
        this.starty = starty; 
        this.coins = coins; 
        //spr = new Sprite(parent, "policeman_strip.png", 4); 
        //light = new Flashlight(parent); 
          
        /* Ask how to implement Sprite (non-animated) 
         *  
         * Figure out how to do collision with platforms (possibly use blocks?) 
         *  & Need to implement into Player or not? 
         *  
         *  
         */
    } 
      
    /* Collision Functions */
      
    /* Check if the player is colliding with a block above it */
    boolean collisionUp(ArrayList<Platform> platforms,Player p1) { 
        for (int i = 0; i < platforms.size(); i++) { 
            Platform temp = platforms.get(i); 
            if(((temp.x < p1.x) && (temp.x + temp.w > p1.x)) && 
                    (temp.y + temp.h > p1.y) && (p1.y + p1.h > temp.y)) 
                return true; 
        } 
        return false; 
    } 
    /* Check if the player is colliding with a block below it */
    boolean collisionDown(ArrayList<Platform> platforms,Player p1) { 
        for (int i = 0; i < platforms.size(); i++) { 
            Platform temp = platforms.get(i); 
            if((p1.x > temp.x && p1.x < (temp.x + temp.w)) && 
                    ((p1.y + p1.h) > temp.y) && (p1.y < temp.y + temp.h)) 
                return true; 
        } 
        return false; 
    } 
    /* Check if the player is colliding with a block to the right of it */
    boolean collisionRight(ArrayList<Platform> platforms,Player p1) { 
        for (int i = 0; i < platforms.size(); i++) { 
            Platform temp = platforms.get(i); 
            if((p1.y > temp.y && p1.y < (temp.y + temp.h)) &&  
                    ((p1.x + p1.w) > temp.x) && ( p1.x < temp.x + temp.w)) 
                return true; 
        } 
        return false; 
          
    } 
    /* Check if the player is colliding with a block to the left of it */
    boolean collisionLeft(ArrayList<Platform> platforms,Player p1) { 
        for (int i = 0; i < platforms.size(); i++) { 
            Platform temp = platforms.get(i); 
            if((p1.y > temp.y && p1.y < (temp.y + temp.h)) &&  
                    (p1.x < (temp.x + temp.w)) && (p1.x + p1.w > temp.x)) 
                return true; 
        } 
        return false; 
    } 
      
    boolean collisionCoin(ArrayList<Coin> coins, Player p1) { 
        for(int i = 0; i < coins.size(); i++) { 
            Coin coin = coins.get(i); 
            if ((p1.x < coin.x + 10 && p1.x > coin.x - 10) && (p1.y < coin.y + 10 && p1.y > coin.y - 10)) 
                return true; 
        } 
        return false; 
    } 
      
    int getCoin(ArrayList<Coin> coins, Player p1) { 
        for(int i = 0; i < coins.size(); i++) { 
            Coin coin = coins.get(i); 
            if ((p1.x < coin.x + 10 && p1.x > coin.x - 10) && (p1.y < coin.y + 10 && p1.y > coin.y - 10)) 
                return i; 
        } 
        return -1; 
    } 
      
    void update() 
    { 
        int index = getCoin(coins,this); 
        if(collisionCoin(coins,this) && index != -1) { 
            coins_old.add(coins.get(index)); 
            coins.remove(index); 
        } 
          
        if(collisionLeft(platforms,this) || collisionRight(platforms,this) || collisionUp(platforms,this) || collisionDown(platforms,this)) { 
            x = startx; 
            y = starty; 
            parent.rect(x, y, w, h); 
              
            for(int i=0; i < coins_old.size();i++) { 
                coins.add(coins_old.get(i)); 
                coins_old.remove(i); 
            } 
        } 
        KeyReader r = parent.reader; 
        if(r.check("A") && !collisionLeft(platforms,this)) { 
            x -= 3; 
        } 
        else if(r.check("D") && !collisionRight(platforms,this)) { 
            x+=3; 
        } 
        else if(r.check("W") && !collisionUp(platforms,this)) { 
            y -= 3; 
        } 
        else if(r.check("S") && !collisionDown(platforms,this)) { 
            y +=3; 
        } 
        parent.stroke(0x000000); 
        parent.fill(0x000000); 
        parent.rectMode(parent.CORNER); 
        parent.rect(x,y,w,h); 
    } 
    
    void render() 
    { 
        update(); 
        //light.render(origin); 
        //spr.render(origin); 
    } 
      
    boolean inBoundsx() { 
        return ((x > 0) && (x < parent.width)); 
    } 
      
    boolean inBoundsy() { 
        return ((y > 0) && (y < parent.width)); 
    } 
      
      
} 
