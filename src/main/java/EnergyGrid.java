import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnergyGrid {
    private int[][] energyGrid;
    private Color[][] pixelGrid;
    private int height;
    private int width;
    private int maxEnergy;
    private BufferedImage energyPic;
    private BufferedImage invertedEnergyPic;

    public EnergyGrid(Color[][] pixelGrid, int height, int width) {
        this.pixelGrid = pixelGrid;
        this.height = height;
        this.width = width;
        generateEnergyGrid();
    }

    public int[][] getEnergyGrid(){
        return energyGrid;
    }

    private void generateEnergyGrid() {
//        energyPic = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        invertedEnergyPic = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        energyGrid = new int[height][width];
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
                    if (maxEnergy < energy) {
                        maxEnergy = energy;
                    }
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    energyGrid[i][j] = maxEnergy;
                }
//                float grayEnergyPct = (float) energyGrid[i][j] / (float) maxEnergy;
//                energyPic.setRGB(j, i, new Color(grayEnergyPct, grayEnergyPct, grayEnergyPct).getRGB());
//                invertedEnergyPic.setRGB(i, j, new Color(grayEnergyPct, grayEnergyPct, grayEnergyPct).getRGB());


            }
        }
//        String fileOut = "generatedImage.jpg";
//        String invertedFileOut = "invertedGeneratedImage.jpg";
//        sendImageOut(energyPic, fileOut);
//        sendImageOut(invertedEnergyPic, invertedFileOut);
    }


//    private static void sendImageOut(BufferedImage image, String fileOut) {
//        try {
//            File outputfile = new File(fileOut);
//            ImageIO.write(image, "jpg", outputfile);
//
//        } catch (IOException e) {
//            System.out.println("could not save file..."); // use e.getMessage()
//        }
//    }
}
