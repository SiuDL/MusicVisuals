package C19401596;

import processing.core.PApplet;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    private double velX = 0;
    private double velY = 0;

    public void render(){
        game.fill(255);
        game.ellipse(x, y, 100, 100);
    }

    public void playerVel(){
        x += velX;
        y += velY;
    }

    public void update(){

        if(game.keyPressed == true){
            if (game.checkKey(PApplet.UP)){
                setVelY(-20);
            }else if (game.checkKey(PApplet.DOWN)){
                setVelY(8);
            }else if (game.checkKey(PApplet.RIGHT)){
                setVelX(8);
            }else if (game.checkKey(PApplet.LEFT)){
                setVelX(-8);
            }
        }
        if(game.keyPressed == false){
            if (game.keyCode == PApplet.UP){
                setVelY(0);
            }else if (game.keyCode == PApplet.DOWN){
                setVelY(0);
            }else if (game.keyCode == PApplet.RIGHT){
                setVelX(0);
            }else if (game.keyCode == PApplet.LEFT){
                setVelX(0);
            }
        }
    }

    public void setVelX(double velX){
        this.velX = velX;
    }

    public void setVelY(double velY){
        this.velY = velY;
    }
}
