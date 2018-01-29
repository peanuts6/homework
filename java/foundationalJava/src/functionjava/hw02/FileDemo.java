package functionjava.hw02;

import functionjava.hw01.Salary;
import functionjava.hw01.Q72;
import functionjava.hw01.SortUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by xqy on 18/1/18.
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        String fileUrl = "./salary.txt";
//        String encoding = System.getProperty("file.encoding");
//        System.out.println(encoding + " "+ Charset.defaultCharset());

//        writeFile(fileUrl);

//        testSort(200);

//        readFile(fileUrl);

//        readFileWithFileChannel(fileUrl);

//        readFileWithFileChannel2(fileUrl);


    }

    public static void writeFile(String fileUrl){
        PrintWriter outputStream = null;
        Salary[] arr = SortUtil.makeArray(10000000);
        try {
            outputStream = new PrintWriter(new FileWriter(fileUrl));
            Arrays.stream(arr).forEach(outputStream::println);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream!=null) outputStream.close();
        }
    }

    public static void sortList(List<Salary> lists){
        lists.stream()
                .collect(Collectors.groupingBy(s->s.name.substring(0,2)))
                .entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> {
                            long c=e.getValue().stream()
                            .collect(Collectors.summarizingLong(s->s.totalIncome)).getSum()/10000;
                            int b = (int) e.getValue().stream().count();
                            return new Result(c,b);
                        }
                ))
                .entrySet().stream()
                .sorted(new Comparator<Map.Entry<String, Result>>() {
                    @Override
                    public int compare(Map.Entry<String, Result> o1, Map.Entry<String, Result> o2) {
                        return (int) (o2.getValue().totalIncome - o1.getValue().totalIncome);
                    }
                })
                .limit(10)
                .forEach(e->System.out.println(e.getKey()+","+e.getValue().toString()+""));
    }
    public static void testSort(int N){
        List<Salary> list = SortUtil.makeList(N);
        sortList(list);
    }

    public static Salary getSalary(String s){
        String[] a = s.split(",");
        Salary salary = new Salary(a[0],Integer.valueOf(a[1]),Integer.valueOf(a[2]));
        return salary;
    }
    public static void readFile(String fileUrl){
        long start = System.currentTimeMillis();
        BufferedReader inputStream = null;
        String l;
        List<Salary> arr = new ArrayList<>();
        try {
            inputStream = new BufferedReader(new FileReader(fileUrl));
            while((l=inputStream.readLine())!=null){
                arr.add(getSalary(l));
                //System.out.println(l);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null) try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("BufferedReader一行一行读取的速度："+(System.currentTimeMillis()-start));
        sortList(arr);
    }
    public static void readFileWithFileChannel(String fileUrl) throws IOException {
        long start = System.currentTimeMillis();
        RandomAccessFile file = null;
        file = new RandomAccessFile(fileUrl,"rw");
        FileChannel fileChannel = file.getChannel();
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,fileChannel.size());
        ByteBuffer buf = buffer.load();
        System.out.println("一次性MAP读取的速度："+(System.currentTimeMillis()-start));
            List<Salary> arr = new ArrayList<>();
        int block = 64*100*1024;
        String s;
        int index = 0;
        while(buf.remaining()>0) {
            byte[] bb = new byte[buf.remaining()>block?block:buf.remaining()];
            buf.get(bb);
            s = new String(bb);
            int split = s.lastIndexOf("\n")+1;
            byte[] b = Arrays.copyOf(bb,split);
            index += split;
            buf.position(index+1);
            String a = new String(b);
            String[] ss = a.split("\n");
            for (int i = 0; i < ss.length; i++) {
                arr.add(getSalary(ss[i]));
            }
            //System.out.print(a);
        }
        buf.clear();
        fileChannel.close();
        file.close();
        System.out.println("一次性解析玩字符的速度："+(System.currentTimeMillis()-start));
        sortList(arr);
    }
    public static void readFileWithFileChannel2(String fileUrl) throws IOException {
        long start = System.currentTimeMillis();
        RandomAccessFile file = null;
        file = new RandomAccessFile(fileUrl,"r");
        FileChannel fileChannel = file.getChannel();
        // 每次64K
        int block = 64*1024;
        ByteBuffer buffer = ByteBuffer.allocate(block);
        int read = 0;
        String s,sss;
        String[] ss;
        List<Salary> arr = new ArrayList<>();
        while((read = fileChannel.read(buffer))!=-1){
            //翻转状态，初始位置为0
            buffer.flip();
            s = new String(buffer.array());
            int split = s.lastIndexOf('\n')+1;
            byte[] buf = new byte[split];
            //把buffer复制给buf，相当于一次性读取1024字节
            buffer.get(buf,0,buf.length>buffer.remaining()?buffer.remaining():split);
            buffer.compact();
            sss = new String(buf);
            ss = sss.split("\n");
            for(int i=0;i<ss.length;i++){
                if(ss[i].trim().equals("")) break;
                arr.add(getSalary(ss[i]));
            }
            //System.out.println(sss);
        }
        fileChannel.close();
        file.close();
        System.out.println("一块一块buffer读取和解析完字符的速度："+(System.currentTimeMillis()-start));
        sortList(arr);
    }



    public static void readFileWithFileChannel3(String fileUrl) throws IOException {
        RandomAccessFile file = null;
            file = new RandomAccessFile(fileUrl,"r");
            FileChannel fileChannel = file.getChannel();
            // 每次1K
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = 0;
            while((read = fileChannel.read(buffer))!=-1){
                while (buffer.hasRemaining()){
                    System.out.print((char) buffer.get());
                }
                System.out.println("read bytes: " + read);
                buffer.clear();
            }

        fileChannel.close();
        file.close();
    }

}
