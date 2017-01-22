package org.easyarch.myutils.orm.session;

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

    private volatile Map<String,String> mappedSqls = new HashMap<>();

    private Configuration(){}

    public static Configuration getInstance(){
        synchronized (Configuration.class){
            if (configuration == null){
                synchronized (Configuration.class){
                    configuration = new Configuration();
                }
            }
        }
        return configuration;
    }

    public String getMappedSql(String id) {
        return this.mappedSqls.get(id);
    }
    public void addMappedSql(String id,String sql) {
        mappedSqls.put(id, sql);
    }

}
