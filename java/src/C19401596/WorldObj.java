package C19401596;

public class WorldObj extends GameObject{
    public WorldObj(Game game){
        super(game,0,0);
    }

    private float floorY = game.height * 0.88f;

    /*
        rendFloor method to take in variables and
        create floor objects, can be used to create multiple floors
    */
    private void rendFloor(float x, float y, float w, float h){
        game.fill(255);
        game.rect(x, y, w, h);
    }

    public void render() {
        rendFloor(0, floorY, game.height, 100); // creates the main floor within the level
    }

    public void update() {

    }

    public float getFloor(){
        return floorY;
    }
}
