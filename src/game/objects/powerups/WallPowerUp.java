package game.objects.powerups;

import engine.util.Action;
import engine.util.Vector;
import game.Game;
import game.objects.Wall;

import java.awt.*;

public class WallPowerUp extends PowerUp {



    public int powerupLength = 10;
    public WallPowerUp(Vector pos){
        powerupAction = new Action() {
            @Override
            public void act() {
                dePowerTime = System.currentTimeMillis() + 1000*powerupLength;
                isActive =true;
                System.out.println(playerAffected);
              Game.window.currentScene.addGameobject(new Wall(playerAffected));
            }
        };


        this.pos = pos;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        if(!isActive)
            g.drawRect(pos.x,pos.y,width,height);

    }


    @Override
    public void onReady() {

    }

    @Override
    public String getName() {
        return "Wall";
    }
}
