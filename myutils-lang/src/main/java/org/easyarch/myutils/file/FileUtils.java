package org.easyarch.myutils.file;/**
 * Description : 
 * Created by YangZH on 16-10-27
 *  下午7:42
 */

import org.easyarch.myutils.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description :
 * Created by YangZH on 16-10-27
 * 下午7:42
 */

public class FileUtils {

    public static final long ONE_KB = 1024;

    /**
     * The number of bytes in a megabyte.
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;

    /**
     * The number of bytes in a gigabyte.
     */
    public static final long ONE_GB = ONE_KB * ONE_MB;

    private static boolean exists(String path) {
        if (path == null)
            return false;
        return exists(new File(path));
    }

    private static boolean exists(File file) {
        return file == null ? false : file.exists();
    }

    private static File create(String path, boolean isFile) throws Exception {
        if (path == null)
            return null;
        return create(new File(path), isFile);
    }

    private static File create(File file, boolean isFile) throws Exception {
        if (file == null)
            return null;
        if (!file.exists()) {
            if (isFile) {
                file.createNewFile();
            } else {
                file.mkdirs();
            }
        }
        return file;
    }

    public static void rm(String path) throws Exception {
        if (path == null)
            return;
        rm(new File(path));
    }

    public static void rm(File file) throws Exception {
        if (!rm(file,false)){
            String message = "删除失败，可能是目录下有文件夹无法删除," +
                    "请使用 rm(File file,boolean recursive) 方法递归删除";
            throw new RuntimeException(message);
        }
    }
    public static boolean rm(String path,boolean recursive) throws FileNotFoundException {
        return rm(new File(path),recursive);
    }
    public static boolean rm(File file,boolean recursive) throws FileNotFoundException {
        if (!exists(file)) {
            throw new FileNotFoundException("文件" + file.getAbsolutePath() + "不存在");
        }
        if (recursive){
            deleteAll(file);
            return true;
        }else{
            return file.delete();
        }
    }

    public static List<File> ls(String path) throws Exception {
        if (path == null)
            return null;
        return ls(new File(path));
    }

    public static List<File> ls(File file) throws Exception {
        if (!exists(file)) {
            throw new FileNotFoundException("文件" + file.getAbsolutePath() + "不存在");
        }
        return Arrays.asList(file.listFiles());
    }

    public static File touch(String path) throws Exception {
        return create(path, true);
    }

    public static File touch(File file) throws Exception {
        return create(file, true);
    }

    public static File mkdir(String dir) throws Exception {
        return create(dir, false);
    }

    public static File mkdir(File dir) throws Exception {
        return create(dir, false);
    }

    public static String cat(String path) throws Exception {
        if (path == null)
            throw new NullPointerException("path is null");
        return cat(new File(path));
    }

    public static String cat(File file) throws Exception {
        if (file == null)
            throw new NullPointerException("file is null");
        if (!file.exists()) {
            throw new FileNotFoundException("文件" + file.getAbsolutePath() + "不存在");
        }
        InputStream is = new FileInputStream(file);
        String result = IOUtils.toString(is);
        IOUtils.closeIO(is);
        return result;
    }

    public static void cp(String srcPath, String dstPath) throws Exception {
        if (srcPath == null)
            throw new NullPointerException("path is null");
        cp(new File(srcPath), new File(dstPath));
    }

    public static void cp(File src, File dst) throws Exception {
        if (src == null)
            throw new NullPointerException("file is null");
        if (!exists(src)) {
            throw new FileNotFoundException("文件" + src.getAbsolutePath() + "不存在");
        }
        if (!exists(dst)) {
            dst.createNewFile();
        }
        FileInputStream sis = new FileInputStream(src);
        FileOutputStream dos = new FileOutputStream(dst);
        IOUtils.transferFrom(sis, dos);
        IOUtils.closeIO(sis);
        IOUtils.closeIO(dos);
    }

    public static void mv(String srcPath, String dstPath) throws Exception {
        mv(new File(srcPath), new File(dstPath));
    }

    public static void mv(File srcPath, File dstPath) throws Exception {
        cp(srcPath, dstPath);
        rm(srcPath);
    }

    public static File write(String path, byte[] data) throws Exception {
        if (path == null)
            throw new NullPointerException("path is null");
        return write(new File(path), data);
    }

    public static File write(File file, byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        FileOutputStream fos = new FileOutputStream(file);
        IOUtils.transferFrom(bais, fos);
        bais.close();
        fos.close();
        return file;
    }

    public static File vim(String path, String str) throws Exception {
        return write(path, str.getBytes());
    }

    public static File vim(File file, String str) throws Exception {
        return write(file, str.getBytes());
    }

    public static void deleteAll(File file){
        if (!exists(file))
            return;
        File[] files = file.listFiles();
        for (File f:files){
            if (f.isDirectory()){
                if (!f.delete()){//删除该文件夹,删除失败则进入目录删除
                    deleteAll(f);
                    f.delete();//删除完子目录再删除一次当前
                }
            }else{
                f.delete();
            }
        }
    }

    /**
     * 清空指定根目录下所有的空文件夹
     * @param directory
     * @return
     */
    public static int deletEmptyDirectory(File directory) {
        int count = 0;
        if (directory == null || directory.list() == null
                || directory.isFile() || directory.list().length == 0){
            return 0;
        }else {
            File[] files = directory.listFiles();
            for (File f : files) {
                if (f.isDirectory()){
                    if (!f.delete()){//删除该文件夹,删除失败则进入目录删除
                        count += deletEmptyDirectory(f);
                        if (f.delete()){
                            count++;
                        }
                    }else{
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int deletEmptyDirectory(String path){
        return deletEmptyDirectory(new File(path));
    }

    public static void clearDirectory(File directory) {
        if (!directory.exists()) {
            throw new IllegalArgumentException(directory + " does not exist");
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory + " is not a directory");
        }
        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IllegalArgumentException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
        }

        if (null != exception) {
//            throw exception;
        }
    }
    /**
     * 递归遍历
     * @param file
     * @param includeDir
     * @return
     */
    public static List<File> listRecursive(File file,boolean includeDir){
        if (file == null){
            return null;
        }
        List<File> paths = new ArrayList<File>();
        File[] files = file.listFiles();
        for (File f:files){
            if (f.isDirectory()){
                if (includeDir)
                    paths.add(f);
                paths.addAll(listRecursive(f,includeDir));
            }else{
                paths.add(f);
            }
        }
        return paths;
    }

    public static List<File> listFileRecursive(File file){
        return listRecursive(file,false);
    }
    public static List<File> listFileRecursive(String path){
        return listFileRecursive(new File(path));
    }

    public static List<File> listDirectoryRecursive(File file){
        return listRecursive(file,true);
    }
    public static List<File> listDirectoryRecursive(String path){
        return listDirectoryRecursive(new File(path));
    }


    public static boolean isFileNewer(File file, long timeMillis) {
        if (file == null) {
            throw new NullPointerException("file is null");
        }
        if (exists(file)) {
            return false;
        }
        return file.lastModified() > timeMillis;
    }

    public static boolean isFileNewer(File file, Date date) {
        if (file == null) {
            throw new NullPointerException("file is null");
        }
        if (exists(file)) {
            return false;
        }
        return file.lastModified() > date.getTime();
    }

    public static boolean contentEquals(File file1, File file2) {
        if (!exists(file1) || !exists(file2)) {
            return false;
        }
        if (file1 == file2) {
            return true;
        }
        if (file1.isDirectory() || file2.isDirectory()) {
            throw new IllegalArgumentException("Can't compare directories");
        }
        try {
            return IOUtils.equals(new FileInputStream(file1), new FileInputStream(file2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean contentEquals(String path1, String path2) {
        return contentEquals(new File(path1), new File(path2));
    }

    public static List<File> filter(File file,String regex){
        if (file == null){
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        List<File> paths = new ArrayList<File>();
        File[] files = file.listFiles();
        for (File f:files){
            Matcher matcher = pattern.matcher(f.getName());
            if (f.isDirectory()){
                paths.addAll(filter(f,regex));
            }else if (matcher.matches()){
                paths.add(f);
            }
        }
        return paths;
    }

    public static List<File> filter(String path,String regex){
        return filter(new File(path),regex);
    }

    public static List<File> suffixFilter(String path,String suffix){
        suffix = ".*\\.".concat(suffix);
        return filter(path,suffix);
    }
    public static List<File> suffixFilter(File file,String suffix){
        suffix = ".*\\.".concat(suffix);
        return filter(file,suffix);
    }

    public static String byteCountToDisplaySize(long size) {
        String displaySize;

        if (size / ONE_GB > 0) {
            displaySize = String.valueOf(size / ONE_GB) + " GB";
        } else if (size / ONE_MB > 0) {
            displaySize = String.valueOf(size / ONE_MB) + " MB";
        } else if (size / ONE_KB > 0) {
            displaySize = String.valueOf(size / ONE_KB) + " KB";
        } else {
            displaySize = String.valueOf(size) + " bytes";
        }
        return displaySize;
    }

}
