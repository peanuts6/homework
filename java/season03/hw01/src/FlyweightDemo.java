/**
 * Created by xqy on 17/8/13.
 */
import java.util.*;

//一个细力度的小对象
interface Page{
    String getPage();
    void setPage(String pageSize);
}
class MyPage implements Page{
    String pageSize;
    MyPage(String pageSize){
        this.pageSize = pageSize;
    }
    public String getPage(){
        return this.pageSize;
    }
    public void setPage(String pageSize){
        this.pageSize = pageSize;
    }
}
//制作工厂
class PageFactory{
    private static Map treeMap = new TreeMap();
    private static Page page;
    private static int sharedPage = 0;
    public static Page getPage(String num){
        if (treeMap.containsKey(num)) {
            sharedPage++;
        } else {
            page = new MyPage(num);
            treeMap.put(num,page.hashCode());
        }
        return page;
    }
    public static void report() {
        System.out.print("new Page - " + treeMap.size()
                + ", \"shared\" Page - " + sharedPage + ", ");
        for (Object o : treeMap.keySet()) {
            System.out.print(o + " ");
        }
        System.out.println();
    }
}
public class FlyweightDemo {
    public static void main(String[] args){
        String[] pages = {"256","64","128","128","256","312","100"};
        for(String page:pages){
            PageFactory.getPage(page);
        }
        PageFactory.report();
    }
}
