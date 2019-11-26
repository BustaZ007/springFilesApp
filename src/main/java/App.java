
import com.drew.imaging.*;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException, ImageProcessingException {
        try {
//            Mp3File m = new Mp3File("/Users/pavelzaborin/MWA/2.mp3");
//            ID3v2 i = m.getId3v2Tag();
//            System.out.println(i.getLength());
//            System.out.println(i.getFrameSets());

            File file = new File("/Users/pavelzaborin/MWA");
            System.out.println(file.length());
            System.out.println(getDirSize(file));
        }
        catch (Exception e){

        }
    }

    public App(){};

    public static long getDirSize(File dir) {
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

}
