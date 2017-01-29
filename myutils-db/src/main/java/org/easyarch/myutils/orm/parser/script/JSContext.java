package org.easyarch.myutils.orm.parser.script;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 17-1-27
 * 下午11:19
 * description:
 */

public class JSContext {

    private static final String CONTEXT = "ctx";

    private static final String WHERE = "where";

    private static final String WHERE_CONTENT = " where 1 =1 ";

    public Map<String,Map<String ,Object>> defaultVars;

    public JSContext(){
        defaultVars = new HashMap<>();
    }

    private void init(){
        Map<String,Object> vars = new HashMap<>();
        vars.put(WHERE,WHERE_CONTENT);
        defaultVars.put(CONTEXT,vars);
    }
}
