package C19401596;

public class RainVisual extends GameObject{
    public RainVisual(Game game) {
        super(game, 0, 0);
    }

    // y ranges for where the rain falls from
    float y1Range = -1000;
    float y2Range = -50;

    // random variables to control rain element
    float x = game.random(game.width);
    float y = game.random(y1Range, y2Range);
    float speed = game.random(10, 14);
    float len = game.random(5, 10);

    // method to control speed of rain
    public void descend(){
        y = y + speed;

        if(y > game.height){
            y = game.random(-200, -100);
            y = game.random(4, 10);
        }
    }

    // method to display the rain
    public void display(){
        game.stroke(255,255,255);
        game.line(x, y, x, y + len);
    }

    public void render() {
        display();
    }

    public void update() {
        descend();
    }
}
