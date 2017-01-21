package org.easyarch.myutils.orm.build;

import org.easyarch.myutils.orm.binder.ParamBinder;
import org.easyarch.myutils.orm.parser.SQLParser;

import java.util.List;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 17-1-19
 * 下午7:31
 * description:
 */

public class SqlBuilder {

    private SQLParser sqlParser;

//    private ParamParser paramParser;

    private ParamBinder paramBinder;

    private SqlEntity entity;

    public SqlBuilder(){
        sqlParser = new SQLParser();
        paramBinder = new ParamBinder();
        entity = new SqlEntity();
//        paramParser = new ParamParser();
    }

    public SqlBuilder buildSql(String sql){
        sqlParser.parse(sql);
        entity.setNames(sqlParser.getSqlParams());
        return this;
    }

    public SqlBuilder buildParams(Object object,String name){
        paramBinder.reflect(object,name);
        return this;
    }
    public SqlBuilder buildParams(List objects,List<String> names){
        paramBinder.reflect(objects,names);
        return this;
    }

    public SqlBuilder buildParams(Map<String,Object> params){
        paramBinder.reflect(params);
        return this;
    }
    public SqlBuilder buildParams(Object object){
        paramBinder.reflect(object,object.getClass());
        return this;
    }

    public SqlEntity buildEntity(){
        Map<String,Object> mapper = paramBinder.getMapper();
        for (String paramName:entity.getNames()){
            entity.addVal(mapper.get(paramName));
        }
        return entity;
    }

}
