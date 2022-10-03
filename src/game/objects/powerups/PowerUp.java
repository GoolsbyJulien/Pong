package game.objects.powerups;

import engine.util.Action;
import engine.core.GameObject;
import game.Game;

import java.awt.*;

public abstract class PowerUp extends GameObject {
    public int width = 40;
    public int height = 40;
    public Action powerupAction;
    public Action dePowerupAction;

    protected int playerAffected = -1;
    protected boolean isActive;
    protected long dePowerTime = 0;

    protected long deSpawnTime;

    public PowerUp() {
        deSpawnTime = System.currentTimeMillis() + 25000;
    }


    public abstract String getName();


    @Override
    public void tick() {
        Rectangle rect = new Rectangle(this.pos.x, this.pos.y, width, height);
        if (!isActive) {

            if (System.currentTimeMillis() > deSpawnTime)
                Game.window.currentScene.removeGameObject(this);
            if (Game.ball.rect.intersects(rect)) {

                playerAffected = Game.lastHit;
                Game.alertBox.say( getName() + " Power up",playerAffected ==0? Color.white : Color.red,5);
                powerupAction.act();
                System.out.println("Powerup hit, depowertime ==" + dePowerTime);
            }
        } else {

            if (System.currentTimeMillis() > dePowerTime) {
            if(dePowerupAction!=null)
                dePowerupAction.act();
                dispose();
            }
        }


    }

    public void dispose() {
        Game.window.currentScene.removeGameObject(this);

    }
}
