package engine.core;

import engine.util.Vector;

import java.awt.*;

public abstract class GameObject {
    public Vector pos = new Vector();
    public abstract void render(Graphics g);
    public abstract void tick();

    public abstract void onReady();

}
