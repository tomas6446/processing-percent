import processing.core.PApplet;
import processing.core.PFont;

import java.util.Scanner;

/**
 * @author Tomas Kozakas, 1 grupe
 **/
public class Startup extends PApplet {
    private final PApplet pApplet;
    private Controller controller;

    public Startup() {
        pApplet = this;
    }

    public static void main(String[] args) {
        PApplet.main("Startup");
    }

    @Override
    public void settings() {
        size(1200, 1200);
    }

    @Override
    public void setup() {
        frameRate(60);
        PFont pFont = createFont("Arial", 64, true);
        textFont(pFont, 32);

        controller = new Controller(pApplet);

        System.out.println("Percent:");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int percent = Integer.parseInt(input);

        int j = 0;
        for (int i = 0; i < percent; i++) {
            if (i % 10 == 0 && i != 0) {
                j++;
            }
            controller.getDesigner().getMatrix()[j][i % 10] = 1;
            controller.getDesigner().setCounter(controller.getDesigner().getCounter() + 1);
        }
    }

    @Override
    public void draw() {
        clear();
        controller.handleMouseClicks();
        controller.handleResetButton();

        controller.draw();
    }

    @Override
    public void mousePressed() {
        if (mouseButton == LEFT) {
            controller.setLeftMouseButtonClicked(true);
        } else if (mouseButton == RIGHT) {
            controller.setRightMouseButtonClicked(true);
        }
        controller.setMouseXPos(mouseX);
        controller.setMouseYPos(mouseY);
    }

    @Override
    public void mouseReleased() {
        if (mouseButton == LEFT) {
            controller.setLeftMouseButtonClicked(false);
        } else if (mouseButton == RIGHT) {
            controller.setRightMouseButtonClicked(false);
        }
    }

    @Override
    public void mouseDragged() {
        if (mouseButton == LEFT) {
            controller.setLeftMouseButtonClicked(true);
        } else if (mouseButton == RIGHT) {
            controller.setRightMouseButtonClicked(true);
        }
        controller.setMouseXPos(mouseX);
        controller.setMouseYPos(mouseY);
    }
}