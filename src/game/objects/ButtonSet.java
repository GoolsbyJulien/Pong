package game.objects;

import engine.core.GameObject;
import engine.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;

public class ButtonSet extends GameObject {

    public ArrayList<Button> buttons = new ArrayList<>();

    private long lastInputTime;

    public int currentSelectedIndex = 0;

    @Override
    public void render(Graphics g) {
        for (Button b : buttons) {
            b.render(g);
        }
    }

    @Override
    public void tick() {

        if (Keyboard.UP && currentSelectedIndex > 0 && (System.currentTimeMillis() - lastInputTime) >= 200) {
            currentSelectedIndex--;
            lastInputTime = System.currentTimeMillis();
        }

        if (Keyboard.DOWN && currentSelectedIndex < buttons.size() - 1 && (System.currentTimeMillis() - lastInputTime) >= 200) {
            currentSelectedIndex++;
            lastInputTime = System.currentTimeMillis();

        }


        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).tick();
            buttons.get(i).isSelected = (i == currentSelectedIndex);
        }
    }

    @Override
    public void onReady() {

    }
}
