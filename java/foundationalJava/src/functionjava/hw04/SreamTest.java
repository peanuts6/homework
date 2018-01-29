package functionjava.hw04;

import functionjava.hw01.SortUtil;
import functionjava.hw02.Result;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Created by xqy on 18/1/26.
 */
public class SreamTest {
    public static void main(String[] args) throws IOException {
//        List<Salary> lists = makeList(1000000);
//        long start = System.nanoTime();
//        sortList(lists);
//        System.out.println("parallelStream with forEachOrdered 耗时："+(System.nanoTime()-start));
//        incomes.parallelStream()
//                .collect(Collectors.groupingBy(Map.Entry::getKey,mapping(Map.Entry::getValue,toList())))
//                .entrySet().stream().collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        e->{
//                            long sum = e.getValue().stream().collect(Collectors.summarizingLong(s->s.totalIncome)).getSum()/10000;
//                            int c = (int) e.getValue().stream().count();
//                            return new Result(sum,c);
//                        }
//                ))
//                .entrySet().stream().sorted(new Comparator<Map.Entry<String, Result>>() {
//                    @Override
//                    public int compare(Map.Entry<String, Result> o1, Map.Entry<String, Result> o2) {
//                        return (int) (o2.getValue().totalIncome - o1.getValue().totalIncome);
//                    }
//                })
//                .limit(10)
//                .forEach(x->System.out.println(x.getKey()+","+x.getValue()+""));


        List<Salary> lists = readFileWithFileChannel2("./salary.txt");

        List<Map.Entry<String,Result>> incomes = lists.parallelStream().collect(new SalaryCollector());
        incomes.stream()
                .limit(10)
                .forEach(a->System.out.println(a.getKey()+","+a.getValue()));

    }

    public static List<Salary> makeList(int N){
        Random r = new Random();
        List<Salary> arr = new ArrayList<>();
        for(int i=0;i<N;i++){
            arr.add(new Salary(SortUtil.RandomString(5),r.nextInt(100000-5000)+5000,r.nextInt(100000)));
        }
        return arr;
    }
    public static void sortList(List<Salary> lists){
        lists.parallelStream()
                .filter(l->{return l.totalIncome>=100000;})
                .collect(Collectors.groupingBy(s->s.name.substring(0,2)))
                .entrySet().parallelStream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> {
                    long c=e.getValue().parallelStream()
                            .collect(Collectors.summarizingLong(s->s.totalIncome)).getSum()/10000;
                    int b = (int) e.getValue().parallelStream().count();
                    return new Result(c,b);
                }
        ))
                .entrySet().parallelStream()
                .sorted(new Comparator<Map.Entry<String, Result>>() {
                    @Override
                    public int compare(Map.Entry<String, Result> o1, Map.Entry<String, Result> o2) {
                        return (int) (o2.getValue().totalIncome - o1.getValue().totalIncome);
                    }
                })
                .limit(10)
                .forEachOrdered(e->System.out.println(e.getKey()+","+e.getValue().toString()+""));
    }
    public static Salary getSalary(String s){
        String[] a = s.split(",");
        Salary salary = new Salary(a[0],Integer.valueOf(a[1]),Integer.valueOf(a[2]));
        return salary;
    }
    public static List<Salary> readFileWithFileChannel2(String fileUrl) throws IOException {
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
        return arr;
    }
}
