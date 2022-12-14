
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static javax.imageio.ImageIO.read;

public class ImageObject {
    private BufferedImage image;
    private Color[][] pixelGrid;

    private int[][] energyGrid;
    private int height;

    private int width;

    public ImageObject() {
        try {
            File imageFile = new File(ImageObject.class.getResource("Broadway_tower_edit.jpg").getPath());
            image = ImageIO.read(imageFile);
            generatePixelArray();
            generateEnergyGrid();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void generatePixelArray() {
        width = image.getWidth();
        height = image.getHeight();
        pixelGrid = new Color[height][width];
        energyGrid = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixelGrid[row][col] = new Color(image.getRGB(col, row));
            }
        }

    }

    public void generateEnergyGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    continue;
                } else {
                    int energy = (int) (Math.pow(pixelGrid[i - 1][j].getRed() - pixelGrid[i + 1][j].getRed(), 2) +
                            Math.pow(pixelGrid[i - 1][j].getGreen() - pixelGrid[i + 1][j].getGreen(), 2) +
                            Math.pow(pixelGrid[i - 1][j].getBlue() - pixelGrid[i + 1][j].getBlue(), 2) +
                            Math.pow(pixelGrid[i][j + 1].getRed() - pixelGrid[i][j - 1].getRed(), 2) +
                            Math.pow(pixelGrid[i][j + 1].getGreen() - pixelGrid[i][j - 1].getGreen(), 2) +
                            Math.pow(pixelGrid[i][j + 1].getBlue() - pixelGrid[i][j - 1].getBlue(), 2));

                    energyGrid[i][j] = energy;
                }
            }
        }
    }


    public static void main(String[] args) {
        ImageObject m = new ImageObject();
    }
}

