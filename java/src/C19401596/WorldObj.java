package C19401596;

public class WorldObj extends GameObject{
    public WorldObj(Game game){
        super(game,0,0);
    }

    private float floorH = game.height * 0.88f;

    public void rendFloor(){
        game.fill(255);
        game.rect(0, floorH, game.width, 100);
    }

    public void render() {
        rendFloor();
    }

    public void update() {

    }

    public float getFloor(){
        return floorH;
    }
}
