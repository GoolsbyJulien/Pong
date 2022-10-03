package game.objects;

import engine.core.GameObject;
import engine.managers.AudioManager;
import game.Game;

import java.awt.*;
import java.util.Random;

public class Wall extends GameObject {
    int player;
    int width = 10;
    int height = 150;
    int health =3;
    private Rectangle rect, rect2;

    private int secondsAliveFor = 20;
    private long deSpawnTime;

    private static boolean player1WallsActivated = false;
    private static boolean player2WallsActivated = false;

    public Wall(int player) {
        this.player = player;
        onReady();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(player == 0 ? Color.white : Color.red);
        g.drawRect(pos.x, pos.y, width, height);
        g.drawRect(pos.x, pos.y + height + 300, width, height);
    }


    @Override
    public void tick() {
        if (System.currentTimeMillis() > deSpawnTime || health ==0) {
            Game.window.currentScene.removeGameObject(this);
        }
        if (Game.lastHit != player) {
            if (rect.intersects(Game.ball.rect) || rect2.intersects(Game.ball.rect)) {
                if (Game.ball.isInPlay)
                    AudioManager.play("assets/hit.wav");

                Random random = new Random();
                Game.ball.deflect(player, random.nextInt(3) - 1);
                health--;
                Game.lastHit = player;

            }
        }
    }

    @Override
    public void onReady() {


        if (player == 0)
            pos.x = 200 + 50;
        else
            pos.x = Game.window.getWidth() - 250;
        pos.y = -0;
        rect = new Rectangle(this.pos.x, this.pos.y, width, height);
        rect2 = new Rectangle(this.pos.x, this.pos.y + height + 300, width, height);
        deSpawnTime = secondsAliveFor * 1000 + System.currentTimeMillis();

    }


}
