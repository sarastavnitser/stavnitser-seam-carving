import java.awt.image.BufferedImage;
import java.util.Arrays;

public class SeamCreator {
    private ImageObject imageObject;
    private int[][] energyGrid;
    private int[] points;
    private int[][] positions;
    private int[] smallestSeam;
    private int[] newPoints;
    private int height;
    private int width;

    public SeamCreator(ImageObject imageObject) {
        this.imageObject = imageObject;
        this.energyGrid = imageObject.getEnergyGrid().getEnergyGrid();
        this.points = new int[imageObject.getWidth()];
        this.positions = new int[imageObject.getWidth()][imageObject.getHeight()];
        setPointsAndPositions();
        createSmallestSeam();
    }

    private void createSmallestSeam() {
        int minPoint = points[0];
        int minEndPos = 0;
        for (int i = 0; i < this.points.length; i++) {
            if (points[i] < minPoint) {
                minPoint = points[i];
                minEndPos = i;
            }
        }
        smallestSeam = new int[height];
        int currPos = minEndPos;
        for (int i = height-1;i >=0; i--){
            smallestSeam[i] = currPos;
            currPos = positions[currPos][i];
        }
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

    public static void main(String[] args) {
        ImageObject o = new ImageObject();
        SeamCreator s = new SeamCreator(o);

        System.out.println(Arrays.toString(s.points));
        System.out.println(Arrays.deepToString(s.positions));
        System.out.println(Arrays.toString(s.smallestSeam));
        System.out.printf("done");
    }


    public int[] getSmallestSeam() {
        return smallestSeam;
    }

}
