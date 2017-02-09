package org.easyarch.myutils.orm.build;

import org.easyarch.myutils.orm.binding.ParamBinder;
import org.easyarch.myutils.orm.entity.SqlEntity;
import org.easyarch.myutils.orm.mapping.SqlType;
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
        entity.setSql(sql);
        entity.setPreparedSql(sqlParser.getPreparedSql());
        entity.setType(sqlParser.getType());
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

    public SqlEntity buildEntity(String bind){
        Map<String,Object> mapper = paramBinder.getMapper();
        List<String> paramNames = sqlParser.getSqlParamNames();
        entity.setBinder(bind);
        System.out.println("sql parser params : "+paramNames);
        for (String name:paramNames){
            for (Map.Entry<String,Object> entry:mapper.entrySet()){
                if (entry.getKey().equals(name)){
                    entity.addParam(entry.getKey(),entry.getValue());
                }
            }
        }

        return entity;
    }

    public SqlEntity buildEntity(SqlEntity entity){
        this.entity = entity;
        return this.entity;
    }

    public SqlType getType(){
        return entity.getType();
    }

    public String getPreparedSql(){
        return entity.getPreparedSql();
    }

    public List<Map<String,Object>> getParameters(){
        return entity.getParams();
    }

    public void setType(SqlType type) {
        sqlParser.setType(type);
    }

    public void setPreparedSql(String preparedSql) {
        sqlParser.setPreparedSql(preparedSql);
    }

    public void setParams(List<String> params) {
        sqlParser.setParamNames(params);
    }

    @Override
    public String toString() {
        return sqlParser.getOriginSql()+" \n "+sqlParser.getSqlParamNames()+" \n "+entity.getParams();
    }
}
