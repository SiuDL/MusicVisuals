package C19401596;

import processing.core.PApplet;
import processing.core.PImage;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    float pWidth = 100;
    float pHeight = 100;

    public void render(){
        game.fill(255);
        PImage player;
        player = game.loadImage("spr0.png");
        game.image(player, x, y, pWidth, pHeight);  // note: obj collision rely on the player object's scale
    }

    // method to control player player velocity
    public void playerVel(){
        x += velX;
        y += velY;
    }

    public void update(){
        
        playerVel();

        // checks for when key is pressed
        if(game.keyPressed == true){
            if (game.checkKey(PApplet.UP)){
                setVelY(-60);
            }
            if (game.checkKey(PApplet.RIGHT)){
                setVelX(15);
            }
            if (game.checkKey(PApplet.LEFT)){
                setVelX(-15);
            }
            if (game.keyCode == PApplet.RIGHT && game.keyCode == PApplet.LEFT){
                setVelX(0);
            }
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
