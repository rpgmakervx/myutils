package org.easyarch.myutils.orm.parser;

import org.easyarch.myutils.orm.parser.script.JSContext;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Description :
 * Created by xingtianyu on 17-1-27
 * 下午6:38
 * description:
 */

public class JSParser extends ParserAdapter {

    public static final String JAVASCRIPT = "javascript";

    private ScriptEngineManager engineManager ;

    private ScriptEngine engine;

    private JSContext ctx;
    public JSParser(){
        engineManager = new ScriptEngineManager();
        engine = engineManager.getEngineByName(JAVASCRIPT);
        ctx = new JSContext();
    }
    @Override
    public Object parse() {
//        engine.eval()
        return null;
    }

}
