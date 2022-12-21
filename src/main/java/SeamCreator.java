import java.awt.image.BufferedImage;

public class SeamCreator {
    private ImageObject imageObject;

    private EnergyGrid energyGrid;


    private int[] energyScores;

    private int[][] energyPaths;

    private int[] smallestSeam;

    public SeamCreator(ImageObject imageObject) {
        this.imageObject = imageObject;
        this.energyGrid = imageObject.getEnergyGrid();
        this.energyScores = new int[imageObject.getWidth()];
        this.energyPaths = new int[imageObject.getWidth()][imageObject.getWidth()];
    }

    public void createSmallestSeam() {
        //condition if length 3 or smaller
        for (int i = 0; i < imageObject.getHeight(); i++) {
            if(i == 0 || i == 1){
                continue;
            }
            else{
                for (int j = 0;j < imageObject.getWidth(); i ++){
                    if(j == 0 ||  j == imageObject.getWidth() -2 || j == imageObject.getWidth()-1){
                        continue;
                    } else if (j == 1) {
                        energyScores[j] = 0;
                    } else{

                        int leftPlace = j-1;
                        int leftValue = energyGrid.getEnergyGrid()[i-1][j-1];

                        int minValue =leftValue;
                        int minPlace = leftPlace;

                        int upPlace = j;
                        int upValue = energyGrid.getEnergyGrid()[i-1][j];

                        if(upValue<minValue){
                            minValue = upValue;
                            minPlace = upPlace;
                        }
                        int rightPlace = j+1;
                        int rightValue = energyGrid.getEnergyGrid()[i-1][j+1];

                        if(rightValue<minValue){
                            minValue = rightValue;
                            minPlace = rightPlace;
                        }


                    }



                }
            }
        }
    }

    public int[] getSmallestSeam() {
        return smallestSeam;
    }

}
