package org.easyarch.myutils.orm.reflect;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-22
 * 下午7:58
 * description:
 */

public class ClassItemPool {

    private static List<ClassItem> interfaces = new ArrayList<>();

    public static void addInterface(ClassItem item){
        interfaces.add(item);
    }

    public static List<ClassItem> getInterfaces(){
        return interfaces;
    }

}
