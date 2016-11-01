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

    private static int getLength(Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }

    private static Object expandSpace(final Object array, int expsize, final Class<?> contentType) {
        if (array != null) {
            int length = getLength(array);
            Object newarray = Array.newInstance(contentType, length + expsize);
            System.arraycopy(array, 0, newarray, 0, length);
            return newarray;
        }
        return Array.newInstance(contentType, 1);
    }

    public static boolean isEmpty(Object array) {
        return array == null || Array.getLength(array) == 0;
    }
    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> void foreach(T[] array, Loop<T> loop) {
        if (isEmpty(array) || loop == null)
            return;
        int index = 0;
        for (T t : array) {
            loop.loop(t, index);
            index++;
        }
    }

    public static void foreach(int[] array, Loop<Integer> loop) {
        if (isEmpty(array) || loop == null)
            return;
        int index = 0;
        for (Integer t : array) {
            loop.loop(t, index);
            index++;
        }
    }

    public static void foreach(float[] array, Loop<Float> loop) {
        if (isEmpty(array) || loop == null)
            return;
        int index = 0;
        for (Float t : array) {
            loop.loop(t, index);
            index++;
        }
    }

    public static void foreach(double[] array, Loop<Double> loop) {
        if (isEmpty(array) || loop == null)
            return;
        int index = 0;
        for (Double t : array) {
            loop.loop(t, index);
            index++;
        }
    }

    public static void foreach(long[] array, Loop<Long> loop) {
        if (isEmpty(array) || loop == null)
            return;
        int index = 0;
        for (Long t : array) {
            loop.loop(t, index);
            index++;
        }
    }

    public static void foreach(byte[] array, Loop<Byte> loop) {
        if (isEmpty(array) || loop == null)
            return;
        int index = 0;
        for (Byte t : array) {
            loop.loop(t, index);
            index++;
        }
    }

    public static void foreach(short[] array, Loop<Short> loop) {
        if (isEmpty(array) || loop == null)
            return;
        int index = 0;
        for (Short t : array) {
            loop.loop(t, index);
            index++;
        }
    }

    public static <T> int index(T[] array, T elem) {
        int index = 0;
        for (T t : array) {
            if (t.equals(elem))
                return index;
            index++;
        }
        return -1;
    }
    public static int index(Object array, Object elem) {
        if (isEmpty(array))
            return -1;
        int length = getLength(array);
        for (int index = 0;index<length;index++) {
            if (Array.get(array,index).equals(elem))
                return index;
        }
        return -1;
    }
    public static int index(int[] array, int elem) {
        if (isEmpty(array))
            return -1;
        for (int index = 0;index<array.length;index++) {
            if (array[index] == elem)
                return index;
        }
        return -1;
    }
    public static int index(float[] array, float elem) {
        if (isEmpty(array))
            return -1;
        for (int index = 0;index<array.length;index++) {
            if (array[index] == elem)
                return index;
        }
        return -1;
    }
    public static int index(double[] array, double elem) {
        if (isEmpty(array))
            return -1;
        for (int index = 0;index<array.length;index++) {
            if (array[index] == elem)
                return index;
        }
        return -1;
    }
    public static int index(long[] array, long elem) {
        if (isEmpty(array))
            return -1;
        for (int index = 0;index<array.length;index++) {
            if (array[index] == elem)
                return index;
        }
        return -1;
    }
    public static int index(byte[] array, byte elem) {
        if (isEmpty(array))
            return -1;
        for (int index = 0;index<array.length;index++) {
            if (array[index] == elem)
                return index;
        }
        return -1;
    }
    public static int index(short[] array, short elem) {
        if (isEmpty(array))
            return -1;
        for (int index = 0;index<array.length;index++) {
            if (array[index] == elem)
                return index;
        }
        return -1;
    }

    private static Object add(Object array, Object elem, Class<?> type) {
        final Object newarray = expandSpace(array, 1, type);
        int length = getLength(array);
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
        int length = getLength(array);
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

    public static <T> Object addAll(Object array, T... elems) {
        int srcLength = getLength(array);
        int elemsLength = Array.getLength(elems);
        if (srcLength < elemsLength) {
            throw new ArrayIndexOutOfBoundsException("src length is " + srcLength +
                    ", but elemes length is " + elemsLength);
        }
        T[] expanedArray = (T[]) expandSpace(array, elemsLength, array.getClass().getComponentType());
        System.arraycopy(elems, 0, expanedArray, 0, elemsLength + srcLength);
        return expanedArray;
    }

    public static <T> T[] addAll(T[] array, T... elems) {
        return (T[]) addAll((Object) array, elems);
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
        int srcLength = getLength(array);
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
        int srcLength = getLength(array);
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
        int srcLength = getLength(array);
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
        int srcLength = getLength(array);
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
        int srcLength = getLength(array);
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
        int srcLength = getLength(array);
        int elemsLength = Array.getLength(elems);
        if (srcLength < elemsLength) {
            throw new ArrayIndexOutOfBoundsException("src length is " + srcLength +
                    ", but elemes length is " + elemsLength);
        }
        short[] expanedArray = (short[]) expandSpace(array, elemsLength, Integer.TYPE);
        System.arraycopy(elems, 0, expanedArray, srcLength, elemsLength);
        return expanedArray;
    }

    public static Object remove(Object array, int index, Class<?> type) {
        int srcLength = getLength(array);
        if (index < 0 || index >= srcLength) {
            throw new IndexOutOfBoundsException("src length is " + srcLength +
                    ", but index is " + index);
        }

        final Object result = Array.newInstance(type, srcLength - 1);
        System.arraycopy(array, 0, result, 0, index);
        if (index < srcLength - 1) {
            System.arraycopy(array, index + 1, result, index, srcLength - index - 1);
        }

        return result;
    }

    public static Object removeElem(Object array,Object elem){
        int srcLength = getLength(array);
        if (elem == null) {
            return null;
        }

        final Object result = Array.newInstance(array.getClass().getComponentType(), srcLength - 1);
        int point = index(array, elem);
        if (point == -1)
            return array;
        System.arraycopy(array, 0, result, 0, point);
        if (point < srcLength - 1) {
            System.arraycopy(array, point + 1, result, point, srcLength - point - 1);
        }
        return result;
    }

    public static<T> T[] removeElem(T[] array, T elem,Class<T> type) {
        int srcLength = getLength(array);
        if (elem == null) {
            return null;
        }

        final T[] result = (T[]) Array.newInstance(type, srcLength - 1);
        int point = index(array, elem);
        if (point == -1)
            return array;
        System.arraycopy(array, 0, result, 0, point);
        if (point < srcLength - 1) {
            System.arraycopy(array, point + 1, result, point, srcLength - point - 1);
        }
        return result;
    }

    public static <T> T[] remove(T[] array, int index) {
        return (T[]) remove((Object) array, index, array.getClass().getComponentType());
    }

    public static int[] remove(int[] array, int index) {
        return (int[]) remove(array, index, Integer.TYPE);
    }

    public static float[] remove(float[] array, int index) {
        return (float[]) remove(array, index, Float.TYPE);
    }

    public static double[] remove(double[] array, int index) {
        return (double[]) remove(array, index, Double.TYPE);
    }

    public static long[] remove(long[] array, int index) {
        return (long[]) remove(array, index, Long.TYPE);
    }

    public static byte[] remove(byte[] array, int index) {
        return (byte[]) remove(array, index, Byte.TYPE);
    }

    public static short[] remove(short[] array, int index) {
        return (short[]) remove(array, index, Short.TYPE);
    }

    public static int[] removeElem(int[] array,int elem){
        return (int[]) removeElem((Object)array,elem);
    }
    public static float[] removeElem(float[] array,float elem){
        return (float[]) removeElem((Object)array,elem);
    }
    public static double[] removeElem(double[] array,double elem){
        return (double[]) removeElem((Object)array,elem);
    }
    public static long[] removeElem(long[] array,long elem){
        return (long[]) removeElem((Object)array,elem);
    }
    public static byte[] removeElem(byte[] array,byte elem){
        return (byte[]) removeElem((Object)array,elem);
    }
    public static short[] removeElem(short[] array,short elem){
        return (short[]) removeElem((Object)array,elem);
    }

    public static void main(String[] args) {
        float[] nums = new float[]{0f, 1f, 2f, 3.8f, 4f, 5.9f, 6f, 7.7f, 8.3f, 9.4f};
        float[] newnums = removeElem(nums,6);
//        nums = (int[]) add(nums, 11, 10);
        for (float n : newnums) {
            System.out.println(n);
        }
    }

}
