package functionjava.hw02;

import java.util.Scanner;

/**
 * Created by xqy on 18/1/22.
 */
public class DecoratorDemo {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int count = 0;

        String helloStr = "Enter your charactor: ";
        String choiseStr = "Enter your choise: \n1：加密\n2：反转字符串\n3：转成大写\n4：转成小写\n5：扩展或者剪裁到10个字符，不足部分用！补充\n6: (exit)退出\n输入任意组合，比如 1，3 表示先执行1的逻辑";

        String choise="",str="",s;
        System.out.println(helloStr);
        while (true){
            s = scanner.nextLine();
            if(s.equals("exit") || s.equals("6")){
                System.out.println("bye bye good queen.");
                System.exit(0);
            }
            if(count++%2==0){
                str = s;
                System.out.println(choiseStr);
            }else{
                choise = s;
                Decorator decorator = new MyString(str);
                String[] l = choise.split(",");
                for(int i=0;i<l.length;i++){
                    switch (l[i]){
                        case "1":
                            decorator.encode();
                            break;
                        case "2":
                            decorator.reverse();
                            break;
                        case "3":
                            decorator.toUpperCase();
                            break;
                        case "4":
                            decorator.toLowerCase();
                            break;
                        case "5":
                            decorator.fixed();
                            break;
                        default:
                            //decorator.end();
                    }
                }
                decorator.end();
                System.out.println(helloStr);
            }

        }

    }
}
