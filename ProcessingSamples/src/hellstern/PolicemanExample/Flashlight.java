package hellstern.PolicemanExample;

import processing.core.*;

public class Flashlight
{
  private PImage totalMask, bg_tmp;
  private Sprite lightMask;
  private static Main parent;
  
  Flashlight(Main parent)
  {
	  if (this.parent == null) this.parent = parent;
	  lightMask = new Sprite(this.parent, "light.jpg", 1, Sprite.LEFT);
	  lightMask.speed = 0;
  }
  
  void render(PVector origin)
  {
	  bg_tmp = parent.bg.get();
      parent.background(0);
    
      lightMask.xscale = parent.dist(origin.x, origin.y, parent.mouseX, parent.mouseY)/lightMask.height;
      if (lightMask.xscale < 0.75)
        lightMask.xscale = 0.75f;
      lightMask.angle = parent.atan2(parent.mouseY - origin.y, parent.mouseX - origin.x);
      lightMask.render(origin);
    
      totalMask = parent.get();
      bg_tmp.mask(totalMask);
      parent.background(0);
      parent.image(bg_tmp, 0, 0);
  }
}