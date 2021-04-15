package C19401596;

public class WorldObj extends GameObject{
    public WorldObj(Game game){
        super(game,0,0);
    }

    // floor variables
    private float floorX = 0;
    private float floorY = game.height * 0.9f;
    private float floorW = game.width;
    private float floorH = 100;
    private float halfFH = floorH / 2;

    // left wall veriables
    private float leftWallX = 0;
    private float leftWallY = 0;
    private float leftWallW = 20;
    private float leftWallH = game.height;

    // right wall veriables
    private float rightWallW = 20;
    private float rightWallX = game.width - rightWallW;
    private float rightWallY = 0;
    private float rightWallH = game.height;

    /*
        rendFloor method to take in variables and
        create floor objects, can be used to create multiple floors
    */
    private void rendFloor(float x, float y, float w, float h){
        game.fill(255,255,255);
        game.rect(x, y, w, h);
    }

    public void render(){
        rendFloor(floorX, floorY, floorW, floorH); // creates the main floor within the level
        rendFloor(leftWallX, leftWallY, leftWallW, leftWallH); // creates the left wall within level
        rendFloor(rightWallX, rightWallY, rightWallW, rightWallH); // creates the left wall within level
    }

    public void update(){

    }

    public float getFloor(){
        return floorY - halfFH; // halfFH coincidentally is also half player w
    }

    public float getLeftWall(){
        return leftWallW + 50; // 50 = half of player width
    }

    public float getRightWall(){
        return rightWallX - 50;
    }
}
