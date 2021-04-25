package C19401596;

import processing.core.PImage;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    float pWidth = 100;
    float pHeight = 100;

    boolean jumped = false;

    public void animator(PImage[] a){
        PImage[] anim = a;
        frame = (frame + 1) % anim.length;
        game.image(anim[frame], x, y, pWidth, pHeight);
    }

    public void render(){
        
        if((game.checkKey('D') == false) && (game.checkKey('A') == false) && getVelY() != 0){
            animator(game.animI);
        }else if(game.checkKey(' ')){
            animator(game.animI);
        }else if (game.checkKey('D') && game.checkKey('A') == false){
            animator(game.animR);
        }else if (game.checkKey('A') && game.checkKey('D') == false){
            animator(game.animL);
        }else if (game.checkKey('D') && game.checkKey('A')){
            animator(game.animI);
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
}
