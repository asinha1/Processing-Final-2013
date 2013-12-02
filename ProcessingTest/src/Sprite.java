import processing.core.*;

public class Sprite {
  private PApplet parent;
  private PImage sprite_strip, current_frame;
  private int originx, originy;
  
  public final int height, width, nframes;
  public float frame, speed, angle, xscale, yscale;
  public static final int LEFT = 0, CORNER = 1, CENTER = 2, RIGHT = 3, TOP = 4, BOTTOM = 5;
  
  Sprite(PApplet parent, String filename, int nframes, int originx, int originy)
  {
	this.parent = parent;
	this.nframes = nframes;
	this.originx = originx;
	this.originy = originy;
	xscale = yscale = 1;
	frame = 0;
	speed = 0.25f;
	angle = 0;
	sprite_strip = parent.loadImage(filename);
	this.height = sprite_strip.height;
	this.width = sprite_strip.width/nframes;
	current_frame = sprite_strip.get(0, 0, this.width, this.height);
  }
  
  Sprite(PApplet parent, String filename, int nframes, int originMode)
  {
    this(parent, filename, nframes, 0, 0);
    if (originMode == BOTTOM) {
      originx = this.width/2;
      originy = this.height;
    } else if (originMode == CENTER) {
      originx = this.width/2;
      originy = this.height/2;
    } else if (originMode == LEFT) {
      originx = 0;
      originy = this.height/2;
    } else if (originMode == RIGHT) {
      originx = this.width;
      originy = this.height/2;
    } else if (originMode == TOP) {
      originx = this.width/2;
      originy = 0;
    }
  }
  
  Sprite(PApplet parent, String filename, int nframes)
  {
    this(parent, filename, nframes, CENTER);
  }
  
  void update()
  {
    frame += speed;
    while (frame > nframes)
      frame -= nframes;
    current_frame = sprite_strip.get((parent.round(frame) % nframes) * this.width, 0, this.width, this.height);
  }
  
  void render(int x, int y)
  {
    parent.pushMatrix();
    parent.translate(x, y);
    parent.rotate(angle);
    parent.scale(xscale, yscale);
    parent.image(current_frame, -originx, -originy);
    parent.popMatrix();
}

}
