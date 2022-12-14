package engine.render;

import engine.input.Keyboard;
import engine.core.Scene;
import engine.input.Mouse;
import engine.util.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends Canvas implements Runnable {


    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private boolean displayFPS = false;
    private BufferStrategy bs;
    private Graphics g;
    private Color backgroundColor = Color.black;
    private long startTime;
    public Scene currentScene;
    public Action eachFrame = null;
    public Window(String title, Scene scene) {
        currentScene = scene;
        JFrame frame = new JFrame(title);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(this);

        frame.setLocationRelativeTo(null);
        setBackground(Color.black);
        frame.setVisible(true);

        addKeyListener(new Keyboard());
        Mouse mouse = new Mouse();
        frame.addMouseMotionListener(mouse);
        frame.addMouseListener(mouse);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        frame.setResizable(false);


        Thread t = new Thread(this);
        t.start();

    }


    public void changeScene(Scene s) {
        currentScene = s;
        currentScene.load();
    }

    public long getTime() {
        return System.currentTimeMillis() - startTime;
    }


    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }
    @Override
    public void run() {

        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / 60;
        final double timeF = 1000000000 / 60;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        changeScene(currentScene);
        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                currentScene.update();
                ;

                ticks++;
                deltaU--;
            }


            if (deltaF >= 1) {

                render();

                if(eachFrame!=null)
                    eachFrame.act();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                if (displayFPS)
                    System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }


    private void render() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        g.setColor(backgroundColor);
        g.fillRect(0,0,getWidth(),getHeight());
        currentScene.render(g);

        bs.show();
        g.dispose();
    }
}
