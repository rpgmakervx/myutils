package org.easyarch.myutils.orm.build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 17-1-21
 * 下午7:19
 * description:
 */

public class SqlEntity {


    private List<Map<String,Object>> params;

    public SqlEntity() {
        params = new ArrayList<>();
    }

    public List<Map<String,Object>> getParams(){
        return params;
    }

    public void addParam(String name,Object value){
        Map<String,Object> param = new HashMap<>();
        params.add(param);
    }
}
