import processing.core.PApplet;

public class Platform { 
    int x,y,w,h; 
    private PApplet parent; 
      
    public Platform(PApplet parent2, int x, int y,int w, int h) { 
        this.parent = parent2; 
        this.x = x; 
        this.y = y; 
        this.w = w; 
        this.h = h; 
    } 
      
    void render() { 
        parent.rectMode(parent.CORNER); 
        parent.rect(x,y,w,h); 
    } 
} 
