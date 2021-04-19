package C19401596;

import processing.core.PApplet;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    public void render(){
        game.fill(255);
        game.ellipse(x, y, 100, 100); // note: obj collision rely on the player object's scale
    }

    // method to control player player velocity
    public void playerVel(){
        x += velX;
        y += velY;
    }

    public void update(){

        // checks for when key is pressed
        if(game.keyPressed == true){
            if (game.checkKey(PApplet.UP)){
                setVelY(-60);
            }
            if (game.checkKey(PApplet.RIGHT)){
                setVelX(20);
            }
            if (game.checkKey(PApplet.LEFT)){
                setVelX(-20);
            }
        }

        // checks for when key is released
        if(game.keyPressed == false){
            if(game.keyCode == PApplet.UP){
                setVelY(0);
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
