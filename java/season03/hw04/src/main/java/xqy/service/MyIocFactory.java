package xqy.service;

/**
 * Created by xqy on 17/9/5.
 */
public interface MyIocFactory {
    <T> T getBean(Class<T>t);
    <T> T getBean(String name);
}
