package C19401596;

import processing.core.PImage;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    float pWidth = 100;
    float pHeight = 100;
    float halfPWH = 100/2;
    float halfPW = pWidth / 2;

    boolean jumped = false;
    boolean faceL = false;
    boolean faceR = false;
    boolean attL = false;
    boolean attR = false;

    public void animator(PImage[] a){
        PImage[] anim = a;
        if(delay == 0){
            frame = (frame + 1) % anim.length;
        }
        delay = (delay + 1) % 3;
        game.image(anim[frame], x, y, pWidth, pHeight);
    }

    public void loadIdle(PImage[] a){
        PImage[] anim = a;
        game.image(anim[0], x, y, pWidth, pHeight);
    }

    public int attAnimator(PImage[] a){
        PImage[] anim = a;
        if(delay == 0){
            frame = (frame + 1) % anim.length;
        }
        delay = (delay + 1) % 3;
        game.image(anim[frame], x, y);
        return frame;
    }

    public void render(){

        if(!game.checkKey('D') && !game.checkKey('A') && !game.contact){
            loadIdle(game.playerJ);
        }else if(!game.checkKey('D') && !game.checkKey('A') && !game.checkKey('J') && faceL == false){
            loadIdle(game.playerIR);
        }else if(!game.checkKey('D') && !game.checkKey('A') && !game.checkKey('J') && faceR == false){
            loadIdle(game.playerIL);
        }else if (game.checkKey('D') && !game.checkKey('A') && !game.checkKey('J')){
            animator(game.playerR);
            faceR = true;
            faceL = false;
        }else if (game.checkKey('A') && !game.checkKey('D') && !game.checkKey('J')){
            animator(game.playerL);
            faceL = true;
            faceR = false;
        }else if (game.checkKey('D') && game.checkKey('A')){
            loadIdle(game.playerIR);
        }

        if(game.checkKey('J') && game.checkKey('D') && attL == false){
            attAnimator(game.playerAR);
            attR = true;
            attL = false;
            setVelX(0);
        }else if(game.checkKey('J') && game.checkKey('A') && attR == false){
            attAnimator(game.playerAL);
            attL = true;
            attR = false;
            setVelX(0);
        }else if(game.checkKey('J') && faceL == false){
            attAnimator(game.playerAR);
            setVelX(0);
        }else if(game.checkKey('J') && faceR == false){
            attAnimator(game.playerAL);
            setVelX(0);
        }
    }

    // method to control player player velocity
    public void playerVel(){
        x += velX;
        y += velY;
    }

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

    public void update(){
        
        playerVel();

        // checks for when key is pressed
        
        if(game.checkKey(' ')){
            jump();
        }
        if(game.checkKey('D')){
            setVelX(15);
        }
        if(game.checkKey('A')){
            setVelX(-15);
        }
        if(game.checkKey('D') && game.checkKey('A')){
            setVelX(0);
        }

        // checks for when key is released
        if(game.checkKey(' ') == false){
            setVelY(getVelY() * getDecel());
        }
        if(game.checkKey('D') == false){
            setVelX(getVelX() * getDecel());
        }
        if(game.checkKey('A') == false){
            setVelX(getVelX() * getDecel());
        }
    }

    public float getPlayerW(){
        return halfPW;
    }
}
