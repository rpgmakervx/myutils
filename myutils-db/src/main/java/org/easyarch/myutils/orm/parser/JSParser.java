package org.easyarch.myutils.orm.parser;

import org.easyarch.myutils.orm.parser.script.JSContext;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.Reader;

/**
 * Description :
 * Created by xingtianyu on 17-1-27
 * 下午6:38
 * description:
 */

public class JSParser<T> extends ParserAdapter<T> {

    public static final String JAVASCRIPT = "javascript";

    private ScriptEngineManager engineManager ;

    private ScriptEngine engine;

    private String funcName;

    private JSContext ctx;

    public JSParser(String funcName){
        engineManager = new ScriptEngineManager();
        engine = engineManager.getEngineByName(JAVASCRIPT);
        ctx = new JSContext();
        this.funcName = funcName;
    }
    @Override
    public void parse(Reader reader) {
        try {
            engine.eval(reader);
            Invocable func = (Invocable)engine;
            func.invokeFunction(funcName,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T parse() {
        return super.parse();
    }
}
