package C19401596;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet{

    // boolean array for checking keys pressed
    boolean[] keys = new boolean[1024];

    // defining array sizes for PImage arrays
    int P_IDLE = 1;
    int P_SIZE = 4;
    int E_IDLE = 5;
    int ED_IDLE = 1;
    int E_ATT = 10;

    //  --  PImage arrays for storing sprite .png's  --  //
    // Player PImage
    PImage[] playerIR = new PImage[P_IDLE];
    PImage[] playerIL = new PImage[P_IDLE];
    PImage[] playerJ = new PImage[P_IDLE];
    PImage[] playerID = new PImage[P_IDLE];
    PImage[] playerL = new PImage[P_SIZE];
    PImage[] playerR = new PImage[P_SIZE];
    PImage[] playerAR = new PImage[P_SIZE];
    PImage[] playerAL = new PImage[P_SIZE];

    // Enemy PImage
    PImage[] enemyID = new PImage[ED_IDLE];
    PImage[] enemyIL = new PImage[E_IDLE];
    PImage[] enemyIR = new PImage[E_IDLE];
    PImage[] enemyL = new PImage[P_SIZE];
    PImage[] enemyR = new PImage[P_SIZE];
    PImage[] enemyAL = new PImage[E_ATT];
    PImage[] enemyAR = new PImage[E_ATT];

    Minim minim; // Connect to minim
    AudioPlayer menu, battle, triumph, aftermath;

    // Game objects
    Player p;
    Enemy e;
    WorldObj obj;

    // RainVisual object to render in rainfall
    RainVisual[] rv = new RainVisual[150];

    // ArrayList to store all game objects
    ArrayList<GameObject> gameObj = new ArrayList<GameObject>();

    // variable to keep track of gamestate for game menu
    int gameState = 0;

    // alpha variables to fade elements in or out
    int alpha = 0;
    int bgAlpha = 0;

    // boolean variable to check if player is making contact with the floor
    Boolean contact;

    public void settings(){
        //size(1600, 900);
        fullScreen();
    }

    public void setup(){
        // creating game objects
        obj = new WorldObj(this);
        p = new Player(this, (width * 0.1f), obj.getFloor());
        e = new Enemy(this, (width * 0.9f), obj.getFloor());

        // creating new minim object
        minim = new Minim(this);

        // using the minim library to load in .mp3 files
        menu = minim.loadFile("battle.mp3", width);
        battle = minim.loadFile("battleStart.mp3", width);
        triumph = minim.loadFile("triumph.mp3", width);
        aftermath = minim.loadFile("aftermath.mp3", width);

        // adding player and enemy object into gameObj array list
        gameObj.add(p);
        gameObj.add(e);

        // for each loop to go through each game object
        for(GameObject ob : gameObj){
            // if instance of player object, load in all player related animation folders
            if(ob instanceof Player){
                // for loop to load all images into arrays at setup
                for(int i = 0; i < P_SIZE; i++){
                    if(i == 0){
                        playerIR[i] = loadImage("player/animIR/spr"+i+".png");
                        playerIL[i] = loadImage("player/animIL/spr"+i+".png");
                        playerJ[i] = loadImage("player/animJ/spr"+i+".png");
                        playerID[i] = loadImage("player/animD/spr"+i+".png");
                        
                    }
                    playerL[i] = loadImage("player/animL/spr"+i+".png");
                    playerR[i] = loadImage("player/animR/spr"+i+".png");
                    playerAR[i] = loadImage("player/animAR/spr"+i+".png");
                    playerAL[i] = loadImage("player/animAL/spr"+i+".png");
                }
            }
            // if instance of enemy object, load in all enemy related animation folders
            if(ob instanceof Enemy){
                // for loop to load all images into arrays at setup
                for(int i = 0; i < E_ATT; i++){
                    if(i == 0){
                        enemyID[i] = loadImage("enemy/animD/spr"+i+".png");
                    }
                    if(i < 4){
                        enemyL[i] = loadImage("enemy/animL/spr"+i+".png");
                        enemyR[i] = loadImage("enemy/animR/spr"+i+".png");
                    }
                    if(i < 5){
                        enemyIL[i] = loadImage("enemy/animIL/spr"+i+".png");
                        enemyIR[i] = loadImage("enemy/animIR/spr"+i+".png");
                    }
                    enemyAL[i] = loadImage("enemy/animAL/spr"+i+".png");
                    enemyAR[i] = loadImage("enemy/animAR/spr"+i+".png");
                }
            }
        }

        // add obj into game object array list
        gameObj.add(obj);

        // for loop to go through RainVisual object to load in all individual rain elements 
        for(int i = 0; i < rv.length; i++){
            rv[i] = new RainVisual(this);
            gameObj.add(rv[i]);
        }

        // checking player and enemy co-ordinates
        //System.out.println(p.getX()+" "+e.getX()+"\n"+p.getY()+" "+e.getY());
    }

    public void draw(){
        // game state 0: main menu
        if(gameState == 0){
            menu.play();
            bgAlpha = 0;
            background(0);

            // if statement to check if alpha is not equal to 225
            // increase alpha value (fading text element)
            if(alpha != 255){
                alpha++;
            }

            fill(255,255,255, alpha);
            textAlign(CENTER);
            textSize(50);
            text("Defeat The Nameless Usurper", width/2, height/2);
            textSize(20);
            text("Press 0 to skip", width / 2, height * 0.6f);

            if(checkKey('0') == true){
                menu.pause();
                menu.rewind();
                alpha = 0;
                gameState = 1;
            }

        }else if(gameState == 1){// game state 1: in game
            battle.play();

            noStroke();
            background(50,50,50);

            gravity();
            collisionDetect();
            hitDetect();

            for(int i = gameObj.size() - 1; i >= 0; i--){
                GameObject goB = gameObj.get(i);
                noStroke();
                goB.render();
                goB.update();
            }

            if(p.health == 0){
                battle.pause();
                battle.rewind();
                gameState = 2;
            }else if(e.health == 0){
                battle.pause();
                battle.rewind();
                gameState = 3;
            }

        }else if(gameState == 2){// game state 2: death screen

            aftermath.play();

            if(bgAlpha != 255){
                bgAlpha++;
            }

            background(0);
            fill(255,0,0, bgAlpha);
            textAlign(CENTER);
            textSize(50);
            text("You Died", width/2, height/2);

            fill(255,255,255, bgAlpha);
            textSize(20);
            text("Will you rise once more?\n\nPress 0 to continue", width / 2, height * 0.6f);

            if(checkKey('0') == true){
                aftermath.pause();
                aftermath.rewind();
                keys['0'] = false;
                alpha = 0;
                reset();
                gameState = 0;
            }
        }else if(gameState == 3){// game state 3: victory state
            triumph.play();

            if(bgAlpha != 255){
                bgAlpha++;
            }

            background(0);
            fill(255,255,255, bgAlpha);
            textAlign(CENTER);
            textSize(50);
            text("Victory", width/2, height/2);

            fill(255,255,255, bgAlpha);
            textSize(20);
            text("Ending E - The Un[E]nding Fight\n\nPress 0 to continue?", width / 2, height * 0.6f);

            if(checkKey('0') == true){
                triumph.pause();
                triumph.rewind();
                keys['0'] = false;
                alpha = 0;
                reset();
                gameState = 0;
            }
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

    public void reset(){
        p.health = p.maxHealth;
        e.health = e.maxHealth;

        p.setX(width * 0.1f);
        p.setY(obj.getFloor());

        e.setX(width * 0.9f);
        e.setY(obj.getFloor());

        p.alpha = 0;
        e.alpha = 0;
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

    public void hitDetect(){
        // player attack hit box
        p.hitxR = p.getX() + 100;
        p.hitxL = p.getX() + 10;
        p.hitY = p.getY() + 20;

        // enemy attack hit box
        e.hitxR = e.getX() + 100;
        e.hitxL = e.getX() + 10;
        e.hitY = e.getY() + 20;

        /*
        if(e.attacked == true && e.faceL == true){            
            fill(0, 255, 0);
            rect(e.getX() + 50, e.getY() + 20, 50, 80);
        }else if(e.attacked == true && e.faceR == true){
            fill(0, 255, 0);
            rect(e.getX() + 40, e.getY() + 20, 50, 80);
        }
        */

        //  --  Player vs Enemy hit detection   --  //
        // checks for when enemy gets hit
        if(p.attacked == true){ 
            // if player facing right and player attack frame is at 2
            if(p.attFrame == 2 && p.faceR == true){
                //  -- showing right hit box --  //
                //fill(255, 0, 0);
                //rect(p.hitxR, p.hitY, 40, 80);

                if(e.health > 0){
                    if(e.attacked == false && (p.hitxR >= e.getX() && p.hitxR <= (e.getX() + e.getEnemy()) && p.hitY >= e.getY())){
                        e.health -= 1;
                    }else if(e.faceL == true && e.attacked == true && (p.hitxR >= e.getX() && p.hitxR <= (e.getX() + e.getEnemy() + 50) && p.hitY >= e.getY())){
                        e.health -= 1;
                    }   
                }
            }
            // if player facing left and player attack frame is at 2
            if(p.attFrame == 2 && p.faceL == true){
                //  -- showing hit left box --  //
                //fill(255, 0, 0);
                //rect(p.hitxL, p.hitY, 40, 80);

                if(e.health > 0){
                    if(e.attacked == false && (p.hitxL >= e.getX() && p.hitxL <= (e.getX() + e.getEnemy()) && p.hitY >= e.getY())){
                        e.health -= 1;
                    }else if(e.faceR == true && e.attacked == true && (p.hitxL >= e.getX() + 50 && p.hitxL <= (e.getX() + e.getEnemy() + 30) && p.hitY >= e.getY())){
                        e.health -= 1;
                    }
                }
            }
        }

        // checks for when player gets hit
        if(e.attacked == true){
            // if enemy is facing left
            if((e.attFrame == 4 || e.attFrame == 8 || e.attFrame == 11) && e.faceL == true){
                //  -- showing hit left box --  //
                //fill(255, 0, 0);
                //rect(e.hitxL, e.hitY, 40, 80);

                if(p.health > 0){
                    if(p.attacked == false && (e.hitxL >= p.getX() && e.hitxL <= (p.getX() + p.getPlayerW()) && e.hitY >= p.getY())){
                        p.health -= 1;
                    }else if(p.attacked == true && (e.hitxL >= p.getX() && e.hitxL <= (p.getX() + p.getPlayerW()) && e.hitY >= p.getY())){
                        p.health -= 1;
                    }
                }
            }
            // if enemy is facing right
            if((e.attFrame == 4 || e.attFrame == 8 || e.attFrame == 11) && e.faceR == true){
                //  -- showing hit right box --  //
                //fill(255, 0, 0);
                //rect(e.hitxR, e.hitY, 40, 80);

                if(p.health > 0){
                    if(p.attacked == false && (e.hitxR >= p.getX() && e.hitxR <= (p.getX() + p.getPlayerW()) && e.hitY >= p.getY())){
                        p.health -= 1;
                    }else if(p.attacked == true && (e.hitxR >= p.getX() && e.hitxR <= (p.getX() + p.getPlayerW()) && e.hitY >= p.getY())){
                        p.health -= 1;
                    }
                }
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
        }
    }

    public void keyPressed() {
        keys[keyCode] = true;
    }

    public void keyReleased() {
        keys[keyCode] = false;
    }
}
