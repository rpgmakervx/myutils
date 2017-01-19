package org.easyarch.myutils.orm.build;

import org.easyarch.myutils.orm.parser.ParamParser;
import org.easyarch.myutils.orm.parser.SQLParser;

/**
 * Description :
 * Created by xingtianyu on 17-1-19
 * 下午7:31
 * description:
 */

public class SqlBuilder {

    private SQLParser sqlParser;

    private ParamParser paramParser;

    private ParamBinder paramBinder;

    public SqlBuilder(String sql){
        sqlParser = new SQLParser(sql);
        paramParser = new ParamParser();
        paramBinder = new ParamBinder();
    }

}
