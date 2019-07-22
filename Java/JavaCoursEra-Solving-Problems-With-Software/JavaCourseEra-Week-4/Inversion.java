import edu.duke.*;
import java.io.*;

public class Inversion
{
    //I started with the image I wanted (inImage)
    private static ImageResource makeInversion(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //set pixel's red to 255- inPixel.getRed()
            pixel.setRed(255- inPixel.getRed());
            //set pixel's green
            pixel.setGreen(255- inPixel.getGreen());
            //set pixel's blue
            pixel.setBlue(255- inPixel.getBlue() );
        }
        //outImage is your answer
        return outImage;
    }

    private static void selectAndConvert()
    {
        DirectoryResource directory = new DirectoryResource();
        for(File file: directory.selectedFiles())
        {
            ImageResource image = new ImageResource(file);
            ImageResource invertedImage = makeInversion(image);
            String fname = image.getFileName();
            String newName = "inverted-"+ fname;
            invertedImage.setFileName("./images/" + newName);
            invertedImage.draw();
            invertedImage.save();
        }
    }

    // demo
    public static void main(String[] args) {
        selectAndConvert();
    }
}