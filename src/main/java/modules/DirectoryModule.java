package modules;

import java.io.File;
import java.util.Scanner;

public class DirectoryModule implements CommonModule {
    File file;

    public boolean checkExtention(String extention) {
        file = new File(extention);
        return file.isDirectory();
    }

    public void chooseFunction(String name) {
        try {
            file = new File("/Users/pavelzaborin/MWA/" + name);
            while (true){
                Scanner in = new Scanner(System.in);
                System.out.print("Your file it's directory. Input number of function, which you like:\n" +
                        "1 - List of files on this directory\n" +
                        "2 - Look total size of this directory");
                int num = in.nextInt();
                while (num != 1 && num != 2){
                    System.out.println("Wrong number, try again: ");
                    num = in.nextInt();
                }
                switch (num){
                    case 1:
                        writeTree();
                        break;
                    case 2:
                        writeSize();
                        break;
                }
            }
        }
        catch (Exception e){
            System.out.println("SOMETHING WRONG WITH CHOOSE OPERATION FOR DIRECTORY");
        }
    }
    private void writeTree(){
        try {
            File[] files = file.listFiles();
            if (files == null || files.length == 0){
                System.out.println("In your directory no files");
            }
            else {
                System.out.println("Files in your directory: ");
                for (File f : files){
                    System.out.println(f.getName());
                }
            }
        }
        catch (Exception e){
            System.out.println("SOMETHING WRONG WITH LOOK FILES IN THIS DIRECTORY");
        }
    }

    private void writeSize(){
        try {
            System.out.println("Total size of your directory is " + getDirSize(file) + " bytes");
        }
        catch (Exception e){
            System.out.println("SOMETHING WRONG WITH LOOK SIZE OF THIS DIRECTORY");
        }
    }

    private long getDirSize(File dir) {
        try {
            long size = 0;
            if (dir.isFile()) {
                size = dir.length();
            } else {
                File[] subFiles = dir.listFiles();
                for (File file : subFiles) {
                    if (file.isFile()) {
                        size += file.length();
                    } else {
                        size += getDirSize(file);
                    }
                }
            }
            return size;
        }
        catch (Exception e ){
            System.out.println("SOMETHING WRONG WITH LOOK SIZE OF THIS DIRECTORY");
            return -1;
        }
    }
}
