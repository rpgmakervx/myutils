package org.easyarch.myutils.orm.parser;

import static org.easyarch.myutils.orm.parser.Token.POINT;
import static org.easyarch.myutils.orm.parser.Token.SEPERTOR;

/**
 * Description :
 * Created by code4j on 17-1-19
 * 上午10:00
 * description:
 */

public class ParamParser implements Parser {

    private String paramToken;

    private String prefixToken;

    @Override
    public void parse(String src) {
        int beginIndex = SEPERTOR.length();
        String word = src.substring(beginIndex, src.length() - beginIndex);
        String[] words = word.split(POINT);
        if (words.length > 0){
            prefixToken = words[0];
            paramToken = words[1];
        }
    }

}
