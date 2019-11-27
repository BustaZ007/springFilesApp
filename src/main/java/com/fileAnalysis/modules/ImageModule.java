package com.fileAnalysis.modules;

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
                System.out.print(" \nYour file it's image. Input number of function, which you like:\n" +
                        "1 - Size information output\n" +
                        "2 - Exif information output\n" +
                        "3 - Display image \n" +
                        "4 - Exit from this module \n");
                int num = in.nextInt();
                while (num != 1 && num != 2 && num != 3 && num != 4){
                    System.out.println(" \nWrong number, try again:  \n");
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
                    case 4:
                        return;
                }
            }
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH CHOOSE OPERATION FOR IMAGE \n");
        }
    }

    private void writeExif(){
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            Iterable<Directory> m = metadata.getDirectories();
            for (Directory d : m){
                if (d.toString().contains("Exif")){
                    System.out.println(" \n" + d);
                    Collection<Tag> t = d.getTags();
                    for (Tag tag : t){
                        System.out.println("\t" + tag.getTagName() + " : " + tag.getDescription());
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH EXIF INFORMATION ON IMAGE \n");
        }

    }

    private void writeSize(){
        try {
            MBFImage image = ImageUtilities.readMBF(file);
            System.out.println(" \nSize of image: " +  image.getWidth() + "x" + image.getHeight());
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH GETTING SIZE ON IMAGE \n");
        }
    }

    private void displayImage(){
        try {
            MBFImage image = ImageUtilities.readMBF(file);
            DisplayUtilities.display(image);
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH DISPLAYING IMAGE \n");
        }
    }
}
