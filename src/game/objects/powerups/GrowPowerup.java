package game.objects.powerups;

import engine.util.Action;
import engine.util.Vector;
import game.Game;

import java.awt.*;

public class GrowPowerup extends PowerUp {



    public int powerupLength = 10;
    public GrowPowerup(Vector pos){
        powerupAction = new Action() {
            @Override
            public void act() {
                dePowerTime = System.currentTimeMillis() + 1000*powerupLength;
                isActive =true;
                if(playerAffected ==0){
                    Game.player1.grow(175);
                } else {

                    Game.player2.grow(175);

                }
            }
        };

        dePowerupAction = () -> {
            if(playerAffected ==0) {
                Game.player1.reset();
            }else {
                Game.player2.reset();
            }
        };
        this.pos = pos;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        if(!isActive)
        g.drawRect(pos.x,pos.y,width,height);

    }


    @Override
    public void onReady() {

    }

    @Override
    public String getName() {
        return "Super Size";
    }
}
