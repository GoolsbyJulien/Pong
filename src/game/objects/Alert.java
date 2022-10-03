package game.objects;

import engine.core.GameObject;
import engine.input.Keyboard;
import engine.util.Vector;
import game.Game;

import java.awt.*;

public class Alert extends GameObject {
    private long stopTime;
    private Color color;
    private String text;
    private boolean isActive;

    public Alert(Vector pos) {
        this.pos = pos;
    }

    public void say(String text, Color color, int seconds) {

        stopTime = seconds * 1000 + System.currentTimeMillis();
        this.color = color;
        this.text = text;

        isActive = true;
    }

    @Override
    public void render(Graphics g) {

        if (!isActive)
            return;
        g.setColor(color);
        g.setFont(new Font("Verdana", 0, 20 ));
        g.drawString(text, pos.x -  text.length() * 3 , pos.y);
    }


    @Override
    public void tick() {


        if (System.currentTimeMillis() > stopTime) {
            dispose();
        }
    }

    public void dispose() {
        isActive = false;
        text = "";


    }

    @Override
    public void onReady() {

    }
}
