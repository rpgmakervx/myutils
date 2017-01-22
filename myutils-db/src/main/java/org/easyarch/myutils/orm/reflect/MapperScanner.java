package org.easyarch.myutils.orm.reflect;

import org.easyarch.myutils.file.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import static org.easyarch.myutils.orm.parser.Token.POINT;

/**
 * Description :
 * Created by xingtianyu on 17-1-21
 * 下午11:31
 * description:
 */

public class MapperScanner {

    public static final String CLASSPATH = MapperScanner.class.getClassLoader().getResource("").getPath();

    public void scan(String packagePath) throws Exception {
        String copyPath = packagePath.replace(POINT,File.separator);
        String classpath = CLASSPATH + copyPath + File.separator;
        File file = new File(classpath);
        if (!file.exists()){
            throw new FileNotFoundException("package not found");
        }
        if (file.isFile()){
            throw new IllegalArgumentException("please offer a package name");
        }
        //包名第一层作为前缀
        String prefix = packagePath.split("\\"+POINT)[0];
        List<File> classFiles = FileUtils.listFileRecursive(classpath);
        for (File cf : classFiles){
            String filename = cf.getPath();
            String copyFileName = filename.substring(0, filename.lastIndexOf(".")).replace(File.separator,".");
            String interfaceName = copyFileName.substring(copyFileName.indexOf(prefix),copyFileName.length());
            URL[] urls = new URL[]{new URL("file:"+filename)};
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class clazz = classLoader.loadClass(interfaceName);
            if (clazz.isInterface()){
                ClassItem item = new ClassItem(clazz.getName(),clazz,clazz.getDeclaredMethods());
                ClassItemPool.addInterface(item);
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        File file = new File("/home/code4j/58daojia/com/daojia/dao/entity/classs.java");
//        String filename = file.getPath();
//        filename = filename.substring(0, filename.lastIndexOf(".")).replace(File.separator,".");
//        System.out.println(filename);
//        System.out.println(filename.substring(filename.indexOf("com"),filename.length()));
        MapperScanner scanner = new MapperScanner();
        scanner.scan("org.easyarch.myutils.db");
        for (ClassItem item : ClassItemPool.getInterfaces()){
            System.out.println(item.getItemName());
        }
    }
}
