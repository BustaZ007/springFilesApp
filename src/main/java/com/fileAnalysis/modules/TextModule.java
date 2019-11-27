package com.fileAnalysis.modules;

import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;

public class TextModule implements CommonModule {

    private File file;

    public boolean checkExtention(String extention) {
        int index = extention.indexOf('.');
        return index != -1 && extention.substring(index).equals(".txt");
    }

    public void chooseFunction(String name) {
        try {
            file = new File("/Users/pavelzaborin/MWA/" + name);
            while (true){
                Scanner in = new Scanner(System.in);
                System.out.print(" \nYour file it's text. Input number of function, which you like:\n" +
                        "1 - Print a number of lines\n" +
                        "2 - Print the frequency of occurrence of a character\n" +
                        "3 - Print charset of this text file\n" +
                        "4 - Exit from this module \n");
                int num = in.nextInt();
                while (num != 1 && num != 2 && num != 3 && num != 4){
                    System.out.println(" \nWrong number, try again: \n ");
                    num = in.nextInt();
                }
                switch (num){
                    case 1:
                        numberLines();
                        break;
                    case 2:
                        charFreq();
                        break;
                    case 3:
                        checkCharset();
                        break;
                    case 4:
                        return;
                }
            }
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH CHOOSE OPERATION FOR TEXT DOCUMENT \n");
        }
    }

    private void charFreq(){
        try {
            String text = "";
            Scanner in = new Scanner(file);
            while(in.hasNext())
                text = text.concat(in.nextLine() + "\r\n");
            in.close();

            TreeMap<Character, Integer> userList = new TreeMap<Character, Integer>();

            for (int i = 0; i < text.length(); i++) {
                Character key = text.charAt(i);
                userList.put(key, userList.getOrDefault(key, 0) + 1);
            }
            System.out.println(" \nIf you see an empty character it means a space character! \n");
            for (Character ch : userList.keySet()){
                System.out.println(ch.toString() + " = " + userList.get(ch));
            }
        }
        catch (Exception e){

        }
    }

    private void numberLines(){
        try {
            FileReader fileReader = new FileReader(file);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);

            int lineNumber = 0;

            while (lineNumberReader.readLine() != null){
                lineNumber++;
            }

            System.out.println(" \nCount of line in this text document: " + lineNumber);

            lineNumberReader.close();
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH COUNT LINES IN TEXT DOCUMENT \n");
        }
    }

    private void checkCharset(){
        try {
            OutputStream os = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(os);
            System.out.println(" \n" + writer.getEncoding() + " \n");
            os.close();
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH GET CHARSET FROM TEXT DOCUMENT \n");
        }
    }
}
