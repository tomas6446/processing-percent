import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

import java.util.Scanner;

/**
 * @author Tomas Kozakas
 */
@Getter
@Setter
public class Controller {
    private PApplet pApplet;
    private Designer designer;
    private boolean leftMouseButtonClicked = false;
    private boolean rightMouseButtonClicked = false;
    private int mouseXPos;
    private int mouseYPos;

    public Controller(PApplet pApplet) {
        this.pApplet = pApplet;
        designer = new Designer(pApplet);
    }

    public void handleMouseClicks() {
        if (leftMouseButtonClicked || rightMouseButtonClicked) {
            for (int i = 0; i < designer.getMatrix().length; i++) {
                for (int j = 0; j < designer.getMatrix()[0].length; j++) {
                    int x = j * designer.getTileSize() + designer.getOffset();
                    int y = i * designer.getTileSize() + designer.getOffset();
                    if (checkCollision(mouseXPos, mouseYPos, 1, 1, x, y, designer.getTileSize(), designer.getTileSize())) {
                        switch (designer.getMatrix()[i][j]) {
                            case 1 -> {
                                if (rightMouseButtonClicked) {
                                    designer.getMatrix()[i][j] = 0;
                                    designer.setCounter(designer.getCounter() - 1);
                                }
                            }
                            case 0 -> {
                                if (leftMouseButtonClicked) {
                                    designer.getMatrix()[i][j] = 1;
                                    designer.setCounter(designer.getCounter() + 1);
                                }
                            }
                            default ->
                                    throw new IllegalStateException("Unexpected value: " + designer.getMatrix()[i][j]);
                        }
                    }
                }
            }
        }
        leftMouseButtonClicked = false;
        rightMouseButtonClicked = false;
    }

    public void handleResetButton() {
        int x = designer.getOffset();
        int y = designer.getTileSize();
        if (checkCollision(mouseXPos, mouseYPos, 1, 1, x, y, designer.getTileSize() * 4, 32)) {
            this.designer = new Designer(pApplet);
        }
    }

    private boolean checkCollision(int r1x, int r1y, int r1w, int r1h, int r2x, int r2y, int r2w, int r2h) {
        return r1x + r1w >= r2x &&      // r1 right edge past r2 left
                r1x <= r2x + r2w &&     // r1 left edge past r2 right
                r1y + r1h >= r2y &&     // r1 top edge past r2 bottom
                r1y <= r2y + r2h;       // r1 bottom edge past r2 top
    }

    public void draw() {
        designer.draw();
    }
}
