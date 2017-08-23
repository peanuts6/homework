import java.lang.reflect.*;

/**
 * Created by xqy on 17/8/22.
 */

public class FactoryMethodReflection {
    public static Object createObject(String objName, String[] args) throws ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,InvocationTargetException{
        Class<?> clazz = Class.forName(objName);
        Constructor<?> constructor = clazz.getConstructor();
        Object obj = constructor.newInstance();
        return obj;
    }
    public static void init(Object[] args){
        for(Object arg:args){

        }
    }
    public static void main(String[] args){
        DecodedImage decodedImage;
    }
}
