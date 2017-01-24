package org.easyarch.myutils.orm.parser;

import static org.easyarch.myutils.orm.parser.Token.POINT;
import static org.easyarch.myutils.orm.parser.Token.KEY;

/**
 * Description :
 * Created by code4j on 17-1-19
 * 上午10:00
 * description:
 */

public class ParamParser implements Parser {

    private String []paramTokens;

    @Override
    public void parse(String src) {
        int beginIndex = KEY.length();
        String word = src.substring(beginIndex, src.length() - beginIndex);
        paramTokens = word.split(POINT);
    }

    public int getLevel(){
        return paramTokens.length;
    }

}
