/**
 * Created by leader on 17/8/13.
 */
import java.util.*;

//component
interface AbstractFile {
    void ls();
}
//leaf
class File implements AbstractFile {
    private String name;

    public File(String name) {
        this.name = name;
    }
    public void ls() {
        System.out.println(CompositeDemo.compositeBuilder + name);
    }
}
//composite
class Directory implements AbstractFile {
    private String name;
    private ArrayList includedFiles = new ArrayList();

    public Directory(String name) {
        this.name = name;
    }
    public void add(Object obj) {
        includedFiles.add(obj);
    }
    public void ls() {
        System.out.println(CompositeDemo.compositeBuilder + name);
        CompositeDemo.compositeBuilder.append("   ");
        for (Object includedFile : includedFiles) {
            AbstractFile obj = (AbstractFile) includedFile;
            obj.ls();
        }
        CompositeDemo.compositeBuilder.setLength(
                CompositeDemo.compositeBuilder.length() - 3);
    }
}

public class CompositeDemo {
    public static StringBuffer compositeBuilder = new StringBuffer();

    public static void main(String[] args) {
        Directory music = new Directory("music");
        Directory scorpions = new Directory("Scorpions");
        Directory jon = new Directory("Jon");
        File track1 = new File("Forrest Gump Suite.mp3");
        File track2 = new File("Last Goodbye.mp3");
        File track3 = new File("Love Story meets Viva La Vida.mp3");
        File track4 = new File("Long Long Ago.mp3");
        File track5 = new File("Peanuts Medley Live.mp3");
        File track6 = new File("Wind of change.mp3");
        music.add(track1);
        music.add(jon);
        music.add(track2);
        jon.add(track3);
        jon.add(track4);
        jon.add(track5);
        music.add(scorpions);
        scorpions.add(track6);
        music.ls();
    }
}
