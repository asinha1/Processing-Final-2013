public class Platform { 
    int x,y,w,h; 
    private PlatformerMain parent; 
      
    public Platform(PlatformerMain parent, int x, int y,int w, int h) { 
        this.parent = parent; 
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
