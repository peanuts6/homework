/**
 * Created by leader on 17/8/11.
 */

interface ImageReader {
    DecodedImage getDecodeImage();
}
class GifReader implements ImageReader {
    private DecodedImage decodedImage;

    public GifReader(String image) {
        this.decodedImage = new DecodedImage(image);
    }

    @Override
    public DecodedImage getDecodeImage() {
        return decodedImage;
    }
}
class JpegReader implements ImageReader {
    private DecodedImage decodedImage;

    public JpegReader(String image) {
        decodedImage = new DecodedImage(image);
    }

    @Override
    public DecodedImage getDecodeImage() {
        return decodedImage;
    }
}
class PngReader implements ImageReader {
    private DecodedImage decodedImage;

    public PngReader(String image) {
        decodedImage = new DecodedImage(image);
    }

    @Override
    public DecodedImage getDecodeImage() {
        return decodedImage;
    }
}

class DecodedImage {
    private String image;

    public DecodedImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return image + ": is decoded";
    }
}

public class FactoryMethodDemo {
    public static void main(String[] args){
        DecodedImage decodedImage;
        ImageReader reader = null;
        String[] images = {"./images/a.gif","./images/b.jpeg","./images/c.png"};
        String format;
        for(int i=0;i<images.length;i++){
            format = images[i].substring(images[i].lastIndexOf('.') + 1, (images[i].length()));
            switch (format){
                case "gif":
                    reader = new GifReader(images[i]);
                    break;
                case "jpeg":
                    reader = new JpegReader(images[i]);
                    break;
                case "png":
                    reader = new PngReader(images[i]);
                    break;
            }
            assert reader != null;
            decodedImage = reader.getDecodeImage();
            System.out.println(decodedImage);
        }
    }
}
