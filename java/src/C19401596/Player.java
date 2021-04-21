package C19401596;

import processing.core.PApplet;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    float pWidth = 100;
    float pHeight = 100;

    public void loadIdle(){
        for(int i = 0; i < game.anim.length - 1; i++){
            game.image(game.anim[0], x, y, pWidth, pHeight);
        }
    }

    public void animateRRun(){
        for(int i = 2; i < 4; i++){
            game.image(game.anim[i], x, y, pWidth, pHeight);
        }
    }

    public void animateLRun(){
        for(int i = 7; i < game.anim.length - 1; i++){
            game.image(game.anim[i], x, y, pWidth, pHeight);
        }
    }

    public void render(){
        
        if((game.checkKey(PApplet.RIGHT) == false) && (game.checkKey(PApplet.LEFT) == false) && getVelY() != 0){
            loadIdle();
        }
        if(game.keyPressed == true){
            if (game.checkKey(PApplet.UP)){
                loadIdle();
            }else if (game.checkKey(PApplet.RIGHT) && game.checkKey(PApplet.LEFT) == false){
                animateRRun();
            }else if (game.checkKey(PApplet.LEFT) && game.checkKey(PApplet.RIGHT) == false){
                animateLRun();
            }else if (game.checkKey(PApplet.RIGHT) && game.checkKey(PApplet.LEFT)){
                loadIdle();
            }
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
        if(game.keyPressed == false){
            setVelY(getVelY() * getDecel());
            setVelX(getVelX() * getDecel());

            /* To be used later on
            if(game.keyCode == PApplet.UP){
                setVelY(getVelY() * getDecel());
                setVelX(getVelX() * getDecel());
            }
            if (game.keyCode == PApplet.RIGHT){
                setVelX(getVelX() * getDecel());
            }
            if (game.keyCode == PApplet.LEFT){
                setVelX(getVelX() * getDecel());
            }
            */
        }
    }
}
