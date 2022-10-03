package game.objects;

import engine.core.GameObject;
import engine.input.Keyboard;
import engine.managers.AudioManager;
import engine.render.Window;
import game.Game;

import java.awt.*;
import java.util.Random;

public class Paddle extends GameObject {


    private int player = 0;
    private int speed = 20;

    private int width;


    private int defaultWidth = 10;
    private int defaultHeight = 100;

    private int height;
    private int splitHeight =80/3;


    public Paddle(int player) {
        this.player = player;
        height = defaultHeight;
        width = defaultWidth;

    }

    @Override
    public void render(Graphics g) {


//        g.setColor(Color.red);
//        g.fillRect(this.pos.x, this.pos.y, 10, 80 /3);
//        g.setColor(Color.green);
//        g.fillRect(this.pos.x , this.pos.y+ 80 /3, 10, 80 /3);
//        g.setColor(Color.orange);
//        g.fillRect(this.pos.x , this.pos.y+ (80 /3) *2, 10, 80 /3);

        if(player == 0)
        g.setColor(Color.WHITE);
        else
        g.setColor(Color.red);

        g.fillRect(this.pos.x, this.pos.y, width, height);


    }
    public void grow(int height) {
        this.height = height;

    }

    public void reset() {
        width =defaultWidth;
        height = defaultHeight;
    }
    @Override
    public void tick() {

        Rectangle up = new Rectangle(this.pos.x, this.pos.y, width, splitHeight);
        Rectangle middle = new Rectangle(this.pos.x , this.pos.y+ splitHeight, width, splitHeight);
        Rectangle down = new Rectangle(this.pos.x , this.pos.y+ (splitHeight *2), width, splitHeight);

        Rectangle rect= new Rectangle(this.pos.x , this.pos.y, width, height);


        if(Game.lastHit != player) {
            if( rect.intersects(Game.ball.rect)) {
                if(Game.ball.isInPlay)
                AudioManager.play("assets/hit.wav");

                Random random = new Random();
                Game.ball.deflect(player, random.nextInt(3) - 1);

                Game.lastHit = player;

            }
//            if (up.intersects(game.GameManager.ball.rect)) {
//
//                game.GameManager.ball.deflect(player, -1);
//
//                game.GameManager.lastHit = player;
//            } else if (middle.intersects(game.GameManager.ball.rect)) {
//
//                game.GameManager.ball.deflect(player, 0);
//                game.GameManager.lastHit = player;
//
//            } else if (down.intersects(game.GameManager.ball.rect)) {
//
//                game.GameManager.ball.deflect(player, 1);
//                game.GameManager.lastHit = player;
//
//            }
        }
        if (player == 0) {
            if (Keyboard.W && pos.y > 0) {
                pos.y-= speed;
            }
            if (Keyboard.S && pos.y < engine.render.Window.WIDTH -320)  {
                pos.y+=speed;
            }
           // System.out.println("W: " + engine.core.Input.W + " S" + engine.core.Input.S);
        }else {
            if (Keyboard.UP && pos.y > 0) {
                pos.y-= speed;
            }
            if (Keyboard.DOWN && pos.y < engine.render.Window.WIDTH -320) {
                pos.y+=speed;
            }

        }
    }

    @Override
    public void onReady() {
        if (player == 0)
            pos.x = 10;
        else
            pos.x = Window.WIDTH - 40;


    }
}
