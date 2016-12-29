package org.easyarch.myutils.test.array;

import org.easyarch.myutils.array.ArrayUtils;
import org.easyarch.myutils.array.handler.LoopHandler;
import org.junit.Test;

/**
 * Description :
 * Created by xingtianyu on 16-12-29
 * 下午8:31
 * description:
 */

public class TestArray {

    @Test
    public void testNewArray(){
        Integer[] integers = ArrayUtils.newArray(Integer.class,10);
        System.out.println(integers.length);
        for (Integer i:integers){
            System.out.println(i);
        }
    }

    @Test
    public void testIsEmpty(){
        int[] ints = null;
        System.out.println(ArrayUtils.isEmpty(ints));
        ints = new int[10];
        System.out.println(ArrayUtils.isEmpty(ints));
        ints[0] = 0;
        System.out.println(ArrayUtils.isEmpty(ints));
        Integer[] integers = null;
        System.out.println(ArrayUtils.isEmpty(integers));
        integers = new Integer[10];
        System.out.println(ArrayUtils.isEmpty(integers));
        integers[0] = 0;
        System.out.println(ArrayUtils.isEmpty(integers));
    }

    @Test
    public void testAdd(){
        int[] ints = new int[10];
        double[] doubles = new double[10];
        float[] floats = new float[10];
        long[] longs = new long[10];
        short[] shorts = new short[10];
        byte[] bytes = new byte[10];
        String[] strings = new String[10];
        ints = ArrayUtils.add(ints,1,1);
        doubles = ArrayUtils.add(doubles,1,1.08);
        floats = ArrayUtils.add(floats,1,1.5f);
        longs = ArrayUtils.add(longs,1,100000l);
        shorts = ArrayUtils.add(shorts,1,(short) 1);
        bytes = ArrayUtils.add(bytes,1, (byte) 0xff);
        strings = ArrayUtils.add(strings, 1,"1");
        System.out.println(ints[1]);
        System.out.println(doubles[1]);
        System.out.println(floats[1]);
        System.out.println(longs[1]);
        System.out.println(shorts[1]);
        System.out.println(bytes[1]);
        System.out.println(strings[1]);
        System.out.println(strings.length);
    }
    @Test
    public void testAddAll(){
        int[] ints = new int[]{0,1,2,3,4,5,6,7,8,9};
        ints = ArrayUtils.addAll(ints,10,11,12,13,14,15);
        System.out.println(ints[12]);
    }

    @Test
    public void testIndexOf(){
        int[] ints = new int[]{0,1,2,3,2,5,6,7,8,2};
        System.out.println(ArrayUtils.indexOf(ints,2));
    }

    @Test
    public void testContains(){
        int[] ints = new int[]{0,1,2,3,2,5,6,7,8,2};
        System.out.println(ArrayUtils.contains(ints,8));
        System.out.println(ArrayUtils.contains(ints,10));
    }
    @Test
    public void testRemove(){
        int[] ints = new int[]{0,1,2,3,4,5,6,7,8,9};
        ints = ArrayUtils.remove(ints,9);
        System.out.println(ints.length);
        System.out.println(ints[ints.length-1]);
    }

    @Test
    public void testForeach(){
        int[] ints = new int[]{0,1,2,3,4,5,6,7,8,9};
        ArrayUtils.foreach(ints, new LoopHandler<Integer>() {
            @Override
            public void loop(Integer elem, int index) {
                System.out.println("element:"+elem+",index:"+index);
            }
        });
    }
}
