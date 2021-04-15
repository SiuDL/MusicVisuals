package C19401596;

// import java.lang.*;
import processing.core.PApplet;

public class Game extends PApplet{

    boolean[] keys = new boolean[1024];

    Player p;
    WorldObj obj;

    public void settings(){
        size(1200, 800);
    }

    public void setup(){
        p = new Player(this, width/2, height/2);
        obj = new WorldObj(this);
        System.out.println("Floor Height: " + obj.getFloor());
    }

    public void draw(){
        background(115,115,115);
        obj.render();
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
