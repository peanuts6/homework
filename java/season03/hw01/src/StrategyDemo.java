/**
 * Created by xqy on 17/8/15.
 */
import java.util.*;

interface CompressionStrategy {
    public void compressFiles(List<File> files);
}
class ZipCompressionStrategy implements CompressionStrategy {
    public void compressFiles(List<File> files) {
        System.out.println("ZIP approach");
    }
}
class RarCompressionStrategy implements CompressionStrategy {
    public void compressFiles(List<File> files) {
        System.out.println("RAR approach");
    }
}
class CompressionContext {
    private CompressionStrategy strategy;
    //运行时设置算法
    public void setCompressionStrategy(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    //匹配对应算法
    public void createArchive(List<File> files) {
        strategy.compressFiles(files);
    }
}
public class StrategyDemo {
    public static void main(String[] args) {
        List <File> fileList = new ArrayList<File>();
        fileList.add(new File("./a.png"));
        fileList.add(new File("./b.gif"));
        fileList.add(new File("./c.jpg"));
        CompressionContext ctx = new CompressionContext();
        ctx.setCompressionStrategy(new ZipCompressionStrategy());
        ctx.createArchive(fileList);
    }
}
