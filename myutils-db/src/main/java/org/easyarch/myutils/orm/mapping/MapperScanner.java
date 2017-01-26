package org.easyarch.myutils.orm.mapping;

import org.easyarch.myutils.file.FileUtils;
import org.easyarch.myutils.orm.annotation.sql.Mapper;
import org.easyarch.myutils.orm.cache.CacheFactory;
import org.easyarch.myutils.orm.cache.InterfaceCache;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import static org.easyarch.myutils.orm.parser.Token.ANY;
import static org.easyarch.myutils.orm.parser.Token.POINT;

/**
 * Description :
 * Created by xingtianyu on 17-1-21
 * 下午11:31
 * description:
 */

public class MapperScanner {

    public static final String CLASSPATH = MapperScanner.class.getClassLoader().getResource("").getPath();

    public static final String CLASS = ".class";

    private InterfaceCache interfaceCache = CacheFactory.getInstance().getInterfaceCache();

    /**
     * 扫描整个工程
     * @throws Exception
     */
    public void scan() throws Exception {
        List<File> classFiles = FileUtils.listFileRecursive(CLASSPATH);
        int endPoint = CLASSPATH.lastIndexOf(File.separator);

        for (File cf:classFiles){
            String filename = cf.getPath();
            if (!filename.endsWith(CLASS)){
                continue;
            }
            String packagePath = filename.substring(endPoint + 1,filename.length());
            String interfaceName = packagePath.substring(0, filename.lastIndexOf("."))
                    .replace(File.separator,POINT);
            URL[] urls = new URL[]{new URL("file:"+filename)};
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class<?> clazz = classLoader.loadClass(interfaceName);
            addClass(clazz);
        }
    }

    /**
     * 路径扫描，目前部支持通配符*扫描
     * @param packagePath
     * @throws Exception
     */
    public void scan(String packagePath) throws Exception {
        if(packagePath.contains(ANY)){
            packagePath = packagePath.replaceAll("\\"+POINT+ANY,"");
        }
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
            if (!cf.getPath().endsWith(CLASS)){
                continue;
            }
            String filename = cf.getPath();
            String copyFileName = filename.substring(0, filename.lastIndexOf(".")).replace(File.separator,".");
            String interfaceName = copyFileName.substring(copyFileName.indexOf(prefix),copyFileName.length());
            URL[] urls = new URL[]{new URL("file:"+filename)};
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class<?> clazz = classLoader.loadClass(interfaceName);
            addClass(clazz);
        }
    }

    private void addClass(Class<?> clazz){
        if (clazz.isInterface()){
            Mapper mapper = clazz.getAnnotation(Mapper.class);
            String namespace = clazz.getName();
            if (mapper != null && !mapper.namespace().isEmpty()){
                namespace = mapper.namespace();
            }
            ClassItem classItem = new ClassItem(namespace,clazz,clazz.getDeclaredMethods());
            interfaceCache.set(clazz,classItem);
        }
    }

    public static void main(String[] args) throws Exception {
//        File file = new File("/home/code4j/58daojia/com/daojia/dao/entity/classs.java");
//        String filename = file.getPath();
//        filename = filename.substring(0, filename.lastIndexOf(".")).replace(File.separator,".");
//        System.out.println(filename);
//        System.out.println(filename.substring(filename.indexOf("com"),filename.length()));
//        MapperScanner scanner = new MapperScanner();
//        scanner.scan("org.easyarch.myutils.db");
//        for (ClassItem item : ClassItemPool.getInterfaces()){
//            System.out.println(item.getItemName());
//        }
        System.out.println(CLASSPATH);
        String interfaceFile = "/home/code4j/IDEAWorkspace/myutils/myutils-db/target/classes/org/easyarch/myutils/db/wrapper/Wrapper.class";
        int endPoint = CLASSPATH.lastIndexOf(File.separator);
        String packagePath = interfaceFile.substring(endPoint + 1,interfaceFile.length());
        System.out.println("packagePath:"+packagePath);
        int prefixEndPoint = packagePath.indexOf(File.separator);
        System.out.println("prefixEndPoint:"+prefixEndPoint);
        String prefix = packagePath.substring(0,prefixEndPoint);
        System.out.println(prefix);

    }
}
