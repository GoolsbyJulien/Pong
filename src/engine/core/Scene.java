package engine.core;

import engine.managers.GameObjectManager;
import engine.util.Action;

import java.awt.*;

public class Scene {
    public GameObjectManager goManager;

    public UILayer uiLayer;
    public Action loadScene;

    public Scene (){
        goManager = new GameObjectManager();
    }

    public void addGameobject(GameObject e) {
        goManager.add(e);
    }
    public void removeGameObject(GameObject e) {
        goManager.remove(e);
    }

    public void update() {
        goManager.tick();
    }


    public void load() {
        goManager.load();
        if(loadScene!=null)
        loadScene.act();
    }
    public void render(Graphics g) {
        goManager.render(g);
        if(uiLayer!=null)
        uiLayer.draw(g);
    }
    public void dispose(){
        goManager.dispose();
    }


}
