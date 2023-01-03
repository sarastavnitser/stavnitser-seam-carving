import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class VerticalSeamCreator {
    private ImageObject imageObject;
    private int[][] energyGrid;
    private int[] points;
    private int[][] positions;
//    private int[] smallestSeam;
    private int[] newPoints;
    private int height;
    private int width;

    public VerticalSeamCreator(ImageObject imageObject) {
        this.imageObject = imageObject;
        this.energyGrid = imageObject.getEnergyGrid().getEnergyGrid();
        this.points = new int[imageObject.getWidth()];
        this.positions = new int[imageObject.getWidth()][imageObject.getHeight()];
        setPointsAndPositions();

    }

    public int[] createSmallestVerticalSeam() {

        int minPoint = points[0];
        int minEndPos = 0;
        for (int i = 0; i < this.points.length; i++) {
            if (points[i] < minPoint) {
                minPoint = points[i];
                minEndPos = i;
            }
        }
        int [] smallestSeam = new int[height];
        int currPos = minEndPos;
        for (int i = height-1;i >=0; i--){
            smallestSeam[i] = currPos;
            currPos = positions[currPos][i];
        }
        return smallestSeam;
    }

    private void setPointsAndPositions() {
        this.height = energyGrid.length;
        this.width = energyGrid[0].length;
        this.points = new int[width];
        this.positions = new int[width][height];
        for (int i = 0; i < height; i++) {
            this.newPoints = new int[width];
            for (int j = 0; j < width; j++) {
                if (i == 0) {
                    newPoints[j] = energyGrid[i][j];
                    positions[j][i] = j;
                } else if (j == 0) {
                    int posUp = j;
                    int posRight = j + 1;
                    int valUp = points[j];
                    int minVal = valUp;
                    int minPos = posUp;
                    int valRight = points[j + 1];
                    if (valRight < minVal) {
                        minVal = valRight;
                        minPos = posRight;
                    }
                    int thisEnergy = energyGrid[i][j];
                    positions[j][i] = minPos;
                    newPoints[j] = minVal + thisEnergy;
                } else if (j == width - 1) {
                    int posleft = j - 1;
                    int posUp = j;
                    int valLeft = points[j - 1];
                    int minVal = valLeft;
                    int minPos = posleft;
                    int valUp = points[j];
                    if (valUp < minVal) {
                        minVal = valUp;
                        minPos = posUp;
                    }
                    int thisEnergy = energyGrid[i][j];
                    positions[j][i] = minPos;
                    newPoints[j] = minVal + thisEnergy;
                } else {
                    int posleft = j - 1;
                    int posUp = j;
                    int posRight = j + 1;
                    int valLeft = points[j - 1];
                    int minVal = valLeft;
                    int minPos = posleft;
                    int valUp = points[j];
                    if (valUp < minVal) {
                        minVal = valUp;
                        minPos = posUp;
                    }
                    int valRight = points[j + 1];
                    if (valRight < minVal) {
                        minVal = valRight;
                        minPos = posRight;
                    }
                    int thisEnergy = energyGrid[i][j];
                    positions[j][i] = minPos;
                    newPoints[j] = minVal + thisEnergy;
                }
            }
            points = newPoints;
        }


    }

    public static void main(String[] args) throws IOException {
//        File imageFile = new File(ImageObject.class.getResource("Broadway_tower_edit.jpg").getPath());
        BufferedImage image = ImageIO.read(ImageFrame.class.getResourceAsStream("Broadway_tower_edit.jpg"));
        ImageObject o = new ImageObject(image);


//        System.out.println(Arrays.toString(s.points));
//        System.out.println(Arrays.deepToString(s.positions));
//        System.out.println(Arrays.toString(s.smallestSeam));
//        System.out.println(Arrays.deepToString(o.getEnergyGrid().getEnergyGrid()));
//        System.out.println(o.getPixelGrid()[0].length);
        for (int i = 0; i < 20; i ++){
            o.removeSmallestVerticalSeam();
            o.removeSmallestHorizontalSeam();
        }

//        System.out.println(o.getPixelGrid()[0].length);

//        System.out.println(o.getPixelGrid()[0].length);
//        System.out.println(Arrays.deepToString(o.getEnergyGrid().getEnergyGrid()));
//        System.out.println(Arrays.deepToString(o.getPixelGrid()));
        BufferedImage energyPic = new BufferedImage(o.getWidth(), o.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < o.getHeight(); i++) {
            for (int j = 0; j < o.getWidth(); j++) {
                energyPic.setRGB(j, i, o.getPixelGrid()[i][j].getRGB());

            }
        }
        String fileOut = "generatedImage.jpg";
        String invertedFileOut = "invertedGeneratedImage.jpg";
        sendImageOut(energyPic, fileOut);

//        System.out.println(Arrays.deepToString(r.removeSmallestSeam(o, s.smallestSeam)));
//        System.out.println(Arrays.deepToString(o.getEnergyGrid().getEnergyGrid()));

    HorizontalSeamCreator h = new HorizontalSeamCreator(o);
    int[][] griddy = new int[][]{{1,2},{1,2},{1,2}};
        System.out.println(Arrays.deepToString(griddy));
    griddy = h.invert2DArray(griddy);
        System.out.println(Arrays.deepToString(griddy));
        System.out.printf("done");
    }
    private static void sendImageOut(BufferedImage image, String fileOut) {
        try {
            File outputfile = new File(fileOut);
            ImageIO.write(image, "jpg", outputfile);

        } catch (IOException e) {
            System.out.println("could not save file..."); // use e.getMessage()
        }
    }



}
