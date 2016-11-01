package org.easyarch.myutils.array;/**
 * Description : 
 * Created by YangZH on 16-11-1
 *  上午12:30
 */

import org.easyarch.myutils.array.action.Loop;

import java.lang.reflect.Array;

/**
 * Description :
 * Created by code4j on 16-11-1
 * 上午12:30
 */

public class ArrayUtils {

    private static Object expandSpace(final Object array, int expsize, final Class<?> contentType) {
        if (array != null) {
            int length = Array.getLength(array);
            Object newarray = Array.newInstance(contentType, length + expsize);
            System.arraycopy(array, 0, newarray, 0, length);
            return newarray;
        }
        return Array.newInstance(contentType, 1);
    }

    public static boolean isEmpty(int[] array){
        return array == null||array.length == 0;
    }
    public static boolean isEmpty(float[] array){
        return array == null||array.length == 0;
    }
    public static boolean isEmpty(double[] array){
        return array == null||array.length == 0;
    }
    public static boolean isEmpty(long[] array){
        return array == null||array.length == 0;
    }
    public static boolean isEmpty(byte[] array){
        return array == null||array.length == 0;
    }
    public static boolean isEmpty(short[] array){
        return array == null||array.length == 0;
    }
    public static<T> boolean isEmpty(T[] array){
        return array == null||array.length == 0;
    }

    public static<T> void foreach(T[] array,Loop<T> loop){
        if (isEmpty(array)||loop == null)
            return;
        for (T t:array){
            loop.loop(t);
        }
    }
    public static void foreach(int[] array,Loop<Integer> loop){
        if (isEmpty(array)||loop == null)
            return;
        for (Integer i:array){
            loop.loop(i);
        }
    }
    public static void foreach(float[] array,Loop<Float> loop){
        if (isEmpty(array)||loop == null)
            return;
        for (Float i:array){
            loop.loop(i);
        }
    }
    public static void foreach(double[] array,Loop<Double> loop){
        if (isEmpty(array)||loop == null)
            return;
        for (Double i:array){
            loop.loop(i);
        }
    }
    public static void foreach(long[] array,Loop<Long> loop){
        if (isEmpty(array)||loop == null)
            return;
        for (Long i:array){
            loop.loop(i);
        }
    }
    public static void foreach(byte[] array,Loop<Byte> loop){
        if (isEmpty(array)||loop == null)
            return;
        for (Byte i:array){
            loop.loop(i);
        }
    }
    public static void foreach(short[] array,Loop<Short> loop){
        if (isEmpty(array)||loop == null)
            return;
        for (Short i:array){
            loop.loop(i);
        }
    }


    private static Object add(Object array, Object elem, Class<?> type) {
        final Object newarray = expandSpace(array, 1, type);
        int length = Array.getLength(array);
        Array.set(newarray, length, elem);
        return newarray;
    }

    private static Object add(Object array, int index, Object elem, final Class<?> type) {
        if (array == null) {
            if (index > 0) {
                throw new ArrayIndexOutOfBoundsException("insert point is " + index + ",but array size is " + 0);
            }
            final Object newarray = Array.newInstance(type, 1);
            Array.set(newarray, 0, elem);
            return newarray;
        }
        int length = Array.getLength(array);
        if (index > length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("insert point is " + index + ",but array size is " + 0);
        }
        final Object newarray = Array.newInstance(array.getClass().getComponentType(), length + 1);
        System.arraycopy(array, 0, newarray, 0, index);
        Array.set(newarray, index, elem);
        if (index < length) {
            System.arraycopy(array, index, newarray, index + 1, length - index);
        }
        return newarray;
    }

    public static <T> T[] addAll(T[] array,T... elems){
        int srcLength = Array.getLength(array);
        int elemsLength = Array.getLength(elems);
        if (srcLength < elemsLength) {
            throw new ArrayIndexOutOfBoundsException("src length is " + srcLength +
                    ", but elemes length is " + elemsLength);
        }
        T[] expanedArray = (T[]) expandSpace(array, elemsLength, array.getClass().getComponentType());
        System.arraycopy(elems, 0, expanedArray, 0, elemsLength + srcLength);
        return expanedArray;
    }

    public static <T> T[] add(T[] array, int index, T elem) {
        return (T[]) add(array, index, elem, array.getClass().getComponentType());
    }

    public static <T> T[] add(T[] array, T elem) {
        return (T[]) add(array, elem, array.getClass().getComponentType());
    }


    public static int[] add(int[] array, int elem) {
        return (int[]) add(array, elem, Integer.TYPE);
    }

    public static float[] add(float[] array, float elem) {
        return (float[]) add(array, elem, Float.TYPE);
    }

    public static double[] add(double[] array, double elem) {
        return (double[]) add(array, elem, Double.TYPE);
    }

    public static long[] add(long[] array, long elem) {
        return (long[]) add(array, elem, Long.TYPE);
    }

    public static byte[] add(byte[] array, byte elem) {
        return (byte[]) add(array, elem, Byte.TYPE);
    }

    public static short[] add(short[] array, short elem) {
        return (short[]) add(array, elem, Short.TYPE);
    }

    public static int[] add(int[] array, int index, int elem) {
        return (int[]) add(array, index, elem, Integer.TYPE);
    }

    public static float[] add(float[] array, int index, float elem) {
        return (float[]) add(array, index, elem, Float.TYPE);
    }

    public static double[] add(double[] array, int index, double elem) {
        return (double[]) add(array, index, elem, Double.TYPE);
    }

    public static long[] add(long[] array, int index, long elem) {
        return (long[]) add(array, index, elem, Long.TYPE);
    }

    public static byte[] add(byte[] array, int index, byte elem) {
        return (byte[]) add(array, index, elem, Byte.TYPE);
    }

    public static short[] add(short[] array, int index, short elem) {
        return (short[]) add(array, index, elem, Short.TYPE);
    }

    public static int[] addAll(int[] array, int... elems) {
        int srcLength = Array.getLength(array);
        int elemsLength = Array.getLength(elems);
        if (srcLength < elemsLength) {
            throw new ArrayIndexOutOfBoundsException("src length is " + srcLength +
                    ", but elemes length is " + elemsLength);
        }
        int[] expanedArray = (int[]) expandSpace(array, elemsLength, Integer.TYPE);
        System.arraycopy(elems, 0, expanedArray, srcLength, elemsLength);
        return expanedArray;
    }

    public static float[] addAll(float[] array, float... elems) {
        int srcLength = Array.getLength(array);
        int elemsLength = Array.getLength(elems);
        if (srcLength < elemsLength) {
            throw new ArrayIndexOutOfBoundsException("src length is " + srcLength +
                    ", but elemes length is " + elemsLength);
        }
        float[] expanedArray = (float[]) expandSpace(array, elemsLength, Integer.TYPE);
        System.arraycopy(elems, 0, expanedArray, srcLength, elemsLength);
        return expanedArray;
    }

    public static double[] addAll(double[] array, double... elems) {
        int srcLength = Array.getLength(array);
        int elemsLength = Array.getLength(elems);
        if (srcLength < elemsLength) {
            throw new ArrayIndexOutOfBoundsException("src length is " + srcLength +
                    ", but elemes length is " + elemsLength);
        }
        double[] expanedArray = (double[]) expandSpace(array, elemsLength, Integer.TYPE);
        System.arraycopy(elems, 0, expanedArray, srcLength, elemsLength);
        return expanedArray;
    }

    public static long[] addAll(long[] array, long... elems) {
        int srcLength = Array.getLength(array);
        int elemsLength = Array.getLength(elems);
        if (srcLength < elemsLength) {
            throw new ArrayIndexOutOfBoundsException("src length is " + srcLength +
                    ", but elemes length is " + elemsLength);
        }
        long[] expanedArray = (long[]) expandSpace(array, elemsLength, Integer.TYPE);
        System.arraycopy(elems, 0, expanedArray, srcLength, elemsLength);
        return expanedArray;
    }

    public static byte[] addAll(byte[] array, byte... elems) {
        int srcLength = Array.getLength(array);
        int elemsLength = Array.getLength(elems);
        if (srcLength < elemsLength) {
            throw new ArrayIndexOutOfBoundsException("src length is " + srcLength +
                    ", but elemes length is " + elemsLength);
        }
        byte[] expanedArray = (byte[]) expandSpace(array, elemsLength, Integer.TYPE);
        System.arraycopy(elems, 0, expanedArray, srcLength, elemsLength);
        return expanedArray;
    }

    public static short[] addAll(short[] array, short... elems) {
        int srcLength = Array.getLength(array);
        int elemsLength = Array.getLength(elems);
        if (srcLength < elemsLength) {
            throw new ArrayIndexOutOfBoundsException("src length is " + srcLength +
                    ", but elemes length is " + elemsLength);
        }
        short[] expanedArray = (short[]) expandSpace(array, elemsLength, Integer.TYPE);
        System.arraycopy(elems, 0, expanedArray, srcLength, elemsLength);
        return expanedArray;
    }


    public static void main(String args[]) {
        int[] array = new int[10];
        for (int index = 0; index < 10; index++) {
            array[index] = index + 1;
        }
        foreach(array, new Loop<Integer>() {
            @Override
            public void loop(Integer elem) {

            }
        });
//        array = addAll(array, 11,12,13,14,15);
//        for (int i : array) {
//            System.out.println(i);
//        }
    }

}
