package C19401596;

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    float pWidth = 100;
    float pHeight = 100;

    PImage playerIdle = new PImage();
    PImage[] playerRun = new PImage[5];

    public void animateRRun(){
        for(int i = 2; i < playerRun.length; i++){
            playerRun[i] = game.loadImage("sprR/spr"+i+".png");
            game.image(playerRun[i], x, y, pWidth, pHeight);
        }
    }

    public void animateLRun(){
        for(int i = 2; i < playerRun.length; i++){
            playerRun[i] = game.loadImage("sprL/sprl"+i+".png");
            game.image(playerRun[i], x, y, pWidth, pHeight);
        }
    }

    public void render(){
        
        if(game.keyPressed == false){
            playerIdle = game.loadImage("sprR/spr0.png");
            game.image(playerIdle, x, y, pWidth, pHeight);  // note: obj collision rely on the player object's scale
        }
        if(game.keyPressed == true){
            if (game.checkKey(PApplet.UP)){
                playerIdle = game.loadImage("sprR/spr0.png");
                game.image(playerIdle, x, y, pWidth, pHeight); 
            }else if (game.checkKey(PApplet.RIGHT) && game.checkKey(PApplet.LEFT) == false){
                animateRRun();
            }else if (game.checkKey(PApplet.LEFT) && game.checkKey(PApplet.RIGHT) == false){
                animateLRun();
            }else if (game.checkKey(PApplet.RIGHT) && game.checkKey(PApplet.LEFT)){
                playerIdle = game.loadImage("sprR/spr0.png");
                game.image(playerIdle, x, y, pWidth, pHeight);
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
        
        if (game.checkKey(PApplet.UP)){
            setVelY(-60);
        }
        if (game.checkKey(PApplet.RIGHT)){
            setVelX(15);
        }
        if (game.checkKey(PApplet.LEFT)){
            setVelX(-15);
        }
        if (game.checkKey(PApplet.RIGHT) && game.checkKey(PApplet.LEFT)){
            setVelX(0);
        }
        

        // checks for when key is released
        if(game.keyPressed == false){
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
        }
    }
}
