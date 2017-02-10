package org.easyarch.myutils.test.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Description :
 * Created by xingtianyu on 17-2-10
 * 下午2:31
 * description:
 */

public class TestScript {

    public static void main(String[] args) {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("javascript") ;
    }
}
