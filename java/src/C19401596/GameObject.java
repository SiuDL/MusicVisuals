package C19401596;

public abstract class GameObject {
    float x, y;
    float velX = 0;
    float velY = 0;
    Game game;
    float w = 50;
    float halfW = w / 2;

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

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
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

    // subclasses must implement these abstract methods, otherwise they will be abstract
    public abstract void render();

    public abstract void update();
}