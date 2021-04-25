package C19401596;

public abstract class GameObject{
    
    Game game;
    int frame = 0;

    float x, y;
    float velX = 0;
    float velY = 0;

    final private float gravPull = 10;
    final private float direction = 10;
    final private float decel = 0.9f;

    public GameObject(Game game, float x, float y)
    {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVelX(float velX){
        this.velX = velX;
    }

    public void setVelY(float velY){
        this.velY = velY;
    }

    public float getVelY(){
        return velY;
    }

    public float getVelX(){
        return velX;
    }

    public float getGrav() {
        return gravPull;
    }

    public float getDirect() {
        return direction;
    }

    public float getDecel(){
        return decel;
    }

    // subclasses must implement these abstract methods, otherwise they will be abstract
    public abstract void render();

    public abstract void update();
}