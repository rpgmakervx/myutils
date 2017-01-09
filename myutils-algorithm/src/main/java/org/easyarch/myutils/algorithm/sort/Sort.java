package org.easyarch.myutils.algorithm.sort;

import java.util.List;

/**
 * Description :
 * Created by code4j on 17-1-5
 * 下午1:06
 * description:
 */

public interface Sort {

    public void sort(List<Integer> list,boolean desc);

    public void sort(Integer[] list,boolean desc);

    public void swap(Integer[] list,int left,int right);
    public void swap(List<Integer> list,int left,int right);

}
