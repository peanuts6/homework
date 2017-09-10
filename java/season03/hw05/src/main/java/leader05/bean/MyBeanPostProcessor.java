package leader05.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


@Component
public class MyBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Field[] fields = o.getClass().getDeclaredFields();
        Class[] classes = o.getClass().getInterfaces();
        if (fields.length > 4){
            System.out.println(String.format("waring %s has %d properties:",s,fields.length));
            for(Field field:fields){
                System.out.println(String.format("     property %s type %s",field.getName(),field.getType().toString()));
            }
        }
        if (classes.length > 2){
            System.out.println(String.format("waring %s has implement %d interfaces: ",s,classes.length));
            for(Class clazz:classes){
                System.out.println(String.format("     interface %s : ",clazz.toString()));
            }
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}
