package org.easyarch.myutils.array;/**
 * Description : 
 * Created by YangZH on 16-11-1
 *  上午12:30
 */

import java.lang.reflect.Array;

/**
 * Description :
 * Created by code4j on 16-11-1
 * 上午12:30
 */

public class ArrayUtils {

    private static Object expandSpace(final Object array,int expsize,final Class<?> contentType){
        if (array != null){
            int length = Array.getLength(array);
            Object newarray = Array.newInstance(contentType,length + expsize);
            System.arraycopy(array,0,newarray,0,length);
            return newarray;
        }
        return Array.newInstance(contentType,1);
    }

    public static Object add(Object array,int index,Object elem,final Class<?> type){
        if (array == null){
            if (index > 0){
                throw new ArrayIndexOutOfBoundsException("insert point is "+index+",but array size is "+0);
            }
            final Object newarray = Array.newInstance(type,1);
            Array.set(newarray,0,elem);
            return newarray;
        }
        int length = Array.getLength(array);
        if (index > length || index < 0){
            throw new ArrayIndexOutOfBoundsException("insert point is "+index+",but array size is "+0);
        }
        final Object newarray = Array.newInstance(array.getClass().getComponentType(),length + 1);
        System.arraycopy(array,0,newarray,0,index);
        Array.set(newarray,index,elem);
        if (index < length){
            System.arraycopy(array,index,newarray,index + 1,length - index);
        }
        return newarray;
    }

    public static Object add(Object array,Object elem,Class<?> type){
        final Object newarray = expandSpace(array,1,type);
        int length = Array.getLength(array);
        Array.set(newarray,length - 1,elem);
        return newarray;
    }

    public static Integer[] add(Integer[] array, Integer elem){
        return (Integer[]) add(array,elem,Integer.TYPE);
    }

    public static int[] add(int[] array,int elem){
        return (int[]) add(array,elem,Integer.TYPE);
    }

    public static Float[] add(Float[] array, Float elem){
        return (Float[]) add(array,elem,Float.TYPE);
    }
    public static float[] add(float[] array,float elem){
        return (float[]) add(array,elem,Float.TYPE);
    }

    public static Double[] add(Double[] array, Double elem){
        return (Double[]) add(array,elem,Double.TYPE);
    }
    public static double[] add(double[] array,double elem){
        return (double[]) add(array,elem,Double.TYPE);
    }

    public static Long[] add(Long[] array, Long elem){
        return (Long[]) add(array,elem,Long.TYPE);
    }
    public static long[] add(long[] array,long elem){
        return (long[]) add(array,elem,Long.TYPE);
    }

    public static Byte[] add(Byte[] array, Byte elem){
        return (Byte[]) add(array,elem,Byte.TYPE);
    }
    public static byte[] add(byte[] array,byte elem){
        return (byte[]) add(array,elem,Byte.TYPE);
    }

    public static Short[] add(Short[] array, Short elem){
        return (Short[]) add(array,elem,Short.TYPE);
    }
    public static short[] add(short[] array,short elem){
        return (short[]) add(array,elem,Short.TYPE);
    }

    public static int[] add(int[] array,int index,int elem){
        return (int[]) add(array,index,elem,Integer.TYPE);
    }
    public static Integer[] add(Integer[] array,int index,Integer elem){
        return (Integer[]) add(array,index,elem,Integer.TYPE);
    }

    public static float[] add(float[] array,int index,float elem){
        return (float[]) add(array,index,elem,Float.TYPE);
    }
    public static Float[] add(Float[] array,int index,Float elem){
        return (Float[]) add(array,index,elem,Float.TYPE);
    }

    public static double[] add(double[] array,int index,double elem){
        return (double[]) add(array,index,elem,Double.TYPE);
    }
    public static Double[] add(Double[] array,int index,Double elem){
        return (Double[]) add(array,index,elem,Double.TYPE);
    }

    public static long[] add(long[] array,int index,long elem){
        return (long[]) add(array,index,elem,Long.TYPE);
    }
    public static Long[] add(Long[] array,int index,Long elem){
        return (Long[]) add(array,index,elem,Long.TYPE);
    }

    public static byte[] add(byte[] array,int index,byte elem){
        return (byte[]) add(array,index,elem,Byte.TYPE);
    }
    public static Byte[] add(Byte[] array,int index,Byte elem){
        return (Byte[]) add(array,index,elem,Byte.TYPE);
    }

    public static short[] add(short[] array,int index,short elem){
        return (short[]) add(array,index,elem,Short.TYPE);
    }
    public static Short[] add(Short[] array,int index,Short elem){
        return (Short[]) add(array,index,elem,Short.TYPE);
    }


}
