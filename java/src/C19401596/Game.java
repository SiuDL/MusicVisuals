package C19401596;

// import java.lang.*;
import processing.core.PApplet;

public class Game extends PApplet{

    boolean[] keys = new boolean[1024];

    Player p;
    WorldObj objF;

    public void settings(){
        size(1200, 800);
    }

    public void setup(){
        p = new Player(this, width/2, height/2);
        objF = new WorldObj(this);
    }

    public void draw(){
        background(0);
        objF.render();
    }

    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }

    public void mousePressed() {
    }

    public void keyPressed() {
        keys[keyCode] = true;
    }

    public void keyReleased() {
        keys[keyCode] = false;
    }
}
