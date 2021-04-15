package C19401596;

import processing.core.PApplet;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    private double velX = 0;
    private double velY = 0;

    public void render(){
        game.ellipse(x, y, 100, 100);
    }

    public void playerVel(){
        x += velX;
        y += velY;
    }

    public void update(){
        playerVel();

        if(game.keyPressed == true){
            if (game.keyCode == PApplet.UP){
                setVelY(-5);
            }
            if (game.keyCode == PApplet.DOWN){
                setVelY(5);
            }
            if (game.keyCode == PApplet.RIGHT){
                setVelX(5);
            }
            if (game.keyCode ==PApplet.LEFT){
                setVelX(-5);
            }
        }
        if(game.keyPressed == false){
            if (game.keyCode == PApplet.UP){
                setVelY(0);
            }
            if (game.keyCode == PApplet.DOWN){
                setVelY(0);
            }
            if (game.keyCode == PApplet.RIGHT){
                setVelX(0);
            }
            if (game.keyCode == PApplet.LEFT){
                setVelX(0);
            }
        }
    }

    public void setVelX(float velX){
        this.velX = velX;
    }

    public void setVelY(float velY){
        this.velY = velY;
    }
}
