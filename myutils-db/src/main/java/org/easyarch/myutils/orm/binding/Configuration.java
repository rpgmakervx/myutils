package org.easyarch.myutils.orm.binding;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 17-1-23
 * 上午12:41
 * description:
 */

public class Configuration {

    private static volatile Configuration configuration;

    private volatile Map<String, Map<String, String>> mappedSqls = new HashMap<>();

    private Configuration() {
    }

    public static Configuration getInstance() {
        synchronized (Configuration.class) {
            if (configuration == null) {
                synchronized (Configuration.class) {
                    configuration = new Configuration();
                }
            }
        }
        return configuration;
    }

    public String getMappedSql(String namespace, String id) {
        if (this.mappedSqls.containsKey(namespace)) {
            return mappedSqls.get(namespace).get(id);
        }
        return "";
    }

    public void addMappedSql(String namespace, String id, String sql) {
        Map<String, String> newMap = new HashMap<>();
        newMap.put(id, sql);
        if (mappedSqls.containsKey(namespace)) {
            Map<String, String> sqlMap = mappedSqls.get(namespace);
            sqlMap.putAll(newMap);
        } else {
            mappedSqls.put(namespace,newMap);
        }
    }

    public static void main(String[] args) {
        Map<String, Map<String, String>> map = new HashMap<>();
        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        map1.put("a","hello1");
        map1.put("b","hello2");
        map1.put("c","hello3");
        map2.put("d","hello5");
        map2.put("e","hello5");
        map.put("1", map1);
        Map<String,String> m = map.get("1");
        m.putAll(map2);
        System.out.println(map);
    }
}
