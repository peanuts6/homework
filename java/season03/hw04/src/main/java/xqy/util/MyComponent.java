package xqy.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xqy on 17/9/5.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyComponent {
    public enum ComponentType{
        singleton,prototype,customer
    }
    ComponentType injectBy() default ComponentType.singleton;
    String name() ;
    boolean threadsafe() default false;
}
