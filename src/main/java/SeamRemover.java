public class SeamRemover {
    private ImageObject imageObject;
    private int[] smallestSeam;

    public SeamRemover(ImageObject imageObject, int[] smallestSeam){
        this.imageObject = imageObject;
        this.smallestSeam = smallestSeam;
    }
}
