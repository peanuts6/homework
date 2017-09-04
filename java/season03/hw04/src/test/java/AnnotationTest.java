import leader.reflect.LeaderComonent;
import leader.service.MyService;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xqy on 17/9/4.
 */
public class AnnotationTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Annotation[] annos = MyService.class.getAnnotations();
        Map<String,Object> myBeanMap = new HashMap<>();
        for(Annotation ann:annos){
            Class<? extends Annotation> annCls = ann.annotationType();
            System.out.println(annCls);
            if(annCls == LeaderComonent.class){
                LeaderComonent leaderComonent = (LeaderComonent) ann;
                System.out.println(leaderComonent);
                Object obj = MyService.class.newInstance();
                myBeanMap.put(leaderComonent.name(),obj);
            }
        }
    }
}
