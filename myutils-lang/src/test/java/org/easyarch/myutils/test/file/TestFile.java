package org.easyarch.myutils.test.file;

import org.easyarch.myutils.file.FileFilter;
import org.easyarch.myutils.file.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 16-12-29
 * 下午10:54
 * description:
 */

public class TestFile {

    public void testVim(){
        try {
            FileUtils.vim("/home/code4j/txt","邢天宇");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testReadWrite(){
        byte[] data = FileUtils.read("/home/code4j/picture/50k.jpg");
        System.out.println(data.length);
        try {
            FileUtils.write("/home/code4j/image.jpg",data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testCopy(){
        try {
            FileUtils.cp("/home/code4j/picture/50k.jpg","/home/code4j/image111.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testMv(){
        try {
            FileUtils.mv("/home/code4j/picture/50k.jpg","/home/code4j/image111.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testCat(){
        try {
            System.out.println(FileUtils.cat("/home/code4j/maven/conf/settings.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testEquals(){
        boolean eq = FileUtils.eq("/home/code4j/picture/50k.jpg","/home/code4j/image111.jpg");
        System.out.println(eq);
    }

    public void testDeleteAll(){
        FileUtils.deleteAll("/home/code4j/dumps/dir");
    }

    public void testDeleteEmptyDir(){
        FileUtils.deletEmptyDirectory("/home/code4j/dumps");
    }

    public void testFilter(){
        List<File> files =  FileUtils.filter("/home/code4j/dumps", new FileFilter() {
            @Override
            public boolean accept(File file) {
                String suffix = file.getName().
                        substring(file.getName().
                                lastIndexOf(".") + 1,
                                file.getName().length());
                return suffix.equals("json");
            }
        });
        System.out.println(files);
    }

    public void testRM(){
        try {
            FileUtils.rm("/home/code4j/image111.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
