package com.fileAnalysis.modules;

import java.io.File;
import java.util.Scanner;

public class DirectoryModule implements CommonModule {
    private File file;

    public boolean checkExtention(String extention) {
        file = new File("/Users/pavelzaborin/MWA/" + extention);
        boolean a = file.isDirectory();
        return a;
    }

    public void chooseFunction(String name) {
        try {
            file = new File("/Users/pavelzaborin/MWA/" + name);
            while (true){
                Scanner in = new Scanner(System.in);
                System.out.print(" \nYour file it's directory. Input number of function, which you like:\n" +
                        "1 - List of files on this directory\n" +
                        "2 - Look total size of this directory\n" +
                        "3 - Exit from this module\n");
                int num = in.nextInt();
                while (num != 1 && num != 2 && num != 3){
                    System.out.println("Wrong number, try again: \n");
                    num = in.nextInt();
                }
                switch (num){
                    case 1:
                        writeTree();
                        break;
                    case 2:
                        writeSize();
                        break;
                    case 3:
                        return;
                }
            }
        }
        catch (Exception e){
            System.out.println("SOMETHING WRONG WITH CHOOSE OPERATION FOR DIRECTORY \n");
        }
    }
    private void writeTree(){
        try {
            File[] files = file.listFiles();
            if (files == null || files.length == 0){
                System.out.println("In your directory no files \n");
            }
            else {
                System.out.println(" \nFiles in your directory: ");
                for (File f : files){
                    System.out.println(f.getName());
                }
            }
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH LOOK FILES IN THIS DIRECTORY \n");
        }
    }

    private void writeSize(){
        try {
            System.out.println(" \nTotal size of your directory is " + getDirSize(file) + " bytes \n");
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH LOOK SIZE OF THIS DIRECTORY \n");
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
            System.out.println(" \nSOMETHING WRONG WITH LOOK SIZE OF THIS DIRECTORY \n");
            return -1;
        }
    }
}
