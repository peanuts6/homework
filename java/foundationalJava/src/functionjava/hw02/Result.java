package functionjava.hw02;

/**
 * Created by xqy on 18/1/26.
 */
public class Result {
    public long totalIncome;
    public int count;
    public Result(long _total,int _count){
        totalIncome = _total;
        count = _count;
    }
    public void setTotalIncome(long in){
        totalIncome = in;
    }
    public void setCount(int c){
        count = c;
    }
    public String toString(){
        return totalIncome+"万,"+count+"人";
    }
}
