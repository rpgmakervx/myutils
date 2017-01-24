package org.easyarch.myutils.test.orm.reflect;

import org.easyarch.myutils.orm.reflect.MappedMethod;
import org.easyarch.myutils.test.User;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 17-1-24
 * 下午5:36
 * description:
 */

public class MappedMethodTest {

    @Test
    public void test() throws NoSuchMethodException {
        Method method = MappedMethod.class.getMethod("method", String.class, String.class, String.class);
        Method insert = MappedMethod.class.getMethod("insert", User.class);
        Method query = MappedMethod.class.getMethod("query", Map.class);
        MappedMethod mm = new MappedMethod(null, null);
        User user = new User();
        user.setId(110);
        user.setUsername("xingtianyu");
        user.setAge(23);
        Map<String,Object> map = new HashMap<>();
        map.put("id",1220);
        map.put("username","xiaoming");
        map.put("age",22);
        mm.delegateExecute("UserMapper",insert,new Object[]{map});
    }
}
