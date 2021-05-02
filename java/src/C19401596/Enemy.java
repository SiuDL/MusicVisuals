package C19401596;

import processing.core.PImage;

public class Enemy extends GameObject{

    public Enemy(Game game, float x, float y){
        super(game, x, y);
    }

    float eWidth = 100;
    float eHeight = 100;
    float halfEW = eWidth / 2;

    boolean jumped = false;

    public void animator(PImage[] a){
        PImage[] anim = a;
        if(delay == 0){
            frame = (frame + 1) % anim.length;
        }
        delay = (delay + 1) % 4;
        game.image(anim[frame], x, y, eWidth, eHeight);
    }

    public void enemyVel(){
        x += velX;
        y += velY;
    }

    public void render(){
        animator(game.enemyI);
    }

    public void update(){

        enemyVel();

        if(getX() > game.p.getX()){
            //setVelX(-5);
        }else if(getX() < game.p.getX()){
            //setVelX(5);
        }
        
        if(getX() > game.p.getX() && getX() < game.p.getX() + 40){
            setVelX(0);
        }else if(getX() < game.p.getX() && getX() > game.p.getX() - 40){
            setVelX(0);
        }
    }
    
    public float getEnemy(){
        return halfEW;
    }
}
