package C19401596;

import processing.core.PApplet;

public class Game extends PApplet{

    boolean[] keys = new boolean[1024];

    Player p;
    WorldObj obj;

    Boolean contact;

    public void settings(){
        size(1600, 900);
    }

    public void setup(){
        obj = new WorldObj(this);
        p = new Player(this, width/2, obj.getFloor());
    }

    public void draw(){
        background(80,80,80);
        p.render();
        p.playerVel();
        p.update();
        obj.render();

        gravity();
        collisionDetect();
    }
    
    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }
    
    // method to simulate a gravitational downforce
    public void gravity(){

        // checks for player coming into contact with the floor
        if(p.getY() >= obj.getFloor()){
            contact = true;
        }else{
            contact = false;
        }

        // gravity affecting velY
        p.setVelY(p.getVelY() + p.gravPull);

        // gravity affecting velX
        if(contact == false){// start outer if
            if(checkKey(RIGHT)){
                // check right velX
                p.setVelX(p.getVelX() * 0.9f); 
            }
            if(checkKey(LEFT)){
                // check left velX
                p.setVelX(p.getVelX() * 0.9f);
            }
        }// end outer if
    }
    
    // method to detect collision between world objects and the player object
    public void collisionDetect(){

        // stops player from clipping through the ground
        if(p.getY() >= obj.getFloor()){
            p.setY(obj.getFloor());
        }

        // stops player from clipping through the left wall
        if(p.getX() <= obj.getLeftWall()){
            p.setX(obj.getLeftWall());
        }

        // stops player from clipping through the right wall
        if(p.getX() >= obj.getRightWall()){
            p.setX(obj.getRightWall());
        }

        // stops player from clipping through the roof
        if(p.getY() <= obj.getRoof()){
            p.setY(obj.getRoof());
        }
    }

    public void keyPressed() {
        keys[keyCode] = true;
    }

    public void keyReleased() {
        keys[keyCode] = false;
    }
}
