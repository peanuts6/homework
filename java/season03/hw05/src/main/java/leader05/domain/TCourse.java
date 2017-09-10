package leader05.domain;

/**
 * Created by leader05 on 17/9/8.
 */
public class TCourse {
    private int id;
    private String name;
    private int mark;

    public TCourse(int id,String name,int mark){
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String toString(){
        return String.format("TCourse[id=%d, name=%s, mark=%d]",id,name,mark);
    }
}
