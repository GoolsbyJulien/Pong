package game;

import engine.util.Action;
import engine.core.Scene;
import engine.render.Window;
import engine.util.Vector;
import game.objects.*;
import game.objects.Button;
import game.objects.powerups.PowerUpSpawner;
import game.objects.powerups.SuperSpeedPowerUp;
import game.objects.powerups.WallPowerUp;

import java.awt.*;

public class Game {

    public static int player1Score = 0;
    public static int player2Score = 0;
    public static Paddle player1;
    public static Paddle player2;
    public static int lastHit = 0;
    public static Ball ball;
    public static Window window;
    public static Alert alertBox;
    private Scene menuScene = new Scene();
    private  Scene gameScene = new Scene();
    private  Scene aboutScene = new Scene();

    public static int playerTurn = 1;





    public Game() {


        // Create Scenes
        createGameScene();
        createMenuScene();
        createAboutScene();

        //tells the engine to create window and set the scene to main scene
        window = new Window("Pong", menuScene);

    }

    public void createAboutScene() {

        aboutScene.uiLayer = g -> {
        };
            aboutScene.addGameobject(new Button("Back", new Vector(40, 500), new Action() {
            @Override
            public void act() {

                window.changeScene(menuScene);
            }
        }));




    }
    public void createGameScene() { // This function creates the Game scene adding all gameobjects
        alertBox = new Alert(new Vector(350,40));

        Ball ball = new Ball();
        gameScene.addGameobject(ball);

        Paddle player1 = new Paddle(0);

        Paddle player2 = new Paddle(1);
        gameScene.addGameobject(player1);
        gameScene.addGameobject(player2);
        gameScene.addGameobject(alertBox);

    gameScene.addGameobject(new PowerUpSpawner());
//         gameScene.addGameobject(new SuperSpeedPowerUp(new Vector(200,250)));
        gameScene.uiLayer = g -> {




            g.setFont(new Font("Verdana", 0, 30));
            g.setColor(Color.white);
            g.drawString(Game.player1Score + "", 150, 50);
            g.setColor(Color.red);
            g.drawString(Game.player2Score + "", window.getWidth() - 200, 50);
        };


        gameScene.loadScene = () -> start(ball, player1, player2);


    }

    public void createMenuScene() {

        menuScene.uiLayer = g -> {

            g.setFont(new Font("Verdana", 0, 80));
            g.setColor(Color.white);

            g.drawString("Pong", 300, 80);
            g.setFont(new Font("Verdana", 0, 20));

            g.drawString("By Julien Goolsby", 310, 120);
        };

        ButtonSet buttonSet = new ButtonSet();
        buttonSet.buttons.add(new Button("Start", new Vector(370, 200), new Action() {
            @Override
            public void act() {
                window.changeScene(gameScene);

            }
        }));

        buttonSet.buttons.add(new Button("About", new Vector(370, 250), new Action() {
            @Override
            public void act() {
                window.changeScene(aboutScene);

            }
        }));
        buttonSet.buttons.add(new Button("Exit", new Vector(370, 300), new Action() {
            @Override
            public void act() {
                System.exit(0);
            }
        }));

        menuScene.addGameobject(buttonSet);

    }


    public void start(Ball b, Paddle p1, Paddle p2) {
        player1 = p1;
        player2 = p2;
        ball = b;
    }

    public static void main(String[] args) {
        new Game();
    }

}
