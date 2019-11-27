package com.fileAnalysis.modules;


import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class MusicModule implements CommonModule {

    private File file;

    public boolean checkExtention(String extention) {
        int index = extention.indexOf('.');
        return index != -1 && extention.substring(index).equals(".mp3");
    }

    public void chooseFunction(String name) {
        try {
            file = new File("/Users/pavelzaborin/MWA/" + name);
            while (true){
                Scanner in = new Scanner(System.in);
                System.out.print(" \nYour file it's music. Input number of function, which you like:\n" +
                        "1 - Title information from tags output\n" +
                        "2 - Look duration of track\n" +
                        "3 - Display image\n" +
                        "4 - Exit from this module \n");
                int num = in.nextInt();
                while (num != 1 && num != 2 && num != 3 && num != 4){
                    System.out.println(" \nWrong number, try again:  \n");
                    num = in.nextInt();
                }
                switch (num){
                    case 1:
                        writeTitle();
                        break;
                    case 2:
                        writeLong();
                        break;
                    case 3:
                        writeArtist();
                        break;
                    case 4:
                        return;
                }
            }
        }
        catch (Exception e){
            System.out.println("\nSOMETHING WRONG WITH CHOOSE OPERATION FOR MUSIC \n");
        }
    }

    private void writeTitle(){
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            MP3File mp3 = (MP3File) audioFile;
            for(String t : mp3.getTag().getAll(FieldKey.TITLE)){
                System.out.println(" \nTitle of music: " + t);
            }
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH LOOK TITLE INFORMATION IN MUSIC \n");
        }
    }

    private void writeLong(){
        try {
            FileInputStream fileS = new FileInputStream(file);
            Bitstream bitstream = new  Bitstream(fileS);
            Header h = bitstream.readFrame();
            long tn = fileS.getChannel().size();
            System.out.println(" \nDuration of this track in seconds: " + Math.round(h.total_ms((int) tn)/1000));
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH CHECK DURATION OF MUSIC \n");
        }
    }

    private void writeArtist(){
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            MP3File mp3 = (MP3File) audioFile;
            for(String t : mp3.getTag().getAll(FieldKey.ARTIST)){
                System.out.println(" \nArtist of music: " + t);
            }
        }
        catch (Exception e){
            System.out.println(" \nSOMETHING WRONG WITH LOOK ARTIST INFORMATION IN MUSIC \n");
        }
    }
}
