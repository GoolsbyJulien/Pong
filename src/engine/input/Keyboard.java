package engine.input;

import engine.util.Vector;

import java.awt.event.*;

public class Keyboard extends KeyAdapter {


    public static boolean W = false;
    public static boolean S = false;
    public static boolean UP = false;
    public static boolean DOWN = false;
    public static boolean E = false;
    public static boolean ENTER = false;
    public static boolean F = false;


    @Override
    public void keyPressed(KeyEvent keyEvent) {


        if (keyEvent.getKeyCode() == keyEvent.VK_W) {
            W = true;
        } else if (keyEvent.getKeyCode() == keyEvent.VK_ENTER) {
            ENTER = true;
        } else if (keyEvent.getKeyCode() == keyEvent.VK_F) {
            F = true;
        } else if (keyEvent.getKeyCode() == keyEvent.VK_S) {
            S = true;
        } else if (keyEvent.getKeyCode() == keyEvent.VK_UP) {
            UP = true;
        } else if (keyEvent.getKeyCode() == keyEvent.VK_DOWN) {
            DOWN = true;

        } else if (keyEvent.getKeyCode() == keyEvent.VK_E) {
            E = true;

        }


    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == keyEvent.VK_W) {
            W = false;
        } else if (keyEvent.getKeyCode() == keyEvent.VK_S) {
            S = false;
        } else if (keyEvent.getKeyCode() == keyEvent.VK_F) {
            F = false;
        } else if (keyEvent.getKeyCode() == keyEvent.VK_UP) {
            UP = false;
        } else if (keyEvent.getKeyCode() == keyEvent.VK_DOWN) {
            DOWN = false;

        } else if (keyEvent.getKeyCode() == keyEvent.VK_E) {
            E = false;

        } else if (keyEvent.getKeyCode() == keyEvent.VK_ENTER) {
            ENTER = false;

        }


    }


}
