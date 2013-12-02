package biar.KeyReaderExample;

import java.util.HashMap;
import processing.core.*;
import java.awt.event.KeyEvent;

public class KeyReader {
	private static KeyReader instance;

	private boolean[] keys;
	private HashMap<String, Integer> keyMap;
	private static boolean DEBUG;
	private static PApplet parent;
  
  protected KeyReader(PApplet parent, boolean DEBUGMODE) {
	  KeyReader.parent = parent;
	  KeyReader.DEBUG = DEBUGMODE;
	  
	  keys = new boolean[200];
	  keyMap = new HashMap<String, Integer>();
	  for (int i = 0; i < keys.length; i++) {
		  String ithKey = KeyEvent.getKeyText(i).toUpperCase();
		  try {
		    if (!(ithKey.substring(0,7).equals("UNKNOWN"))) {
		        addKeyToList(ithKey, i);
		    }
		  } catch(StringIndexOutOfBoundsException e) {
		        addKeyToList(ithKey, i);
		  }
	  }
  }
  
  protected KeyReader(PApplet parent) {
	  this(parent, false);
  }
  
  public static KeyReader getInstance(PApplet parent) {
	  if (instance == null) {
		  instance = new KeyReader(parent);
		  return instance;
	  }
	  else return instance;
  }
  
  public static KeyReader getInstance(PApplet parent, boolean DEBUG) {
	  if (instance == null) {
		  instance = new KeyReader(parent, DEBUG);
		  return instance;
	  }
	  else return instance;
  }
  
  private void addKeyToList(String _key, Integer _keyCode) {
    keyMap.put(_key, _keyCode);
    if (DEBUG) System.out.println(_key);
  }
  
  public boolean check(String k)
  {
    Integer code = keyMap.get(k.toUpperCase());
    return code != null && keys[code];
  }
  
  boolean check(int _keyCode) {
	  return keys[_keyCode];
  }
  
  public void onKeyPress() {
    keys[parent.keyCode] = true;
  }
  
  public void onKeyRelease() {
    keys[parent.keyCode] = false;
  }
}