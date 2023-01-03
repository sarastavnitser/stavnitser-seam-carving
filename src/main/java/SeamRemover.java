import java.awt.*;

public class SeamRemover {
    private ImageObject imageObject;



    public SeamRemover(){
    }

    public void removeSmallestVerticalSeam(ImageObject imageObject){
        int[] smallestSeam = new VerticalSeamCreator(imageObject).createSmallestVerticalSeam();
        Color[][] pixelGrid = imageObject.getPixelGrid();
        if(smallestSeam.length!=pixelGrid.length){
            //exception
//            return null;
        }
        else{
            int gridWidth  = pixelGrid[0].length;
            for (int i = 0 ; i < smallestSeam.length; i ++ ){
                Color[] newRow = new Color[gridWidth - 1];
                for (int j = 0; j <gridWidth - 1; j ++ ){
                    if (j < smallestSeam[i]){
                        newRow[j] = pixelGrid[i][j];
                    }
                    else{
                        newRow[j] = pixelGrid[i][j+1];
                    }
                }
                pixelGrid[i] = newRow;
            }
        }
        imageObject.setPixelGrid(pixelGrid);
        imageObject.setHeight(pixelGrid.length);
        imageObject.setWidth(pixelGrid[0].length);
        EnergyGrid energyGrid = new EnergyGrid(pixelGrid);
        imageObject.setEnergyGrid(energyGrid);
    }

    public Color[][] invert2DArray(Color[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        Color[][] newArray = new Color[cols][rows];
        for(int i = 0; i < cols; i ++){
            for (int j = 0; j < rows; j ++){
                newArray[i][j] = array[j][i];
            }
        }
        return newArray;
    }
    public void removeSmallestHorizontalSeam(ImageObject imageObject){
        HorizontalSeamCreator horizontalSeamCreator = new HorizontalSeamCreator(imageObject);
        int[] smallestSeam = horizontalSeamCreator.createSmallestHorizontalSeam();
        Color[][] pixelGrid = imageObject.getPixelGrid();
        pixelGrid = invert2DArray(pixelGrid);
        if(smallestSeam.length!=pixelGrid.length){
            //exception
//            return null;
        }
        else{
            int gridWidth  = pixelGrid[0].length;
            for (int i = 0 ; i < smallestSeam.length; i ++ ){
                Color[] newRow = new Color[gridWidth - 1];
                for (int j = 0; j <gridWidth - 1; j ++ ){
                    if (j < smallestSeam[i]){
                        newRow[j] = pixelGrid[i][j];
                    }
                    else{
                        newRow[j] = pixelGrid[i][j+1];
                    }
                }
                pixelGrid[i] = newRow;
            }
        }
        pixelGrid = invert2DArray(pixelGrid);
        imageObject.setPixelGrid(pixelGrid);
        imageObject.setHeight(pixelGrid.length);
        imageObject.setWidth(pixelGrid[0].length);
        EnergyGrid energyGrid = new EnergyGrid(pixelGrid);
        imageObject.setEnergyGrid(energyGrid);
    }


}
