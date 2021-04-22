package C19401596;

import processing.core.PApplet;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    float pWidth = 100;
    float pHeight = 100;

    int frame;
    
    public void loadIdle(){
        frame = (frame + 1) % game.animI.length;
        game.image(game.animI[frame], x, y, pWidth, pHeight);
    }

    public void animateRRun(){
        frame = (frame + 1) % game.animR.length;
        game.image(game.animR[frame], x, y, pWidth, pHeight);
    }

    public void animateLRun(){
        frame = (frame + 1) % game.animL.length;
        game.image(game.animL[frame], x, y, pWidth, pHeight);
    }

    public void render(){
        
        if((game.checkKey(PApplet.RIGHT) == false) && (game.checkKey(PApplet.LEFT) == false) && getVelY() != 0){
            loadIdle();
        }else if(game.checkKey(PApplet.UP)){
            loadIdle();
        }else if (game.checkKey(PApplet.RIGHT) && game.checkKey(PApplet.LEFT) == false){
            animateRRun();
        }else if (game.checkKey(PApplet.LEFT) && game.checkKey(PApplet.RIGHT) == false){
            animateLRun();
        }else if (game.checkKey(PApplet.RIGHT) && game.checkKey(PApplet.LEFT)){
            loadIdle();
        }       
    }

    // method to control player player velocity
    public void playerVel(){
        x += velX;
        y += velY;
    }

    public void update(){
        
        playerVel();

        // checks for when key is pressed
        
        if(game.checkKey(PApplet.UP)){
            setVelY(-60);
        }
        if(game.checkKey(PApplet.RIGHT)){
            setVelX(15);
        }
        if(game.checkKey(PApplet.LEFT)){
            setVelX(-15);
        }
        if(game.checkKey(PApplet.RIGHT) && game.checkKey(PApplet.LEFT)){
            setVelX(0);
        }

        // checks for when key is released
        if(game.checkKey(PApplet.UP) == false){
            setVelY(getVelY() * getDecel());
        }
        if(game.checkKey(PApplet.RIGHT) == false){
            setVelX(getVelX() * getDecel());
        }
        if(game.checkKey(PApplet.LEFT) == false){
            setVelX(getVelX() * getDecel());
        }
    }
}
