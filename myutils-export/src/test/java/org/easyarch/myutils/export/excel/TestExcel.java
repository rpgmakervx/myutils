package org.easyarch.myutils.export.excel;

import org.easyarch.myutils.export.excel.entity.User;
import org.easyarch.myutils.format.TimeUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easyarch.myutils.export.excel.ExcelUtils.SUFFIX;

/**
 * Description :
 * Created by xingtianyu on 16-12-30
 * 上午9:48
 * description:
 */

public class TestExcel {

    @Test
    public void testExcel(){
        List<User> users = new ArrayList<User>();
        long begin = System.currentTimeMillis();
        for (int index=0;index<50000;index++){
            users.add(new User("xxx", 22+index, "dfghjhdasfdsgdfbdv"+index,"1SFdsgfdhgfG1"+index,"1afsafsgds1"+index, TimeUtils.getDateOffsetByNow(-5000+index)));
        }
        ExcelUtils.build(users);
        ExcelUtils.disk("/home/code4j/58daojia/名单" + SUFFIX);
        System.out.println("time:"+ (System.currentTimeMillis() - begin));
    }
}
