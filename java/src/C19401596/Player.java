package C19401596;

//import processing.core.PApplet;

public class Player extends GameObject{

    public Player(Game game, float x, float y){
        super(game, x, y);
    }

    float pWidth = 100;
    float pHeight = 100;

    int frame;
    
    public void loadIdle(){
        frame = (frame + 1) % game.animI.length;
        game.image(game.animI[frame], x, y, pWidth, pHeight);
    }

    public void animateRRun(){
        frame = (frame + 1) % game.animR.length;
        game.image(game.animR[frame], x, y, pWidth, pHeight);
    }

    public void animateLRun(){
        frame = (frame + 1) % game.animL.length;
        game.image(game.animL[frame], x, y, pWidth, pHeight);
    }

    public void render(){
        
        if((game.checkKey('D') == false) && (game.checkKey('A') == false) && getVelY() != 0){
            loadIdle();
        }else if(game.checkKey(' ')){
            loadIdle();
        }else if (game.checkKey('D') && game.checkKey('A') == false){
            animateRRun();
        }else if (game.checkKey('A') && game.checkKey('D') == false){
            animateLRun();
        }else if (game.checkKey('D') && game.checkKey('A')){
            loadIdle();
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
        
        if(game.checkKey(' ')){
            setVelY(-60);
        }
        if(game.checkKey('D')){
            setVelX(15);
        }
        if(game.checkKey('A')){
            setVelX(-15);
        }
        if(game.checkKey('D') && game.checkKey('A')){
            setVelX(0);
        }

        // checks for when key is released
        if(game.checkKey(' ') == false){
            setVelY(getVelY() * getDecel());
        }
        if(game.checkKey('D') == false){
            setVelX(getVelX() * getDecel());
        }
        if(game.checkKey('A') == false){
            setVelX(getVelX() * getDecel());
        }
    }
}
