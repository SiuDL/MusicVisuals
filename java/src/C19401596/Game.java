package C19401596;

// import java.lang.*;
import processing.core.PApplet;

public class Game extends PApplet{

    boolean[] keys = new boolean[1024];

    Player p;
    WorldObj obj;

    Boolean contact = true;

    public void settings(){
        size(1400, 900);
    }

    public void setup(){
        p = new Player(this, width/2, height/2);
        obj = new WorldObj(this);
    }

    public void draw(){
        background(80,80,80);
        p.render();
        p.playerVel();
        p.update();
        obj.render();

        collisionDetect();
    }
    
    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }

    public void gravity(){
        System.out.println("Gravity is in effect");
        p.setVelY(5);
    }

    public void collisionDetect(){

        // stops player from falling through the ground
        if(p.getY()>= obj.getFloor()){
            p.setY(obj.getFloor());
        }

        if(p.getX() <= obj.getLeftWall()){
            p.setX(obj.getLeftWall());
        }

        if(p.getX() >= obj.getRightWall()){
            p.setX(obj.getRightWall());
        }

        if(p.getY() <= obj.getRoof()){
            p.setY(obj.getRoof());
        }
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
