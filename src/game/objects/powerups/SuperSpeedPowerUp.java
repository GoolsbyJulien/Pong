package game.objects.powerups;

import engine.util.Action;
import engine.util.Vector;
import game.Game;

import java.awt.*;

public class SuperSpeedPowerUp extends PowerUp{
    public SuperSpeedPowerUp(Vector pos){
        this.pos = pos;
        powerupAction = new Action() {
            @Override
            public void act() {
                Game.ball.superSpeedHit(playerAffected);
    dePowerTime=0;
            isActive = true;
            }
        };
        dePowerupAction = new Action() {
            @Override
            public void act() {

            }
        };
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        if(!isActive)
            g.drawRect(pos.x,pos.y,width,height);    }

    @Override
    public void onReady() {

    }

    @Override
    public String getName() {
        return "Super Speed";
    }
}
