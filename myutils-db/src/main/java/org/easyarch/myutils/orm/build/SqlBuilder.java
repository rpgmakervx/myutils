package org.easyarch.myutils.orm.build;

import org.easyarch.myutils.orm.parser.SQLParser;

import java.util.List;

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

    public SqlBuilder(){
        sqlParser = new SQLParser();
        paramBinder = new ParamBinder();
//        paramParser = new ParamParser();
    }

    public SqlBuilder buildSql(String sql){
        sqlParser.parse(sql);
        List<String> paramList = sqlParser.getSqlParams();
        return this;
    }

    public SqlBuilder buildParams(Object object,String name){
        return this;
    }

}
