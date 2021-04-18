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

    public void playerVel(){
        x += velX;
        y += velY;
    }

    public void update(){

        if(game.keyPressed == true){
            if (game.checkKey(PApplet.UP)){
                setVelY(-50);
            }
            if (game.checkKey(PApplet.RIGHT)){
                setVelX(15);
            }
            if (game.checkKey(PApplet.LEFT)){
                setVelX(-15);
            }
        }

        if(game.keyPressed == false){
            if(game.keyCode == PApplet.UP){
                setVelY(0);
            }
            if (game.keyCode == PApplet.RIGHT || game.keyCode == PApplet.LEFT){
                setVelX(0);
            }
        }
    }
}
