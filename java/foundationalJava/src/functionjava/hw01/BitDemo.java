package functionjava.hw01;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by xqy on 18/1/15.
 */
public class BitDemo {
    public static void main(String[] args){
        //q1();
        //q2();
        q3();

        byte[] a = {1,2,3, 5,3,4, 3,5,9};
        Integer[] b = {0,1,2};
        Arrays.sort(b, Comparator.comparingInt(x->a[3 * x]));
        for(int i=0;i<3;i++){
            System.out.print(a[3*b[i]]);
            System.out.print(",");
            System.out.print(a[3*b[i]+1]);
            System.out.print(",");
            System.out.print(a[3*b[i]+2]);
            System.out.println(",");
        }
    }

    public static void q1(){
        byte ba=127;
        int bb=ba<<2;
        byte cc = (byte) ((byte) ba<<2);
        System.out.println("ba<<2,cast to int: "+bb);
        System.out.println("ba<<2,cast to byte: "+cc);
    }
    public static void q2(){
        int a=-1024;
        for(int i=a;i<0;i++){
            System.out.print("" + a%128);
            if(i%8 == 0 ){
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println("a>>1: "+ (a>>1));
        System.out.println("a>>>1: "+ (a>>>1));
    }
    public static void q3(){
        int SIZE=10240;
        byte[][] arr = new byte[SIZE][SIZE];
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                arr[i][j] = 1;
            }
        }

        long sum1 = 0;
        long sum2 = 0;

        // 行优先
        long start1 = System.currentTimeMillis();
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                sum1 += arr[i][j];
            }
        }
        System.out.println("row first: SUM = "+sum1 + " time consuming = " + (System.currentTimeMillis() - start1));

        // 列优先
        long start2 = System.currentTimeMillis();
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                sum2 += arr[j][i];
            }
        }
        System.out.println("column first: SUM = "+sum2+" time consuming = "+(System.currentTimeMillis()-start2));
    }
}
