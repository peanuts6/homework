package leader.bean;

import java.util.Date;

/**
 * Created by leader05 on 17/9/4.
 */
public class LDOrder {
    private long price;
    private Date createDate;
    private int userId;
    public long getPrice(){
        return price;
    }
    public void setPrice(long price){
        this.price = price;
    }
    public Date getCreateDate(){
        return createDate;
    }
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }
    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    @Override
    public String toString(){
        return "LDOrder{userId="+userId+",price="+price+",createDate="+createDate.toString()+"}";
    }
}
