package game.objects;

import engine.input.Mouse;
import engine.util.Action;
import engine.core.GameObject;
import engine.input.Keyboard;
import engine.util.Vector;

import java.awt.*;

public class Button extends GameObject {

    String text;
    Action action;
    public boolean isSelected = false;
    public Font font = new Font("Verdana", 0, 20);
    private Rectangle rect;


    public Button(String text, Vector pos, Action action) {
        this.text = text;
        this.action = action;
        this.pos = pos;
        onReady();
    }

    @Override
    public void render(Graphics g) {
        if (rect.contains(Mouse.mousePos.x, Mouse.mousePos.y))
            g.setColor(Color.red);
        else
            g.setColor(Color.white);
        g.setFont(font);

        g.drawString(isSelected ? ">" + text + "<" : text, pos.x, pos.y);
    }

    @Override
    public void tick() {

        if (Mouse.RIGHT_CLICK && rect.contains(Mouse.lastClick.x, Mouse.lastClick.y) || Keyboard.ENTER && isSelected)
            action.act();
    }

    @Override
    public void onReady() {

        rect = new Rectangle(pos.x - 40, pos.y - 20, 20 * text.length() + 40, 40);

    }
}
