package org.easyarch.myutils.orm.parser;

import org.easyarch.myutils.orm.build.SqlBuilder;
import org.easyarch.myutils.orm.entity.SqlEntity;
import org.easyarch.myutils.orm.parser.script.JSContext;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.Reader;
import java.util.HashMap;
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

    private SqlBuilder sqlBuilder;

    private ScriptEngine engine;

    private Reader reader;

    private JSContext ctx;

    private static Map<String,SqlEntity> sqlValues = new HashMap<>();
    private static Map<String,Invocable> jsFunctions = new HashMap<>();

    public JSParser(Reader reader){
        engineManager = new ScriptEngineManager();
        engine = engineManager.getEngineByName(JAVASCRIPT);
        this.sqlBuilder = new SqlBuilder();
        this.reader = reader;
        ctx = new JSContext();
    }

    @Override
    public void parse(SqlEntity entity) {
        try {
            String namespace = String.valueOf(engine.get(NAMESPACE));
            Invocable func = jsFunctions.get(namespace);
            if (func == null){
                engine.eval(reader);
                func = (Invocable)engine;
            }
            String sql = (String) func.invokeFunction(entity.getSuffix(),entity.getParams());
            sqlBuilder.buildSql(sql);
            sqlBuilder.buildParams(entity.getParams());
            SqlEntity sqlEntity = sqlBuilder.buildEntity(entity.getBinder());
            sqlValues.put(namespace,sqlEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SqlEntity getContent(String namespace) {
        SqlEntity sql = sqlValues.get(namespace);
        return sql;
    }
}
