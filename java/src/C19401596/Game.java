package C19401596;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet{

    boolean[] keys = new boolean[1024];

    // defining array sizes for PImage arrays
    int IDLE = 1;
    int SIZE = 3;

    // PImage arrays for storing sprite .png's
    PImage[] playerI = new PImage[IDLE];
    PImage[] playerJ = new PImage[IDLE];
    PImage[] playerL = new PImage[SIZE];
    PImage[] playerR = new PImage[SIZE];
    PImage[] enemyI = new PImage[IDLE];
    PImage[] enemyL = new PImage[SIZE];
    PImage[] enemyR = new PImage[SIZE];

    Player p;
    Enemy e;
    WorldObj obj;
    ArrayList<GameObject> gameObj = new ArrayList<GameObject>();

    Boolean contact;

    public void settings(){
        //size(1600, 900);
        fullScreen();
    }

    public void setup(){
        obj = new WorldObj(this);
        p = new Player(this, (width * 0.1f), obj.getFloor());
        e = new Enemy(this, (width * 0.8f), obj.getFloor());
        
        // for loop to load all images into arrays at setup
        for(int i = 0; i < SIZE; i++){
            if(i == 0){
                playerI[i] = loadImage("player/animI/spr"+i+".png");
                playerJ[i] = loadImage("player/animJ/spr"+i+".png");
                enemyI[i] = loadImage("enemy/animI/spr"+i+".png");
            }
            playerL[i] = loadImage("player/animL/spr"+i+".png");
            playerR[i] = loadImage("player/animR/spr"+i+".png");
            enemyL[i] = loadImage("enemy/animL/spr"+i+".png");
            enemyR[i] = loadImage("enemy/animR/spr"+i+".png");
        }

        gameObj.add(p);
        gameObj.add(e);
        gameObj.add(obj);

        // checking player and enemy co-ordinates
        //System.out.println(p.getX()+" "+e.getX()+"\n"+p.getY()+" "+e.getY());
    }

    public void draw(){
        background(80,80,80);

        gravity();
        collisionDetect();

        for(int i = gameObj.size() - 1; i >= 0; i--){
            GameObject goB = gameObj.get(i);
            goB.render();
            goB.update();
        }
        // checking where the enemy's head xy co-ordinates
        //fill(255,0,0);
        //line(e.getX(), e.getY()+20, e.getX() + 100, e.getY()+20);
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
        p.setVelY(p.getVelY() + p.getGrav() * p.getDecel());

        // checks for player coming into contact with the floor
        if(p.getY() >= obj.getFloor()){
            contact = true;
        }else{
            contact = false;
            keys[' '] = false;
        }

        // gravity affecting velX
        if(contact == false){// start outer if
            if(checkKey('D') == false){
                p.setVelX(p.getVelX() - p.getDirect());
            }
            if(checkKey('A') == false){
                p.setVelX(p.getVelX() + p.getDirect());
            }
        }
    }
    
    // method to detect collision between world objects and the player object
    public void collisionDetect(){

        //  --  world object collsion   --  //
        // stops player from clipping through the floor
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

        // stops enemy from clipping through the floor
        if(e.getY() >= obj.getFloor()){
            e.setY(obj.getFloor());
        }

        // stops enemy from clipping through the left wall
        if(e.getX() <= obj.getLeftWall()){
            e.setX(obj.getLeftWall());
        }

        // stops enemy from clipping through the right wall
        if(e.getX() >= obj.getRightWall()){
            e.setX(obj.getRightWall());
        }

        // stops enemy from clipping through the roof
        if(e.getY() <= obj.getRoof()){
            e.setY(obj.getRoof());
        }

        //  -- Player vs Enemy collision detection  --  //
        if((p.getX() >= (e.getX() - e.getEnemy())) && (p.getX() <= (e.getX() + e.getEnemy())) && (p.getY() >= e.getY())){
            //System.out.println("detect");
            if(p.getX() > e.getX() - e.getEnemy()){
                p.setVelX(-2);
            }
            if(p.getX() > e.getX()){
                p.setVelX(2);
            }
        }
    }

    public void keyPressed() {
        keys[keyCode] = true;
    }

    public void keyReleased() {
        keys[keyCode] = false;
    }
}
