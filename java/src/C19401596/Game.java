package C19401596;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet{

    boolean[] keys = new boolean[1024];

    int SIZE = 3;
    PImage[] animI = new PImage[1];
    PImage[] animL = new PImage[SIZE];
    PImage[] animR = new PImage[SIZE];

    Player p;
    WorldObj obj;
    ArrayList<GameObject> gameObj = new ArrayList<GameObject>();

    Boolean contact;

    public void settings(){
        size(1600, 900);
    }

    public void setup(){
        obj = new WorldObj(this);
        p = new Player(this, width/2, obj.getFloor());
        
        for(int i = 0; i < SIZE; i++){
            if(i == 0){
                animI[i] = loadImage("anim/spr"+i+".png");
            }
            animL[i] = loadImage("animL/spr"+i+".png");
            animR[i] = loadImage("animR/spr"+i+".png");
        }

        gameObj.add(p);
        gameObj.add(obj);
    }

    public void draw(){
        background(80,80,80);

        for(int i = gameObj.size() - 1; i >= 0; i--){
            GameObject goB = gameObj.get(i);
            goB.render();
            goB.update();
        }

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

        // constant downforce applied to the player
        // gravity affecting velY
        p.setVelY(p.getVelY() + p.getGrav());

        // checks for player coming into contact with the floor
        if(p.getY() >= obj.getFloor()){
            contact = true;
        }else{
            contact = false;
            keys[UP] = false;
        }

        // gravity affecting velX
        if(contact == false){// start outer if
            if(checkKey(RIGHT) == false){
                p.setVelX(p.getVelX() - p.getDirect() * p.getDecel());
            }
            if(checkKey(LEFT) == false){
                p.setVelX(p.getVelX() + p.getDirect() * p.getDecel());
            }
        }
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
