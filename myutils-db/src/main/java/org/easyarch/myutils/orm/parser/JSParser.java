package org.easyarch.myutils.orm.parser;

import org.easyarch.myutils.orm.build.SqlEntity;
import org.easyarch.myutils.orm.parser.script.JSContext;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.Reader;
import java.util.Map;

import static org.easyarch.myutils.orm.parser.script.JSContext.NAMESPACE;

/**
 * Description :
 * Created by xingtianyu on 17-1-27
 * 下午6:38
 * description:
 */

public class JSParser extends ParserAdapter<SqlEntity> {

    public static final String JAVASCRIPT = "javascript";

    private ScriptEngineManager engineManager ;

    private ScriptEngine engine;

    private Reader reader;

    private JSContext ctx;

    private static Map<String,Invocable> jsFunctions;

    public JSParser(Reader reader){
        engineManager = new ScriptEngineManager();
        engine = engineManager.getEngineByName(JAVASCRIPT);
        this.reader = reader;
        ctx = new JSContext();
    }

    @Override
    public void parse(SqlEntity entity) {
        try {
            engine.eval(reader);
            Invocable func = (Invocable)engine;
            func.invokeFunction(entity.getSuffix(),entity.getParams());
            String namespace = String.valueOf(engine.get(NAMESPACE));
            jsFunctions.put(namespace,func);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
