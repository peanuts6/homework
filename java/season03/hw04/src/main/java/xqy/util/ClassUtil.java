package xqy.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ClassUtil {

	public static ClassLoader getCurrentClassLoader(){
		ClassLoader classLoader = null;

		try {
			classLoader = Thread.currentThread().getContextClassLoader();
		} catch (SecurityException ex) {

		}
		return classLoader;
	}
	public static List<String> getPackageContent(String packageName) throws IOException{
	    final List<String> list = new ArrayList<>();
	    final URL baseDir = getCurrentClassLoader().getResource(packageName);
	    Enumeration<URL> urls = getCurrentClassLoader().getResources(packageName);
	    String basePackage = baseDir.getPath();

	    while (urls.hasMoreElements()) {
	        URL url = urls.nextElement();
	        Path dir = Paths.get(url.getFile());
	        Files.walk(dir)
					.filter(p -> p.toString().endsWith(".class"))
                    .map(p -> {
                        File file = p.toFile();
                        String fileStr = file.getPath().toString();
                        fileStr = fileStr.replace(basePackage,"");
                        String pf = fileStr.replace(".class","");
                        return packageName + pf.replace('/','.');
                    })
					.distinct()
					.forEach(p->{
						list.add(p);
						//System.out.println(p);
					});
	    }
	    return list;
	}
}
