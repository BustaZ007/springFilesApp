package modules;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

import java.io.File;
import java.util.Collection;
import java.util.Scanner;

public class ImageModule implements CommonModule {

    private File file;

    public boolean checkExtention(String extention) {
        int index = extention.indexOf('.');
        return index != -1 && extention.substring(index).equals(".jpg");
    }

    public void chooseFunction(String name){
        try {
            file = new File("/Users/pavelzaborin/MWA/" + name);
            while (true){
                Scanner in = new Scanner(System.in);
                System.out.print("Your file it's image. Input number of function, which you like:\n" +
                        "1 - Size information output\n" +
                        "2 - Exif information output\n" +
                        "3 - Display image");
                int num = in.nextInt();
                while (num != 1 && num != 2 && num != 3){
                    System.out.println("Wrong number, try again: ");
                    num = in.nextInt();
                }
                switch (num){
                    case 1:
                        writeSize();
                        break;
                    case 2:
                        writeExif();
                        break;
                    case 3:
                        displayImage();
                        break;
                }
            }
        }
        catch (Exception e){
            System.out.println("SOMETHING WRONG WITH CHOOSE OPERATION FOR IMAGE");
        }
    }

    private void writeExif(){
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            Iterable<Directory> m = metadata.getDirectories();
            for (Directory d : m){
                if (d.toString().contains("Exif")){
                    System.out.println(d);
                    Collection<Tag> t = d.getTags();
                    for (Tag tag : t){
                        System.out.println("\t" + tag.getTagName() + " : " + tag.getDescription());
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("SOMETHING WRONG WITH EXIF INFORMATION ON IMAGE");
        }

    }

    private void writeSize(){
        try {
            MBFImage image = ImageUtilities.readMBF(file);
            System.out.println("Size of image: " +  image.getWidth() + "x" + image.getHeight());
        }
        catch (Exception e){
            System.out.println("SOMETHING WRONG WITH GETTING SIZE ON IMAGE");
        }
    }

    private void displayImage(){
        try {
            MBFImage image = ImageUtilities.readMBF(file);
            DisplayUtilities.display(image);
        }
        catch (Exception e){
            System.out.println("SOMETHING WRONG WITH DISPLAYING IMAGE");
        }
    }
}
