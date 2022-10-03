package game.objects.powerups;

import engine.core.GameObject;
import engine.util.Util;
import engine.util.Vector;
import game.Game;

import java.awt.*;
import java.util.Random;

public class PowerUpSpawner extends GameObject {


    private long nextSpawnTime;

    private void setNextSpawnTime() {

        int secs = Util.RandomRange(10, 20);

        nextSpawnTime = secs * 1000 + System.currentTimeMillis();
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void tick() {
        if (System.currentTimeMillis() > nextSpawnTime) {
            spawnPowerUP();
            setNextSpawnTime();
        }
    }

    public void spawnPowerUP() {

        int x = Util.RandomRange(100, 600);
        int y = Util.RandomRange(100, 500);
        System.out.println("Spawned PowerUp at " + x + " " + y);
        int i = Util.RandomRange(0, 2);
        System.out.println("Power Id = "+i);
        if (i == 0)
            Game.window.currentScene.addGameobject(new GrowPowerup(new Vector(x, y)));
       else if (i == 1)
            Game.window.currentScene.addGameobject(new WallPowerUp(new Vector(x, y)));
        else
            Game.window.currentScene.addGameobject(new SuperSpeedPowerUp(new Vector(x, y)));


    }

    @Override
    public void onReady() {
        nextSpawnTime = System.currentTimeMillis() + 20000;
    }
}
