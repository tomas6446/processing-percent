import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Tomas Kozakas
 */

@Setter
@Getter
public class Designer {
    private int[][] matrix;
    private int tileSize = 64;
    private int offset = 300;
    private int counter = 0;
    private PApplet pApplet;
    private PImage pImage;

    public Designer(PApplet pApplet) {
        this.pApplet = pApplet;
        this.matrix = new int[10][10];
        this.pImage = pApplet.loadImage("tile.png");
    }

    public void draw() {
        pApplet.fill(255, 255, 255);
        pApplet.text("press to reset", offset, tileSize);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int x = j * tileSize + offset;
                int y = i * tileSize + offset;
                switch (matrix[i][j]) {
                    case 0 -> pApplet.image(pImage, x, y, tileSize, tileSize);
                    case 1 -> pApplet.rect(x, y, tileSize, tileSize);
                    default -> throw new IllegalStateException("Unexpected value: " + matrix[i][j]);
                }
            }
        }
        pApplet.text(counter + "%", tileSize, tileSize);
    }
}
