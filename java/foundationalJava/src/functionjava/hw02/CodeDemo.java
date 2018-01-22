package functionjava.hw02;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by xqy on 18/1/17.
 */
public class CodeDemo {
    public static void main(String[] args){
//        System.out.println("Default Charset: " + Charset.defaultCharset().displayName());
//        String s="中国";
//        ByteBuffer utf8 = Charset.forName("UTF-8").encode(s);
//        ByteBuffer gbk = Charset.forName("GBK").encode(s);
//        ByteBuffer latin = Charset.forName("ISO-8859-1").encode(s);
//        System.out.println("utf8 encode: len="+utf8.array().length+"");
//        System.out.print("              code = ");
//        while (utf8.hasRemaining()){
//            System.out.print((char) utf8.get()); // 返回1byte数据
//        }
//        System.out.println();
//
//        System.out.println("gbk encode: len="+ gbk.array().length+ "");
//        System.out.print("              code = ");
//        while (gbk.hasRemaining()){
//            System.out.print((char) gbk.get()); // 返回1byte数据
//        }
//        System.out.println();
//
//        System.out.println("latin encode: len="+latin.array().length+"");
//        System.out.print("              code = ");
//        while (latin.hasRemaining()){
//            System.out.print((char) latin.get()); // 返回1byte数据
//        }
//        System.out.println();

        //2
        int a=10240;
        OutputStream outBigEndian,outLittleEndian;
        InputStream inBigEndian,inLittleEndian;
        Path bigEndian = Paths.get("./bigEndian.txt");
        Path littleEndian = Paths.get("./littleEndian.txt");
        try {
            outBigEndian = new FileOutputStream(bigEndian.toFile());
            outLittleEndian = new FileOutputStream(littleEndian.toFile());
            byte[] bytes1 = intToBigEndianBytes(a);
            outBigEndian.write(bytes1);
            outBigEndian.close();
            byte[] bytes2 = intToLittleEndianBytes(a);
            outLittleEndian.write(bytes2);
            outLittleEndian.close();
            inBigEndian = new FileInputStream(bigEndian.toFile());
            int c = -1;
            byte[] bigbytes = Files.readAllBytes(bigEndian);
            System.out.print("BigEndian: ");
            while((c=inBigEndian.read())!=-1){
                System.out.print((char) c);
            }
            System.out.println(" int="+bigEndianBytesToInt(bigbytes));
            inBigEndian.close();
            inLittleEndian = new FileInputStream(littleEndian.toFile());
            int d = -1;
            byte[] littlebytes = Files.readAllBytes(littleEndian);
            System.out.print("LittleEndian: ");
            while ((d=inLittleEndian.read())!=-1){
                System.out.print((char) d);
            }
            System.out.println(" int="+littleEndianBytesToInt(littlebytes));
            inLittleEndian.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] intToBigEndianBytes(int i){
        byte[] b = new byte[4];
        b[0] = (byte) ((0xff000000&i)>>24);
        b[1] = (byte) ((0x00ff0000&i)>>16);
        b[2] = (byte) ((0x0000ff00&i)>>8);
        b[3] = (byte) (0x000000ff&i);
        return b;
    }
    public static byte[] intToLittleEndianBytes(int i){
        byte[] b = new byte[4];
        b[0] = (byte) (0x000000ff&i);
        b[1] = (byte) ((0x0000ff00&i)>>8);
        b[2] = (byte) ((0x00ff0000&i)>>16);
        b[3] = (byte) (i>>24);
        return b;
    }
    public static int bigEndianBytesToInt(byte[] b){
        return (b[0]<<24) | (b[1]<<16) | (b[2]<<8) | b[3];
    }
    public static int littleEndianBytesToInt(byte[] b){
        return (b[3]<<24) | (b[2]<<16) | (b[1]<<8) | b[0];
    }
}
