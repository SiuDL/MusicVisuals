package C19401596;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy extends GameObject{

    public Enemy(Game game, float x, float y){
        super(game, x, y);
    }

    // enemy width and height
    float eWidth = 100;
    float eHeight = 100;
    float halfEW = eWidth / 2;

    // enemy health variable and max health variable for mapping to health bar
    int health = 100;
    final int maxHealth = health;

    // alpha variable for fading effect
    int alpha = 0;

    // frame animation and delay variables
    int frame = 0;
    int frameIdle = 0;
    int attFrame = 0;
    int delay = 0;
    int attDelay = 0;
    int idlDelay = 0;

    // boolean values for enemy actions
    boolean faceL = false;
    boolean faceR = false;
    boolean withinRange = false;
    boolean attacked = false;

    // method to animate enemy movement
    public void animator(PImage[] a){
        PImage[] anim = a;
        if(delay == 0){
            frame = (frame + 1) % anim.length;
        }
        delay = (delay + 1) % 5;
        game.image(anim[frame], x, y, eWidth, eHeight);
    }

    // method to animate enemy idle
    public void loadIdle(PImage[] a){
        PImage[] anim = a;
        if(idlDelay == 0){
            frameIdle = (frameIdle + 1) % anim.length;
        }
        idlDelay = (idlDelay + 1) % 7;
        game.image(anim[frameIdle], x, y, eWidth, eHeight);
    }

    // method to animate enemy attack animation
    public void attAnimator(PImage[] a){
        PImage[] anim = a;
        if(attDelay == 0){
            attFrame = (attFrame + 1) % anim.length;
        }
        attDelay = (attDelay + 1) % 5;
        game.image(anim[attFrame], x, y);
    }

    // method to control enemy velocity
    public void enemyVel(){
        x += velX;
        y += velY;
    }

    // method to check if enemy attacked
    public void attack(){
        if(game.checkKey('J')){
            attacked = true;
        }else{
            attacked = false;
        }
    }

    public void render(){

        game.textAlign(PApplet.CENTER);

        // if statement to change alpha value
        // makes text or shape to fade in or out
        if(alpha != 255){
            alpha = alpha + 1;
        }

        // Enemy name
        game.textSize(20);
        game.fill(255, 255, 255, alpha);
        game.text("The Nameless Usurper", game.width/2, game.height * 0.07f);

        // mapping main enemy health bar to top of screen
        yC = game.height * 0.1f;
        xC = PApplet.map(health, 0, maxHealth, game.width * 0.1f, game.width * 0.9f);
        
        // if health is not 0, display health bar
        if(health != 0){
            game.strokeWeight(5);
            game.stroke(0, alpha);
            game.line(game.width * 0.1f, yC, game.width * 0.9f, yC);
            game.strokeWeight(4);
            game.stroke(255, 0, 0, alpha);
            game.line(game.width * 0.1f, yC, xC, yC);

            // mapping main enemy health bar to top of enemy
            /*
            yC = getY() * 0.99f;
            xC = PApplet.map(health, 0, maxHealth, getX(), getX() + 100);

            game.strokeWeight(2);
            game.stroke(255, 0, 0, alpha);
            game.line(getX(), yC, xC, yC);
            */
        }else{
            game.noStroke();
            game.strokeWeight(5);
            game.stroke(0);
            game.line(game.width * 0.1f, yC, game.width * 0.9f, yC);
        }
    }

    public void update(){

        enemyVel();

        // if statements to check if enemy is within range of player
        if(getX() > game.p.getX() && health != 0){
            // if within range is false, enemy will move to where player is
            if(withinRange == false){
                animator(game.enemyL);
                attacked = false;
                setVelX(-5);
            }else{// otherwise, animate enemy attack animation
                attAnimator(game.enemyAL);
                faceL = true;
                faceR = false;
                attacked = true;
            }
        }else if(getX() < game.p.getX() && health != 0){
            if(withinRange == false){
                animator(game.enemyR);
                attacked = false;
                setVelX(5);
            }else{
                attAnimator(game.enemyAR);
                faceR = true;
                faceL = false;
                attacked = true;
            }
        }
        
        if(health != 0){
            // if enemy is within range, stop enemy from moving
            if(getX() > game.p.getX() && getX() < game.p.getX() + 30){
                withinRange = true;
                setVelX(0);
            }else if(getX() < game.p.getX() && getX() > game.p.getX() - 70){
                withinRange = true;
                setVelX(0);
            }else{
                withinRange = false;
            }
        }else{
            loadIdle(game.enemyID);
            setVelX(0);
            setVelY(0);
        }
    }
    
    public float getEnemy(){
        return halfEW;
    }
}
