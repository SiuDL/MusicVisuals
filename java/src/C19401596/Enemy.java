package C19401596;

public class Enemy extends GameObject{

    public Enemy(Game game, float x, float y){
        super(game, x, y);
    }

    public void enemyVel(){
        x += velX;
        y += velY;
    }

    public void render() {
        
    }

    public void update() {
        
    }
    
}
