package C19401596;

import processing.core.PImage;

public class Enemy extends GameObject{

    public Enemy(Game game, float x, float y){
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

    public void enemyVel(){
        x += velX;
        y += velY;
    }

    public void render() {
        animator(game.enemyI);
    }

    public void update() {
        
    }
    
}
