package game.objects;

import engine.core.GameObject;
import engine.input.Keyboard;
import engine.managers.AudioManager;
import engine.render.Window;
import engine.util.Util;
import engine.util.Vector;
import game.Game;

import java.awt.*;
import java.util.Random;

public class Ball extends GameObject {

    private int currentSpeed = 10;
    private int superSpeedSpeed = 13;
    private int defaultSpeed =10;
    private int superSpeedHit = -1;
    private int size = 20;

    public Rectangle rect;

    public boolean isInPlay = false;


    public void deflect(int id, int direction) {

        int modi = id == 0 ? 1 : -1;
        System.out.println("Deflected " + direction);
        direction *= Util.RandomRange(1,6);

        if(superSpeedHit == id){
            System.out.println("SuperSpeed hit");
            currentSpeed = superSpeedSpeed;
            superSpeedHit =-1;
        }
        System.out.println("Ball hit by player " +(Game.lastHit+1) + " Direction: " + direction );
        movement.x = currentSpeed * modi;

        movement.y = direction;

//      int  speedMod =  r.nextInt(2) + -1;
//
//        currentSpeed +=speedMod;
//
//
//        if(currentSpeed > maxSpeed)
//            currentSpeed = maxSpeed;
//        if(currentSpeed < defaultSpeed)
//            currentSpeed = defaultSpeed;
        currentSpeed =defaultSpeed;

    }


    public void superSpeedHit(int player){
        superSpeedHit =player;
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Game.lastHit ==0 ? Color.red : Color.white);

        g.fillOval(pos.x, pos.y, size, size);
    }


    private Vector movement = new Vector();

    @Override
    public void tick() {

//
//


        rect = new Rectangle(pos.x, pos.y, size, size);
        if (!isInPlay) {
            Game.lastHit = Game.playerTurn;
            if (Keyboard.E) {
                currentSpeed = defaultSpeed;

                if (Game.playerTurn == 0) {

                    deflect(Game.playerTurn,0);
                } else {

                    deflect(Game.playerTurn,0);
                }
                isInPlay = true;
            } else if (Game.playerTurn == 0) {
                pos.x = Game.player1.pos.x + 10;
                pos.y = Game.player1.pos.y + 20;
            } else {
                pos.x = Game.player2.pos.x - 20;
                pos.y = Game.player2.pos.y + 20;
            }
        } else {
            pos.x += movement.x;
            pos.y += movement.y;


            if (pos.x > Window.WIDTH - 10)

                score(0);
            else if (pos.x < 0)
                score(1);
        }
        if(pos.y >= 540 || pos.y <0) {
            AudioManager.play("assets/wall.wav");

            movement.y = -movement.y;
        }
    }

    public void score(int player) {
        isInPlay = false;
        movement.zero();
        System.out.println("Score Player " + player);
        if (player == 0) {
            Game.playerTurn = 0;
            Game.player1Score++;
        } else {
            Game.playerTurn = 1;
            Game.player2Score++;
        }
    }

    @Override
    public void onReady() {

    }
}
