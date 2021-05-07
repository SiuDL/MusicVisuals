package C19401596;

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    // player width and height
    float pWidth = 100;
    float pHeight = 100;
    float halfPWH = 100/2;
    float halfPW = pWidth / 2;

    // player health variable and max health variable for mapping to health bar
    int health = 80;
    final int maxHealth = health;

    // alpha variable for fading effect
    int alpha = 0;
    int frame = 0;

    // frame animation and delay variables
    int attFrame = 0;
    int delay = 0;
    int attDelay = 0;

    // boolean values for player actions
    boolean jumped = false;
    boolean attacked = false;
    boolean faceL = false;
    boolean faceR = false;
    boolean attL = false;
    boolean attR = false;

    // method to animate player movement
    public void animator(PImage[] a){
        PImage[] anim = a;
        if(delay == 0){
            frame = (frame + 1) % anim.length;
        }
        delay = (delay + 1) % 3;
        game.image(anim[frame], x, y, pWidth, pHeight);
    }

    // method to load in a single idle image
    public void loadIdle(PImage[] a){
        PImage[] anim = a;
        game.image(anim[0], x, y, pWidth, pHeight);
    }

    // method to animate player attack animation
    public void attAnimator(PImage[] a){
        PImage[] anim = a;
        if(attDelay == 0){
            attFrame = (attFrame + 1) % anim.length;
        }
        attDelay = (attDelay + 1) % 4;
        game.image(anim[attFrame], x, y);
    }

    public void render(){

        game.textAlign(PApplet.CENTER);

        // if statement to change alpha value
        // makes text or shape to fade in or out
        if(alpha != 255){
            alpha = alpha + 1;
        }

        yC = getY() * 0.99f;
        xC = PApplet.map(health, 0, maxHealth, getX(), getX() + 100);

        if(health != 0){
            game.strokeWeight(2);
            game.stroke(255, 0, 0, alpha);
            game.line(getX(), yC, xC, yC);

                //  --  if statement to check movement animation  --  //
            if(!game.checkKey('D') && !game.checkKey('A') && !game.contact && attacked == false){
                loadIdle(game.playerJ);
            }else if(!game.checkKey('D') && !game.checkKey('A') && attacked == false && faceL == false){
                loadIdle(game.playerIR);
            }else if(!game.checkKey('D') && !game.checkKey('A') && attacked == false && faceR == false){
                loadIdle(game.playerIL);
            }else if (game.checkKey('D') && !game.checkKey('A') && attacked == false){
                animator(game.playerR);
                faceR = true;
                faceL = false;
            }else if (game.checkKey('A') && !game.checkKey('D') && attacked == false){
                animator(game.playerL);
                faceL = true;
                faceR = false;
            }else if (game.checkKey('D') && game.checkKey('A') && attacked == false){
                loadIdle(game.playerIR);
            }

            //  --  if statement to check attack animation  --  //
            if(attacked == true && game.checkKey('D') && attL == false){
                attAnimator(game.playerAR);
                attR = true;
                attL = false;
            }else if(attacked == true && game.checkKey('A') && attR == false){
                attAnimator(game.playerAL);
                attL = true;
                attR = false;
            }else if(attacked == true && faceL == false){
                attAnimator(game.playerAR);
            }else if(attacked == true && faceR == false){
                attAnimator(game.playerAL);
            }
        }else{
            game.noStroke();

            loadIdle(game.playerID);
            setVelX(0);
            setVelY(0);
        }
    }

    // method to control player player velocity
    public void playerVel(){
        x += velX;
        y += velY;
    }

    // method to check if player jumped
    public void jump(){
        if(jumped == false){
            setVelY(-70);
            jumped = true;
        }else{
            if(game.contact == true){
                jumped = false;  
            }
        }
    }

    // method to check if player attacked
    public void attack(){
        if(game.checkKey('J')){
            attacked = true;
        }else{
            attacked = false;
        }
    }

    public void update(){
        
        playerVel();
        attack();

        // checks for when key is pressed
        
        if(game.checkKey(' ')){
            jump();
        }
        if(game.checkKey('D') || attacked == true){
            setVelX(10);
        }
        if(game.checkKey('A') || attacked == true){
            setVelX(-10);
        }
        if(game.checkKey('D') && game.checkKey('A') || attacked == true){
            setVelX(0);
        }

        // checks for when key is released
        if(!game.checkKey(' ')){
            setVelY(getVelY() * getDecel());
        }
        if(!game.checkKey('D')){
            setVelX(getVelX() * getDecel());
        }
        if(!game.checkKey('A')){
            setVelX(getVelX() * getDecel());
        }
    }

    public float getPlayerW(){
        return halfPW;
    }
}
