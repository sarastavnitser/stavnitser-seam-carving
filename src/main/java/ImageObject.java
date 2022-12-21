
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static javax.imageio.ImageIO.read;

public class ImageObject {
    private BufferedImage image;


    private EnergyGrid energyGrid;
    private Color[][] pixelGrid;


    private int height;

    private int width;


    public ImageObject() {
        try {
            File imageFile = new File(ImageObject.class.getResource("camera-icon.png").getPath());
            image = ImageIO.read(imageFile);
            generatePixelArray();

            energyGrid = new EnergyGrid(pixelGrid, height, width);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void generatePixelArray() {
        width = image.getWidth();
        height = image.getHeight();
        pixelGrid = new Color[height][width];



        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixelGrid[row][col] = new Color(image.getRGB(col, row));
            }
        }


    }

    public static void main(String[] args) {
        ImageObject m = new ImageObject();
    }


    public EnergyGrid getEnergyGrid() {
        return energyGrid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

//write tests for everything
// put everything into little classes
//1. Read image into buffered image (either color[][] or int[][])
//2. calculate double[][] energy
//3. find the lowest energy vertical seam (or horizontal)
//4. remove seam
//5. Go to step 2 (until hit the number of seams you want to remove vertically and then also horizontal)

