package xqy.service;

import xqy.util.Autowired;
import xqy.util.ClassUtil;
import xqy.util.MyComponent;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by xqy on 17/9/5.
 */
public class MyIocContext implements MyIocFactory{
    public List<String> classes = new ArrayList<>();
    public Map<String,Object> beans = new HashMap<>();
    public Map<String,Object[]> beanTypes = new HashMap<>();
    public Map<String,Set<String>> beanDefinations = new HashMap<>();

    public MyIocContext() throws ClassNotFoundException {
        this("xqy");
    }
    public MyIocContext(String scanPackages) {
        scanFile(scanPackages);
        try {
            parseAnnotation();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public MyIocContext(String[] scanPackages) {
        for(String s:scanPackages){
            scanFile(s);
        }
        try {
            parseAnnotation();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void scanFile(String scanPackages){
        try {
            this.classes.addAll(ClassUtil.getPackageContent(scanPackages));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void parseAnnotation() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        for(String clz:classes){
            Class tClass = ClassUtil.getCurrentClassLoader().loadClass(clz.toString());
            //System.out.println("-------------------------");
            //System.out.println(tClass);

            Annotation[] annotations =tClass.getAnnotations();
            for(Annotation ann:annotations){
                Class<? extends Annotation> annClass = ann.annotationType();
                if(annClass == MyComponent.class) {
                    MyComponent myComponent = (MyComponent) ann;
                    System.out.println(ann);
                    System.out.println(tClass.getGenericSuperclass());
                    Object obj = tClass.newInstance();
                    if(obj!=null) {
                        this.beans.put(myComponent.name(), obj);
                        Object[] objs = this.beanTypes.get(tClass.getName());
                        List<Object> objects;
                        if (objs != null && objs.length > 0) {
                            objects = Arrays.asList(objs);
                        } else {
                            objects = new ArrayList<>();
                        }
                        objects.add(obj);
                        this.beanTypes.put(tClass.getName(), objects.toArray());
                    }
                }
            }
        }
    }

    @Override
    public Object getBean(Class t) {
        try {
            return getByClass(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getBean(String name) {
        Object obj = this.beans.get(name);
        if(obj==null){
            System.out.println("bean name "+name+" is not defined");
        } else {
            wiring(obj);
        }
        return obj;
    }

    void wiring(Object obj){
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        boolean isAutowired;
        for(Field field:fields){
            String name = field.getName();
            isAutowired = field.isAnnotationPresent(Autowired.class);
            Type realType = field.getGenericType();
            if(isAutowired){
                System.out.println("--- field auto wired");
                field.setAccessible(true);
                try {
                    System.out.println(realType.getClass());
                    System.out.println(realType.toString());
                    System.out.println(Class.forName(realType.toString(),false,ClassUtil.getCurrentClassLoader()));
                    field.set(obj,this.getBean(Class.forName(realType.toString(),true,obj.getClass().getClassLoader())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("Field :"+""+name +" realType:"+realType +"  ");
        }

        for(Method method:methods){
            isAutowired = method.isAnnotationPresent(Autowired.class);
            if(isAutowired){
                System.out.println("--- method auto wired");
            }
        }
    }


    public Object getByClass(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        Object[] objects = this.beanTypes.get(clazz.getName());
        if(objects !=null){
            if(objects.length>1){
                System.out.println(objects.length+" bean defined. need to qualify");
                return null;
            }
            return objects[0];
        }
        return null;
    }

}
