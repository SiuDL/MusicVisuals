package C19401596;

public class WorldObj extends GameObject{
    public WorldObj(Game game){
        super(game,0,0);
    }

    // floor variables
    private float floorX = 0;
    private float floorY = game.height * 0.95f;
    private float floorW = game.width;
    private float floorH = 100;

    // roof variables
    private float roofX = 0;
    private float roofY = 0;
    private float roofW = game.width;
    private float roofH = 20;

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
        rendObj method to take in variables and
        create world objects, can be used to create multiple objects
    */
    private void rendObj(float x, float y, float w, float h){
        game.fill(0);
        game.rect(x, y, w, h);
    }

    public void render(){
        rendObj(floorX, floorY, floorW, floorH); // creates the main floor within the level
        rendObj(leftWallX, leftWallY, leftWallW, leftWallH); // creates the left wall
        rendObj(rightWallX, rightWallY, rightWallW, rightWallH); // creates the left wall
        rendObj(roofX, roofY, roofW, roofH); // creates the roof
    }

    public void update(){

    }

    public float getFloor(){
        return floorY - floorH;
    }

    public float getLeftWall(){
        return leftWallX;
    }

    public float getRightWall(){
        return rightWallX - 70;
    }

    public float getRoof(){
        return roofX - 4;
    }
}
