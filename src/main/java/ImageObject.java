
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static javax.imageio.ImageIO.read;

public class ImageObject {
    private BufferedImage image;

    private EnergyGrid energyGrid;
    private Color[][] pixelGrid;
    private SeamRemover seamRemover = new SeamRemover();


    private int height;

    private int width;


    public ImageObject(File imageFile) {
        try {

            image = ImageIO.read(imageFile);
            width = image.getWidth();
            height = image.getHeight();
            generatePixelArray();

            energyGrid = new EnergyGrid(pixelGrid);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generatePixelArray() {

        pixelGrid = new Color[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixelGrid[row][col] = new Color(image.getRGB(col, row));
            }
        }
    }

//    public static void main(String[] args) {
//        ImageObject m = new ImageObject();
//    }


    public EnergyGrid getEnergyGrid() {
        return energyGrid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public Color[][] getPixelGrid(){
        return pixelGrid;
    }
    public void setPixelGrid(Color[][] pixelGrid){
        this.pixelGrid = pixelGrid;
    }
    public void removeSmallestVerticalSeam(){
//        VerticalSeamCreator creator = new VerticalSeamCreator(this);
        seamRemover.removeSmallestVerticalSeam(this);
    }
    public void removeSmallestHorizontalSeam(){
        seamRemover.removeSmallestHorizontalSeam(this);

    }
    public  void setHeight(int height){
        this.height = height;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setEnergyGrid(EnergyGrid energyGrid){
        this.energyGrid = energyGrid;
    }
    public void resetImageToCurrentPixelGrid(){
        //take current pixel grid and create image out of it, set the BufferedImage (this.image) to be the new image of this class
    }
}

//write tests for everything
// put everything into little classes
//1. Read image into buffered image (either color[][] or int[][])
//2. calculate double[][] energy
//3. find the lowest energy vertical seam (or horizontal)
//4. remove seam
//5. Go to step 2 (until hit the number of seams you want to remove vertically and then also horizontal)

