package org.easyArch.myutils.file;/**
 * Description : 
 * Created by YangZH on 16-10-27
 *  下午7:42
 */

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.easyArch.myutils.io.IOUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Description :
 * Created by YangZH on 16-10-27
 * 下午7:42
 */

public class FileUtils {

    private static boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    private static File create(String path, boolean isFile) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            if (isFile){
                file.createNewFile();
            }else{
                file.mkdirs();
            }
        }
        return file;
    }

    public static void rm(String path) throws Exception {
        File file = new File(path);
        if (!exists(path)){
            throw new FileNotFoundException("文件" + path + "不存在");
        }
        file.delete();
    }

    public static List<File> ls(String path) throws Exception {
        File file = new File(path);
        if (!exists(path)){
            throw new FileNotFoundException("文件" + path + "不存在");
        }
        return Arrays.asList(file.listFiles());
    }

    public static File touch(String path) throws Exception {
        return create(path, true);
    }


    public static File mkdir(String path) throws Exception {
        return create(path, false);
    }

    public static String cat(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("文件" + path + "不存在");
        }
        InputStream is = new FileInputStream(file);
        String result = IOUtils.toString(is);
        IOUtils.closeIO(is);
        return result;
    }

    public static void cp(String srcPath, String dstPath) throws Exception {
        File src = new File(srcPath);
        if (!src.exists()) {
            throw new FileNotFoundException("文件" + srcPath + "不存在");
        }
        File dst = new File(dstPath);
        if (dst.isDirectory()) {
            throw new FileNotFoundException(dstPath + "是目录");
        }
        if (!dst.exists()) {
            dst.createNewFile();
        }
        FileInputStream sis = new FileInputStream(src);
        FileOutputStream dos = new FileOutputStream(dst);
        IOUtils.transferFrom(sis, dos);
        IOUtils.closeIO(sis);
        IOUtils.closeIO(dos);
    }
    public static void mv(String srcPath, String dstPath) throws Exception{
        cp(srcPath,dstPath);
        rm(srcPath);
    }

    public static File write(String path,byte[] data) throws Exception {
        File file = touch(path);
        ByteArrayInputStream bais = new ByteInputStream(data,data.length);
        FileOutputStream fos = new FileOutputStream(file);
        IOUtils.transferFrom(bais, fos);
        bais.close();
        fos.close();
        return file;
    }

    public static File vim(String path,String str) throws Exception {
        return write(path,str.getBytes());
    }

    public static void main(String[] args) throws Exception {
//        cp("/home/code4j/58daojia/txt","/home/code4j/58daojia/dump");
        vim("/home/code4j/58daojia/txt","asasas");
    }
}
